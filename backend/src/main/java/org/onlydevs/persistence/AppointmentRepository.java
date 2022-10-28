package org.onlydevs.persistence;

import org.onlydevs.domain.Appointment;

import java.time.LocalDateTime;

public interface AppointmentRepository {
    public Appointment save(Appointment appointment);

    public Appointment update(Long id, LocalDateTime newDateTime);
}
