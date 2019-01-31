package vn.blu.tvviem.loansys.models.hoso;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "loai_ho_so")
public class LoaiHoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ten_loai_hs", length = 30, nullable = false)
    private String tenLoaiHoSo;

    @Column(name = "mo_ta_loai", length = 50)
    private String moTaLoaiHs;
}
