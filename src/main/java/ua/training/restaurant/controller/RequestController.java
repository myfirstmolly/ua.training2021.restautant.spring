package ua.training.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.restaurant.dto.RequestDto;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.service.RequestService;

import javax.validation.Valid;

@Controller
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public String getAllRequests(@RequestParam(defaultValue = "1") Integer pageNo,
                                 @AuthenticationPrincipal User user,
                                 Model model) {
        model.addAttribute("requests", requestService.findAllByUser(user, pageNo));
        return "orders";
    }

    @GetMapping("/status")
    public String getAllRequestsByStatus(@RequestParam(defaultValue = "1") Integer pageNo,
                                         @RequestParam String status,
                                         @AuthenticationPrincipal User user,
                                         Model model) {
        if (status.equalsIgnoreCase(Status.OPENED.getName()))
            return "redirect:/cart";

        model.addAttribute("requests", requestService.findAllByUserAndStatus(user,
                Status.valueOf(status), pageNo));
        return "orders";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("request", requestService.findById(id));
        return "order";
    }

    @PostMapping
    public void setRequestStatus(@Valid RequestDto request, BindingResult result, @AuthenticationPrincipal User user) {
        if (result.hasErrors())
            return;
        requestService.updateRequestStatus(user, request.getRequest(), request.getStatus());
    }

}
