package com.restaurant.api.service;

import com.restaurant.api.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> findById(Long id);

    List<Category> findAll();

}
