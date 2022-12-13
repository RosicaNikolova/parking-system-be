package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.GetEmployeesByLastNameUseCase;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.AppointmentRepository;
import org.onlydevs.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetEmployeesByLastName implements GetEmployeesByLastNameUseCase {

    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployeesByLastName(String lastName) {
        return employeeRepository.getEmployeesByLastName(lastName);
    }
}
