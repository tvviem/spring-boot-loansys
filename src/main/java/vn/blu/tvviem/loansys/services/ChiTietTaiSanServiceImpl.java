package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;
import vn.blu.tvviem.loansys.models.taisan.TaiSanThongTin;
import vn.blu.tvviem.loansys.models.taisan.TaiSanThongTinId;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;
import vn.blu.tvviem.loansys.repositories.TaiSanRepo;
import vn.blu.tvviem.loansys.repositories.ThongTinRepo;
import vn.blu.tvviem.loansys.repositories.ThongTinTaiSanRepo;
import vn.blu.tvviem.loansys.services.protocol.ChiTietTaiSanService;

/**
 * Cai dat xu ly thao tac tren tung chi tiet tai san
 * */
@Service
public class ChiTietTaiSanServiceImpl implements ChiTietTaiSanService {

    @Autowired
    private ThongTinTaiSanRepo thongTinTaiSanRepo;
    @Autowired
    private TaiSanRepo taiSanRepo;
    @Autowired
    private ThongTinRepo thongTinRepo;

    @Override
    public boolean saveThongTinTaiSan(Long taiSanId, Integer thongTinId, String noiDung) {
        TaiSan taiSan = taiSanRepo.findById(taiSanId).orElse(null);
        ThongTin thongTin = thongTinRepo.findById(thongTinId).orElse(null);
        // Neu taiSanId va thongTinId hop le va loai tai san co chua thong tin
        if (taiSan!=null && thongTin!=null && taiSan.getLoaiTaiSan().getThongTins().contains(thongTin)) {
            TaiSanThongTin taiSanThongTin = new TaiSanThongTin(taiSan, thongTin, noiDung);
            thongTinTaiSanRepo.save(taiSanThongTin); // insert
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteThongTinTaiSan(Long taiSanId, Integer thongTinId) {
        TaiSan taiSan = taiSanRepo.findById(taiSanId).orElse(null);
        ThongTin thongTin = thongTinRepo.findById(thongTinId).orElse(null);

        if (taiSan!=null && thongTin!=null) {
            TaiSanThongTinId taiSanThongTinId = new TaiSanThongTinId(taiSan, thongTin);
            if(thongTinTaiSanRepo.existsById(taiSanThongTinId)) { // neu co chi tiet nay
                thongTinTaiSanRepo.deleteById(taiSanThongTinId);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateThongTinTaiSan(Long taiSanId, Integer thongTinId, String noiDung) {
        TaiSan taiSan = taiSanRepo.findById(taiSanId).orElse(null);
        ThongTin thongTin = thongTinRepo.findById(thongTinId).orElse(null);
        if (taiSan!=null && thongTin!=null && taiSan.getLoaiTaiSan().getThongTins().contains(thongTin)) {
            TaiSanThongTinId taiSanThongTinId = new TaiSanThongTinId(taiSan, thongTin);
            // TaiSanThongTin taiSanThongTin = thongTinTaiSanRepo.findById(taiSanThongTinId).orElse(null);
            if (thongTinTaiSanRepo.existsById(taiSanThongTinId)) { // neu co id trong chi_tiet_ts
                TaiSanThongTin taiSanThongTin = new TaiSanThongTin(taiSan, thongTin, noiDung);
                thongTinTaiSanRepo.save(taiSanThongTin); // updated
                return true;
            }
        }
        return false;
    }
}
