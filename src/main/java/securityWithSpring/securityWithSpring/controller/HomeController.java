package securityWithSpring.securityWithSpring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String showMainPage() {
        return "mainPage";
    }
}
