package ua.training.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.restaurant.entities.Dish;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.exceptions.EmptyRequestException;
import ua.training.restaurant.exceptions.RequestNotFoundException;
import ua.training.restaurant.service.RequestItemService;
import ua.training.restaurant.service.RequestService;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestItemService requestItemService;

    @GetMapping
    public String getCart(Model model, @AuthenticationPrincipal User user) {
        Request request = requestService.findRequestInCart(user);
        model.addAttribute("request", request);
        return "cart";
    }

    @GetMapping("/checkout")
    public String goToCheckout(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute(requestService.findRequestInCart(user));
        return "checkout-form";
    }

    @PostMapping("/add/{dish}")
    public String addDishToCart(@PathVariable Dish dish,
                                @AuthenticationPrincipal User user) {
        requestItemService.addItem(dish, user);
        return "redirect:/menu/dish/" + dish.getId();
    }

    @GetMapping(value = "/decreaseQuantity/{dish}")
    public String decreaseDishQuantity(@PathVariable Dish dish,
                                       @AuthenticationPrincipal User user) {
        requestItemService.decreaseQuantity(dish, user);
        return "redirect:/cart";
    }

    @GetMapping(value = "/increaseQuantity/{dish}")
    public String increaseDishQuantity(@PathVariable Dish dish,
                                       @AuthenticationPrincipal User user) {
        requestItemService.addItem(dish, user);
        return "redirect:/cart";
    }

    @PostMapping(value = "/checkout")
    public String checkout(@RequestParam String deliveryAddress, @AuthenticationPrincipal User user) {
        try {
            requestService.checkout(user, deliveryAddress);
        } catch (RequestNotFoundException | EmptyRequestException e) {
            return "redirect:/cart";
        }
        return "redirect:/requests";
    }

    @PostMapping(value = "/delete/{dish}")
    public String removeDishFromCart(@PathVariable Dish dish, @AuthenticationPrincipal User user) {
        requestItemService.removeItem(dish, user);
        return "redirect:/cart";
    }

}
