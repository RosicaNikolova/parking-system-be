package org.onlydevs.persistence.impl;

import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Visitor;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FakeAppointmentRepository implements AppointmentRepository {
    private final List<Appointment> appointments;
    private Long idIncrementor;

    public FakeAppointmentRepository()
    {
        appointments = new ArrayList<>();
        appointments.add(Appointment.builder().id(1l).firstNameEmployee("EmployeeFirstName1").lastNameEmployee("EmployeeLastName1")
                .visitor(
                        Visitor.builder().firstName("Visitor1FirstName").lastName("Visitor1LastName").email("Visitor1@outlook.com").phoneNumber("0612345678").build())
                        .comesByCar(false).licensePlate("XX-999-999").dateTime(LocalDateTime.parse("2022-02-17T18:30:00")).build());
        appointments.add(Appointment.builder().id(2l).firstNameEmployee("EmployeeFirstName2").lastNameEmployee("EmployeeLastName2")
                .visitor(
                        Visitor.builder().firstName("Visitor2FirstName").lastName("Visitor2LastName").email("Visitor2@outlook.com").phoneNumber("0687654321").build())
                        .comesByCar(false).licensePlate("99-XXX-XXX").dateTime(LocalDateTime.parse("2022-02-17T18:30:00")).build());
        idIncrementor = appointments.stream().count()+1;
    }

    public Appointment save(Appointment appointment)
    {
        Appointment toAdd = null;
        if (appointment.getId() == null || appointment.getId() == 0l)
        {
            toAdd = Appointment.builder()
                    .id(idIncrementor)
                    .visitor(appointment.getVisitor())
                    .firstNameEmployee(appointment.getFirstNameEmployee())
                    .firstNameEmployee(appointment.getLastNameEmployee())
                    .comesByCar(appointment.getComesByCar())
                    .licensePlate(appointment.getLicensePlate())
                    .dateTime(appointment.getDateTime())
                    .build();
            appointments.add(toAdd);
        }
        return toAdd;
    }

}
