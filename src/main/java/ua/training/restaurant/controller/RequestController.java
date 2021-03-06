package ua.training.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.training.restaurant.entities.Request;
import ua.training.restaurant.entities.Status;
import ua.training.restaurant.entities.User;
import ua.training.restaurant.exceptions.RequestNotFoundException;
import ua.training.restaurant.service.RequestService;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Controller
@RequestMapping("/requests")
@Slf4j
public class RequestController {

    @Autowired
    private RequestService requestService;

    @GetMapping
    public String getAllRequests(@RequestParam(defaultValue = "1") Integer pageNo,
                                 @RequestParam(defaultValue = "all") String status,
                                 @AuthenticationPrincipal User user,
                                 Model model) {
        if (status.equalsIgnoreCase(Status.OPENED.getName()))
            return "redirect:/cart";

        Page<Request> requestPage = requestService.findAllByUserAndStatus(user, status, pageNo);
        model.addAttribute("requests", requestPage.getContent());
        model.addAttribute("requestPage", requestPage);
        model.addAttribute("statusList", Arrays.asList(Status.values()).subList(1, Status.values().length));
        model.addAttribute("currentStatus", status);
        return "orders";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        Request request = requestService.findById(id).orElseThrow(
                () -> new RequestNotFoundException(String.format("order with id %s is not found", id)));
        model.addAttribute("order", request);
        model.addAttribute("statusList", Status.getSublist(request.getStatus().getId() + 1));
        return "order";
    }

    @PostMapping("/update/{request}")
    public String setRequestStatus(@PathVariable @NotNull Request request,
                                   @RequestParam(name = "status") Status status,
                                   @AuthenticationPrincipal User user) {
        requestService.updateRequestStatus(user, request, status);
        return "redirect:/requests/" + request.getId();
    }

}
