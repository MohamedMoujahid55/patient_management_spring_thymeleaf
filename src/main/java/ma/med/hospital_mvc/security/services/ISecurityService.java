package ma.med.hospital_mvc.security.services;

import ma.med.hospital_mvc.security.entities.RoleEntity;
import ma.med.hospital_mvc.security.entities.UserEntity;

public interface ISecurityService {
    UserEntity saveNewUser(String username, String password, String rePassword);
    RoleEntity saveNewRole(String roleName, String roleDesc);
    void addRoleToUser(String username, String rolename);
    void removeRoleFromUser(String username, String rolename);
    UserEntity loadByusername(String username);
}
