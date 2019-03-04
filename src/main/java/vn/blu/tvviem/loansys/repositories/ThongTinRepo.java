package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;

import java.util.List;
import java.util.Optional;

public interface ThongTinRepo extends JpaRepository<ThongTin, Integer> {
    // List<ThongTin> findAllByLoaiTaiSan(LoaiTaiSan loaiTaiSan);
    List<ThongTin> findByLoaiTaiSanId(Integer loaiTaiSanId);
    Optional<ThongTin> findByIdAndLoaiTaiSanId(Integer id, Integer loaiTaiSanId);
}
