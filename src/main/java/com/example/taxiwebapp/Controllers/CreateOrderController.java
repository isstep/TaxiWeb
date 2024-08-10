package com.example.taxiwebapp.Controllers;

import com.example.taxiwebapp.EntityClasses.TaxiOrderEntity;
import com.example.taxiwebapp.EntityClasses.UsersEntity;
import com.example.taxiwebapp.EntityRepositories.TaxiOrderRepository;
import com.example.taxiwebapp.ServiceMethods.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class CreateOrderController {

    private  UserService userService;
    private TaxiOrderRepository taxiOrderRepository;

    @Autowired
    public void RegisterController (TaxiOrderRepository taxiOrderRepository){this.taxiOrderRepository = taxiOrderRepository;}

    @Autowired
    public void CreateOrderController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/createOrder")
    public String createOrder(Model model) {
        return "createOrderPage";
    }

    @PostMapping("/createOrder")
    public String acceptOrder(@RequestParam("orderLatitude") double orderLatitude,
                              @RequestParam("orderLongitude") double orderLongitude,
                              @RequestParam("arrivalLatitude") double arrivalLatitude,
                              @RequestParam("arrivalLongitude") double arrivalLongitude,
                              @RequestParam("price") double price) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            UsersEntity user = userService.findByUsername(username);
            Long id = user.getId();
            TaxiOrderEntity taxiOrderEntity = new TaxiOrderEntity();
            taxiOrderEntity.setSenderId(id);
            taxiOrderEntity.setPrice(price);
            taxiOrderEntity.setOrderLatitude(orderLatitude);
            taxiOrderEntity.setOrderLongitude(orderLongitude);
            taxiOrderEntity.setArrivalLatitude(arrivalLatitude);
            taxiOrderEntity.setArrivalLongitude(arrivalLongitude);
            taxiOrderEntity.setStatusId(1L);
            taxiOrderEntity.setOrderDate(Timestamp.valueOf(LocalDateTime.now()));
            taxiOrderRepository.save(taxiOrderEntity);
        }


        return "redirect:/mainPage";
    }
}
