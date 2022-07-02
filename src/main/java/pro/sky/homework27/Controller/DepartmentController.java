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
@RequestMapping ("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalaryFromDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.findEmployeeWithMaxSalaryFromDepartment(departmentId);
    }
    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalaryFromDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.findEmployeeWithMinSalaryFromDepartment(departmentId);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List <Employee> findEmployeesFromDepartment(@RequestParam("departmentId") int departmentId) {
        return departmentService.findEmployeesFromDepartment(departmentId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> findEmployees(@RequestParam("departmentId") int departmentId) {
    return departmentService.findEmployees();
    }
}

