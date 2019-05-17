package vn.blu.tvviem.loansys.models.hoso;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "hinh_thuc_lai")
public class HinhThucLai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "ten_hinh_thuc", length = 40, nullable = false, unique = true)
    @NotBlank
    private String tenHinhThuc;

    @Column(name = "mo_ta", length = 60)
    private String moTa;

    public HinhThucLai() {
    }

    public HinhThucLai(@NotBlank String tenHinhThuc, String moTa) {
        this.tenHinhThuc = tenHinhThuc;
        this.moTa = moTa;
    }
}
