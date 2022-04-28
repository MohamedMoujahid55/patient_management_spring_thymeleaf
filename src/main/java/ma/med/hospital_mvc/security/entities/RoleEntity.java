package ma.med.hospital_mvc.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data @AllArgsConstructor @NoArgsConstructor
public class RoleEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(unique = true)
    private String roleName;
    private String role_desc;
    /*@ManyToMany(fetch = FetchType.EAGER)
    private List<UserEntity> users = new ArrayList<>();*/
}
