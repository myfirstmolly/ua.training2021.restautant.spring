package ua.training.restaurant.service;

import ua.training.restaurant.dto.UserDto;
import ua.training.restaurant.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import ua.training.restaurant.exceptions.NotUniqueUsernameException;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    User save(UserDto user) throws NotUniqueUsernameException;

    void delete(User user);

}
