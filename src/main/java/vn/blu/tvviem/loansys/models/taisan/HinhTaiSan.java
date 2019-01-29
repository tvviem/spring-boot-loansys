package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "hinh_tai_san")
public class HinhTaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="id_tai_san")
    private @NonNull
    TaiSan taiSan;

    @Column(name = "duong_dan", length = 60)
    private String duongDan;

}
