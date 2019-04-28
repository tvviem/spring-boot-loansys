package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.blu.tvviem.loansys.exceptions.EntityNotFoundException;
import vn.blu.tvviem.loansys.models.baomat.User;
import vn.blu.tvviem.loansys.models.hoso.HoSo;
import vn.blu.tvviem.loansys.models.hoso.LoaiHoSo;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;
import vn.blu.tvviem.loansys.repositories.HoSoRepo;
import vn.blu.tvviem.loansys.repositories.TaiSanHoSoRepo;
import vn.blu.tvviem.loansys.repositories.UserRepository;
import vn.blu.tvviem.loansys.services.protocol.HoSoService;
import vn.blu.tvviem.loansys.services.protocol.KhachHangService;
import vn.blu.tvviem.loansys.services.protocol.LoaiHoSoService;
import vn.blu.tvviem.loansys.services.protocol.TaiSanService;
import vn.blu.tvviem.loansys.web.dto.HoSoGiamDocRoleDto;
import vn.blu.tvviem.loansys.web.dto.HoSoNhanVienRoleDto;
import vn.blu.tvviem.loansys.web.dto.HoSoThuNganRoleDto;
import vn.blu.tvviem.loansys.web.dto.TaiSanTheChap;

import java.math.BigDecimal;
import java.util.Date;

@Service("hoSoService")
public class HoSoServiceImpl implements HoSoService {

    @Autowired
    private HoSoRepo hoSoRepo;
    @Autowired
    private LoaiHoSoService loaiHoSoService;
    @Autowired
    private TaiSanService taiSanService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private TaiSanHoSoRepo taiSanHoSoRepo;

