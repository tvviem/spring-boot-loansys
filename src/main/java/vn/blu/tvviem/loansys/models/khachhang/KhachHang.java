package vn.blu.tvviem.loansys.models.khachhang;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.blu.tvviem.loansys.models.AuditModel;
import vn.blu.tvviem.loansys.models.hoso.HoSo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "khach_hang", indexes = @Index(name = "idx", columnList = "ho_ten,so_dien_thoai"))
// @EntityListeners(AuditingEntityListener.class) // dùng để tạo CreatedDate and Last...
public class KhachHang extends AuditModel {
    private static final long serialVersionUID = -9179396662373251049L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @NotBlank(message = "hoTen is required")
    @Column(name = "ho_ten", length = 50, nullable = false)
    private String hoTen;

    @Column(name = "phai", columnDefinition = "TINYINT(1) default null COMMENT '0=false=female, 1=true=male'")
    private boolean phai;

    @NotBlank(message = "diaChi is required")
    @Column(name = "dia_chi", length = 80, nullable = false)
    private String diaChi;

    @Column(name = "so_dien_thoai", length = 15)
    private String soDienThoai;

    @Column(name = "so_cmnd", length = 15, nullable = false, unique = true)
    private String soCmnd;

    @Column(name = "ngay_cap", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "ngayCapCmnd phai co!")
    private Date ngayCapCmnd;
    @Column(name = "noi_cap", length = 30, nullable = false)
    private String noiCap;
    @Column(name = "hinh", length = 40)
    private String duongDanHinh;

    @ManyToMany(mappedBy = "cacKhachHang")
    @JsonIgnore
    private Set<HoSo> cacHoSo;

    public KhachHang(@NotBlank(message = "hoTen is required") String hoTen, boolean phai, @NotBlank(message = "diaChi is required") String diaChi, String soDienThoai, String soCmnd, @NotNull(message = "ngayCapCmnd phai co!") Date ngayCapCmnd, String noiCap, String duongDanHinh) {
        this.hoTen = hoTen;
        this.phai = phai;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.soCmnd = soCmnd;
        this.ngayCapCmnd = ngayCapCmnd;
        this.noiCap = noiCap;
        this.duongDanHinh = duongDanHinh;
    }

    /* @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false, updatable = false)
    private Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;*/
}
