package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "chi_tiet_ts")
public class TaiSanThongTin implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_tai_san")
    private TaiSan taiSan;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_thong_tin")
    private ThongTin thongTin;

    @Column(name = "noi_dung", length = 60)
    private String noiDung;
}
