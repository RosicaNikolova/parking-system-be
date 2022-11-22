package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.domain.Appointment;
import org.onlydevs.persistence.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetTimeSlotsForDateForEmployeeUseCase implements org.onlydevs.business.GetTimeSlotsForDateForEmployeeUseCase {

    AppointmentRepository appointmentRepository;

    @Override
    public List<LocalTime> timeSlotsForDate(Long employeeId, LocalDate date) {
        LocalTime startTime = LocalTime.of(9, 0);

        List<LocalTime>time = new ArrayList<>();
        for(int i=0; i<16; i++){
            time.add(startTime);
            startTime=startTime.plusMinutes(30);
        }

        List<Appointment> appointmentsForDate = appointmentRepository.getAppointmentsForDateForEmployee(employeeId, date);
        if(appointmentsForDate.isEmpty()){
            return time;
        }
        else {

//            List<LocalTime> availableTimeSlots =
//                    time.stream()
//                            .filter(
//                                    appointment -> appointmentsForDate.stream()
//                                            .anyMatch(meeting -> meeting.getDateTime().getHour() != appointment.getHour() || meeting.getDateTime().getMinute() != appointment.getMinute()))
//                            .collect(Collectors.toList());
//           // return availableTimeSlots;

            List <LocalTime> available = new ArrayList<>();

            for(LocalTime timeslot: time){
                for (Appointment appointment : appointmentsForDate){
                    if(timeslot.getHour() != appointment.getDateTime().getHour() && timeslot.getMinute() != appointment.getDateTime().getMinute()){
                        available.add(timeslot);
                    }
                }

            }
            return available;

        }
    }
}
