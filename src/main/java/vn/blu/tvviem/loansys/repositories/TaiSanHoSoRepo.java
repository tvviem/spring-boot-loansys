package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.hoso.TaiSanHoSo;
import vn.blu.tvviem.loansys.models.hoso.TaiSanHoSoId;

import java.math.BigDecimal;
import java.util.List;

public interface TaiSanHoSoRepo extends CrudRepository<TaiSanHoSo, TaiSanHoSoId> {
    // Liet ke cac tai san co lam ho so vay
    List<TaiSanHoSo> findByTaiSanHoSoId_TaiSan_Id(Long taiSanId);
    // Liet ke cac TaiSanHoSo co lam ho so va kiem tra viec thu hoi no
    List<TaiSanHoSo> findByTaiSanHoSoId_TaiSan_IdAndTaiSanHoSoId_HoSo_DaThuHoiNo(Long taiSanId, boolean daThuHoiNo);

    // using JPQL
    @Query(value = "SELECT SUM(ts_hs.duocPhepVay) FROM TaiSanHoSo ts_hs, HoSo hs " +
            "WHERE hs.id = ts_hs.taiSanHoSoId.hoSo.id and ts_hs.taiSanHoSoId.taiSan.id = ?1 and hs.daThuHoiNo=?2 " +
            "GROUP BY ts_hs.taiSanHoSoId.taiSan.id")
    BigDecimal sumDuocPhepVayByTaiSanIdAndThuHoiNo(Long taiSanId, boolean daThuHoiNo);

    // List ke tai san co lam ho so vay theo thong tin ho so
    List<TaiSanHoSo> findByTaiSanHoSoId_HoSo_IdOrderByDeXuatDesc(Long hoSoId);
}
