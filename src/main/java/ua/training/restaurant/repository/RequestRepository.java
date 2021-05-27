package ua.training.restaurant.repository;

import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {

    Page<Request> findAllByUserAndStatusNot(User user, Status status, Pageable pageable);

    Page<Request> findAllByStatusNot(Status status, Pageable pageable);

    Page<Request> findAllByStatus(Status status, Pageable pageable);

    Page<Request> findAllByUserAndStatus(User user, Status status, Pageable pageable);

    Optional<Request> findFirstByUserAndStatus(User user, Status status);

}
