package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.blu.tvviem.loansys.models.taisan.HinhTaiSan;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

import java.util.List;
import java.util.Optional;

@Repository
public interface HinhTaiSanRepo extends JpaRepository<HinhTaiSan, Long> {
    List<HinhTaiSan> findHinhTaiSansByTaiSan(TaiSan taiSan);
    Optional<HinhTaiSan> findByIdAndTaiSan(Long hindTaiSanId, TaiSan taiSan);
    Optional<HinhTaiSan> findByIdAndTaiSan_Id(Long hindTaiSanId, Long taiSanId);


}
