package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.UpdateAppointmentUseCase;
import org.onlydevs.domain.Appointment;
import org.onlydevs.outlook.OutlookCalendarService;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@AllArgsConstructor
public class UpdateAppointment implements UpdateAppointmentUseCase {
    private AppointmentRepository appointmentRepository;

    @Autowired
    private OutlookCalendarService outlookCalendarService;

    @Override
    public Appointment updateAppointment(Long id, LocalDateTime newDateTime , LocalTime newEndTime) {
        try {
            outlookCalendarService.editAppointment(appointmentRepository.getAppointment(id).orElse(null).getOutlookAppointmentId(), newDateTime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return appointmentRepository.update(id, newDateTime, newEndTime);
    }
}
