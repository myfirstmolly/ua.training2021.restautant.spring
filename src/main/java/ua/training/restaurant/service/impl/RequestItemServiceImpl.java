package ua.training.restaurant.service.impl;

import lombok.extern.slf4j.Slf4j;
import ua.training.restaurant.repository.RequestItemRepository;
import ua.training.restaurant.service.RequestItemService;
import ua.training.restaurant.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.restaurant.entities.*;

import java.util.Optional;

@Service
@Slf4j
public class RequestItemServiceImpl implements RequestItemService {

    @Autowired
    private RequestItemRepository requestItemRepository;

    @Autowired
    private RequestService requestService;

    @Override
    public Optional<RequestItem> findById(Integer id) {
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
                        .price(dish.getPrice())
                        .dish(dish)
                        .build());

        int quantity = requestItem.getQuantity() + 1;
        requestItem.setQuantity(quantity);
        requestItemRepository.save(requestItem);
        requestService.save(request);
    }

    @Override
    @Transactional
    public void decreaseQuantity(Dish dish, User user) {
        Request request = requestService.findRequestInCart(user);
        requestItemRepository
                .findFirstByRequest(request)
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
    public void removeItem(Dish dish, User user) {
        Request request = requestService.findRequestInCart(user);
        requestItemRepository
                .findFirstByRequest(request)
                .ifPresent(i -> requestItemRepository.delete(i));
    }
}
