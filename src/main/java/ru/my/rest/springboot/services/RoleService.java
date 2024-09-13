package ru.my.rest.springboot.services;


import ru.my.rest.springboot.models.Role;
import ru.my.rest.springboot.models.User;

import java.util.List;

public interface RoleService {
    void updateRoleForUser(User user);

    Role getRoleById(Long id);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    Role saveRole(Role role);

    void addDefaultRoles();

    void updateDefaultRolesToDefaultUser(User admin);
}
