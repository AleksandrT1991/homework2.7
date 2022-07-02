package pro.sky.homework27.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework27.model.Employee;
import pro.sky.homework27.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam ("name") String name,
                        @RequestParam ("surname") String surname,
                        @RequestParam ("salary") double salary,
                        @RequestParam ("departmentId") int departmentId) {
        return employeeService.add(name, surname, salary, departmentId);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam ("name") String name,
                        @RequestParam ("surname") String surname) {
        return employeeService.remove(name, surname);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam ("name") String name,
                        @RequestParam ("surname") String surname)
                          {
        return employeeService.find(name, surname);
    }
    @GetMapping("/")
    public List<Employee> getAll() {
        return employeeService.getAll();

    }


}
