package org.onlydevs.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class Appointment {
    private Long id;
    private Visitor visitor;
    private Employee employee;
    private Boolean comesByCar;
    private String licensePlate;

    public Visitor getVisitor() {
        return visitor;
    }
}
