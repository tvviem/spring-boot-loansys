package vn.blu.tvviem.loansys.services.protocol;

import vn.blu.tvviem.loansys.models.hoso.GhiLai;
import vn.blu.tvviem.loansys.web.dto.GhiLaiDto;

import java.math.BigDecimal;

public interface GhiLaiService {
    // Ghi lai cho ho so no
    GhiLai nopLai(GhiLaiDto ghiLaiDto);
    // Xac dinh LAI tren don vi ngay
    BigDecimal xacDinhTienLaiGop(BigDecimal tongTienVay, int mucLaiSuat, int kyHan, int hinhThucLaiId);
    // Kiem tra tong so ngay nop cua mot ho so
    Integer tongNgayDaNop(Long hoSoId);
    // Tra ve chi tiet nop lai cua mot hoso
    Iterable<GhiLai> layChiTietGhiLai(Long hoSoId);
    // Xoa mot thong tin nop lai
    boolean xoaThongTinGhiLai(Long id);
}
