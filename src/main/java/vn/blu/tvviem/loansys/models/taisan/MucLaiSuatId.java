package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class MucLaiSuatId implements Serializable {

    @ManyToOne
    @JoinColumn(name="id_loai_ts")
    private @NonNull
    LoaiTaiSan loaiTaiSan;

    @CreatedDate
    @NonNull
    @Column(name = "ngay_tao")
    private Date ngayTao;

    public MucLaiSuatId() {
    }

    public MucLaiSuatId(LoaiTaiSan loaiTaiSan, Date ngayTao) {
        this.loaiTaiSan = loaiTaiSan;
        this.ngayTao = ngayTao;
    }
}
