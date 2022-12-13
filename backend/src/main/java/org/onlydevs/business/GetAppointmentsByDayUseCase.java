package org.onlydevs.business;

import org.onlydevs.domain.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface GetAppointmentsByDayUseCase {
    List<Appointment> getAppointmentsByDay(LocalDate date);

}
