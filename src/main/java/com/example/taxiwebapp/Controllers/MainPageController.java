package com.example.taxiwebapp.Controllers;

import com.example.taxiwebapp.EntityClasses.RolesEntity;
import com.example.taxiwebapp.EntityClasses.UsersEntity;
import com.example.taxiwebapp.EntityRepositories.RolesRepository;
import com.example.taxiwebapp.EntityRepositories.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;

    public MainPageController(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @GetMapping("/mainPage")
    public String showMainPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UsersEntity usersEntity = usersRepository.findByUsername(username);
        Long roleId = usersEntity.getRoleId();
        RolesEntity rolesEntity = rolesRepository.findRolesEntityById(roleId);

        model.addAttribute("userRole", rolesEntity.getRoleName());
        System.out.println();
        return "mainPage";
    }
}