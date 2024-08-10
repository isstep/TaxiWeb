package com.example.taxiwebapp.Controllers;

import com.example.taxiwebapp.EntityClasses.StatusEntity;
import com.example.taxiwebapp.EntityClasses.TaxiOrderEntity;
import com.example.taxiwebapp.EntityClasses.UsersEntity;
import com.example.taxiwebapp.EntityRepositories.StatusRepository;
import com.example.taxiwebapp.EntityRepositories.TaxiOrderRepository;
import com.example.taxiwebapp.EntityRepositories.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaxistOrdersController {
    private final UsersRepository usersRepository;
    private final TaxiOrderRepository taxiOrderRepository;
    private final StatusRepository statusRepository;


    public TaxistOrdersController(UsersRepository usersRepository, TaxiOrderRepository taxiOrderRepository, StatusRepository statusRepository) {
        this.usersRepository = usersRepository;
        this.taxiOrderRepository = taxiOrderRepository;
        this.statusRepository = statusRepository;
    }

    @GetMapping("/taxistOrders")
    public String getTaxiOrders(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UsersEntity user = usersRepository.findByUsername(username);
        List<TaxiOrderEntity> taxiOrders = taxiOrderRepository.findTaxiOrderEntitiesByRecipientId(user.getId());

        Map<Long, String> statusMap = new HashMap<>();
        List<StatusEntity> statusList = statusRepository.findAll();
        for (StatusEntity status : statusList) {
            statusMap.put(status.getId(), status.getStatusName());
        }

        Map<Long, String> usernamesMap = new HashMap<>();
        List<UsersEntity> usersEntityList = usersRepository.findAll();
        for (UsersEntity users : usersEntityList) {
            usernamesMap.put(users.getId(), users.getUsername()); // Заменено statusMap на usernamesMap
        }

        model.addAttribute("taxiOrders", taxiOrders);
        model.addAttribute("statusMap", statusMap);
        model.addAttribute("usernamesMap", usernamesMap); // Заменено UsernamesMap на usernamesMap

        return "ordersPage";
    }
}

