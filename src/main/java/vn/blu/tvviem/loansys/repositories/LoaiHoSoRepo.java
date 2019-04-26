package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.hoso.LoaiHoSo;

import java.util.Optional;

public interface LoaiHoSoRepo extends CrudRepository<LoaiHoSo, Integer> {
    Optional<LoaiHoSo> findFirstByTenLoaiHoSo(String tenLoaiHs);
}
