package com.example.taxiwebapp.Controllers;

import com.example.taxiwebapp.EntityClasses.StatusEntity;
import com.example.taxiwebapp.EntityClasses.TaxiOrderEntity;
import com.example.taxiwebapp.EntityClasses.UsersEntity;
import com.example.taxiwebapp.EntityRepositories.StatusRepository;
import com.example.taxiwebapp.EntityRepositories.TaxiOrderRepository;
import com.example.taxiwebapp.EntityRepositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class OrderController {
    private UsersRepository usersRepository;
    private StatusRepository statusRepository;
    private TaxiOrderRepository taxiOrderRepository;

    public OrderController(UsersRepository usersRepository, StatusRepository statusRepository,TaxiOrderRepository taxiOrderRepository) {
        this.usersRepository = usersRepository;
        this.statusRepository = statusRepository;
        this.taxiOrderRepository=taxiOrderRepository;
    }

    @GetMapping("/order")
    public String getOrderDetails(@RequestParam("orderId") Long orderId,@RequestParam(value = "error",required = false) String error, Model model) {

        TaxiOrderEntity order = taxiOrderRepository.findTaxiOrderEntityById(orderId);
        Optional<UsersEntity> sender = usersRepository.findById(order.getSenderId());
        Optional<UsersEntity> recipient = null;
        if (order.getRecipientId() != null) {
            recipient = usersRepository.findById(order.getRecipientId());
        }
        Optional<StatusEntity> status = statusRepository.findById(order.getStatusId());

        String senderName = sender.map(UsersEntity::getUsername).orElse("Ошибка");
        String recipientName = recipient != null ? recipient.map(UsersEntity::getUsername).orElse("В ожидании") : "В ожидании";
        String statusName = status.map(StatusEntity::getStatusName).orElse("Unknown");


        if (error!= null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("status", statusName);
        model.addAttribute("recipient", recipientName);
        model.addAttribute("sender", senderName);
        model.addAttribute("order", order);

        return "orderPage";
    }

    @PostMapping("/order")
    public String cancelOrder(@RequestParam("orderId") Long orderId, @RequestParam(value = "error", required = false) String error, RedirectAttributes redirectAttributes) {

        TaxiOrderEntity order = taxiOrderRepository.findTaxiOrderEntityById(orderId);


        if ((order.getStatusId() == 2L) || (order.getStatusId() == 3L)) {
            redirectAttributes.addAttribute("error", "Заказы с таким статусом отменить нельзя");
            redirectAttributes.addAttribute("orderId", orderId);
            return "redirect:/order";
        }
        else {
        order.setStatusId(3L);
        taxiOrderRepository.save(order);
        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/order";
        }
    }



}
