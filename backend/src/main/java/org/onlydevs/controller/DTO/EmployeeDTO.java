package org.onlydevs.controller.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    public Long id;
    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;
    @NotBlank
    public String email;
}
