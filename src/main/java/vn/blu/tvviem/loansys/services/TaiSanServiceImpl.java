package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.blu.tvviem.loansys.exceptions.ResourceNotFoundException;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.models.taisan.*;
import vn.blu.tvviem.loansys.repositories.*;
import vn.blu.tvviem.loansys.services.protocol.TaiSanService;
import vn.blu.tvviem.loansys.web.dto.ChiTietThongTin;
import vn.blu.tvviem.loansys.web.dto.TaiSanDto;

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
    @Autowired
    private HinhTaiSanService hinhTaiSanService;

    // Tao tai san
    @Override
    @Transactional
    public TaiSan saveTaiSan(TaiSanDto taiSanDto) {
        TaiSan taiSanTemp = new TaiSan();
        KhachHang khachHang = khachHangRepo.findById(taiSanDto.getKhachHangId()).orElseThrow(() -> new ResourceNotFoundException(
                "khachHangId: " + taiSanDto.getKhachHangId() + " not found"));
        LoaiTaiSan loaiTaiSan = loaiTaiSanRepo.findById(taiSanDto.getLoaiTaiSanId()).orElseThrow(() -> new ResourceNotFoundException(
                "loaiTaiSanId: " + taiSanDto.getLoaiTaiSanId() + " not found"));
        taiSanTemp.setKhachHang(khachHang);
        taiSanTemp.setLoaiTaiSan(loaiTaiSan);

        /*TaiSan taiSanCreated = taiSanRepo.save(taiSanTemp);
        taiSanRepo.flush();// De lay id_tai_san truoc khi save chi tiet thong tin*/
        // List<TaiSanThongTin> taiSanThongTins = new ArrayList<>();

        for (ChiTietThongTin chiTietThongTin : taiSanDto.getCacThongTin()) {
            Integer idThongTin = chiTietThongTin.getIdThongTin();
            ThongTin thongTin = thongTinRepo.findById(chiTietThongTin.getIdThongTin()).orElseThrow(() -> new ResourceNotFoundException("thongTinId: " + idThongTin + " not found"));

            taiSanTemp.addThongTin(thongTin, chiTietThongTin.getNoiDung()); // need check exist thongTin client-side
        }

        /*ThongTin thongTin = thongTinRepo.findById(10).get();
        taiSanTemp.removeThongTin(thongTin);*/

        // Save hinh anh tai san vao table hinh_tai_san


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
        boolean isExistTaiSan = taiSanRepo.findById(taiSanId).orElse(null) != null;
        if(isExistTaiSan) {
            taiSanRepo.deleteById(taiSanId);
        }
        return isExistTaiSan;
    }
}
