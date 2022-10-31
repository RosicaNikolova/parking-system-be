package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.GetAppointmentsUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAppointments implements GetAppointmentsUseCase {

    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAppointments() {
        return appointmentRepository.getAppointments();
    }
}
