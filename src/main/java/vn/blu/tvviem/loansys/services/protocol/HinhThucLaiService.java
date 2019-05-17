package vn.blu.tvviem.loansys.services.protocol;

import vn.blu.tvviem.loansys.models.hoso.HinhThucLai;

public interface HinhThucLaiService {
    HinhThucLai luuHinhThuc(HinhThucLai hinhThucLai);

    HinhThucLai capNhatHinhThuc(HinhThucLai hinhThucLai);

    Iterable<HinhThucLai> getCacHinhThucLai();

    // Tim kiem mot loai tai san
    HinhThucLai getHinhThucLai(Integer id);

    // Xoa loai tai san
    void deleteHinhThuc(HinhThucLai hinhThucLai);

    HinhThucLai getByTenHinhThuc(String tenHinhThuc);
}
