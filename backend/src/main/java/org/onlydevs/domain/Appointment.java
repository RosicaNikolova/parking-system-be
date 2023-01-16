package org.onlydevs.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
@AllArgsConstructor
public class Appointment {

    private Long id;
    private String outlookAppointmentId;
    private Visitor visitor;
    private Employee employee;
    private Boolean comesByCar;
    private String licensePlate;
    private LocalDateTime dateTime;
    private LocalTime endTime;

    public Visitor getVisitor() {
        return visitor;
    }
}
