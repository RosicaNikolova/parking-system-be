package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.CreateEmployeeUseCase;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEmployee implements CreateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    @Override
    public Long createEmployee(Employee employee) {
       return employeeRepository.createEmployee(employee);
    }
}
