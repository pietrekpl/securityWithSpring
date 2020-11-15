package securityWithSpring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegisterController {

    @GetMapping("login")
    public String showLoginPage(){
        return "login";
    }
    @GetMapping("departments")
    public String getDepartments(){
        return "departments";
    }
}
