package org.onlydevs.persistence;

import org.onlydevs.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    List<Employee> getEmployeesByLastName(String lastName);

    List<Employee> getEmployees();
    Long createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Long employeeId);

    Optional<Employee> getEmployee(Long id);
}
