package securityWithSpring.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import securityWithSpring.model.Employee;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private static final List<Employee> employeesList = Arrays.asList(
      new Employee(1,"Johnnie","Walker"),
      new Employee(2,"Jack","Daniels"),
      new Employee(3,"Jim","Beam")
    );

    @GetMapping(path = "{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
        return  employeesList.stream().filter(employee ->employeeId.equals(employee.getId()))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Employee with provided id not found"));
    }
}
