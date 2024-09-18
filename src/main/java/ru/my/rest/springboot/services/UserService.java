package ru.my.rest.springboot.services;

import ru.my.rest.springboot.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    User save(User user);

    User update(User updateUser);

    User delete(int id);

    User findByLogin(String login);

    void saveDefaultUser(User admin);
}
