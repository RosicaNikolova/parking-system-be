package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.DeleteAppointmentUseCase;
import org.onlydevs.outlook.OutlookCalendarService;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class DeleteAppointment implements DeleteAppointmentUseCase {
    private AppointmentRepository appointmentRepository;

    @Autowired
    private OutlookCalendarService outlookCalendarService;

    @Override
    public void deleteAppointment(Long id) {
        String outlookId = appointmentRepository.getAppointment(id).orElse(null).getOutlookAppointmentId();
        appointmentRepository.delete(id);
        try {
            outlookCalendarService.deleteAppointment(outlookId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
