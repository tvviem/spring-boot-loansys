package vn.blu.tvviem.loansys.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class HoSoGiamDocRoleDto {
    private BigDecimal giamDocDuyet; // So tien duyet
    private boolean daDuyet;
    private String ghiChuThem;
}
