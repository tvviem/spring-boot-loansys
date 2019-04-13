package vn.blu.tvviem.loansys.models.hoso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.blu.tvviem.loansys.models.baomat.User;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "ho_so")
public class HoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false)
    private Date ngayTao;

    @Column(name = "tong_vay_quy_dinh", precision = 12)
    @Range(min = 1000000, max = 1000000000, message = "tongVayQuyDinh from 1M to 1B")
    private BigDecimal tongVayQuyDinh;

    @Column(name = "tong_vay_de_xuat", precision = 12)
    private BigDecimal tongVayDeXuat;

    @Column(name = "khach_muon_vay", precision = 12)
    private BigDecimal khachMuonVay;

    @Column(name = "ky_han", columnDefinition = "TINYINT(3)")
    private int kyHan;

    @Column(name = "muc_lai_suat", precision = 7, scale = 5, nullable = false)
    private BigDecimal mucLaiSuat;

    @Column(name = "giam_doc_duyet", precision = 12)
    private BigDecimal giamDocDuyet;

    @Column(name = "da_duyet", columnDefinition = "TINYINT(1) default 0") // tinyint(1) in MySQL
    private boolean daDuyet;

    @Column(name = "da_giai_ngan", columnDefinition = "TINYINT(1) default 0") // tinyint(1) in MySQL
    private boolean daGiaiNgan;

    @Column(name = "da_ke_du_no", columnDefinition = "TINYINT(1) default 0") // tinyint(1) in MySQL
    private boolean daKeDuNo;

    @Column(name = "da_thu_hoi_no", columnDefinition = "TINYINT(1) default 0") // tinyint(1) in MySQL
    private boolean daThuHoiNo;

    @Column(name = "ghi_chu", length = 400)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name="id_loai_hs", nullable = false)
    @NotEmpty
    private LoaiHoSo loaiHoSo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "gioi_thieu",
            joinColumns = @JoinColumn(name = "id_khach_hang", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_ho_so", referencedColumnName = "id"))
    private Set<KhachHang> cacKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nhan_vien_tin_dung_id", nullable = false)
    @JsonIgnore
    private User nhanVienTinDung; // Thong tin cua mot loai tai san

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat", nullable = false)
    @NotNull
    private Date ngayCapNhat;
}
