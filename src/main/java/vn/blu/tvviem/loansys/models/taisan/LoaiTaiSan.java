package vn.blu.tvviem.loansys.models.taisan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity @NoArgsConstructor @Setter @Getter
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

    @OneToMany(mappedBy = "loaiTaiSan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ThongTin> thongTins = new HashSet<>();

    /*public LoaiTaiSan(String tenLoai, String ghiChu, ThongTin... thongTins) {
        this.tenLoai = tenLoai;
        this.ghiChu = ghiChu;
        this.thongTins = Stream.of(thongTins).collect(Collectors.toSet());
        this.thongTins.forEach(thongTin -> thongTin.setLoaiTaiSan(this));
    }*/
}
