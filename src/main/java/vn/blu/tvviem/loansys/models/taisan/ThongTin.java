package vn.blu.tvviem.loansys.models.taisan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

//@Data
@Entity @NoArgsConstructor @AllArgsConstructor
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

}
