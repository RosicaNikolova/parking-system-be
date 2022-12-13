package org.onlydevs.persistence;

import org.onlydevs.domain.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getEmployeesByLastName(String lastName);
}
