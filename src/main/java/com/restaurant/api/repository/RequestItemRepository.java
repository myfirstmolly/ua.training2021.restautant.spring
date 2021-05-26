package com.restaurant.api.repository;

import com.restaurant.api.entities.RequestItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {

}
