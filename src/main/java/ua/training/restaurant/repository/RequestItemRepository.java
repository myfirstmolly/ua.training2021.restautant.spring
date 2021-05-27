package ua.training.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.RequestItem;

import java.util.Optional;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Integer> {

    Optional<RequestItem> findFirstByRequest(Request request);

    Optional<RequestItem> findFirstByRequestAndDish(Request request, Dish dish);
}
