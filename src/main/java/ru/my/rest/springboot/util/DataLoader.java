package ru.my.rest.springboot.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.my.rest.springboot.models.User;
import ru.my.rest.springboot.services.UserService;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @Override
    @Transactional
    public void run(String... args) {
        User defaultAdmin = new User("Admin", "Admin", 20, "admin@mail.ru", "admin");
        userService.saveDefaultUser(defaultAdmin);
    }
}
