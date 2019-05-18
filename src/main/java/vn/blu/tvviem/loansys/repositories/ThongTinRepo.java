package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;

import java.util.List;
import java.util.Optional;

public interface ThongTinRepo extends JpaRepository<ThongTin, Integer> {
    // List<ThongTin> findAllByLoaiTaiSan(LoaiTaiSan loaiTaiSan);
    List<ThongTin> findByLoaiTaiSanId(Integer loaiTaiSanId);
    // Su dung trong ThongTin
    Optional<ThongTin> findByIdAndLoaiTaiSanId(Integer id, Integer loaiTaiSanId);
    // Tim thong tin da co ten va loai tai san
    Optional<ThongTin> findByTenThongTinAndLoaiTaiSanId(String tenThongTin, Integer loaiTaiSanId);
}
