package vn.blu.tvviem.loansys.services.protocol;

import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;

import java.util.List;

public interface LoaiTaiSanService {
    // Tao loai tai san
    LoaiTaiSan saveLoaiTs(LoaiTaiSan loaiTaiSan);

    // Tra ve danh sach cac loai tai san
    List<LoaiTaiSan> getAllLoaiTaiSan();

    // Tim kiem mot loai tai san
    LoaiTaiSan getOneLoaiTs(Integer id);

    // Xoa loai tai san
    void deleteLoaiTaiSan(LoaiTaiSan loaiTaiSan);
}
