package com.restaurant.api.service.impl;

import com.restaurant.api.entities.Dish;
import com.restaurant.api.entities.Request;
import com.restaurant.api.entities.RequestItem;
import com.restaurant.api.entities.User;
import com.restaurant.api.repository.RequestItemRepository;
import com.restaurant.api.service.RequestItemService;
import com.restaurant.api.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestItemServiceImpl implements RequestItemService {

    @Autowired
    private RequestItemRepository requestItemRepository;

    @Autowired
    private RequestService requestService;

    @Override
    public Optional<RequestItem> findById(Long id) {
        return requestItemRepository.findById(id);
    }

    @Override
    public void addItem(User user, Dish dish) {
        Request request = requestService.findRequestInCart(user);

        RequestItem requestItem = request
                .getRequestItems()
                .stream()
                .filter(r -> r.getDish().equals(dish))
                .findFirst()
                .orElse(RequestItem.builder().request(request).quantity(0).dish(dish).build());

        int quantity = requestItem.getQuantity();
        requestItem.setQuantity(quantity + 1);
        requestItemRepository.save(requestItem);
    }

    @Override
    public RequestItem update(RequestItem requestItem) {
        return null;
    }

    @Override
    public void delete(RequestItem requestItem) {

    }
}
