package ua.training.restaurant.service;

import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.exceptions.EmptyRequestException;
import ua.training.restaurant.exceptions.RequestNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RequestService {

    Page<Request> findAllByUser(User user, int pageNo);

    Page<Request> findAllByUserAndStatus(User user, Status status, int pageNo);

    Optional<Request> findById(Integer id);

    Request findRequestInCart(User user);

    Request checkout(User user, String deliveryAddress) throws RequestNotFoundException,
            EmptyRequestException;

    Request updateRequestStatus(User manager, Request request, Status status);

    Request save(Request request);
}
