package ua.training.restaurant.dao;

import ua.training.restaurant.entities.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByUsername(String username);

    User save(User user);

    void delete(User user);

}
