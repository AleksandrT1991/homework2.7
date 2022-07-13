package pro.sky.homework27;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.homework27.exceptions.*;
import pro.sky.homework27.model.Employee;
import pro.sky.homework27.service.EmployeeService;
import pro.sky.homework27.service.ValidatorService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @ParameterizedTest
    @MethodSource("params")

    public void addNegativeTest1(String name, String surname, double salary, int department) {
        Employee expected = new Employee(name, surname, salary, department);
        assertThat(employeeService.add(name, surname, salary, department)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(name, surname, salary, department));
    }

    @ParameterizedTest
    @MethodSource("params")

    public void addNegativeTest2(String name, String surname, double salary, int department) {
        List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(employeeService.add(employee.getName(), employee.getSurname(), employee.getSalary(),
                        employee.getDepartment())).isEqualTo(employee));
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add(name, surname, salary, department));
    }

    @Test
    public void addNegativeTest3() {
        assertThatExceptionOfType(IncorrectNameException.class).isThrownBy(() -> employeeService.add("Иван#",
                "Ivanov", 55000, 1));
        assertThatExceptionOfType(IncorrectSurnameException.class).isThrownBy(() -> employeeService.add("Petr",
                "!Петров", 65000, 1));
        assertThatExceptionOfType(IncorrectNameException.class).isThrownBy(() -> employeeService.add("Мария%",
                "Иванова?", 75000, 2));
    }

    @ParameterizedTest
    @MethodSource("params")

    public void removeNegativeTest(String name, String surname, double salary, int department) {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() ->
                employeeService.remove("test", "test"));

        Employee expected = new Employee(name, surname, salary, department);
        assertThat(employeeService.add(name, surname, salary, department)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> employeeService.
                remove("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")

    public void removePositiveTest(String name, String surname, double salary, int department) {
        Employee expected = new Employee(name, surname, salary, department);
        assertThat(employeeService.add(name, surname, salary, department)).isEqualTo(expected);

        assertThat(employeeService.remove(name, surname)).isEqualTo(expected);

        assertThat(employeeService.getAll()).isEmpty();

    }

    @ParameterizedTest
    @MethodSource("params")

    public void findNegativeTest(String name, String surname, double salary, int department) {
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() ->
                employeeService.find("test", "test"));

        Employee expected = new Employee(name, surname, salary, department);
        assertThat(employeeService.add(name, surname, salary, department)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(() -> employeeService.
                find("test", "test"));
    }
    @ParameterizedTest
    @MethodSource("params")
    public void findPositiveTest (String name, String surname, double salary, int department) {
        Employee expected = new Employee(name, surname, salary, department);
        assertThat(employeeService.add(name, surname, salary, department)).isEqualTo(expected);

        assertThat(employeeService.find(name, surname)).isEqualTo(expected);

        assertThat(employeeService.getAll()).hasSize(1);
    }
    private List<Employee> generateEmployees(int size) {
        return Stream.iterate(1, i -> 1 + 1).limit(size)
                .map(i -> new Employee("Name" + (char) ((int) 'a' + i), "Surname" + (char) ((int) 'a' + i), 10000 + i,
                        i)).collect(Collectors.toList());
    }
    public static Stream<Arguments> params(){
        return Stream.of(
                Arguments.of("Ivan", "Ivanov", 55000, 1),
                Arguments.of("Petr", "Petrov", 65000, 1),
                Arguments.of("Mariya", "Ivanova", 75000, 1));

    }
}
