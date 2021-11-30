package ua.training.restaurant.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;

import java.util.Optional;

public interface RequestDao {

    Optional<Request> findFirstByUserAndStatus(User user, Status status);

    Page<Request> findAllByStatus(Status status, Pageable pageable);

    Page<Request> findAllByUserAndStatus(User user, Status status, Pageable pageable);

    Page<Request> findAllByStatusNot(Status status, Pageable pageable);

    Page<Request> findAllByUserAndStatusNot(User user, Status status, Pageable pageable);

    Optional<Request> findById(Integer id);

    Request save(Request request);

}
