package org.onlydevs.domain;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Appointment {

    private Long id;
    private Visitor visitor;
    private Employee employee;
    private Boolean comesByCar;
    private String licensePlate;
    private LocalDateTime dateTime;

    public Visitor getVisitor() {
        return visitor;
    }
}
