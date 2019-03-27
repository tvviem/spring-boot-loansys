package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "muc_lai_suat")
public class LaiSuat {

    @EmbeddedId
    private MucLaiSuatId id;

    @NotBlank
    @Column(name = "ty_le", precision = 7, scale = 5, nullable = false)
    private BigDecimal tyLe;
}
