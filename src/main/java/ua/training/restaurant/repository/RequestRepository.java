package ua.training.restaurant.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;

import java.util.Optional;

public interface RequestRepository extends PagingAndSortingRepository<Request, Integer> {

    Page<Request> findAllByUserAndStatusNot(User user, Status status, Pageable pageable);

    Page<Request> findAllByStatusNot(Status status, Pageable pageable);

    Page<Request> findAllByStatus(Status status, Pageable pageable);

    Page<Request> findAllByUserAndStatus(User user, Status status, Pageable pageable);

    Optional<Request> findFirstByUserAndStatus(User user, Status status);

}
