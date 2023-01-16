package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.DeleteEmployeeUseCase;
import org.onlydevs.persistence.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteEmployee implements DeleteEmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteEmployee(employeeId);
    }
}
