package ua.training.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.training.restaurant.dto.DishDto;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.exceptions.DishIsOrderedException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DishServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    void findAll() {
        assertNotNull(dishService.findAll(1, "id", Optional.empty()));
        assertNotNull(dishService.findAll(1, "name", Optional.empty()));
        assertNotNull(dishService.findAll(1, "price", Optional.empty()));
    }

    @Test
    void findById() {
        assertNotNull(dishService.findById(7));
    }

    @Test
    void saveDish() throws DishIsOrderedException {
        Dish dish = dishService.findById(7);
        DishDto dishDto = new DishDto();
        dishDto.setName("");
        dishDto.setNameUkr("");
        dishDto.setPrice("2000");
        dishDto.setDescription("");
        dishDto.setDescriptionUkr("");
        dishDto.setImagePath("");
        dishDto.setCategory(dish.getCategory());
        Dish d = dishService.saveDish(dishDto);
        assertNotNull(d);
        dishService.deleteDish(d);
    }
}