package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.GetEmployeesUseCase;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetEmployees implements GetEmployeesUseCase {

    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.getEmployees();
    }
}
