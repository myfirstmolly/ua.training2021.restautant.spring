package ua.training.restaurant.repository;

import ua.training.restaurant.entities.Category;
import ua.training.restaurant.entities.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends PagingAndSortingRepository<Dish, Integer> {

    Page<Dish> findAllByCategory(Category category, Pageable pageable);

}
