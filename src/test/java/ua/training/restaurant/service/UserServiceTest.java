package ua.training.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.training.restaurant.dto.UserDto;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.exceptions.NotUniqueUsernameException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void save() throws NotUniqueUsernameException {
        UserDto userDto = new UserDto();
        userDto.setName("");
        userDto.setEmail("");
        userDto.setPassword("");
        userDto.setPhoneNumber("");
        userDto.setUsername("");
        User user = userService.save(userDto);
        assertNotNull(user);
        userService.delete(user);
    }

}