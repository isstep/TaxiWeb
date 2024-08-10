package com.example.taxiwebapp.Controllers;

import com.example.taxiwebapp.EntityClasses.UsersEntity;
import com.example.taxiwebapp.EntityRepositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private UsersRepository usersRepository;

    @Autowired
    public void RegisterController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UsersEntity());
        return "registerPage";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UsersEntity user, Model model) {
        try {
            // Проверяем, существует ли пользователь с таким именем
            if (usersRepository.findByUsername(user.getUsername()) != null) {
                model.addAttribute("error", "Пользователь с таким именем уже существует");
                return "registerPage";
            }

            // Проверяем, существует ли пользователь с таким email
            if (usersRepository.findByUserEmail(user.getUserEmail()) != null) {
                model.addAttribute("error", "Пользователь с таким email уже существует");
                return "registerPage";
            }

            // Проверяем, существует ли пользователь с таким номером телефона
            if (usersRepository.findByPhoneNumber(user.getPhoneNumber()) != null) {
                model.addAttribute("error", "Пользователь с таким номером телефона уже существует");
                return "registerPage";
            }

            user.setRoleId(3L);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
            usersRepository.save(user);
            return "redirect:/mainPage";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("error", "Произошла ошибка при регистрации пользователя");
            return "registerPage";
        }
    }
}
