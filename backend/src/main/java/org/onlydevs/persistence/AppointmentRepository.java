package org.onlydevs.persistence;

import org.onlydevs.domain.Appointment;

public interface AppointmentRepository {
    public Appointment save(Appointment appointment);
}
