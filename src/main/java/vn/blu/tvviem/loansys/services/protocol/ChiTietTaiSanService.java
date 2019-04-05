package vn.blu.tvviem.loansys.services.protocol;

/**
 * Cac chuc nang tuong tac tren mot thong tin cua tai san
 * */
public interface ChiTietTaiSanService {
    // Them 1 thong tin ma tai san chua tung luu hoac neu co thi updated
    boolean saveThongTinTaiSan(Long taiSanId, Integer thongTinId, String noiDung);
    // Xoa 1 thong tin cua tai san da luu
    boolean deleteThongTinTaiSan(Long taiSanId, Integer thongTinId);

}
