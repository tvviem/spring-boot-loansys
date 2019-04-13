package vn.blu.tvviem.loansys.models.hoso;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class GhiLaiId implements Serializable {
    @ManyToOne
    @JoinColumn(name="id_ho_so")
    @NotEmpty
    private HoSo hoSo;

    @CreatedDate
    @NotEmpty
    @Column(name = "ngay_nop")
    private Date ngayNop;

    public GhiLaiId() {
    }
}
