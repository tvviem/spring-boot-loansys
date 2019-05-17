package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.models.hoso.GhiLaiId;

public interface GhiLaiRepo extends CrudRepository<GhiLai, GhiLaiId> {
    // using JPQL
    @Query(value = "SELECT SUM(ghiLai.soNgayNop) FROM GhiLai ghiLai " +
            "WHERE ghiLai.id.hoSo.id = ?1 GROUP BY ghiLai.id.hoSo.id")
    Integer sumSoNgayNopByHoSoId(Long hoSoId);
}
