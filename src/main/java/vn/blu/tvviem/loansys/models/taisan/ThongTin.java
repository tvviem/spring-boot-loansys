package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "thong_tin")
public class ThongTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private @NonNull String tenThongTin;

    @ManyToOne
    @JoinColumn(name="id_loai_ts")
    private @NonNull LoaiTaiSan loaiTaiSan; // Thong tin cua mot loai tai san

    @ManyToMany(mappedBy = "cacThongTin")
    private Set<TaiSan> cacTaiSan;

    public ThongTin() {
    }

    public ThongTin(String tenThongTin, LoaiTaiSan loaiTaiSan, Set<TaiSan> cacTaiSan) {
        this.tenThongTin = tenThongTin;
        this.loaiTaiSan = loaiTaiSan;
        this.cacTaiSan = cacTaiSan;
    }
}
