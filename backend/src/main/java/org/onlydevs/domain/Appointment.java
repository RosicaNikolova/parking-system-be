package org.onlydevs.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Appointment {
    private Long id;
    private Visitor visitor;
    private Boolean comesByCar;
    private String licensePlate;
    private String parkingSpot;
}
