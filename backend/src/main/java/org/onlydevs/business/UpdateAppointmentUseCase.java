package org.onlydevs.business;

import org.onlydevs.domain.Appointment;

import java.time.LocalDateTime;
import java.time.LocalTime;

public interface UpdateAppointmentUseCase {
    Appointment updateAppointment(Long id, LocalDateTime newDateTime, LocalTime newEndTime);
}
