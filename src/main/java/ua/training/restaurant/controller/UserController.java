package ua.training.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.restaurant.dto.UserDto;
import ua.training.restaurant.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String goToRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping
    public String register(@Valid UserDto user, BindingResult result, Model model) {
        if (result.hasErrors())
            return "redirect:/register";
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("notUniqueUsername", true);
            return "redirect:/register";
        }
        userService.save(user);
        return "index";
    }

}
