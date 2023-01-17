package org.onlydevs.business;

import org.onlydevs.domain.Employee;

import java.util.List;

public interface GetEmployeesUseCase {
    List<Employee> getEmployees();
}
