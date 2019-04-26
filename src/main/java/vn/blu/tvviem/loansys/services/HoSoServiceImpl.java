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
import vn.blu.tvviem.loansys.repositories.UserRepository;
import vn.blu.tvviem.loansys.services.protocol.HoSoService;
import vn.blu.tvviem.loansys.services.protocol.KhachHangService;
import vn.blu.tvviem.loansys.services.protocol.LoaiHoSoService;
import vn.blu.tvviem.loansys.services.protocol.TaiSanService;
import vn.blu.tvviem.loansys.web.dto.HoSoNhanVienRoleDto;
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
            System.out.println("INSIDE UserDetails check: " + username);
            nhanVien = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format(
                    "Not found roles of username[%s]", username)));
        } else {
            System.out.println("OUTSIDE UserDetails check!");
            String username = principal.toString();
        }
        if(nhanVien != null) {
            System.out.println("INSIDE NHanvien!=null check!");
            hoSoTemp.setNhanVienTinDung(nhanVien);
        } else {
            throw new EntityNotFoundException(User.class, "nhanVienTaoId", hoSoNhanVienRoleDto.getNhanVienTaoId().toString());
        }

        // Tinh tong vay quy dinh
        BigDecimal tongGiaTriTaiSan = new BigDecimal(0);
        BigDecimal tongDuocPhepVay = new BigDecimal(0);
        BigDecimal tongVayDeXuat = new BigDecimal(0);

        for (TaiSanTheChap taiSanTheChap : hoSoNhanVienRoleDto.getCacTaiSan()) {
            Long taiSanId = taiSanTheChap.getTaiSanId();
            TaiSan taiSanSearch = taiSanService.findOneByTaiSanId(taiSanId); // default throw javax.persistence.EntityNotFoundException

            hoSoTemp.addTaiSanTheChap(taiSanSearch, taiSanTheChap.getGiaTri(), taiSanTheChap.getDuocPhepVay(),
                    taiSanTheChap.getDeXuat());

            tongGiaTriTaiSan = tongGiaTriTaiSan.add(taiSanTheChap.getGiaTri());
            tongDuocPhepVay = tongDuocPhepVay.add(taiSanTheChap.getDuocPhepVay());
            tongVayDeXuat = tongVayDeXuat.add(taiSanTheChap.getDeXuat());
        }

        //if(hoSoNhanVienRoleDto.getKhachHangsGioiThieu() != null) { // Co nhung ho so khong ai gioi thieu
            for (String khachHangId : hoSoNhanVienRoleDto.getKhachHangsGioiThieu()) {
                System.out.println("DEBUG: idKhachHang="+khachHangId);
                KhachHang khachHang = khachHangService.getOneKhachHang(Long.parseLong(khachHangId));
                if(khachHang!=null) {
                    hoSoTemp.getCacKhachHang().add(khachHang); // add khach hang gioi thieu ho so
                }
            }
        //}
        System.out.println("Over khachHangGioiThieu!!!");
        hoSoTemp.setKhachMuonVay(hoSoNhanVienRoleDto.getKhachMuonVay());
        hoSoTemp.setKyHan(hoSoNhanVienRoleDto.getKyHan());
        hoSoTemp.setMucLaiSuat(hoSoNhanVienRoleDto.getMucLaiSuat());

        hoSoTemp.setTongGiaTriTaiSan(tongGiaTriTaiSan);
        hoSoTemp.setTongVayQuyDinh(tongDuocPhepVay);
        hoSoTemp.setTongVayDeXuat(tongVayDeXuat);
        hoSoTemp.setGhiChu(hoSoNhanVienRoleDto.getGhiChu());

        return hoSoRepo.save(hoSoTemp);
    }

    @Override
    public Page<HoSo> getAllHoSo(Pageable pageable) {
        return null;
    }

    @Override
    public HoSo findOneHoSoById(Long hoSoId) {
        return null;
    }

    @Override
    public boolean deleteHoSo(Long hoSoId) {
        return false;
    }

    @Override
    public HoSo updateHoSo(Long hoSoId, HoSoNhanVienRoleDto hoSoNhanVienRoleDto) {
        return null;
    }

    @Override
    public Iterable<HoSo> getHoSosByNgayTao(Date ngayTao) {
        return null;
    }
}
