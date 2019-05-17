package vn.blu.tvviem.loansys.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class HoSoNhanVienRoleDto {
    private Integer loaiHoSoId; // ky gui dau tu hoac ho so vay tin chap/the chap
    private Integer hinhThucLaiId; // Hinh thuc lai id

    private List<String> khachHangsGioiThieu;
    private List<TaiSanTheChap> cacTaiSan;

    private BigDecimal khachMuonVay; // nhu cau khach hang
    private int kyHan;
    private int mucLaiSuat;
    private int mucHoaHong;
    private String ghiChu;
}
