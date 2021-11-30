package ua.training.restaurant.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.training.restaurant.dao.RequestDao;
import ua.training.restaurant.dao.RequestItemDao;
import ua.training.restaurant.entities.*;
import ua.training.restaurant.service.RequestItemService;

@Service
@Slf4j
public class RequestItemServiceImpl implements RequestItemService {

    @Autowired
    private RequestItemDao requestItemRepository;

    @Autowired
    private RequestDao requestRepository;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public RequestItem addItem(Dish dish, User user) {
        RequestItem requestItem = requestItemRepository
                .findFirstByUserAndStatusAndDish(user, Status.OPENED, dish)
                .orElse(RequestItem.builder()
                        .request(findRequestInCart(user))
                        .quantity(0)
                        .dish(dish)
                        .build());

        requestItem.setQuantity(requestItem.getQuantity() + 1);
        return requestItemRepository.save(requestItem);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removeItem(Dish dish, User user) {
        requestItemRepository
                .findFirstByUserAndStatusAndDish(user, Status.OPENED, dish)
                .ifPresent(i -> requestItemRepository.delete(i));
    }

    private Request findRequestInCart(User user) {
        return requestRepository
                .findFirstByUserAndStatus(user, Status.OPENED)
                .orElseGet(() -> requestRepository.save(Request.builder().user(user).status(Status.OPENED).build()));
    }

}
