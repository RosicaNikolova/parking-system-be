package org.onlydevs.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class Appointment {
    private Long id;
    private Visitor visitor;
    private Boolean comesByCar;
    private String licensePlate;
    private String parkingSpot;

    public Visitor getVisitor() {
        return visitor;
    }
}
