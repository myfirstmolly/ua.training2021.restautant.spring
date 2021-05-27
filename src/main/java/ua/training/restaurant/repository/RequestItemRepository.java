package ua.training.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.training.restaurant.entities.*;

import java.util.Optional;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {

    @Query("select ri from RequestItem ri join ri.request r " +
            "where r.user = :user and ri.dish = :dish " +
            "and r.status = :status")
    Optional<RequestItem> findByUserAndDishAndStatus(@Param("user") User user,
                                                     @Param("dish") Dish dish,
                                                     @Param("status") Status status);

    Optional<RequestItem> findFirstByRequestAndDish(Request request, Dish dish);
}
