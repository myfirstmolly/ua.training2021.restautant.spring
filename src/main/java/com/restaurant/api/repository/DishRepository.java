package com.restaurant.api.repository;

import com.restaurant.api.entities.Category;
import com.restaurant.api.entities.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {

    Page<Dish> findAllByCategory(Category category, Pageable pageable);

}
