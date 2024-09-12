package ru.my.rest.springboot.services;


import ru.my.rest.springboot.models.Role;
import ru.my.rest.springboot.models.User;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);

    Role getRoleByName(String name);

    List<Role> getAllRoles();

    Role saveRole(Role role);

    public void addDefaultRoles();

    public void updateDefaultRolesToDefaultUser(User admin);
}
