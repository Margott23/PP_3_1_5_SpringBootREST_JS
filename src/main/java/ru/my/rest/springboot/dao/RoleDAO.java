package ru.my.rest.springboot.dao;


import ru.my.rest.springboot.models.Role;

import java.util.List;

public interface RoleDAO {
    Role getRoleById(Long id);

    Role findByRole(String name);

    List<Role> findAll();

    Role save(Role role);
}
