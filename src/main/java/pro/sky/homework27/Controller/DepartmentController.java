package pro.sky.homework27.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.homework27.model.Employee;
import pro.sky.homework27.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee employeeWithMaxSalaryFromDepartment(@RequestParam("departmentId") int department) {
        return departmentService.employeeWithMaxSalary(department);
    }
    @GetMapping("/min-salary")
    public Employee employeeWithMinSalaryFromDepartment(@RequestParam("departmentId") int department) {
        return departmentService.employeeWithMinSalary(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List <Employee> employeesFromDepartment(@RequestParam("departmentId") int department) {
        return departmentService.employeesFromDepartment(department);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> employeesGroupeDepartment(@RequestParam("departmentId") int department) {
    return departmentService.employeesGroupeDepartment();
    }
}

