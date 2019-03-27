package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hinh_tai_san")
@Data
public class HinhTaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_tai_san", nullable = false, columnDefinition = "unsigned")
    @JsonIgnore
    private TaiSan taiSan;

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
