package vn.blu.tvviem.loansys.models.hoso;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "loai_ho_so")
public class LoaiHoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "ten_loai_hs", length = 40, nullable = false)
    @NotBlank
    private String tenLoaiHoSo;

    @Column(name = "mo_ta_loai", length = 60)
    private String moTaLoaiHs;
}
