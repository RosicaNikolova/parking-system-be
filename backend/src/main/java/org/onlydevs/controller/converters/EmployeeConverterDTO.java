package org.onlydevs.controller.converters;

import lombok.NoArgsConstructor;
import org.onlydevs.controller.DTO.EmployeeDTO;
import org.onlydevs.domain.Employee;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class EmployeeConverterDTO {

    public EmployeeDTO covertEmployeeToDTO(Employee employee){
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }
}
