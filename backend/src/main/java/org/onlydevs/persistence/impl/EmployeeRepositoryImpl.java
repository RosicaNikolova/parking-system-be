package org.onlydevs.persistence.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.EmployeeRepository;
import org.onlydevs.persistence.EmployeeRepositoryJPA;
import org.onlydevs.persistence.converter.EmployeeConverter;
import org.onlydevs.persistence.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Employee> getEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(employeeEntity -> EmployeeConverter.convertToModel(employeeEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Long createEmployee(Employee employee) {
            EmployeeEntity employeeEntity = EmployeeEntity.builder()
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .build();
            return employeeRepository.save(employeeEntity).getId();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
        EmployeeEntity employeeUpdated = employeeRepository.save(employeeEntity);
        return EmployeeConverter.convertToModel(employeeUpdated);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        Optional<EmployeeEntity> entity = employeeRepository.findById(id);
        Employee employee = Employee.builder()
                .id(entity.get().getId())
                .email(entity.get().getEmail())
                .lastName(entity.get().getLastName())
                .firstName(entity.get().getFirstName())
                .build();
        return  Optional.of(employee);
    }
}
