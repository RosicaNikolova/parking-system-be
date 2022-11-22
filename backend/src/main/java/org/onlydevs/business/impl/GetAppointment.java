package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.GetAppointmentUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAppointment implements GetAppointmentUseCase {
    private AppointmentRepository appointmentRepository;

    @Override
    public Optional<Appointment> getAppointmnet(Long id) {
        return appointmentRepository.getAppointment(id);
    }
}
