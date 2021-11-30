package ua.training.restaurant.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ua.training.restaurant.dao.RequestDao;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.repository.RequestRepository;

import java.util.Optional;

@Repository
public class RequestDaoImpl implements RequestDao {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Optional<Request> findFirstByUserAndStatus(User user, Status status) {
        return requestRepository.findFirstByUserAndStatus(user, status);
    }

    @Override
    public Page<Request> findAllByStatus(Status status, Pageable pageable) {
        return requestRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Request> findAllByUserAndStatus(User user, Status status, Pageable pageable) {
        return requestRepository.findAllByUserAndStatus(user, status, pageable);
    }

    @Override
    public Page<Request> findAllByStatusNot(Status status, Pageable pageable) {
        return requestRepository.findAllByStatusNot(status, pageable);
    }

    @Override
    public Page<Request> findAllByUserAndStatusNot(User user, Status status, Pageable pageable) {
        return requestRepository.findAllByUserAndStatusNot(user, status, pageable);
    }

    @Override
    public Optional<Request> findById(Integer id) {
        return requestRepository.findById(id);
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }
}
