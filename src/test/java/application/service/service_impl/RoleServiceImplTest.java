package application.service.service_impl;

import application.entity.Role;
import application.repository.RoleRepository;
import application.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Test
    void findRoleByRoleNameAndSaveIfDoesntExists() {
        String roleName = "ADMIN";
        Role role = new Role();
        RoleService roleService = new RoleServiceImpl(roleRepository);

        Mockito.when(roleRepository.findRoleByRoleName(roleName)).thenReturn(role);
        Role returnedRole = roleService.findRoleByRoleNameAndSaveIfDoesntExists(roleName);

        Mockito.verify(roleRepository, Mockito.times(1)).findRoleByRoleName(roleName);
        assertEquals(role, returnedRole);
    }

    @Test
    void findRoleByRoleNameAndSaveIfDoesntExistsTest2() {
        String roleName = "ADMIN";
        Role role = null;
        Role roleForCheck = new Role();
        roleForCheck.setRoleName(roleName);
        RoleService roleService = new RoleServiceImpl(roleRepository);

        Mockito.when(roleRepository.findRoleByRoleName(roleName)).thenReturn(role);
        Role returnedRole = roleService.findRoleByRoleNameAndSaveIfDoesntExists(roleName);

        Mockito.verify(roleRepository, Mockito.times(1)).findRoleByRoleName(roleName);
        Mockito.verify(roleRepository, Mockito.times(1)).save(Mockito.any(Role.class));
        assertEquals(roleForCheck, returnedRole);
    }
}