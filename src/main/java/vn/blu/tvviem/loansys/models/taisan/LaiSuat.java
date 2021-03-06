package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "muc_lai_suat")
public class LaiSuat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_loai_ts")
    private @NonNull
    LoaiTaiSan loaiTaiSan;

    @CreatedDate
    @NonNull
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @NonNull
    @Column(name = "ty_le", precision = 7, scale = 5)
    private BigDecimal tyLe;
}
