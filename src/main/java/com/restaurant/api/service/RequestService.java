package com.restaurant.api.service;

import com.restaurant.api.entities.Request;
import com.restaurant.api.entities.Status;
import com.restaurant.api.entities.User;
import com.restaurant.api.exceptions.EmptyRequestException;
import com.restaurant.api.exceptions.RequestNotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RequestService {

    Page<Request> findAll(int pageNo);

    Page<Request> findAllByStatus(Status status, int pageNo);

    Page<Request> findAllByUserAndStatus(User user, Status status, int pageNo);

    Optional<Request> findById(Long id);

    Request findRequestInCart(User user);

    Request approveRequest(User user, String deliveryAddress) throws RequestNotFoundException,
            EmptyRequestException;

    Request updateRequestStatus(User manager, Request request, Status status);

}
