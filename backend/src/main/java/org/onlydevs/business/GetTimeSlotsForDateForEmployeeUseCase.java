package org.onlydevs.business;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface GetTimeSlotsForDateForEmployeeUseCase {
    List<LocalTime> timeSlotsForDate(Long employeeId, LocalDate date);
    List<LocalTime> endTimeSlots(LocalTime startTime, Long employeeId, LocalDate date);
}
