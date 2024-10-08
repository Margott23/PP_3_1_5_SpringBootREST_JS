package ru.my.rest.springboot.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.my.rest.springboot.dao.RoleDAO;
import ru.my.rest.springboot.models.Role;
import ru.my.rest.springboot.models.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleRepository;

    @Transactional
    public void updateRoleForUser(User user) {
        List<Role> roles = new ArrayList<>();
        if (!user.getRoles().isEmpty()) {
            for (Role role : user.getRoles()) {
                Role rRole = roleRepository.findByRole(role.getRoleName());
                roles.add(rRole);
            }
        } else {
            Role rRole = roleRepository.findByRole("ROLE_USER");
            roles.add(rRole);
        }
        user.setRoles(roles);
    }

    public RoleServiceImpl(RoleDAO roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByRole(name);
    }

    @Override
    @Transactional
    public void addDefaultRoles() {
        if (roleRepository.findAll().isEmpty()) {
            Role adminRole = new Role("ROLE_ADMIN");
            Role userRole = new Role("ROLE_USER");
            roleRepository.save(adminRole);
            roleRepository.save(userRole);
        }
    }

    @Override
    @Transactional
    public void updateDefaultRolesToDefaultUser(User admin) {
        admin.setRoles(List.of(roleRepository.findByRole("ROLE_ADMIN")));
    }
}
