package org.onlydevs.business;

import org.onlydevs.domain.Appointment;

import java.time.LocalDateTime;

public interface CreateAppointmentUseCase {
    Appointment CreateAppointment(Appointment appointment);
}
