package application.service.service_impl;

import application.entity.Role;
import application.repository.RoleRepository;
import application.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByRoleNameAndSaveIfDoesntExists(String roleName) {
        Role role = roleRepository.findRoleByRoleName(roleName);
        if (role == null) {
            Role userRole = new Role();
            userRole.setRoleName(roleName);
            roleRepository.save(userRole);
            return userRole;
        } else return role;
    }
}
