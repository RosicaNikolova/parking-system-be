package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.GetAppointmentsUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.outlook.OutlookCalendarService;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAppointments implements GetAppointmentsUseCase {

    private AppointmentRepository appointmentRepository;
    @Autowired
    private OutlookCalendarService outlookCalendarService;

    @Override
    public List<Appointment> getAppointments() {
        try {
            outlookCalendarService.getAppointments();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return appointmentRepository.getAppointments();
    }
}
