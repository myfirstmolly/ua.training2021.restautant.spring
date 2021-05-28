package ua.training.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.training.restaurant.entities.*;

import java.util.Optional;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {

    @Query("select ri from RequestItem ri join ri.request r where r.user=?1 and r.status=?2 and " +
            "ri.dish=?3")
    Optional<RequestItem> findFirstByUserAndStatusAndDish(User user, Status status, Dish dish);
}
