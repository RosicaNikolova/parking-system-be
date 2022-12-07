package org.onlydevs.persistence.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.onlydevs.domain.Appointment;
import org.onlydevs.persistence.AppointmentRepository;
import org.onlydevs.persistence.AppointmentRepositoryJPA;
import org.onlydevs.persistence.converter.AppointmentConverter;
import org.onlydevs.persistence.entity.AppointmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {
    @Autowired
    private final AppointmentRepositoryJPA appointmentRepositoryJPA;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity appointmentEntity = AppointmentConverter.convertToEntity(appointment);
        AppointmentEntity savedAppointmentEntity = appointmentRepositoryJPA.save(appointmentEntity);
        Appointment savedAppointment = AppointmentConverter.convertToModel(savedAppointmentEntity);
        return savedAppointment;
    }

    @Override
    public Appointment update(Long id, LocalDateTime newDateTime) {
        return null;
    }

    @Override
    public List<Appointment> getAppointments() {
        return null;
    }

    @Override
    public Optional<Appointment> getAppointment(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Appointment> getAppointmentsForDateForEmployee(Long employeeId, LocalDate date) {
        return null;
    }
}
