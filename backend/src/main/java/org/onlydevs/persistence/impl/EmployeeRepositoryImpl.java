package org.onlydevs.persistence.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.EmployeeRepository;
import org.onlydevs.persistence.EmployeeRepositoryJPA;
import org.onlydevs.persistence.converter.EmployeeConverter;
import org.onlydevs.persistence.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final EmployeeRepositoryJPA employeeRepository;
    @Override
    public List<Employee> getEmployeesByLastName(String lastName) {
        List<Employee> employees = new ArrayList<>();
        ModelMapper m = new ModelMapper();
        for (EmployeeEntity e : employeeRepository.findAll()) {
            if(e.getLastName().toLowerCase().contains(lastName.toLowerCase())){
                employees.add(EmployeeConverter.convertToModel(e));
            }
        }
        return employees;
    }
}
