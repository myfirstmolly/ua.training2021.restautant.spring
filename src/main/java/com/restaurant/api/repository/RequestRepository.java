package com.restaurant.api.repository;

import com.restaurant.api.entities.Request;
import com.restaurant.api.entities.Status;
import com.restaurant.api.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface RequestRepository extends PagingAndSortingRepository<Request, Long> {

    Page<Request> findAllByUser(User user, Pageable pageable);

    Page<Request> findAllByStatusNot(Status status, Pageable pageable);

    Page<Request> findAllByStatus(Status status, Pageable pageable);

    Page<Request> findAllByUserAndStatus(User user, Status status, Pageable pageable);

    Optional<Request> findFirstByUserAndStatus(User user, Status status);

}
