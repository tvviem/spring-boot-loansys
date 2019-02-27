package vn.blu.tvviem.loansys.models.baomat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "nguoi_dung")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    @Column(name = "ten")
    private String firstName;
    @Column(name = "ho_lot")
    private String lastName;
    private String email;
    private String password;

   /* private boolean isUsing2FA;
    private String secret;*/

    @Column(name = "hinh_dai_dien")
    private String avatarPath;
    @Column(name = "kich_hoat", columnDefinition = "TINYINT(1)") // tinyint(1) in MySQL
    private boolean enabled;
    @Column(name = "the_bai_het_han", columnDefinition = "TINYINT(1)") // tinyint(1) in MySQL
    private boolean tokenExpired;

    @ManyToMany
    @JoinTable(
            name = "nguoi_dung_vai_tro",
            joinColumns = @JoinColumn(
                    name = "nguoi_dung_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "vai_tro_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;
}
