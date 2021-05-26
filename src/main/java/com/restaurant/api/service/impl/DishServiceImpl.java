package com.restaurant.api.service.impl;

import com.restaurant.api.entities.Category;
import com.restaurant.api.entities.Dish;
import com.restaurant.api.repository.DishRepository;
import com.restaurant.api.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    private static final int LIMIT = 12;

    @Override
    public Page<Dish> findAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        return dishRepository.findAll(pageable);
    }

    @Override
    public Page<Dish> findAll(int pageNo, String orderBy) {
        if (orderBy == null)
            return findAll(pageNo);

        Pageable pageable = PageRequest.of(pageNo, LIMIT, Sort.by(orderBy));
        return dishRepository.findAll(pageable);
    }

    @Override
    public Page<Dish> findAllByCategory(Category category, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        return dishRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public void deleteDish(Dish dish) {
        dishRepository.delete(dish);
    }
}
