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
import ua.training.restaurant.repository.DishRepository;
import ua.training.restaurant.service.DishService;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    private static final int LIMIT = 12;

    @Override
    public Page<Dish> findAll(int pageNo, String orderBy) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT, Sort.by(orderBy));
        return dishRepository.findAll(pageable);
    }

    @Override
    public Page<Dish> findAll(int pageNo, String orderBy, Category category) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT, Sort.by(orderBy));
        return dishRepository.findAllByCategory(category, pageable);
    }

    @Override
    public Dish saveDish(DishDto dish) {
        int price;
        if (dish.getPrice().contains("."))
            price = Integer.parseInt(dish.getPrice().replace(".", ""));
        else
            price = Integer.parseInt(dish.getPrice()) * 100;
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
    public void deleteDish(Integer dish) {
        dishRepository.deleteById(dish);
    }
}
