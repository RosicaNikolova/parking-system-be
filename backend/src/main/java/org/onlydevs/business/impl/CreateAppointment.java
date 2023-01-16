package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.CreateAppointmentUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.outlook.OutlookCalendarService;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAppointment implements CreateAppointmentUseCase {
    private AppointmentRepository appointmentRepository;
    @Autowired
    private OutlookCalendarService outlookCalendarService;
    @Override
    public Appointment CreateAppointment(Appointment appointment) {
        Appointment savedAppointment = null;
        try {
            appointment = Appointment.builder()
            .id(appointment.getId())
            .outlookAppointmentId(outlookCalendarService.createAppointment(appointment).getId())
            .visitor(appointment.getVisitor())
            .employee(appointment.getEmployee())
            .comesByCar(appointment.getComesByCar())
            .licensePlate(appointment.getLicensePlate())
            .dateTime(appointment.getDateTime())
                    .endTime(appointment.getEndTime())
            .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment;
    }
}
