package vn.blu.tvviem.loansys.models.hoso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.blu.tvviem.loansys.models.baomat.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ghi_lai")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor
public class GhiLai implements Serializable {
    @EmbeddedId
    private GhiLaiId id;

    @Column(name = "so_ngay_nop", columnDefinition = "SMALLINT(3) UNSIGNED", nullable = false)
    @Length(max = 3, message = "so ngay nop tu 1 den 999 ngay")
    private int soNgayNop;

    @Column(name = "so_tien_nop", precision = 8, nullable = false)
    @NotNull(message = "So tien nop phai duoc thiet lap")
    private BigDecimal soTienNop;

    @Column(name = "ngay_nhac_nop", nullable = false)
    private Date ngayNhacNop;

    @ManyToOne
    @JoinColumn(name="id_cong_tac_vien", nullable = false)
    @JsonIgnore
    private User congTacVien;
}
