package com.restaurant.api.service;

import com.restaurant.api.entities.Category;
import com.restaurant.api.entities.Dish;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DishService {

    Page<Dish> findAll(int pageNo);

    Page<Dish> findAll(int pageNo, String orderBy);

    Page<Dish> findAllByCategory(Category category, int pageNo);

    Optional<Dish> findById(Long id);

    Dish saveDish(Dish dish);

    void deleteDish(Dish dish);

}
