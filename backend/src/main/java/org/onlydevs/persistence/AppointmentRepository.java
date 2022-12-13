package org.onlydevs.persistence;

import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);

    Appointment update(Long id, LocalDateTime newDateTime);
    List<Appointment> getAppointments();

    Optional<Appointment> getAppointment(Long id);

    void delete(Long id);

    List<Appointment> getAppointmentsForDateForEmployee(Long employeeId, LocalDate date);

}
