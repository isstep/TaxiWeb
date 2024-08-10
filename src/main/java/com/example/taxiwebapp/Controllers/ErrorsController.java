package com.example.taxiwebapp.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorsController {

    @GetMapping("/insufficientAccessPage")
    public String ErrorRole(){
        return "403errorPage";
    }

    @GetMapping("/404error")
    public String handleError() {
        return "404errorPage";
    }

    @GetMapping("/500error")
    public String handle500Error() {
        return "500errorPage";
    }

    @GetMapping("/blockedPage")
    public String BlockError() {
        return "blockedPage";
    }
}
