package vn.blu.tvviem.loansys.models.khachhang;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.blu.tvviem.loansys.models.hoso.HoSo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "khach_hang")
@EntityListeners(AuditingEntityListener.class) // dùng để tạo CreatedDate and Last...
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "ho_ten may not be blank")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date ngayCapCmnd;
    @Column(name = "noi_cap", length = 30)
    private String noiCap;
    @Column(name = "hinh", length = 40)
    private String duongDanHinh;

    @ManyToMany(mappedBy = "cacKhachHang")
    @JsonIgnore
    private Set<HoSo> cacHoSo;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false, updatable = false)
    private Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;
}
