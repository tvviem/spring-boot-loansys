package vn.blu.tvviem.loansys.services.protocol;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

public interface KhachHangService {
    // Tao khach hang
    KhachHang saveKhachHang(KhachHang k);
    // Xoa khach hang
    void deleteKhachHang(KhachHang khachHang);

    /** Cac chuc nang truy van */
    // Tim kiem khach hang voi So CMND
    KhachHang getKhachHangBySoCmnd(String soCmnd);
    // Tra ve 1 khach hang voi tham so la id
    KhachHang getOneKhachHang(Long id);
    // Lay ve danh sach khach hang
    Page<KhachHang> getAllKhachHang(Pageable pageable);
    // Lay ve danh sach khach hang sap theo ho ten
    Page<KhachHang> getKhachHangHavePartHoTen(String partOfHoTen, Pageable pageable);
}
