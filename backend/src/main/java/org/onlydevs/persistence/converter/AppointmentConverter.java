package org.onlydevs.persistence.converter;

import org.modelmapper.ModelMapper;
import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Employee;
import org.onlydevs.domain.Visitor;
import org.onlydevs.persistence.entity.AppointmentEntity;
import org.onlydevs.persistence.entity.EmployeeEntity;

public class AppointmentConverter {
    public static AppointmentEntity convertToEntity(Appointment appointment)
    {
        ModelMapper m = new ModelMapper();
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(appointment.getId())
                .outlookAppointmentId(appointment.getOutlookAppointmentId())
                .dateTime(appointment.getDateTime())
                .endTime(appointment.getEndTime())
                .licensePlate(appointment.getLicensePlate())
                .comesByCar(appointment.getComesByCar())

                .firstName(appointment.getVisitor().getFirstName())
                .lastName(appointment.getVisitor().getLastName())
                .email(appointment.getVisitor().getEmail())
                .phoneNumber(appointment.getVisitor().getPhoneNumber())

                .employee(m.map(appointment.getEmployee(), EmployeeEntity.class))
                .build();
        return appointmentEntity;
    }
    public static Appointment convertToModel(AppointmentEntity appointmentEntity)
    {
        ModelMapper m = new ModelMapper();
        Appointment appointment = Appointment.builder()
                .id(appointmentEntity.getId())
                .outlookAppointmentId(appointmentEntity.getOutlookAppointmentId())
                .dateTime(appointmentEntity.getDateTime())
                .endTime(appointmentEntity.getEndTime())
                .licensePlate(appointmentEntity.getLicensePlate())
                .comesByCar(appointmentEntity.getComesByCar())

//                .visitor(m.map(appointmentEntity, Visitor.class))
                .visitor(Visitor.builder()
                        .firstName(appointmentEntity.getFirstName())
                        .lastName(appointmentEntity.getLastName())
                        .email(appointmentEntity.getEmail())
                        .phoneNumber(appointmentEntity.getPhoneNumber())
                        .build())
                .employee(Employee.builder()
                        .id(appointmentEntity.getEmployee().getId())
                        .firstName(appointmentEntity.getEmployee().getFirstName())
                        .lastName(appointmentEntity.getEmployee().getLastName())
                        .email(appointmentEntity.getEmployee().getEmail())
                        .build())
                .build();
        return appointment;
    }
}
