package vn.blu.tvviem.loansys.services.protocol;

import org.springframework.http.ResponseEntity;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;

import java.util.List;

public interface ThongTinService {
    // Tao thong tin cua mot loai tai san
    ThongTin saveThongTinCuaLoaiTs(ThongTin thongTin, Integer loaiTaiSanId);

    // Cap nhat thong tin cua mot loai tai san
    ThongTin updateThongTinCuaLoaiTs(Integer loaiTaiSanId, Integer thongTinId, ThongTin thongTin);

    // Xoa thong tin
    ResponseEntity deleteThongTin(Integer loaiTaiSanId, Integer thongTinId);

    // Lay cac dang thong tin cua loai tai san
    List<ThongTin> findByLoaiTaiSanId(Integer loaiTaiSanId);

    // Lay mot thong tin cua mot loai tai san
    ThongTin getThongTinFromLoaiTaiSan(Integer loaiTaiSanId, Integer thongTinId);
}
