package ru.my.rest.springboot.dao;

import ru.my.rest.springboot.models.User;

import java.util.List;


public interface UserDAO {
    List<User> findAll();

    User findById(int id);

    void save(User user);

    void updateUser(User user);

    void deleteById(int id);

    User findByLogin (String login);
}
