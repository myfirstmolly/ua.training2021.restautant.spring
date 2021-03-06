package ua.training.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.training.restaurant.entities.Category;
import ua.training.restaurant.entities.Dish;

public interface DishRepository extends PagingAndSortingRepository<Dish, Integer> {

    Page<Dish> findAllByCategory(Category category, Pageable pageable);

}
