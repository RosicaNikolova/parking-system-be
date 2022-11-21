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
        Appointment savedAppointment = appointmentRepository.save(appointment);
        if(savedAppointment != null)
        {
            try {
                outlookCalendarService.createAppointment(savedAppointment);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return savedAppointment;
    }
}
