package ua.training.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.restaurant.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
