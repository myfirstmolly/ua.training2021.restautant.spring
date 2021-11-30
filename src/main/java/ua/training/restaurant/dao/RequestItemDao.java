package ua.training.restaurant.dao;

import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.entities.RequestItem;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;

import java.util.Optional;

public interface RequestItemDao {

    Optional<RequestItem> findFirstByUserAndStatusAndDish(User user, Status status, Dish dish);

    RequestItem save(RequestItem requestItem);

    void delete(RequestItem requestItem);

}
