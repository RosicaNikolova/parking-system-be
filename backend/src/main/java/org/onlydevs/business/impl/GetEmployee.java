package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.GetEmployeeUseCase;
import org.onlydevs.controller.DTO.EmployeeDTO;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetEmployee implements GetEmployeeUseCase {

    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.getEmployee(id);
    }
}
