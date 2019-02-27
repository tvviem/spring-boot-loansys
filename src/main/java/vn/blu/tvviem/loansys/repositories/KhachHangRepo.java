package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

public interface KhachHangRepo extends JpaRepository<KhachHang, Long> {
}
