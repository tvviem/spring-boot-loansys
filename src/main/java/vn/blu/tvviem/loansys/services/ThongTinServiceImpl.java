package vn.blu.tvviem.loansys.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.blu.tvviem.loansys.exceptions.ResourceNotFoundException;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;
import vn.blu.tvviem.loansys.repositories.LoaiTaiSanRepo;
import vn.blu.tvviem.loansys.repositories.ThongTinRepo;
import vn.blu.tvviem.loansys.services.protocol.ThongTinService;

import java.util.List;

@Service
public class ThongTinServiceImpl implements ThongTinService {

    @Autowired
    private ThongTinRepo thongTinRepo;

    @Autowired
    private LoaiTaiSanRepo loaiTaiSanRepo;

    // Tao thong tin cua mot loai tai san
    public ThongTin saveThongTinCuaLoaiTs(ThongTin thongTin, Integer loaiTaiSanId) {
        return loaiTaiSanRepo.findById(loaiTaiSanId).map((LoaiTaiSan loaiTaiSan) -> {
            thongTin.setLoaiTaiSan(loaiTaiSan);
            return thongTinRepo.save(thongTin);
        }).orElse(null);
    }

    // Cap nhat thong tin cua mot loai tai san
    public ThongTin updateThongTinCuaLoaiTs(Integer loaiTaiSanId, Integer thongTinId, ThongTin thongTin) {
        if(!loaiTaiSanRepo.existsById(loaiTaiSanId)) {
            throw new ResourceNotFoundException("LoaiTaiSanId " + loaiTaiSanId + " not found");
        }
        return thongTinRepo.findById(thongTinId).map(thongTin1 -> {
            thongTin1.setTenThongTin(thongTin.getTenThongTin());
            return thongTinRepo.save(thongTin1);
        }).orElseThrow(() -> new ResourceNotFoundException("thongTinId " + thongTinId + " not found"));
    }

    // Xoa thong tin
    public ResponseEntity deleteThongTin(Integer loaiTaiSanId, Integer thongTinId) {
        return thongTinRepo.findByIdAndLoaiTaiSanId(thongTinId, loaiTaiSanId).map(thongTin -> {
            thongTinRepo.delete(thongTin);
            return ResponseEntity.noContent().build(); // 204
        }).orElseThrow(() -> new ResourceNotFoundException("thongTinId " + thongTinId + " not found of loaiTaiSanId" +
                loaiTaiSanId));
    }

    // Lay cac dang thong tin cua loai tai san
    public List<ThongTin> findByLoaiTaiSanId(Integer loaiTaiSanId) {
        return thongTinRepo.findByLoaiTaiSanId(loaiTaiSanId);
    }

    public ThongTin getThongTinFromLoaiTaiSan(Integer loaiTaiSanId, Integer thongTinId) {
        return thongTinRepo.findByIdAndLoaiTaiSanId(thongTinId, loaiTaiSanId).orElseThrow(() -> new ResourceNotFoundException("thongTinId " + thongTinId + " not found of loaiTaiSanId" + loaiTaiSanId));
    }
}
