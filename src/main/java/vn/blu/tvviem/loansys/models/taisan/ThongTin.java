package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "thong_tin")
@NaturalIdCache
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
public class ThongTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "ten_thong_tin", length = 40, nullable = false)
    private String tenThongTin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="id_loai_ts", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private LoaiTaiSan loaiTaiSan; // Thong tin cua mot loai tai san

    /*// QUAN HE VOI BANG TAI SAN
    @OneToMany(
            mappedBy = "taiSanThongTinId.thongTin",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<TaiSanThongTin> cacTaiSan = new ArrayList<>();*/
}
