package ua.training.restaurant.service.impl;

import ua.training.restaurant.repository.RequestItemRepository;
import ua.training.restaurant.service.RequestItemService;
import ua.training.restaurant.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.restaurant.entities.*;

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
    @Transactional
    public void addItem(Dish dish, User user) {
        Request request = requestService.findRequestInCart(user);

        RequestItem requestItem = requestItemRepository
                .findFirstByRequestAndDish(request, dish)
                .orElse(RequestItem.builder()
                        .request(request)
                        .quantity(0)
                        .dish(dish)
                        .build());

        int quantity = requestItem.getQuantity();
        requestItem.setQuantity(quantity + 1);
        requestItemRepository.save(requestItem);
    }

    @Override
    @Transactional
    public void decreaseQuantity(Dish dish, User user) {
        requestItemRepository
                .findByUserAndDishAndStatus(user, dish, Status.OPENED)
                .ifPresent(i -> {
                    if (i.getQuantity() > 1) {
                        i.setQuantity(i.getQuantity() - 1);
                        requestItemRepository.save(i);
                    } else {
                        requestItemRepository.delete(i);
                    }
                });
    }

    @Override
    @Transactional
    public void removeItem(Dish dish, User user) {
        requestItemRepository
                .findByUserAndDishAndStatus(user, dish, Status.OPENED)
                .ifPresent(item -> requestItemRepository.delete(item));
    }
}
