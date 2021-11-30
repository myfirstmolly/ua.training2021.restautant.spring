package ua.training.restaurant.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.training.restaurant.dao.DishDao;
import ua.training.restaurant.entities.Category;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.repository.DishRepository;

import java.util.Optional;

@Repository
public class DishDaoImpl implements DishDao {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Page<Dish> findAllByCategory(Category category, Pageable pageable) {
        return dishRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Dish> findAll(Pageable pageable) {
        return dishRepository.findAll(pageable);
    }

    @Override
    public Optional<Dish> findById(Integer id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish save(Dish d) {
        return dishRepository.save(d);
    }

    @Override
    public void delete(Dish dish) {
        dishRepository.delete(dish);
    }
}