    // TinDung role
    @Override
    @Transactional
    public HoSo taoHoSo(HoSoNhanVienRoleDto hoSoNhanVienRoleDto) {
        HoSo hoSoTemp = new HoSo();

        LoaiHoSo loaiHoSo = loaiHoSoService.getOneLoaiHS(hoSoNhanVienRoleDto.getLoaiHoSoId());
        if(loaiHoSo!=null) {
            hoSoTemp.setLoaiHoSo(loaiHoSo);
        } else {
            throw new EntityNotFoundException(LoaiHoSo.class, "loaiHoSoId", hoSoNhanVienRoleDto.getLoaiHoSoId().toString());
        }

        User nhanVien = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            nhanVien = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(
                    "Not found username[%s]", username)));
        } else {
            String username = principal.toString();
        }
        if(nhanVien != null) {
            hoSoTemp.setNhanVienTinDung(nhanVien);
        }

        // TINH TONG CAC GIA TRI LUU VAO BANG HO_SO
        BigDecimal tongGiaTriTaiSan = new BigDecimal(0);
        BigDecimal tongDuocPhepVay = new BigDecimal(0);
        BigDecimal tongVayDeXuat = new BigDecimal(0);
        // THEM THONG TIN TAI SAN THE CHAP
        for (TaiSanTheChap taiSanTheChap : hoSoNhanVienRoleDto.getCacTaiSan()) {
            Long taiSanId = taiSanTheChap.getTaiSanId();
            TaiSan taiSanSearch = taiSanService.findOneByTaiSanId(taiSanId); // default throw javax.persistence.EntityNotFoundException

            hoSoTemp.addTaiSanTheChap(taiSanSearch, taiSanTheChap.getGiaTri(), taiSanTheChap.getDuocPhepVay(),
                    taiSanTheChap.getDeXuat());
            tongGiaTriTaiSan = tongGiaTriTaiSan.add(taiSanTheChap.getGiaTri());
            tongDuocPhepVay = tongDuocPhepVay.add(taiSanTheChap.getDuocPhepVay());
            tongVayDeXuat = tongVayDeXuat.add(taiSanTheChap.getDeXuat());
        }
        // CAP NHAT KHACH HANG GIOI THIEU HO SO VAY
        for (String khachHangId : hoSoNhanVienRoleDto.getKhachHangsGioiThieu()) {
            KhachHang khachHang = khachHangService.getOneKhachHang(Long.parseLong(khachHangId));
            if(khachHang!=null) {
                hoSoTemp.getCacKhachHang().add(khachHang); // add khach hang gioi thieu ho so
            }
        }

        hoSoTemp.setKhachMuonVay(hoSoNhanVienRoleDto.getKhachMuonVay());
        hoSoTemp.setKyHan(hoSoNhanVienRoleDto.getKyHan());
        hoSoTemp.setMucLaiSuat(hoSoNhanVienRoleDto.getMucLaiSuat());

        hoSoTemp.setTongGiaTriTaiSan(tongGiaTriTaiSan);
        hoSoTemp.setTongVayQuyDinh(tongDuocPhepVay);
        hoSoTemp.setTongVayDeXuat(tongVayDeXuat);
        hoSoTemp.setGhiChu(hoSoNhanVienRoleDto.getGhiChu());

        return hoSoRepo.save(hoSoTemp);
    }

    // Giam doc role
    @Override
    @Transactional
    public HoSo pheDuyetHoSo(Long hoSoId, HoSoGiamDocRoleDto hoSoGiamDocRoleDto) {
        HoSo hoSoSearch = hoSoRepo.findById(hoSoId).orElse(null);
        if(hoSoSearch!=null) {
            hoSoSearch.setGiamDocDuyet(hoSoGiamDocRoleDto.getGiamDocDuyet());
            hoSoSearch.setDaDuyet(true);
            hoSoSearch.setGhiChu(hoSoSearch.getGhiChu()+hoSoGiamDocRoleDto.getGhiChuThem());
            return hoSoRepo.save(hoSoSearch);
        }
        return null; // khong tim thay ho so id
    }

    // Thu Ngan role
    @Override
    @Transactional
    public HoSo thuNganHoSo(Long hoSoId, HoSoThuNganRoleDto hoSoThuNganRoleDto) {
        HoSo hoSoSearchToUpdate = hoSoRepo.findById(hoSoId).orElse(null);
        if(hoSoSearchToUpdate!=null) {
            hoSoSearchToUpdate.setDaGiaiNgan(hoSoThuNganRoleDto.isDaGiaiNgan());
            hoSoSearchToUpdate.setDaKeDuNo(hoSoThuNganRoleDto.isDaKeDuNo());
            hoSoSearchToUpdate.setDaThuHoiNo(hoSoThuNganRoleDto.isDaThuHoiNo());
            return hoSoRepo.save(hoSoSearchToUpdate);
        }
        return null; // khong tim thay ho so id
    }

    @Override
    public Page<HoSo> getAllHoSo(Pageable pageable) {
        return hoSoRepo.findAll(pageable);
    }

    @Override
    public HoSo findOneHoSoById(Long hoSoId) {
        return hoSoRepo.findById(hoSoId).orElse(null);
    }

    @Override
    public boolean deleteHoSo(Long hoSoId) {
        if(hoSoRepo.existsById(hoSoId)) {
            hoSoRepo.deleteById(hoSoId); // it will set deleted=1
            return true;
        }
        return false;
    }

    @Override
    public Iterable<HoSo> getHoSosByNgayTao(Date ngayTao) {
        return hoSoRepo.findAllByNgayTaoOrderById(ngayTao);
    }

    // Lay tong vay cua 1 tai san Doi voi cac ho so chua thu no
    @Override
    public BigDecimal getTongDuocPhepVayOfTaiSanInHoSo(Long taiSanId) {
        /*List<TaiSanHoSo> taiSanHoSos = taiSanHoSoRepo.findByTaiSanHoSoId_TaiSan_Id(taiSanId);
        BigDecimal tongVay = new BigDecimal(0);
        for (TaiSanHoSo a : taiSanHoSos) {
            if(!a.getTaiSanHoSoId().getHoSo().isDaThuHoiNo()) {
                tongVay = tongVay.add(a.getDuocPhepVay());
            }
        }*/

        /*List<TaiSanHoSo> taiSanHoSos =
                taiSanHoSoRepo.findByTaiSanHoSoId_TaiSan_IdAndTaiSanHoSoId_HoSo_DaThuHoiNo(taiSanId, false);
        BigDecimal tongVay = new BigDecimal(0);
        for (TaiSanHoSo a : taiSanHoSos) {
            tongVay = tongVay.add(a.getDuocPhepVay());
        }*/
        return taiSanHoSoRepo.sumDuocPhepVayByTaiSanIdAndThuHoiNo(taiSanId, false);
    }
}
