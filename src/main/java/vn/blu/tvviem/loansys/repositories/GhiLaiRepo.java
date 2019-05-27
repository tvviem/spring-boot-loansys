package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;

import java.util.Optional;

public interface GhiLaiRepo extends CrudRepository<GhiLai, Long> {
    // using JPQL --> coalesce(SUM(ghi_lai.soNgayNop),0)
    @Query(value = "SELECT SUM(ghi_lai.soNgayNop) FROM GhiLai ghi_lai " +
            "WHERE ghi_lai.hoSo.id = ?1 " +
            "GROUP BY ghi_lai.hoSo.id")
    Integer sumSoNgayNopByHoSoId(Long hoSoId);

    Iterable<GhiLai> findByHoSo_IdOrderByNgayNhacNopDesc(Long hoSoId);

    // Lấy thông tin nhắc nộp lãi gần nhất của một hồ sơ
    Optional<GhiLai> findFirstByHoSo_IdOrderByNgayNhacNopDesc(Long hoSoId);
}
