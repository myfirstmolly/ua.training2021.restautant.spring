package ua.training.restaurant.service;

import org.springframework.data.domain.Page;
import ua.training.restaurant.dto.DishDto;
import ua.training.restaurant.entities.Dish;

import java.util.Optional;

public interface DishService {

    Page<Dish> findAll(int pageNo);

    Page<Dish> findAll(int pageNo, String orderBy, String category);

    Optional<Dish> findById(Long id);

    Dish saveDish(DishDto dish);

    void deleteDish(long id);

}
