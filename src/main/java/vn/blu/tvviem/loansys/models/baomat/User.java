package vn.blu.tvviem.loansys.models.baomat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.blu.tvviem.loansys.models.hoso.HoSo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "nguoi_dung")
@Data @NoArgsConstructor @AllArgsConstructor
@SQLDelete(sql = "UPDATE nguoi_dung SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    private static final long serialVersionUID = -601203744413190984L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username is required")
    @Length(min = 8, max = 20, message = "length of username from 8 to 20 char")
    private String username;

    @Column(nullable = false, length = 70)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(length = 80, nullable = false)
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "ten", length = 15, nullable = false)
    @NotBlank(message = "firstName is required")
    private String firstName;

    @Column(name = "ho_lot", length = 45, nullable = false)
    @NotBlank(message = "lastName is required")
    private String lastName;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "nguoi_dung_vai_tro",
            joinColumns = @JoinColumn(
                    name = "nguoi_dung_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "vai_tro_id", referencedColumnName = "id"))
    @JsonManagedReference // is the forward part of reference – the one that gets serialized normally.
    private Set<Role> roles = new HashSet<>();

    @Column(nullable = false, columnDefinition = "TINYINT(1) default 0")
    private boolean deleted;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "nhanVienTinDung")
    private List<HoSo> hoSos = new ArrayList<>();

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName().name())));
        return authorities;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "ngay_tao")
    @NotNull
    private Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
    @NotNull
    private Date ngayCapNhat;

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }*/

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
