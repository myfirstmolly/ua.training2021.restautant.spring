package ua.training.restaurant.service;

import ua.training.restaurant.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findById(Integer id);

    Optional<Category> findByName(String name);

    List<Category> findAll();

}
