package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.taisan.LoaiTaiSan;

public interface LoaiTaiSanRepo extends JpaRepository<LoaiTaiSan, Integer> {
}