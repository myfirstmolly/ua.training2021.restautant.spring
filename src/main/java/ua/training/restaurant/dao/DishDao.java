package ua.training.restaurant.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.training.restaurant.entities.Category;
import ua.training.restaurant.entities.Dish;

import java.util.Optional;

public interface DishDao {

    Page<Dish> findAllByCategory(Category category, Pageable pageable);

    Page<Dish> findAll(Pageable pageable);

    Optional<Dish> findById(Integer id);

    Dish save(Dish dish);

    void delete(Dish dish);

}
