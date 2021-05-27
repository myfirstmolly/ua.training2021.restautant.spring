package ua.training.restaurant.service.impl;

import ua.training.restaurant.dto.DishDto;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.repository.DishRepository;
import ua.training.restaurant.service.CategoryService;
import ua.training.restaurant.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryService categoryService;

    private static final int LIMIT = 12;

    @Override
    public Page<Dish> findAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
        return dishRepository.findAll(pageable);
    }

    @Override
    public Page<Dish> findAll(int pageNo, String orderBy, String category) {
        if (category.equals("all")) {
            if (orderBy.equals("id")) {
                Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
                return dishRepository.findAll(pageable);
            }
            Pageable pageable = PageRequest.of(pageNo - 1, LIMIT, Sort.by(orderBy));
            return dishRepository.findAll(pageable);
        }
        if (orderBy.equals("id")) {
            Pageable pageable = PageRequest.of(pageNo - 1, LIMIT);
            return dishRepository.findAllByCategory(
                    categoryService.findById(Integer.parseInt(category)).orElseThrow(RuntimeException::new), pageable);
        }
        Pageable pageable = PageRequest.of(pageNo - 1, LIMIT, Sort.by(orderBy));
        return dishRepository.findAllByCategory(
                categoryService.findById(Integer.parseInt(category)).orElseThrow(RuntimeException::new), pageable);
    }

    @Override
    public Optional<Dish> findById(Integer id) {
        return dishRepository.findById(id);
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
