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
//
//            LocalTime start = startime;
//            List<LocalTime> times = new ArrayList<>();
//            for(int i = 0; i<4; i++){
//                times.add(start);
//                start = start.plusMinutes(duration);
//            }

 //           old code
        LocalTime startTime = LocalTime.of(9, 0);

        List<LocalTime>time = new ArrayList<>();
        for(int i=0; i<32; i++){
            time.add(startTime);
            startTime=startTime.plusMinutes(15);
        }

        List<Appointment> appointmentsForDate = appointmentRepository.getAppointmentsForDateForEmployee(employeeId, date);
//
//        List<LocalTime> takenTimeslots = new ArrayList<>();
//        for(Appointment a: appointmentsForDate){
//            LocalTime takenTime = a.getDateTime().toLocalTime();
//            LocalTime endTime = a.getEndTime();
//            takenTimeslots.add(takenTime);
//            for (int i =0; i< 3; i++){
//                takenTime = takenTime.plusMinutes(15);
//                if(takenTime.equals(endTime)){
//                    break;
//                }
//                    takenTimeslots.add(takenTime);
//            }
//        }
//        System.out.println("taken: " + takenTimeslots);


        List<LocalTime> takenTimeSlots = getTakenTimeSlots(appointmentsForDate);

        if(takenTimeSlots.isEmpty()){
            return time;
        }
        else {

            for (LocalTime takenTimeSlot : takenTimeSlots )
            {
                if (time.contains(takenTimeSlot))
                {
                    time.remove(takenTimeSlot);
                }
            }
            return time;
        }
    }

    @Override
    public List<LocalTime> endTimeSlots(LocalTime startTime, Long employeeId, LocalDate date) {
        List<Appointment> appointmentsForDate = appointmentRepository.getAppointmentsForDateForEmployee(employeeId, date);
        List<LocalTime> taken = getTakenTimeSlots(appointmentsForDate);

        List<LocalTime> possible = new ArrayList<>();
        for (int i = 0; i<4; i++){
            startTime = startTime.plusMinutes(15);
            possible.add(startTime);
        }
        if(taken.isEmpty()){
            return possible;
        }
        else {
            System.out.println("Taken: " + taken);
            taken.remove(0);
            System.out.println("Taken: " + taken);
            for (LocalTime takenTimeSlot : taken) {
                if (possible.contains(takenTimeSlot)) {
                    possible.remove(takenTimeSlot);
                }
            }

            int lastIndex = taken.size() - 1;
            if(lastIndex <0) {
                return possible;
            }
                LocalTime lastTakenTime = taken.get(lastIndex);
                List<LocalTime> finalEndTimes = new ArrayList<>();
                if (possible.size() != 4) {
                    for (LocalTime time : possible) {
                        if (time.isAfter(lastTakenTime)) {

                        } else {
                            finalEndTimes.add(time);
                        }
                    }
                } else {
                    finalEndTimes = possible;
                }
                System.out.println("Taken: " + taken);

                System.out.println("New times: " + finalEndTimes);

                System.out.println("Possible: " + possible);
                return finalEndTimes;
            }



    }

    private List<LocalTime> getTakenTimeSlots( List<Appointment> appointmentsForDate){
        List<LocalTime> takenTimeslots = new ArrayList<>();
        for(Appointment a: appointmentsForDate){
            LocalTime takenTime = a.getDateTime().toLocalTime();
            LocalTime endTime = a.getEndTime();
            takenTimeslots.add(takenTime);
            for (int i =0; i< 3; i++){
                takenTime = takenTime.plusMinutes(15);
                if(takenTime.equals(endTime)){
                    break;
                }
                takenTimeslots.add(takenTime);
            }
        }
        return takenTimeslots;
    }
}
