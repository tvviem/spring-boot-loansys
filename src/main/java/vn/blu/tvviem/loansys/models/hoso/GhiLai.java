package vn.blu.tvviem.loansys.models.hoso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.blu.tvviem.loansys.models.baomat.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ghi_lai")
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class GhiLai implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_ho_so", nullable = false)
    @NotNull
    private HoSo hoSo;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "ngay_nop", nullable = false)
    private Date ngayNop;

    @Column(name = "so_ngay_nop", columnDefinition = "SMALLINT(3) UNSIGNED", nullable = false)
    private int soNgayNop;

    @Column(name = "so_tien_nop", precision = 8, nullable = false)
    @NotNull(message = "So tien nop phai duoc thiet lap")
    private BigDecimal soTienNop;

    @Column(name = "tien_hoa_hong", precision = 6, nullable = false)
    @NotNull(message = "Tien hoa hong phai duoc thiet lap")
    private BigDecimal soTienHoaHong;

    @Column(name = "ngay_nhac_nop", nullable = false)
    private Date ngayNhacNop;

    @ManyToOne
    @JoinColumn(name="id_cong_tac_vien", nullable = false)
    @JsonIgnore
    private User congTacVien;

    public GhiLai() {
    }

    public GhiLai(HoSo hoSo) {
        this.hoSo = hoSo;
    }
}
