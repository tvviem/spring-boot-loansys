package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.models.hoso.GhiLaiId;

public interface GhiLaiRepo extends CrudRepository<GhiLai, GhiLaiId> {
    // using JPQL --> coalesce(SUM(ghi_lai.soNgayNop),0)
    @Query(value = "SELECT SUM(ghi_lai.soNgayNop) FROM GhiLai ghi_lai " +
            "WHERE ghi_lai.id.hoSo.id = ?1 GROUP BY ghi_lai.id.hoSo.id")
    Integer sumSoNgayNopByHoSoId(Long hoSoId);
}
