package vn.blu.tvviem.loansys.models.khachhang;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name = "khach_hang")
@EntityListeners(AuditingEntityListener.class)
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(name = "ho_ten", length = 50, nullable = false)
    private String hoTen;
    @NotBlank
    @Column(name = "dia_chi", length = 80)
    private String diaChi;
    @Column(name = "so_dien_thoai", length = 12)
    private String soDienThoai;
    @Column(name = "so_cmnd", length = 12)
    private String soCmnd;
    @Column(name = "ngay_cap")
    private Date ngayCapCmnd;
    @Column(name = "noi_cap", length = 30)
    private String noiCap;
    @Column(name = "hinh", length = 40)
    private String duongDanHinh;
    @CreatedDate
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    public KhachHang() {
    }

    public KhachHang(@NotBlank String hoTen, @NotBlank String diaChi, String soDienThoai, String soCmnd, Date ngayCapCmnd, String noiCap, String duongDanHinh, Date ngayTao, Date ngayCapNhat) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.soCmnd = soCmnd;
        this.ngayCapCmnd = ngayCapCmnd;
        this.noiCap = noiCap;
        this.duongDanHinh = duongDanHinh;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }
}
