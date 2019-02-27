package vn.blu.tvviem.loansys.models.taisan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity @NoArgsConstructor @AllArgsConstructor @Setter @Getter
@Table(name = "loai_tai_san")
public class LoaiTaiSan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "ten_loai", length = 30, nullable = false)
    private String tenLoai;
    @Column(name = "ghi_chu", length = 50)
    private String ghiChu;

    @OneToMany(mappedBy = "loaiTaiSan")
    private List<ThongTin> lsThongTin;

}
