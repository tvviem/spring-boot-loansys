package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

public interface TaiSanRepo extends JpaRepository<TaiSan, Long> {
}
