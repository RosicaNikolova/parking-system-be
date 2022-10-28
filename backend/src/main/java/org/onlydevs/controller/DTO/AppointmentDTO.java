package org.onlydevs.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class AppointmentDTO {
    public String firstNameVisitor;
    public String lastNameVisitor;
    public String emailVisitor;
    public String phoneVisitor;
    public String firstNameEmployee;
    public String lastNameEmployee;
    public Boolean comesByCar;
    public LocalDateTime dateTime;

}
