package vn.blu.tvviem.loansys.models.taisan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
@NoArgsConstructor @AllArgsConstructor
public class MucLaiSuatId implements Serializable {

    @ManyToOne
    @JoinColumn(name="id_loai_ts", columnDefinition = "INT(10) UNSIGNED")
    private LoaiTaiSan loaiTaiSan;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "ngay_tao", nullable = false, updatable = false)
    private Date ngayTao;
}
