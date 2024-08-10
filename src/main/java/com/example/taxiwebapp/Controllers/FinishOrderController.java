package com.example.taxiwebapp.Controllers;

import com.example.taxiwebapp.EntityRepositories.StatusRepository;
import com.example.taxiwebapp.EntityRepositories.TaxiOrderRepository;
import com.example.taxiwebapp.EntityRepositories.UsersRepository;
import org.springframework.stereotype.Controller;

@Controller
public class FinishOrderController {
    private UsersRepository usersRepository;
    private StatusRepository statusRepository;
    private TaxiOrderRepository taxiOrderRepository;

    public FinishOrderController(UsersRepository usersRepository, StatusRepository statusRepository, TaxiOrderRepository taxiOrderRepository) {
        this.usersRepository = usersRepository;
        this.statusRepository = statusRepository;
        this.taxiOrderRepository = taxiOrderRepository;
    }
}
