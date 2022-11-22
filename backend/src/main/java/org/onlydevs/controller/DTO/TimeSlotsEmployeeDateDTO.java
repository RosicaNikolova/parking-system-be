package org.onlydevs.controller.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSlotsEmployeeDateDTO {
    List<LocalTime> timeSlots;
}
