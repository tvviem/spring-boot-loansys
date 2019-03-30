package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.blu.tvviem.loansys.models.taisan.HinhTaiSan;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

import java.util.List;

@Repository
public interface HinhTaiSanRepo extends JpaRepository<HinhTaiSan, Long> {
    List<HinhTaiSan> findHinhTaiSansByTaiSan(TaiSan taiSan);
}
