package com.restaurant.api.service;

import com.restaurant.api.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);

    User save(User user);

}
