package vn.blu.tvviem.loansys.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.blu.tvviem.loansys.models.taisan.TaiSanThongTin;
import vn.blu.tvviem.loansys.models.taisan.TaiSanThongTinId;

public interface ThongTinTaiSanRepo extends CrudRepository<TaiSanThongTin, TaiSanThongTinId> {
}
