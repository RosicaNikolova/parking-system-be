package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.UpdateAppointmentUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UpdateAppointment implements UpdateAppointmentUseCase {
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment updateAppointment(Long id, LocalDateTime newDateTime) {
        return appointmentRepository.update(id, newDateTime);
    }
}
