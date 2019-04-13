package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "thong_tin")
public class ThongTin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    @NotBlank
    @Column(name = "ten_thong_tin", length = 80, nullable = false)
    private String tenThongTin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_loai_ts", nullable = false)
    @JsonIgnore
    private LoaiTaiSan loaiTaiSan; // Thong tin cua mot loai tai san

    public ThongTin(@NotBlank String tenThongTin) {
        this.tenThongTin = tenThongTin;
    }

    public ThongTin(@NotBlank String tenThongTin, LoaiTaiSan loaiTaiSan) {
        this.tenThongTin = tenThongTin;
        this.loaiTaiSan = loaiTaiSan;
    }
}
