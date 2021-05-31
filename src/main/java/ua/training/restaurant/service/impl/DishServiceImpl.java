package ua.training.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.training.restaurant.dto.DishDto;
import ua.training.restaurant.entities.Category;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.exceptions.DishIsOrderedException;
import ua.training.restaurant.exceptions.DishNotFoundException;
import ua.training.restaurant.repository.DishRepository;
import ua.training.restaurant.service.DishService;

import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    private static final int LIMIT = 12;

    @Override
    public Page<Dish> findAll(int pageNo, String orderBy, Optional<Category> category) {
        if (category.isPresent()) {
            Pageable pageable = PageRequest.of(pageNo - 1, LIMIT, Sort.by(orderBy));
            return dishRepository.findAllByCategory(category.get(), pageable);
        }
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT, Sort.by(orderBy));
        return dishRepository.findAll(pageable);
    }

    @Override
    public Dish findById(Integer id) {
        return dishRepository.findById(id).orElseThrow(
                () -> new DishNotFoundException(String.format("dish with id %s is not found", id)));
    }

    @Override
    public Dish saveDish(DishDto dish) {
        int price = getPrice(dish);
        Dish d = Dish.builder()
                .name(dish.getName())
                .nameUkr(dish.getNameUkr())
                .price(price)
                .description(dish.getDescription())
                .descriptionUkr(dish.getDescriptionUkr())
                .category(dish.getCategory())
                .imagePath(dish.getImagePath())
                .build();
        return dishRepository.save(d);
    }

    @Override
    public void deleteDish(Dish dish) throws DishIsOrderedException {
        try {
            dishRepository.delete(dish);
        } catch (Exception ex) {
            throw new DishIsOrderedException();
        }
    }

    private int getPrice(DishDto dish) {
        if (dish.getPrice().contains(".")) {
            return Integer.parseInt(dish.getPrice().replace(".", ""));
        }
        return Integer.parseInt(dish.getPrice()) * 100;
    }
}
