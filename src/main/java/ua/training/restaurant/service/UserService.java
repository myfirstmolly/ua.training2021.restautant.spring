package ua.training.restaurant.service;

import ua.training.restaurant.dto.UserDto;
import ua.training.restaurant.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);

    User save(UserDto user);

}
