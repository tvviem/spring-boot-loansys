package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
@NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MucLaiSuatId implements Serializable {

    @ManyToOne
    @JoinColumn(name="id_loai_ts")
    private LoaiTaiSan loaiTaiSan;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonIgnore
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false, updatable = false)
    private Date ngayTao;
}
