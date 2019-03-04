package vn.blu.tvviem.loansys.models.taisan;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data @NoArgsConstructor
public class TaiSanThongTinId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tai_san")
    private TaiSan taiSan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thong_tin")
    private ThongTin thongTin;

    public TaiSanThongTinId(TaiSan taiSan, ThongTin thongTin) {
        this.taiSan = taiSan;
        this.thongTin = thongTin;
    }
}
