package ua.training.restaurant.dao;

import ua.training.restaurant.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    Optional<Category> findById(Integer id);

    List<Category> findAll();

}
