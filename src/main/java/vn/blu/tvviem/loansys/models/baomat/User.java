package vn.blu.tvviem.loansys.models.baomat;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "nguoi_dung")
@Data
// @Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Skip error serializable in bean
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String email;
    @Column(name = "ten")
    private String firstName;
    @Column(name = "ho_lot")
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

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    /*@ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();*/

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "ngay_cap_nhat")
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
