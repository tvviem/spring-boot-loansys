package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "chi_tiet_ts")
@Data
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class TaiSanThongTin {

    @EmbeddedId
    private TaiSanThongTinId taiSanThongTinId;

    @Column(name = "noi_dung", length = 80)
    private String noiDung;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    public TaiSanThongTin(TaiSan taiSan, ThongTin thongTin, String noiDung) {
        taiSanThongTinId = new TaiSanThongTinId(taiSan, thongTin);
        this.noiDung = noiDung;
    }
}
