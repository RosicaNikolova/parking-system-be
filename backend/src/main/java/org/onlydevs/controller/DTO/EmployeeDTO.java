package org.onlydevs.controller.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
}
