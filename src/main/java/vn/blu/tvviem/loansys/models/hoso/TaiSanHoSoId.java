package vn.blu.tvviem.loansys.models.hoso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaiSanHoSoId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_tai_san")
    private TaiSan taiSan;

    @ManyToOne
    @JoinColumn(name = "id_ho_so")
    @JsonIgnore
    private HoSo hoSo;

}
