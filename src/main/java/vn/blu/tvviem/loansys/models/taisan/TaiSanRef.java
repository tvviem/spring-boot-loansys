package vn.blu.tvviem.loansys.models.taisan;

import javax.persistence.Table;

@Table(name = "chi_tiet_ts")
public class TaiSanRef {
    private Integer thongTinId;

    public TaiSanRef(Integer thongTinId) {
        this.thongTinId = thongTinId;
    }

    public Integer getThongTinId() {
        return thongTinId;
    }
}
