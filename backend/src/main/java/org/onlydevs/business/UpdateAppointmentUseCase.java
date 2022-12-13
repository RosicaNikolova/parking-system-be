package org.onlydevs.business;

import org.onlydevs.domain.Appointment;

import java.time.LocalDateTime;

public interface UpdateAppointmentUseCase {
    Appointment updateAppointment(Long id, LocalDateTime newDateTime, String licensePlate);
}
