package org.onlydevs.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.onlydevs.controller.DTO.AppointmentDTO;
import org.onlydevs.domain.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    @PostMapping
    public ResponseEntity<AppointmentDTO> createAppointment(Appointment appointment)
    {
        return null;
    }
}
