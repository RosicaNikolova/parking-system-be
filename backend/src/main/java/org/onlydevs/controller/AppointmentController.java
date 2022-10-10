package org.onlydevs.controller;

import lombok.RequiredArgsConstructor;
import org.onlydevs.domain.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    //Just to test to see if the application can run springboot
    @GetMapping
    public ResponseEntity<Appointment> test()
    {
        Appointment appointment = new Appointment();
        appointment.setFirstNameVisitor("Mike");
        appointment.setLastNameVisitor("Wang");
        return ResponseEntity.ok().body(appointment);
    }
}
