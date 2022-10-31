package org.onlydevs.controller.converters;

import lombok.NoArgsConstructor;
import org.onlydevs.controller.DTO.AppointmentDTO;
import org.onlydevs.domain.Appointment;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public final class AppointmentConverterDTO {

    public AppointmentDTO convertToDTO (Appointment appointment){

        return AppointmentDTO.builder()
                .firstNameVisitor(appointment.getVisitor().getFirstName())
                .lastNameVisitor(appointment.getVisitor().getLastName())
                .firstNameEmployee(appointment.getEmployee().getFirstName())
                .lastNameEmployee(appointment.getEmployee().getLastName())
                .dateTime(appointment.getDateTime())
                .emailVisitor(appointment.getVisitor().getEmail())
                .phoneVisitor(appointment.getVisitor().getPhoneNumber())
                .comesByCar(appointment.getComesByCar())
                .build();
    }

    public Appointment convertToAppointments (AppointmentDTO dto){

        return null;

    }

}
