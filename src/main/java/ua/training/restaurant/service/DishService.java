package ua.training.restaurant.service;

import org.springframework.data.domain.Page;
import ua.training.restaurant.dto.DishDto;
import ua.training.restaurant.entities.Category;
import ua.training.restaurant.entities.Dish;

public interface DishService {

    Page<Dish> findAll(int pageNo, String orderBy);

    Page<Dish> findAll(int pageNo, String orderBy, Category category);

    Dish saveDish(DishDto dish);

    void deleteDish(Integer id);

}
