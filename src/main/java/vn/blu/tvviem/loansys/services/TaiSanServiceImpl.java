package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.blu.tvviem.loansys.exceptions.ResourceNotFoundException;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.models.taisan.*;
import vn.blu.tvviem.loansys.repositories.KhachHangRepo;
import vn.blu.tvviem.loansys.repositories.LoaiTaiSanRepo;
import vn.blu.tvviem.loansys.repositories.TaiSanRepo;
import vn.blu.tvviem.loansys.repositories.ThongTinRepo;
import vn.blu.tvviem.loansys.services.protocol.TaiSanService;
import vn.blu.tvviem.loansys.web.dto.ChiTietThongTin;
import vn.blu.tvviem.loansys.web.dto.ChiTietThongTinDto;

@Service("taiSanService")
public class TaiSanServiceImpl implements TaiSanService {

    @Autowired
    private TaiSanRepo taiSanRepo;
    @Autowired
    private KhachHangRepo khachHangRepo;
    @Autowired
    private LoaiTaiSanRepo loaiTaiSanRepo;
    @Autowired
    private ThongTinRepo thongTinRepo;

    // Tao tai san
    @Override
    @Transactional
    public TaiSan saveTaiSan(Long khachHangId, Integer loaiTaiSanId, ChiTietThongTinDto chiTietThongTinDto) {
        TaiSan taiSanTemp = new TaiSan();
        KhachHang khachHang = khachHangRepo.findById(khachHangId).orElseThrow(() -> new ResourceNotFoundException("khachHangId: " + khachHangId + " not found"));
        LoaiTaiSan loaiTaiSan = loaiTaiSanRepo.findById(loaiTaiSanId).orElseThrow(() -> new ResourceNotFoundException("loaiTaiSanId: " + loaiTaiSanId + " not found"));
        taiSanTemp.setKhachHang(khachHang);
        taiSanTemp.setLoaiTaiSan(loaiTaiSan);

        /*TaiSan taiSanCreated = taiSanRepo.save(taiSanTemp);
        taiSanRepo.flush();// De lay id_tai_san truoc khi save chi tiet thong tin*/
        // List<TaiSanThongTin> taiSanThongTins = new ArrayList<>();

        for (ChiTietThongTin chiTietThongTin : chiTietThongTinDto.getCacThongTin()) {
            Integer idThongTin = chiTietThongTin.getIdThongTin();
            ThongTin thongTin = thongTinRepo.findById(chiTietThongTin.getIdThongTin()).orElseThrow(() -> new ResourceNotFoundException("thongTinId: " + idThongTin + " not found"));

            taiSanTemp.addThongTin(thongTin, chiTietThongTin.getNoiDung());
        }

        /*System.out.println("TESTING --- CAC THONG TIN ---------");
        System.out.println(taiSanTemp.getTaiSanThongTins());*/

        return taiSanRepo.save(taiSanTemp);
    }

    // Liet ke danh sach cac tai san
    @Override
    public Page<TaiSan> getAllTaiSanPageable(Pageable pageable) {
        return taiSanRepo.findAll(pageable);
    }

    // Tim mot tai san voi id
    @Override
    public TaiSan findOneByTaiSanId(Long taiSanId) {
        return taiSanRepo.getOne(taiSanId);
    }

    @Override
    public boolean deleteTaiSan(Long taiSanId) {
        return false;
    }
}
