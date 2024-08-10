package com.example.taxiwebapp.Controllers;
import com.example.taxiwebapp.EntityClasses.UsersEntity;
import com.example.taxiwebapp.EntityRepositories.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ShowAllUsersController {
    private final UsersRepository usersRepository;

    public ShowAllUsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
@GetMapping("/showAllUsers")
    public String AllUsersPage(Model model){
        List<UsersEntity> usersEntities = usersRepository.findAll();
        model.addAttribute("Users",usersEntities);
        return "allUsersPage";
    }
}
