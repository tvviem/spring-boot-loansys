package vn.blu.tvviem.loansys.models.hoso;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import vn.blu.tvviem.loansys.models.taisan.TaiSan;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "tai_san_ho_so")
public class TaiSanHoSo implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_tai_san")
    private TaiSan taiSan;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_ho_so")
    private HoSo hoSo;

    @Column(name = "gia_tri", precision = 10, nullable = false)
    private BigDecimal giaTri;

    @Column(name = "duoc_phep_vay", precision = 10, nullable = false)
    private BigDecimal duocPhepVay;

    @Column(name = "de_xuat", precision = 10, nullable = false)
    private BigDecimal deXuat;

    @CreatedDate
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;
}
