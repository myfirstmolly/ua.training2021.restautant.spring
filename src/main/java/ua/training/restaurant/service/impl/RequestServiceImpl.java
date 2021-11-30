package ua.training.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.training.restaurant.dao.RequestDao;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Role;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.exceptions.EmptyRequestException;
import ua.training.restaurant.exceptions.RequestNotFoundException;
import ua.training.restaurant.service.RequestService;

import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDao requestDao;

    private static final int LIMIT = 10;

    @Override
    public Page<Request> findAllByUserAndStatus(User user, String status, int pageNo) {
        if (status.equals("all")) {
            return findAllByUser(user, pageNo);
        }
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        if (user.getRole().equals(Role.MANAGER))
            return requestDao.findAllByStatus(Status.valueOf(status), pageable);
        return requestDao.findAllByUserAndStatus(user, Status.valueOf(status), pageable);
    }

    @Override
    public Optional<Request> findById(Integer id) {
        return requestDao.findById(id);
    }

    @Override
    public Optional<Request> findRequestInCart(User user) {
        return requestDao.findFirstByUserAndStatus(user, Status.OPENED);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,
            rollbackFor = { RequestNotFoundException.class, EmptyRequestException.class })
    public Request checkout(User user, String deliveryAddress) throws RequestNotFoundException, EmptyRequestException {
        Request request = requestDao.findFirstByUserAndStatus(user, Status.OPENED)
                .orElseThrow(() -> new RequestNotFoundException("order is not found"));

        if (request.getRequestItems().isEmpty())
            throw new EmptyRequestException("order is empty");

        request.setDeliveryAddress(deliveryAddress);
        request.setStatus(Status.PENDING);
        return requestDao.save(request);
    }

    @Override
    public Request updateRequestStatus(User manager, Request request, Status status) {
        if (Status.COOKING.equals(status))
            request.setApprovedBy(manager);
        request.setStatus(status);
        return requestDao.save(request);
    }

    @Override
    public Request save(Request request) {
        return requestDao.save(request);
    }

    private Page<Request> findAllByUser(User user, int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        if (user.getRole().equals(Role.MANAGER))
            return requestDao.findAllByStatusNot(Status.OPENED, pageable);
        return requestDao.findAllByUserAndStatusNot(user, Status.OPENED, pageable);
    }
}
