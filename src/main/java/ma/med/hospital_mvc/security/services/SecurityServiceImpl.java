package ma.med.hospital_mvc.security.services;

import groovy.util.logging.Slf4j;
import lombok.AllArgsConstructor;
import ma.med.hospital_mvc.security.entities.RoleEntity;
import ma.med.hospital_mvc.security.entities.UserEntity;
import ma.med.hospital_mvc.security.repositories.RoleEntityRepository;
import ma.med.hospital_mvc.security.repositories.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j @AllArgsConstructor
public class SecurityServiceImpl implements ISecurityService {

    private UserEntityRepository userEntityRepository;
    private RoleEntityRepository roleEntityRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserEntity saveNewUser(String username, String password, String rePassword) {
        if (!password.equals(rePassword)) throw new RuntimeException("Password not match");
        String hashedPass = passwordEncoder.encode(password);
        UserEntity userEntity = new UserEntity();
            userEntity.setUserId(UUID.randomUUID().toString());
            userEntity.setUsername(username);
            userEntity.setPassword(hashedPass);
            userEntity.setActive(true);
        UserEntity savedUser = userEntityRepository.save(userEntity);
        return savedUser;
    }

    @Override
    public RoleEntity saveNewRole(String roleName, String roleDesc) {
        if (roleEntityRepository.findByRoleName(roleName) != null)
            throw  new RuntimeException("Role "+roleName+" already exist !!");

        RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRoleName(roleName);
            roleEntity.setRole_desc(roleDesc);
        RoleEntity savedRole = roleEntityRepository.save(roleEntity);
        return savedRole;
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
            UserEntity userEntity = userEntityRepository.findByUsername(username);
            RoleEntity roleEntity = roleEntityRepository.findByRoleName(rolename);

            if (userEntity == null || roleEntity == null)
                throw new RuntimeException("Role or user does not exist !!");

            userEntity.getRoles().add(roleEntity);
    }

    @Override
    public void removeRoleFromUser(String username, String rolename) {
        UserEntity userEntity = userEntityRepository.findByUsername(username);
        RoleEntity roleEntity = roleEntityRepository.findByRoleName(rolename);

        if (userEntity == null || roleEntity == null)
            throw new RuntimeException("Role or user does not exist !!");

        userEntity.getRoles().remove(roleEntity);

    }

    @Override
    public UserEntity loadByusername(String username) {
        return userEntityRepository.findByUsername(username);
    }
}
