package org.onlydevs.persistence.converter;

import org.modelmapper.ModelMapper;
import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.entity.AppointmentEntity;
import org.onlydevs.persistence.entity.EmployeeEntity;

public class EmployeeConverter {
    public static Employee convertToModel(EmployeeEntity employeeEntity)
    {
        Employee employee = Employee.builder()
                        .id(employeeEntity.getId())
                        .firstName(employeeEntity.getFirstName())
                        .lastName(employeeEntity.getLastName())
                        .email(employeeEntity.getEmail())
                        .build();
        return employee;
    }

}
