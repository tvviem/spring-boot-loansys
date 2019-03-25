package vn.blu.tvviem.loansys.models.taisan;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity @NoArgsConstructor @Getter @Setter
@Table(name = "hinh_tai_san")
public class HinhTaiSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_tai_san", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TaiSan taiSan;

    @Column(name = "duong_dan", length = 60)
    private String duongDan;

}
