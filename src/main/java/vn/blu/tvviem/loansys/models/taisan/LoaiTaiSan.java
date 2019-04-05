package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @NoArgsConstructor
@Data
@Table(name = "loai_tai_san")
public class LoaiTaiSan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED")
    private int id;

    @NotBlank
    @Column(name = "ten_loai", length = 30, nullable = false)
    private String tenLoai;

    @Column(name = "ghi_chu", length = 50)
    private String ghiChu;

    @OneToMany(mappedBy = "loaiTaiSan", cascade = CascadeType.ALL)
    private List<ThongTin> thongTins = new ArrayList<>();

    public LoaiTaiSan(@NotBlank String tenLoai, String ghiChu) {
        this.tenLoai = tenLoai;
        this.ghiChu = ghiChu;
    }

    /*public LoaiTaiSan(String tenLoai, String ghiChu, ThongTin... thongTins) {
        this.tenLoai = tenLoai;
        this.ghiChu = ghiChu;
        this.thongTins = Stream.of(thongTins).collect(Collectors.toSet());
        this.thongTins.forEach(thongTin -> thongTin.setLoaiTaiSan(this));
    }*/
}
