package com.restaurant.api.service.impl;

import com.restaurant.api.entities.Request;
import com.restaurant.api.entities.Status;
import com.restaurant.api.entities.User;
import com.restaurant.api.exceptions.EmptyRequestException;
import com.restaurant.api.exceptions.ObjectNotFoundException;
import com.restaurant.api.exceptions.RequestNotFoundException;
import com.restaurant.api.repository.RequestRepository;
import com.restaurant.api.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    private static final int LIMIT = 10;

    @Override
    public Page<Request> findAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        return requestRepository.findAllByStatusNot(Status.OPENED, pageable);
    }

    @Override
    public Page<Request> findAllByStatus(Status status, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        return requestRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Request> findAllByUserAndStatus(User user, Status status, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        return requestRepository.findAllByUserAndStatus(user, status, pageable);
    }

    @Override
    public Optional<Request> findById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public Request findRequestInCart(User user) {
        return requestRepository.findFirstByUserAndStatus(user, Status.OPENED)
                .orElseGet(() -> {
                    Request r = Request.builder().user(user).status(Status.OPENED).build();
                    requestRepository.save(r);
                    return r;
                });
    }

    @Override
    public Request approveRequest(User user, String deliveryAddress) throws RequestNotFoundException,
            EmptyRequestException {

        Request request = requestRepository.findFirstByUserAndStatus(user, Status.OPENED)
                .orElseThrow(RequestNotFoundException::new);

        if (request.getRequestItems().isEmpty())
            throw new EmptyRequestException();

        request.setDeliveryAddress(deliveryAddress);
        request.setStatus(Status.PENDING);
        return requestRepository.save(request);
    }

    @Override
    public Request updateRequestStatus(User manager, Request request, Status status) {
        if (Status.COOKING.equals(status))
            request.setApprovedBy(manager);
        request.setStatus(status);
        return requestRepository.save(request);
    }
}
