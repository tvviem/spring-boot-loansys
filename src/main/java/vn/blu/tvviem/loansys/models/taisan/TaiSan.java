package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "tai_san")
public class TaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="id_khach_hang")
    private @NonNull
    KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name="id_loai_ts")
    private @NonNull LoaiTaiSan loaiTaiSan;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "chi_tiet_ts", joinColumns = @JoinColumn(name = "id_tai_san", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_thong_tin", referencedColumnName = "id"))
    private Set<ThongTin> cacThongTin;

    @CreatedDate
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    public TaiSan() {
    }

    public TaiSan(KhachHang khachHang, LoaiTaiSan loaiTaiSan, Set<ThongTin> cacThongTin) {
        this.khachHang = khachHang;
        this.loaiTaiSan = loaiTaiSan;
        this.cacThongTin = cacThongTin;
    }

}
