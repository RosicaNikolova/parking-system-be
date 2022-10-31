package org.onlydevs.business;

import org.onlydevs.domain.Appointment;

import java.util.List;

public interface GetAppointmentsUseCase {

    List<Appointment> getAppointments();
}
