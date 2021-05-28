package ua.training.restaurant.service;

import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.exceptions.EmptyRequestException;
import ua.training.restaurant.exceptions.RequestNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RequestService {

    Page<Request> findAllByUserAndStatus(User user, String status, int pageNo);

    Optional<Request> findById(Integer id);

    Optional<Request> findRequestInCart(User user);

    Request checkout(User user, String deliveryAddress) throws RequestNotFoundException,
            EmptyRequestException;

    void updateRequestStatus(User manager, Request request, Status status);

    Request save(Request request);
}
