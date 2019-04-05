package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "thong_tin")
public class ThongTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "ten_thong_tin", length = 40, nullable = false)
    private String tenThongTin;

    @ManyToOne
    @JoinColumn(name="id_loai_ts", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LoaiTaiSan loaiTaiSan; // Thong tin cua mot loai tai san
}
