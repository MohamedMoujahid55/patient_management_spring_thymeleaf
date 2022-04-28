package ma.med.hospital_mvc.security.repositories;

import ma.med.hospital_mvc.security.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRoleName(String rolename);
}
