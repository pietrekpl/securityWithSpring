package securityWithSpring.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import securityWithSpring.model.Employee;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("department/api/employees")
public class EmployeeDeparmentController {

    private static final List<Employee> employeesList = Arrays.asList(
            new Employee(1, "Johnnie", "Walker"),
            new Employee(2, "Jack", "Daniels"),
            new Employee(3, "Jim", "Beam")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_ADMINTRAIN')")
    public List<Employee> getAllEmployess() {
        return employeesList;
    }

    // postman test purpose only
    @PostMapping
    @PreAuthorize("hasAuthority('employee:write')")
    public void addEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
    }
    // postman test purpose only
    @DeleteMapping(path = "{employeeId}")
    @PreAuthorize("hasAuthority('employee:write')")
    public void deleteEmployee(@PathVariable("employeeId") Integer employeeID) {
        System.out.println(employeeID);
    }
    // postman test purpose only
    @PutMapping(path = "{employeeId}")
    @PreAuthorize("hasAuthority('employee:write')")
    public void updateEmployee(@PathVariable("employeeId") Integer employeeID, @RequestBody Employee employee) {
        System.out.println("Update " + employee + " with ID " + employeeID);
    }

}
