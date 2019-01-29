package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "loai_tai_san")
@EntityListeners(AuditingEntityListener.class)
public class LoaiTaiSan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ten_loai", length = 30, nullable = false)
    private @NonNull String tenLoai;
    @Column(name = "ghi_chu", length = 60)
    private String ghiChu;

    @OneToMany(mappedBy = "loaiTaiSan")
    private List<ThongTin> lsThongTin;

    public LoaiTaiSan() { }

    public LoaiTaiSan(String tenLoai, String ghiChu, List<ThongTin> lsThongTin) {
        this.tenLoai = tenLoai;
        this.ghiChu = ghiChu;
        this.lsThongTin = lsThongTin;
    }
}
