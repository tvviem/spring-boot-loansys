package vn.blu.tvviem.loansys.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class TaiSanDto {
    private Long khachHangId;
    private Integer loaiTaiSanId;
    private List<ChiTietThongTin> cacThongTin;
}
