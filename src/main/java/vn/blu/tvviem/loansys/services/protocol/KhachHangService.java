package vn.blu.tvviem.loansys.services.protocol;

import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import java.util.List;

public interface KhachHangService {
    // Tao khach hang
    KhachHang saveKhachHang(KhachHang k);
    // Lay ve danh sach khach hang
    List<KhachHang> getAllKhachHang();
    // Tra ve 1 khach hang voi tham so la id
    KhachHang getOneKhachHang(Long id);
    // Xoa khach hang
    void deleteKhachHang(KhachHang khachHang);
}
