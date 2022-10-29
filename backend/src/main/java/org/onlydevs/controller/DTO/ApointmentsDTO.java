package org.onlydevs.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ApointmentsDTO {

    private List<AppointmentDTO> appointmentList;
}
