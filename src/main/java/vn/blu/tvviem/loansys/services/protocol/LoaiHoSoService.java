package vn.blu.tvviem.loansys.services.protocol;

import vn.blu.tvviem.loansys.models.hoso.LoaiHoSo;

public interface LoaiHoSoService {
    LoaiHoSo luuLoaiHs(LoaiHoSo loaiHoSo);

    LoaiHoSo capNhatLoaiHs(LoaiHoSo loaiHoSoUpdated);

    Iterable<LoaiHoSo> getAllLoaiHs();

    // Tim kiem mot loai tai san
    LoaiHoSo getOneLoaiHS(Integer id);

    // Xoa loai tai san
    void deleteLoaiHs(LoaiHoSo loaiHoSo);

    LoaiHoSo getHoSoByTenLoai(String tenLoai);
}
