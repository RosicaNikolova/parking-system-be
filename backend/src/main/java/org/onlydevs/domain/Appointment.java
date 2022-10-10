package org.onlydevs.domain;

import lombok.Builder;

@Builder
public class Appointment {
    private long id;
    private Visitor visitor;
    private Boolean comesByCar;
    private String licensePlate;
    private String parkingSpot;
}
