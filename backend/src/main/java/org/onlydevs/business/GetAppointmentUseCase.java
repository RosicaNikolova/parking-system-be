package org.onlydevs.business;

import org.onlydevs.domain.Appointment;

import java.util.Optional;

public interface GetAppointmentUseCase {
    Optional<Appointment> getAppointmnet(Long id);
}
