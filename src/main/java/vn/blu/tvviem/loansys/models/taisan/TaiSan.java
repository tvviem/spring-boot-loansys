package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Table(name = "tai_san")
@Data
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Skip error serializable in bean
public class TaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_khach_hang", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name="id_loai_ts", nullable = false)
    @JsonIgnore
    private LoaiTaiSan loaiTaiSan;

    // QUAN HE VOI BANG chi_tiet_ts
    @NotEmpty
    @OneToMany(
            mappedBy = "taiSanThongTinId.taiSan",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TaiSanThongTin> taiSanThongTins = new ArrayList<>();

    @OneToMany(
            mappedBy = "taiSan",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<HinhTaiSan> hinhTaiSans = new ArrayList<>();

    @Transient
    private Integer countHinhTaiSans;
    @PostLoad
    private void toComputeSizeOfHinhTaiSans() {
        countHinhTaiSans = hinhTaiSans.size();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false, updatable = false)
    private Date ngayTao;


    // Them thong tin cua mot tai san voi noi dung tuong ung
    public void addThongTin(ThongTin thongTin, String noiDung) {
        //TaiSanThongTin taiSanThongTin = new TaiSanThongTin(this.id, thongTin.getId(), noiDung);
        TaiSanThongTin taiSanThongTin = new TaiSanThongTin(this, thongTin, noiDung);

        this.taiSanThongTins.add(taiSanThongTin);
    }
    // Remove Thong tin
    public void removeThongTin(ThongTin thongTin) {
        this.taiSanThongTins.removeIf(taiSanThongTin -> taiSanThongTin.getTaiSanThongTinId().getTaiSan().equals(this) &&
                taiSanThongTin.getTaiSanThongTinId().getThongTin().equals(thongTin));
    }

    // check exist thongTin in taiSanThongTins
    public boolean isExistThongTin(ThongTin thongTin) {
        return this.taiSanThongTins.stream()
                .filter(taiSanThongTin -> taiSanThongTin.getTaiSanThongTinId().getThongTin().equals(thongTin))
                .findFirst()
                .orElse(null) == null;
    }
}
