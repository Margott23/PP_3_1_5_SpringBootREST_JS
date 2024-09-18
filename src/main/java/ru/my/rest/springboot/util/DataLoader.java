package ru.my.rest.springboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.my.rest.springboot.models.User;
import ru.my.rest.springboot.services.RoleService;
import ru.my.rest.springboot.services.UserService;

@Component
public class DataLoader implements CommandLineRunner {
    public final static Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final UserService userService;
    private final RoleService roleService;
    private final Environment env;

    public DataLoader(UserService userServiceImpl, RoleService roleService, Environment env) {
        this.userService = userServiceImpl;
        this.roleService = roleService;
        this.env = env;
    }

    @Override
    @Transactional
    public void run(String... args) {
        String requiredProperty = env.getRequiredProperty("spring.jpa.hibernate.ddl-auto");
        if (requiredProperty.equals("create-drop")) {
            String defaultPassword = "admin";
            User defaultAdmin = new User("Admin", "Admin", 20, "admin@mail.ru", defaultPassword);
            userService.saveDefaultUser(defaultAdmin);
            log.info("Добавлены роли: {}", roleService.getAllRoles().toString().replace("[", "").replace("]", ""));
            log.info("Добавлен администратор, логин: \"{}\", пароль: \"{}\"", defaultAdmin.getUsername(), defaultPassword);
        } else {
            roleService.addDefaultRoles();
            log.info("Добавлены роли по-умолчанию: {}", roleService.getAllRoles().toString().replace("[", "").replace("]", ""));
        }
    }
}
