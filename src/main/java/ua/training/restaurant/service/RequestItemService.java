package ua.training.restaurant.service;

import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.entities.RequestItem;
import ua.training.restaurant.entities.User;

public interface RequestItemService {

    RequestItem addItem(Dish dish, User user);

    void decreaseQuantity(Dish dish, User user);

    void removeItem(Dish dish, User user);

}
