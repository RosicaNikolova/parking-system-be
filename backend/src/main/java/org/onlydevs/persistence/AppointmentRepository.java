package org.onlydevs.persistence;

import org.onlydevs.domain.Appointment;

import java.time.LocalDateTime;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    Appointment update(Long id, LocalDateTime newDateTime);

    void delete(Long id);
}
