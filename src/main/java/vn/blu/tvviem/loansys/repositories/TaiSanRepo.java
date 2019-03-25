package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

import java.util.List;

public interface TaiSanRepo extends JpaRepository<TaiSan, Long> {
    // using @Query gto custom data fetch
    /*@Query("select ts from taiSan ts join chi_tiet_ts chitiet on ts.id = chitiet.id_tai_san ")
    Page<TaiSan> findAllTaiSan(Pageable pageable);*/
}
