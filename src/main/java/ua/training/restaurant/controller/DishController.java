package ua.training.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.restaurant.dto.DishDto;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.service.CategoryService;
import ua.training.restaurant.service.DishService;

import javax.validation.Valid;

@Controller
@RequestMapping("/menu")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String menu(@RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "id", required = false) String orderBy,
                       @RequestParam(defaultValue = "all", required = false) String category,
                       Model model) {
        model.addAttribute("orderBy", orderBy);
        model.addAttribute("category", category);
        Page<Dish> dishPage = dishService.findAll(pageNo, orderBy, category);
        log.info(pageNo.toString());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dishes", dishPage.getContent());
        model.addAttribute("page", dishPage);
        return "index";
    }

    @GetMapping(value = "/dish/{dish}")
    public String getDish(@PathVariable Dish dish, Model model) {
        model.addAttribute("dish", dish);
        return "dish";
    }

    @GetMapping("/add")
    public String goToAddDishPage(Model model) {
        log.info(categoryService.findAll().toString());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("dishDto", new DishDto());
        return "add-dish";
    }

    @PostMapping("/add")
    public String addDish(@Valid DishDto dishDto, BindingResult result) {
        if (result.hasErrors())
            return "add-dish";
        return "redirect:/menu/dish/" + dishService.saveDish(dishDto).getId();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
    }
}
