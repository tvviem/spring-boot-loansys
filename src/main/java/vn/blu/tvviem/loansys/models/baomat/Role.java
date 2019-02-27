package vn.blu.tvviem.loansys.models.baomat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vai_tro")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_vai_tro")
    private String name;
    @Column(name = "mo_ta_kha_nang")
    private String descriptions;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "vai_tro_quyen",
            joinColumns = @JoinColumn(
                    name = "vai_tro_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "quyen_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role(String name, String descriptions) {
        this.name = name;
        this.descriptions = descriptions;
    }
}
