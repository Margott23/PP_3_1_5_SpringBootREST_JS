package ru.my.rest.springboot.services;

import ru.my.rest.springboot.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(int id);

    void save(User user);

    void update(User updateUser);

    void delete(int id);

    User findByLogin(String login);

//    User getAuthUser();

    void saveDefaultUser(User admin);
}
