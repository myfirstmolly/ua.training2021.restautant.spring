package ua.training.restaurant.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.restaurant.entities.*;
import ua.training.restaurant.repository.RequestItemRepository;
import ua.training.restaurant.repository.RequestRepository;
import ua.training.restaurant.service.RequestItemService;

@Service
@Slf4j
public class RequestItemServiceImpl implements RequestItemService {

    @Autowired
    private RequestItemRepository requestItemRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    @Transactional
    public void addItem(Dish dish, User user) {
        RequestItem requestItem = requestItemRepository
                .findFirstByUserAndStatusAndDish(user, Status.OPENED, dish)
                .orElse(RequestItem.builder()
                        .request(findRequestInCart(user))
                        .quantity(0)
                        .dish(dish)
                        .build());

        int quantity = requestItem.getQuantity() + 1;
        requestItem.setQuantity(quantity);
        requestItemRepository.save(requestItem);
    }

    @Override
    @Transactional
    public void decreaseQuantity(Dish dish, User user) {
        requestItemRepository
                .findFirstByUserAndStatusAndDish(user, Status.OPENED, dish)
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
                .findFirstByUserAndStatusAndDish(user, Status.OPENED, dish)
                .ifPresent(i -> requestItemRepository.delete(i));
    }

    private Request findRequestInCart(User user) {
        return requestRepository
                .findFirstByUserAndStatus(user, Status.OPENED)
                .orElseGet(() -> {
                    Request r = Request.builder()
                            .user(user)
                            .status(Status.OPENED)
                            .build();
                    requestRepository.save(r);
                    return r;
                });
    }

}
