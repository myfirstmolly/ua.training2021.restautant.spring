package com.restaurant.api.controller;

import com.restaurant.api.entities.Dish;
import com.restaurant.api.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping(value = "/{id}")
    public String getDish(@PathVariable long id, Model model) {
        model.addAttribute("dish", dishService.findById(id));
        return "dish";
    }

    @PostMapping("/add")
    public String add(@Valid Dish dish, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add-dish";
        dishService.saveDish(dish);
        return "redirect:/dishes/" + dish.getId();
    }
}
