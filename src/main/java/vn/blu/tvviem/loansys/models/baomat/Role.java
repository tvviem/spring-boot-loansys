package vn.blu.tvviem.loansys.models.baomat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "vai_tro")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "ten_vai_tro", length = 30)
    @Enumerated(EnumType.STRING)
    @NaturalId
    @NotNull(message = "roleName is required")
    private RoleName roleName;

    @Column(name = "mo_ta_kha_nang", length = 100)
    private String descriptions;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference // is the back part of reference – it WILL BE OMITTED from serialization.
    private Set<User> users;

    /*@ManyToMany
    @JoinTable(
            name = "vai_tro_quyen",
            joinColumns = @JoinColumn(
                    name = "vai_tro_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "quyen_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;*/

    public Role(RoleName roleName, String descriptions) {
        this.roleName = roleName;
        this.descriptions = descriptions;
    }
}
