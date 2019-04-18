package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

//, JpaSpecificationExecutor<KhachHang>
public interface KhachHangRepo extends JpaRepository<KhachHang, Long>{
    // Tim khach hang theo soCmnd
    // Optional<KhachHang> findBySoCmnd(String soCmnd);
    Optional<KhachHang> findFirstBySoCmnd(String soCmnd);
    Page<KhachHang> findAllByOrderByHoTenAsc(Pageable pageable);
    Page<KhachHang> findTop5ByOrderByHoTenAsc(Pageable pageable); // lay 5

    Iterable<KhachHang> findBySoCmndStartingWith(String soCmnd); // KhachHang co soCmnd bat dau
    Iterable<KhachHang> findByHoTenStartingWith(@NotBlank(message = "hoTen is required") String hoTen);

    Page<KhachHang> findByHoTenContaining(String partOfHoTen, Pageable pageable); // already
}
