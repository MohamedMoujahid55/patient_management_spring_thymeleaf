package ma.med.hospital_mvc.security.repositories;

import ma.med.hospital_mvc.security.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String roleName);
}
