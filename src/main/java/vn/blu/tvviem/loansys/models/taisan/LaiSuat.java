package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "muc_lai_suat")
public class LaiSuat {

    @EmbeddedId
    private MucLaiSuatId id;

    @Column(name = "ty_le", precision = 7, scale = 5, nullable = false)
    // @Digits(integer = 1, fraction = 2)
    private BigDecimal tyLe;
}
