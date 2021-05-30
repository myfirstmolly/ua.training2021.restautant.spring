package ua.training.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.restaurant.dto.DishDto;
import ua.training.restaurant.entities.Category;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.exceptions.DishIsOrderedException;
import ua.training.restaurant.service.CategoryService;
import ua.training.restaurant.service.DishService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/menu")
@Slf4j
public class MenuController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String menu(@RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "id", required = false) String orderBy,
                       @RequestParam(required = false) Optional<Category> category,
                       Model model) {
        model.addAttribute("orderBy", orderBy);
        category.ifPresent(c -> model.addAttribute("category", c));
        Page<Dish> dishPage = dishService.findAll(pageNo, orderBy, category);
        model.addAttribute("dishes", dishPage.getContent());
        model.addAttribute("page", dishPage);
        model.addAttribute("categories", categoryService.findAll());
        return "index";
    }

    @GetMapping(value = "/dish/{dish}")
    public String getDish(@PathVariable Dish dish, Model model) {
        model.addAttribute("dish", dish);
        return "dish";
    }

    @GetMapping("/add")
    public String goToAddDishPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dish", new DishDto());
        return "add-dish";
    }

    @PostMapping("/add")
    public String addDish(@Valid @ModelAttribute("dish") DishDto dish, BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "add-dish";
        }
        return "redirect:/menu/dish/" + dishService.saveDish(dish).getId();
    }

    @PostMapping(value = "/delete/{dish}")
    public String deleteDish(@PathVariable Dish dish, Model model) {
        try {
            dishService.deleteDish(dish);
            return "redirect:/menu";
        } catch (DishIsOrderedException ex) {
            model.addAttribute("dish", dish);
            model.addAttribute("deleteError", true);
            return "dish";
        }
    }
}
