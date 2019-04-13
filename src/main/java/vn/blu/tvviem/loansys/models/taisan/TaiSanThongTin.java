package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "chi_tiet_ts")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor
public class TaiSanThongTin implements Serializable {

    @EmbeddedId
    private TaiSanThongTinId taiSanThongTinId;

    @NotBlank
    @Column(name = "noi_dung", length = 100, nullable = false)
    private String noiDung;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat", nullable = false)
    @NotNull
    private Date ngayCapNhat;

    public TaiSanThongTin(TaiSan taiSan, ThongTin thongTin, String noiDung) {
        this.taiSanThongTinId = new TaiSanThongTinId();
        this.taiSanThongTinId.setTaiSan(taiSan);
        this.taiSanThongTinId.setThongTin(thongTin);
        this.noiDung = noiDung;
    }

}
