package securityWithSpring.securityWithSpring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import securityWithSpring.model.Employee;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {


    @GetMapping(path = "/{employeeId")
    public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
        return  null;
    }
}
