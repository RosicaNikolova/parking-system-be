package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.GetAppointmentsByDayUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAppointmentsByDayImpl implements GetAppointmentsByDayUseCase {

    private final AppointmentRepository appointmentRepository;
    @Override
    public List<Appointment> getAppointmentsByDay(LocalDate date) {
        return appointmentRepository.getAppointmentsByDay(date);
    }
}
