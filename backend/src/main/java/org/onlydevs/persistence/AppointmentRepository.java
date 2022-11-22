package org.onlydevs.persistence;

import org.onlydevs.domain.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    public Appointment update(Long id, LocalDateTime newDateTime);
    public List<Appointment> getAppointments();

    Optional<Appointment> getAppointment(Long id);

    void delete(Long id);

    List<Appointment> getAppointmentsForDateForEmployee(Long employeeId, LocalDate date);
}
