package org.onlydevs.controller;

import lombok.RequiredArgsConstructor;
import org.onlydevs.business.*;
import org.onlydevs.controller.DTO.*;
import org.onlydevs.controller.converters.EmployeeConverterDTO;
import org.onlydevs.domain.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

  private final GetEmployeesUseCase getEmployeesUseCase;
  private final CreateEmployeeUseCase createEmployeeUseCase;
  private final UpdateEmployeeUseCase updateEmployeeUseCase;
  private final DeleteEmployeeUseCase deleteEmployeeUseCase;

    //@IsAuthenticated
    //@RolesAllowed("ROLE_admin")
    private final GetEmployeesByLastNameUseCase getEmployeesByLastNameUseCase;
    private final EmployeeConverterDTO employeeConverterDTO;

    @PostMapping()
    public Long createEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        Employee employee = employeeConverterDTO.convertToEmployee(employeeDTO);
        return createEmployeeUseCase.createEmployee(employee);
    }

    //@IsAuthenticated
    //@RolesAllowed("ROLE_admin")
    @PutMapping()
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {

        Employee employee = employeeConverterDTO.convertToEmployee(employeeDTO);
        Employee employeeUpdated = updateEmployeeUseCase.updateEmployee(employee);
        EmployeeDTO employeeUpdatedDTO = employeeConverterDTO.covertEmployeeToDTO(employeeUpdated);
        return ResponseEntity.ok().body(employeeUpdatedDTO);
    }

//    @IsAuthenticated
//    @RolesAllowed("ROLE_admin")
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>>getEmployees(){

        List<Employee> employees = getEmployeesUseCase.getEmployees();
        if (employees.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            List<EmployeeDTO> employeeDTOList = new ArrayList<>(employees
                    .stream()
                    .map(employee -> employeeConverterDTO.covertEmployeeToDTO(employee))
                    .collect(Collectors.toList()));
            return ResponseEntity.ok(employeeDTOList);
        }
    }

    //@IsAuthenticated
    //@RolesAllowed("ROLE_admin")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable final Long id) {
        deleteEmployeeUseCase.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
