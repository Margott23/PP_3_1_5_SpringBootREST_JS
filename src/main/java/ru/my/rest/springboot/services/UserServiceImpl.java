package ru.my.rest.springboot.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.my.rest.springboot.dao.UserDAO;
import ru.my.rest.springboot.models.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleService.updateRoleForUser(user);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User delete(int id) {
        userRepository.deleteById(id);
        return userRepository.findById(id);
    }

    @Override
    public User findById(int id) {
        User user = userRepository.findById(id);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User with Id " + id + " not found");
        }
    }

    @Override
    @Transactional
    public User update(User updateUser) {
        updateUser.setPassword(userRepository.findById(updateUser.getId()).getPassword()); //edit password disabled in html
        roleService.updateRoleForUser(updateUser);
        userRepository.updateUser(updateUser);
        return updateUser;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public void saveDefaultUser(User admin) {
        roleService.addDefaultRoles();
        roleService.updateDefaultRolesToDefaultUser(admin);
        String encryptedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encryptedPassword);
        userRepository.save(admin);
    }
}
