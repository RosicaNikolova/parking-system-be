package org.onlydevs.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.onlydevs.business.CreateAppointmentUseCase;
import org.onlydevs.controller.DTO.AppointmentDTO;
import org.onlydevs.domain.Appointment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final CreateAppointmentUseCase createAppointmentUseCase;
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody Appointment appointment)
    {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        Appointment savedAppointment = createAppointmentUseCase.CreateAppointment(appointment);
        appointmentDTO.firstNameVisitor = savedAppointment.getVisitor().getFirstName();
        appointmentDTO.lastNameVisitor = savedAppointment.getVisitor().getLastName();
        appointmentDTO.emailVisitor = savedAppointment.getVisitor().getEmail();
        appointmentDTO.firstNameEmployee = savedAppointment.getEmployee().getFirstName();
        appointmentDTO.lastNameEmployee = savedAppointment.getEmployee().getLastName();
        appointmentDTO.emailEmployee = savedAppointment.getEmployee().getEmail();
        return ResponseEntity.ok().body(appointmentDTO);
    }
}
