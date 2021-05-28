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
import ua.training.restaurant.service.RequestItemService;
import ua.training.restaurant.service.RequestService;

import java.util.Optional;

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
        requestService.findRequestInCart(user)
                .ifPresent((req -> {
                    if (!req.getRequestItems().isEmpty())
                        model.addAttribute("request", req);
                }));
        return "cart";
    }

    @GetMapping("/checkout")
    public String goToCheckout(Model model, @AuthenticationPrincipal User user) {
        Optional<Request> request = requestService.findRequestInCart(user);
        if (request.isPresent() && !request.get().getRequestItems().isEmpty()) {
            model.addAttribute(request.get());
            return "checkout-form";
        }
        return "redirect:/menu";
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
        requestService.checkout(user, deliveryAddress);
        return "redirect:/requests";
    }

    @PostMapping(value = "/delete/{dish}")
    public String removeDishFromCart(@PathVariable Dish dish, @AuthenticationPrincipal User user) {
        requestItemService.removeItem(dish, user);
        return "redirect:/cart";
    }

}
