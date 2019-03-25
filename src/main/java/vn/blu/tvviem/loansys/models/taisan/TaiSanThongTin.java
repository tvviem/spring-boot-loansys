package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "chi_tiet_ts")
@EntityListeners(AuditingEntityListener.class)
@Data @NoArgsConstructor
class TaiSanThongTin implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private TaiSanThongTinId taiSanThongTinId;

    @ManyToOne
    @MapsId("thongTinId")
    @JoinColumn(name = "id_thong_tin")
    private ThongTin thongTin;

    // recursive detect
    /*@ManyToOne
    @MapsId("taiSanId")
    @JoinColumn(name = "id_tai_san")
    private TaiSan taiSan;*/

    @NotBlank
    @Column(name = "noi_dung", length = 80)
    private String noiDung;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    TaiSanThongTin(Long taiSanId, Integer thongTinId, String noiDung) {
        this.taiSanThongTinId = new TaiSanThongTinId(taiSanId, thongTinId);
        this.noiDung = noiDung;
    }

}
