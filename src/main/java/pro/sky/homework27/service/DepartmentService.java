package pro.sky.homework27.service;

import org.springframework.stereotype.Service;
import pro.sky.homework27.exceptions.EmployeeNotFoundException;
import pro.sky.homework27.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public record DepartmentService(EmployeeService employeeService) {

    public Employee findEmployeeWithMaxSalaryFromDepartment(int department) {
        return employeeService.getAll().stream().filter(employee -> employee.getDepartmentId() == department)
                .max(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee findEmployeeWithMinSalaryFromDepartment(int departmentId) {
        return employeeService.getAll().stream().filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary)).
                orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findEmployeesFromDepartment(int departmentId) {
        return employeeService.getAll().stream().filter(employee -> employee.getDepartmentId() == departmentId).
                collect(Collectors.toList());

    }

    public Map<Integer, List<Employee>> findEmployees() {
        return employeeService.getAll().stream().collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
