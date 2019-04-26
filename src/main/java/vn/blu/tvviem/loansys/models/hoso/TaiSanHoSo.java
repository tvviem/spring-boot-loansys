package vn.blu.tvviem.loansys.models.hoso;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import vn.blu.tvviem.loansys.models.AuditModel;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data @NoArgsConstructor
@Table(name = "tai_san_ho_so")
public class TaiSanHoSo extends AuditModel implements Serializable {
    private static final long serialVersionUID = -1273844768485161098L;

    @EmbeddedId
    private TaiSanHoSoId taiSanHoSoId;

    @Column(name = "gia_tri", precision = 10, nullable = false)
    @Range(min = 5000000, max = 2000000000, message = "tongGiaTriTaiSan from 1M to 5B")
    private BigDecimal giaTri;

    @Column(name = "duoc_phep_vay", precision = 10, nullable = false)
    @Min(value = 1000000, message = "Duoc phep vay it nhat 1M")
    private BigDecimal duocPhepVay;

    @Column(name = "de_xuat", precision = 10, nullable = false)
    private BigDecimal deXuat;

    TaiSanHoSo(TaiSan taiSan, HoSo hoSo,
               @Range(min = 5000000, max = 2000000000, message = "tongGiaTriTaiSan from 1M to 5B") BigDecimal giaTri,
               @Min(value = 1000000, message = "Duoc phep vay it nhat 1M") BigDecimal duocPhepVay,
               BigDecimal deXuat) {
        this.taiSanHoSoId = new TaiSanHoSoId();
        this.taiSanHoSoId.setTaiSan(taiSan);
        this.taiSanHoSoId.setHoSo(hoSo);

        this.giaTri = giaTri; // Gia tri tai san duoc tham dinh
        this.duocPhepVay = duocPhepVay; // 80% gia tri tham dinh
        this.deXuat = deXuat; // Nhan vien tin dung de xuat
    }
}
