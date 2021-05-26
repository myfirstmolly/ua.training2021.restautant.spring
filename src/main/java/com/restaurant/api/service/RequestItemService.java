package com.restaurant.api.service;

import com.restaurant.api.entities.Dish;
import com.restaurant.api.entities.RequestItem;
import com.restaurant.api.entities.User;

import java.util.Optional;

public interface RequestItemService {

    Optional<RequestItem> findById(Long id);

    void addItem(User user, Dish dish);

    RequestItem update(RequestItem requestItem);

    void delete(RequestItem requestItem);

}
