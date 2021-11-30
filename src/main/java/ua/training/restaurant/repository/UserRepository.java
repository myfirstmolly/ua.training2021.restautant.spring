package ua.training.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.restaurant.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
