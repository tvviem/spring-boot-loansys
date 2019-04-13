package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import java.util.Optional;

public interface KhachHangRepo extends JpaRepository<KhachHang, Long> {
    Optional<KhachHang> findBySoCmnd(String soCmnd);
}
