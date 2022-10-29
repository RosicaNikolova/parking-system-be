package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.CreateAppointmentUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CreateAppointment implements CreateAppointmentUseCase {
    private AppointmentRepository appointmentRepository;
    @Override
    public Appointment CreateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
}
