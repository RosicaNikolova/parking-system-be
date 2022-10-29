package org.onlydevs.controller;

import lombok.RequiredArgsConstructor;
import org.onlydevs.business.CreateAppointmentUseCase;
import org.onlydevs.business.UpdateAppointmentUseCase;
import org.onlydevs.controller.DTO.AppointmentDTO;
import org.onlydevs.domain.Appointment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final UpdateAppointmentUseCase updateAppointmentUseCase;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody Appointment meeting)
    {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
       // Appointment savedAppointment = createAppointmentUseCase.CreateAppointment(meeting);
//        appointmentDTO.firstNameVisitor = savedAppointment.getVisitor().getFirstName();
//        appointmentDTO.lastNameVisitor = savedAppointment.getVisitor().getLastName();
//        appointmentDTO.emailVisitor = savedAppointment.getVisitor().getEmail();
//        appointmentDTO.firstNameEmployee = savedAppointment.getEmployee().getFirstName();
//        appointmentDTO.lastNameEmployee = savedAppointment.getEmployee().getLastName();
//        appointmentDTO.dateTime = savedAppointment.getDateTime();
//        appointmentDTO.comesByCar = savedAppointment.getComesByCar();
        return ResponseEntity.ok().body(appointmentDTO);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        Appointment updatedAppointment = updateAppointmentUseCase.updateAppointment(appointment.getId(), appointment.getDateTime());

        appointmentDTO.firstNameVisitor = updatedAppointment.getVisitor().getFirstName();
        appointmentDTO.lastNameVisitor = updatedAppointment.getVisitor().getLastName();
        appointmentDTO.emailVisitor = updatedAppointment.getVisitor().getEmail();
        appointmentDTO.firstNameEmployee = updatedAppointment.getEmployee().getFirstName();
        appointmentDTO.lastNameEmployee = updatedAppointment.getEmployee().getLastName();
        appointmentDTO.dateTime = updatedAppointment.getDateTime();
        appointmentDTO.comesByCar = updatedAppointment.getComesByCar();

        return ResponseEntity.ok().body(appointmentDTO);
    }
}
