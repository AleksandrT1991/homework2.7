package pro.sky.homework27.service;

import org.springframework.stereotype.Service;
import pro.sky.homework27.exceptions.EmployeeNotFoundException;
import pro.sky.homework27.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
    public class DepartmentService {

    private final EmployeeService employeeService;

    private DepartmentService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    public Employee employeeWithMaxSalary(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee employeeWithMinSalary(int departmentId) {
        return employeeService.getAll().stream().filter(employee -> employee.getDepartment() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> employeesFromDepartment(int departmentId) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartment() == departmentId).
                collect(Collectors.toList());

    }

    public Map<Integer, List<Employee>> employeesGroupeDepartment() {
        return employeeService.getAll().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
