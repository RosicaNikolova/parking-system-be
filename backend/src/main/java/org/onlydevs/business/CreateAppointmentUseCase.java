package org.onlydevs.business;

import org.onlydevs.domain.Appointment;

public interface CreateAppointmentUseCase {
    Appointment CreateAppointment(Appointment appointment);
}
