package vn.blu.tvviem.loansys.models.hoso;


import lombok.Data;
import lombok.EqualsAndHashCode;
import vn.blu.tvviem.loansys.models.AuditModel;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "tai_san_ho_so")
public class TaiSanHoSo extends AuditModel implements Serializable {
    private static final long serialVersionUID = -1273844768485161098L;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_tai_san")
    private TaiSan taiSan;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_ho_so")
    private HoSo hoSo;

    @Column(name = "gia_tri", precision = 10, nullable = false)
    @Min(value = 5000000, message = "Gia tri tai san tu 5M tro len")
    private BigDecimal giaTri;

    @Column(name = "duoc_phep_vay", precision = 10, nullable = false)
    @Min(value = 1000000, message = "Duoc phep vay it nhat 1M")
    private BigDecimal duocPhepVay;

    @Column(name = "de_xuat", precision = 10, nullable = false)
    private BigDecimal deXuat;

    public TaiSanHoSo() {
    }
}
