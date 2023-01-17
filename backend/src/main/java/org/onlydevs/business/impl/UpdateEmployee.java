package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.UpdateEmployeeUseCase;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UpdateEmployee implements UpdateEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee updateEmployee(Employee employee) {
       return employeeRepository.updateEmployee(employee);
    }
}
