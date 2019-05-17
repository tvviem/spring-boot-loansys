package vn.blu.tvviem.loansys.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class GhiLaiDto {
    private Long hoSoId;
    private int soNgayNop;
    private BigDecimal soTienNop;

}
