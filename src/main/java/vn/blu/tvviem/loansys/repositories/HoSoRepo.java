package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.blu.tvviem.loansys.models.hoso.HoSo;

import java.util.Date;

public interface HoSoRepo extends JpaRepository<HoSo, Long> {
    Iterable<HoSo> findAllByNgayTaoOrderById(Date ngayTao);
}
