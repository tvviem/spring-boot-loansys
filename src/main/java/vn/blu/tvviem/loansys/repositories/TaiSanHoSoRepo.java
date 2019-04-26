package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.hoso.TaiSanHoSo;
import vn.blu.tvviem.loansys.models.hoso.TaiSanHoSoId;

public interface TaiSanHoSoRepo extends CrudRepository<TaiSanHoSo, TaiSanHoSoId> {
}
