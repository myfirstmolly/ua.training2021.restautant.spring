package ua.training.restaurant.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.training.restaurant.dao.RequestItemDao;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.entities.RequestItem;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.repository.RequestItemRepository;

import java.util.Optional;

@Repository
public class RequestItemDaoImpl implements RequestItemDao {

    @Autowired
    private RequestItemRepository requestItemRepository;

    @Override
    public Optional<RequestItem> findFirstByUserAndStatusAndDish(User user, Status status, Dish dish) {
        return requestItemRepository.findFirstByUserAndStatusAndDish(user, status, dish);
    }

    @Override
    public RequestItem save(RequestItem requestItem) {
        return requestItemRepository.save(requestItem);
    }

    @Override
    public void delete(RequestItem requestItem) {
        requestItemRepository.delete(requestItem);
    }
}
