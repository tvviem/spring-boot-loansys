package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor
public class TaiSanThongTinId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_tai_san")
    @JsonIgnore
    private TaiSan taiSan;

    @ManyToOne
    @JoinColumn(name = "id_thong_tin")
    private ThongTin thongTin;

}
