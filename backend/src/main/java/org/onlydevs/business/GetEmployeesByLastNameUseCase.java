package org.onlydevs.business;

import org.onlydevs.domain.Employee;

import java.util.List;

public interface GetEmployeesByLastNameUseCase {

    List<Employee> getEmployeesByLastName(String lastName);
}
