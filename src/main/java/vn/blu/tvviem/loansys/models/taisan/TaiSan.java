package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.blu.tvviem.loansys.models.khachhang.KhachHang;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "tai_san") @Getter @Setter
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
            mappedBy = "taiSanThongTinId.taiSanId",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    //@Transient
    private Set<TaiSanThongTin> taiSanThongTins = new HashSet<>(0);

    @OneToMany(mappedBy = "taiSan", cascade = CascadeType.ALL)
    private Set<HinhTaiSan> hinhAnhs = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false, updatable = false)
    private Date ngayTao;
    // Them thong tin cua mot tai san voi noi dung tuong ung
    public void addThongTin(ThongTin thongTin, String noiDung) {
        TaiSanThongTin taiSanThongTin = new TaiSanThongTin(this.id, thongTin.getId(), noiDung);

        this.taiSanThongTins.add(taiSanThongTin);
    }
    // Remove Thong tin
    public void removeThongTin(ThongTin thongTin) {
        //taiSanThongTin.getThongTin().getCacTaiSan().remove(taiSanThongTin);
        taiSanThongTins.removeIf(taiSanThongTin -> taiSanThongTin.getTaiSanThongTinId().getTaiSanId().equals(this.id) &&
                taiSanThongTin.getTaiSanThongTinId().getThongTinId().equals(thongTin.getId()));
    }

    public TaiSan() {
    }
}
