package ua.training.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RequestServiceTest {

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAllByUserAndStatus() {
        User user = userRepository.findByUsername("test").get();
        assertNotNull(requestService.findAllByUserAndStatus(user, "OPENED", 1));
    }

    @Test
    void findById() {
        assertNotNull(requestService.findById(45));
    }

    @Test
    void findRequestInCart() {
        User user = userRepository.findByUsername("test").get();
        assertNotNull(requestService.findRequestInCart(user));
    }

}