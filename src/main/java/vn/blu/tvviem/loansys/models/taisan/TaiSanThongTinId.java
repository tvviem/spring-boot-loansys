package vn.blu.tvviem.loansys.models.taisan;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data @NoArgsConstructor
public class TaiSanThongTinId implements Serializable {

    @Column(name = "id_tai_san")
    private Long taiSanId;
    @Column(name = "id_thong_tin")
    private Integer thongTinId;

    public TaiSanThongTinId(Long taiSanId, Integer thongTinId) {
        this.taiSanId = taiSanId;
        this.thongTinId = thongTinId;
    }
}
