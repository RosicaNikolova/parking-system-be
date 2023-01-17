package org.onlydevs.persistence.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Employee;
import org.onlydevs.persistence.AppointmentRepository;
import org.onlydevs.persistence.AppointmentRepositoryJPA;
import org.onlydevs.persistence.converter.AppointmentConverter;
import org.onlydevs.persistence.entity.AppointmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public Appointment update(Long id, LocalDateTime newDateTime, LocalTime newEndTime) {
        AppointmentEntity appointmentEntity = appointmentRepositoryJPA.findById(id).orElse(null);
        AppointmentEntity newAppointmentEntity = AppointmentEntity.builder()
                .id(appointmentEntity.getId())
                .outlookAppointmentId(appointmentEntity.getOutlookAppointmentId())
                .email(appointmentEntity.getEmail())
                .phoneNumber(appointmentEntity.getPhoneNumber())
                .dateTime(newDateTime)
                .endTime(newEndTime)
                .employee(appointmentEntity.getEmployee())
                .lastName(appointmentEntity.getLastName())
                .firstName(appointmentEntity.getFirstName())
                .comesByCar(appointmentEntity.getComesByCar())
                .licensePlate(appointmentEntity.getLicensePlate())
                .build();
        return AppointmentConverter.convertToModel(appointmentRepositoryJPA.save(newAppointmentEntity));
    }

    @Override
    public List<Appointment> getAppointments() {
        List<AppointmentEntity> appointmentEntities = appointmentRepositoryJPA.findAll();
        List<Appointment> appointments = new ArrayList<>();
        for(AppointmentEntity e : appointmentEntities)
        {
            Appointment appointment = AppointmentConverter.convertToModel(e);
            appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public Optional<Appointment> getAppointment(Long id) {
        AppointmentEntity ae = appointmentRepositoryJPA.findById(id).get();
        Optional<Appointment> appointment = Optional.of(AppointmentConverter.convertToModel(ae));
        return appointment;
    }

    @Override
    public void delete(Long id) {
        appointmentRepositoryJPA.deleteById(id);
    }

    @Override
    public List<Appointment> getAppointmentsForDateForEmployee(Long employeeId, LocalDate date) {
        List<AppointmentEntity> appointmentEntities = appointmentRepositoryJPA.findAll();
        List<Appointment> appointments = new ArrayList<>();
        for(AppointmentEntity e : appointmentEntities)
        {
            if(e.getEmployee().getId() == employeeId) {
                if (e.getDateTime().toLocalDate().equals(date)) {
                    Appointment appointment = AppointmentConverter.convertToModel(e);
                    appointments.add(appointment);
                }
            }
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsByDay(LocalDate date) {
        List<AppointmentEntity> appointmentEntities = appointmentRepositoryJPA.findAll();
        List<Appointment> appointments = new ArrayList<>();
        for(AppointmentEntity e : appointmentEntities)
        {
            if (e.getDateTime().toLocalDate().equals(date)) {
                Appointment appointment = AppointmentConverter.convertToModel(e);
                appointments.add(appointment);
            }
        }
        return appointments;
    }

}
