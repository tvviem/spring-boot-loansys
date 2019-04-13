package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;

import java.util.Optional;

public interface LoaiTaiSanRepo extends JpaRepository<LoaiTaiSan, Integer> {
    Optional<LoaiTaiSan> findByTenLoai(String tenLoai);
}
