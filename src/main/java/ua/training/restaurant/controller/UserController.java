package ua.training.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.training.restaurant.dto.UserDto;
import ua.training.restaurant.exceptions.NotUniqueUsernameException;
import ua.training.restaurant.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public String goToRegisterPage(Model model) {
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") UserDto user, BindingResult result,
                           Model model, HttpServletRequest request) {
        if (result.hasErrors())
            return "register";

        try {
            userService.save(user);
            request.login(user.getUsername(), user.getPassword());
            log.info("authorized user {}", user.getUsername());
            return "redirect:/menu";
        } catch (ServletException e) {
            log.warn("error while attempting to login user {}", user.getUsername());
            return "redirect:/login";
        } catch (NotUniqueUsernameException e) {
            model.addAttribute("notUniqueUsername", true);
            return "register";
        }
    }

}
