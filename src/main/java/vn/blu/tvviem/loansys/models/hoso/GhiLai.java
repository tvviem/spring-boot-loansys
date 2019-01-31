package vn.blu.tvviem.loansys.models.hoso;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ghi_lai")
public class GhiLai {
    @EmbeddedId
    private GhiLaiId id;

    @Column(name = "so_ngay_nop", columnDefinition = "TINYINT(3) UNSIGNED", nullable = false)
    private int soNgayNop;

    @Column(name = "so_tien_nop", precision = 8, nullable = false)
    private BigDecimal soTienNop;

    @Column(name = "ngay_nhac_nop", nullable = false)
    private Date ngayNhacNop;
}
