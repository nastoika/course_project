package application.service;

import application.entity.Role;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role findRoleByRoleNameAndSaveIfDoesntExists(String name);
}
