package org.onlydevs.business;

import org.onlydevs.domain.Employee;

import java.util.Optional;

public interface GetEmployeeUseCase {

    Optional<Employee> getEmployee(Long id);
}
