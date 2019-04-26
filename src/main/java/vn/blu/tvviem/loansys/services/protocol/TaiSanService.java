package vn.blu.tvviem.loansys.services.protocol;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;
import vn.blu.tvviem.loansys.web.dto.TaiSanDto;

public interface TaiSanService {
    // Tao tai san
    TaiSan saveTaiSan(TaiSanDto taiSanDto);

    // Liet ke danh sach cac tai san
    Page<TaiSan> getAllTaiSanPageable(Pageable pageable);

    // Tim mot tai san voi id
    TaiSan findOneByTaiSanId(Long taiSanId);

    // Xoa mot tai san
    boolean deleteTaiSan(Long taiSanId);

    // Cap nhat thong tin tai san
    TaiSan updateTaiSanById(Long taiSanId, TaiSanDto newTaiSanDto);
    // Tim tai san theo thong tin khach hang (cmnd)
    Iterable<TaiSan> getTaiSansBySoCmndKhachHang(String soCmnd);
    Iterable<TaiSan> getTaiSansByHoTenKhachHang(String partOfHoTen);
}
