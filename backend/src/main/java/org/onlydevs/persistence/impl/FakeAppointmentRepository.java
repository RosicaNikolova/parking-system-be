package org.onlydevs.persistence.impl;

import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Visitor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeAppointmentRepository {
    private final List<Appointment> appointments;
    private Long idIncrementor;

    public FakeAppointmentRepository()
    {
        appointments = new ArrayList<>();
        appointments.add(Appointment.builder().id(1l)
                .visitor(
                        Visitor.builder().firstName("Person1FirstName").lastName("Person1LastName").email("person1@outlook.com").phoneNumber("0612345678").build()
                ).comesByCar(false).licensePlate("XX-999-999").parkingSpot("A1").build());
        appointments.add(Appointment.builder().id(2l)
                .visitor(
                        Visitor.builder().firstName("Person2FirstName").lastName("Person2LastName").email("person2@outlook.com").phoneNumber("0687654321").build()
                ).comesByCar(false).licensePlate("99-XXX-XXX").parkingSpot("A2").build());
        idIncrementor = appointments.stream().count()+1;
    }
}
