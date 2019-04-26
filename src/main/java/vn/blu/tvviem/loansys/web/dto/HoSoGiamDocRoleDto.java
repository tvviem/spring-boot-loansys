package vn.blu.tvviem.loansys.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class HoSoGiamDocRoleDto extends HoSoNhanVienRoleDto {
    private BigDecimal giamDocDuyet; // So tien duyet
    private boolean daDuyet;
    private boolean daGiaiNgan;
    private boolean daKeDuNo;
    private boolean daThuHoiNo;
}
