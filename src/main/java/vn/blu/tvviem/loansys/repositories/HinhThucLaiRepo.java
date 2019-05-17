package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.hoso.HinhThucLai;

import java.util.Optional;

public interface HinhThucLaiRepo extends CrudRepository<HinhThucLai, Integer> {
    Optional<HinhThucLai> findFirstByTenHinhThuc(String tenHinhThuc);
}