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

            for (Appointment a : appointmentsForDate )
            {
                if (time.contains(a.getDateTime().toLocalTime()))
                {
                    time.remove(a.getDateTime().toLocalTime());
                }
            }


//            for (Appointment appointment : appointmentsForDate){
//                for(LocalTime timeslot: time){
//                    if((timeslot != appointment.getDateTime().toLocalTime()))
//                    {
//                        if (!(available.contains(timeslot)))
//                        {
//                            available.add(timeslot);
//                        }
//                    }
//                    //if(timeslot.getHour() != appointment.getDateTime().getHour() && timeslot.getMinute() != appointment.getDateTime().getMinute()){
//                    //}
//                }
//
//            }
            return time;

        }
    }
}
