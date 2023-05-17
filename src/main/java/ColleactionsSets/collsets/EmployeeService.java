package ColleactionsSets.collsets;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
@ControllerAdvice
@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEES = 100;
    private List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Количество сотрудников максимальное");
        }

        Employee newEmployee = new Employee(firstName, lastName);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        }

        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee employeeToRemove = new Employee(firstName, lastName);

        if (!employees.remove(employeeToRemove)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }

        return employeeToRemove;
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) &&
                    employee.getLastName().equals(lastName)) {
                return employee;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    public static class EmployeeNotFoundException extends RuntimeException {
        public EmployeeNotFoundException(String message) {
            super(message);
        }
    }

    public static class EmployeeStorageIsFullException extends RuntimeException {
        public EmployeeStorageIsFullException(String message) {
            super(message);
        }
    }

    public static class EmployeeAlreadyAddedException extends RuntimeException {
        public EmployeeAlreadyAddedException(String message) {
            super(message);
        }
    }
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }
}




