package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hinh_tai_san")
@Data
public class HinhTaiSan {

    @ManyToOne
    @JoinColumn(name="id_tai_san", columnDefinition = "bigint(19) unsigned", nullable = false)
    @JsonIgnore
    private TaiSan taiSan;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_tap_tin", length = 50, nullable = false)
    private String tenTapTin;

    @Column(name = "loai_hinh", length = 20, nullable = false)
    private String loaiHinh;

    @Column(name = "noi_dung_hinh", nullable = false)
    @Lob
    private byte[] noiDungHinh;

    public HinhTaiSan() {}

    public HinhTaiSan(TaiSan taiSan, String tenTapTin, String loaiHinh, byte[] noiDungHinh) {
        this.taiSan = taiSan;
        this.tenTapTin = tenTapTin;
        this.loaiHinh = loaiHinh;
        this.noiDungHinh = noiDungHinh;
    }
}
