package org.onlydevs.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    public Long id;
    public String firstNameVisitor;
    public String lastNameVisitor;
    public String emailVisitor;

    public String phoneVisitor;
    public String firstNameEmployee;
    public String lastNameEmployee;
    public Boolean comesByCar;
    public LocalDateTime dateTime;

}
