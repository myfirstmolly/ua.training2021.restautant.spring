package ua.training.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void findById() {
        assertTrue(categoryService.findById(1).isPresent());
    }

    @Test
    void findAll() {
        assertNotNull(categoryService.findAll());
    }
}