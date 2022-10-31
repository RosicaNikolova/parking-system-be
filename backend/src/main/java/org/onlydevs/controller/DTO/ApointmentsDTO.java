package org.onlydevs.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApointmentsDTO {

    public List<AppointmentDTO> appointmentList;
}
