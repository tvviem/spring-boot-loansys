package vn.blu.tvviem.loansys.models.hoso;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.lang.NonNull;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;
import vn.blu.tvviem.loansys.models.taisan.ThongTin;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "ho_so")
public class HoSo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedDate
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "tong_vay_quy_dinh", precision = 12)
    private BigDecimal tongVayQuyDinh;

    @Column(name = "tong_vay_de_xuat", precision = 12)
    private BigDecimal tongVayDeXuat;

    @Column(name = "khach_muon_vay", precision = 12)
    private BigDecimal khachMuonVay;

    @Column(name = "ky_han", columnDefinition = "TINYINT(3)")
    private int kyHan;

    @Column(name = "muc_lai_suat", precision = 7, scale = 5)
    private BigDecimal mucLaiSuat;

    @Column(name = "giam_doc_duyet", precision = 12)
    private BigDecimal giamDocDuyet;

    @Column(name = "da_duyet", columnDefinition = "TINYINT(1)") // tinyint(1) in MySQL
    private boolean daDuyet;

    @Column(name = "da_giai_ngan", columnDefinition = "TINYINT(1)") // tinyint(1) in MySQL
    private boolean daGiaiNgan;

    @Column(name = "da_ke_du_no", columnDefinition = "TINYINT(1)") // tinyint(1) in MySQL
    private boolean daKeDuNo;

    @Column(name = "da_thu_hoi_no", columnDefinition = "TINYINT(1)") // tinyint(1) in MySQL
    private boolean daThuHoiNo;

    @Column(name = "ghi_chu", length = 400)
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name="id_loai_hs")
    private @NonNull LoaiHoSo loaiHoSo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "gioi_thieu",
            joinColumns = @JoinColumn(name = "id_khach_hang", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_ho_so", referencedColumnName = "id"))
    private Set<KhachHang> cacKhachHang;

    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

}
