package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.DeleteAppointmentUseCase;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteAppointment implements DeleteAppointmentUseCase {
    private AppointmentRepository appointmentRepository;

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.delete(id);
    }
}
