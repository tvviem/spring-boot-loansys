package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity @NoArgsConstructor
@Data
@Table(name = "loai_tai_san")
public class LoaiTaiSan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    @NotBlank
    @Column(name = "ten_loai", length = 40, nullable = false, unique = true)
    private String tenLoai;

    @Column(name = "ghi_chu", length = 80)
    private String ghiChu;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loaiTaiSan")
    private List<ThongTin> thongTins = new ArrayList<>();

    public LoaiTaiSan(@NotBlank String tenLoai, String ghiChu) {
        this.tenLoai = tenLoai;
        this.ghiChu = ghiChu;
    }

    public void addThongTin(ThongTin thongTin) {
        thongTins.add(thongTin);
        thongTin.setLoaiTaiSan(this);
    }

    public void removeThongTin(ThongTin thongTin) {
        thongTins.remove(thongTin);
        thongTin.setLoaiTaiSan(null);
    }
    /*public LoaiTaiSan(String tenLoai, String ghiChu, ThongTin... thongTins) {
        this.tenLoai = tenLoai;
        this.ghiChu = ghiChu;
        this.thongTins = Stream.of(thongTins).collect(Collectors.toSet());
        this.thongTins.forEach(thongTin -> thongTin.setLoaiTaiSan(this));
    }*/
}
