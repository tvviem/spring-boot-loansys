package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "tai_san")
@EntityListeners(AuditingEntityListener.class)
public class TaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_khach_hang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_loai_ts", nullable = false)
    private LoaiTaiSan loaiTaiSan;

    // QUAN HE VOI BANG THONG TIN
    @OneToMany(
            mappedBy = "taiSanThongTinId.taiSan",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<TaiSanThongTin> cacThongTin = new ArrayList<>();

    @OneToMany(mappedBy = "taiSan", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HinhTaiSan> hinhAnhs = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false, updatable = false)
    private Date ngayTao;

    // Them thong tin cua mot tai san voi noi dung tuong ung
    public void addThongTin(ThongTin thongTin, String noiDung) {
        TaiSanThongTin taiSanThongTin = new TaiSanThongTin(this, thongTin, noiDung);
        cacThongTin.add(taiSanThongTin);
        // thongTin.getCacTaiSan().add(taiSanThongTin);
    }
    // Remove Thong tin
    public void removeThongTin(ThongTin thongTin) {
        for (Iterator<TaiSanThongTin> iterator = cacThongTin.iterator();
             iterator.hasNext(); ) {
            TaiSanThongTin taiSanThongTin = iterator.next();

            if (taiSanThongTin.getTaiSanThongTinId().getTaiSan().equals(this) &&
                    taiSanThongTin.getTaiSanThongTinId().getThongTin().equals(thongTin)) {
                iterator.remove();
                //taiSanThongTin.getTaiSanThongTinId().getThongTin().getCacTaiSan().remove(taiSanThongTin);
                taiSanThongTin.getTaiSanThongTinId().setTaiSan(null);
                taiSanThongTin.getTaiSanThongTinId().setThongTin(null);
            }
        }
    }
}
