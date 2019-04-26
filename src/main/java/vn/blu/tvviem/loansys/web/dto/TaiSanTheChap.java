package vn.blu.tvviem.loansys.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TaiSanTheChap {
    private Long taiSanId;
    // Nhan vien tham dinh gia tri hien trang cua tai san
    private BigDecimal giaTri;
    // Duoc phep vay theo quy dinh cong ty tren gia tri tai san
    private BigDecimal duocPhepVay;
    // Nhan vien tin dung de xuat vay
    private BigDecimal deXuat; // De xuat vay cua nhan vien
}
