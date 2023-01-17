package org.onlydevs.controller;

import lombok.RequiredArgsConstructor;
import org.onlydevs.business.*;
import org.onlydevs.controller.DTO.ApointmentsDTO;
import org.onlydevs.controller.DTO.AppointmentDTO;
import org.onlydevs.controller.DTO.EmployeesByLastNameDTO;
import org.onlydevs.controller.DTO.TimeSlotsEmployeeDateDTO;
import org.onlydevs.controller.converters.AppointmentConverterDTO;
import org.onlydevs.controller.converters.EmployeeConverterDTO;
import org.onlydevs.domain.Appointment;
import org.onlydevs.outlook.MailContent;
import org.onlydevs.outlook.OutlookCalendarService;
import org.onlydevs.persistence.AppointmentRepository;
import org.onlydevs.domain.Employee;
import org.onlydevs.security.isauthenticated.IsAuthenticated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final CreateAppointmentUseCase createAppointmentUseCase;

    private final UpdateAppointmentUseCase updateAppointmentUseCase;
    private final AppointmentConverterDTO appointmentConverterDTO;
    private final GetAppointmentsUseCase getAppointmentsUseCase;

    private final GetAppointmentUseCase getAppointmentUseCase;

    private final DeleteAppointmentUseCase deleteAppointmentUseCase;
    private final GetTimeSlotsForDateForEmployeeUseCase getTimeSlotsForDateForEmployeeUseCase;
    private final GetAppointmentsByDayUseCase getAppointmentsByDay;

    //@IsAuthenticated
    //@RolesAllowed("ROLE_secretary")
    private final GetEmployeesByLastNameUseCase getEmployeesByLastNameUseCase;

    private final EmployeeConverterDTO employeeConverterDTO;

//    @IsAuthenticated
//    @RolesAllowed({"ROLE_SECRETARY"})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody Appointment meeting)
    {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        Appointment savedAppointment = createAppointmentUseCase.CreateAppointment(meeting);
        appointmentDTO.firstNameVisitor = savedAppointment.getVisitor().getFirstName();
        appointmentDTO.lastNameVisitor = savedAppointment.getVisitor().getLastName();
        appointmentDTO.emailVisitor = savedAppointment.getVisitor().getEmail();
        appointmentDTO.phoneVisitor = savedAppointment.getVisitor().getPhoneNumber();
        appointmentDTO.firstNameEmployee = savedAppointment.getEmployee().getFirstName();
        appointmentDTO.lastNameEmployee = savedAppointment.getEmployee().getLastName();
        appointmentDTO.dateTime = savedAppointment.getDateTime();
        appointmentDTO.endTime = savedAppointment.getEndTime();
        appointmentDTO.comesByCar = savedAppointment.getComesByCar();
        return ResponseEntity.ok().body(appointmentDTO);
    }

    //@IsAuthenticated
    //@RolesAllowed("ROLE_secretary")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDTO> updateAppointment(@RequestBody Appointment appointment) {
        AppointmentDTO appointmentDTO = new AppointmentDTO();
        Appointment updatedAppointment = updateAppointmentUseCase.updateAppointment(appointment.getId(), appointment.getDateTime(), appointment.getEndTime());

        appointmentDTO.firstNameVisitor = updatedAppointment.getVisitor().getFirstName();
        appointmentDTO.lastNameVisitor = updatedAppointment.getVisitor().getLastName();
        appointmentDTO.emailVisitor = updatedAppointment.getVisitor().getEmail();
        appointmentDTO.phoneVisitor = updatedAppointment.getVisitor().getPhoneNumber();
        appointmentDTO.firstNameEmployee = updatedAppointment.getEmployee().getFirstName();
        appointmentDTO.lastNameEmployee = updatedAppointment.getEmployee().getLastName();
        appointmentDTO.dateTime = updatedAppointment.getDateTime();
        appointmentDTO.endTime = updatedAppointment.getEndTime();
        appointmentDTO.comesByCar = updatedAppointment.getComesByCar();

        return ResponseEntity.ok().body(appointmentDTO);
    }

//    @IsAuthenticated
//    @RolesAllowed("ROLE_secretary")
    @CrossOrigin
    @GetMapping("/appointments")
    public ResponseEntity<ApointmentsDTO> getAppointment(){

        List<Appointment> appointments= getAppointmentsUseCase.getAppointments();
        if (appointments.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            ApointmentsDTO response = new ApointmentsDTO(appointments.stream()
                            .map(appointment -> appointmentConverterDTO.convertToDTO(appointment))
                            .toList());
            return ResponseEntity.ok(response);
        }

    }

    @CrossOrigin
    @GetMapping(value = "/appointments/{year}/{month}/{day}")
    public ResponseEntity<ApointmentsDTO> getAppointmentByDay(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day){
        LocalDate date = LocalDate.of(year, month, day);
        List<Appointment> appointments= getAppointmentsByDay.getAppointmentsByDay(date);
        if (appointments.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            ApointmentsDTO response = new ApointmentsDTO(appointments.stream()
                    .map(appointment -> appointmentConverterDTO.convertToDTO(appointment))
                    .toList());
            return ResponseEntity.ok(response);
        }

    }

    //@IsAuthenticated
    //@RolesAllowed("ROLE_secretary")
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDTO> getAppointment(@PathVariable(value = "id") final long id) {

        final Optional<Appointment> appointmentOptional = getAppointmentUseCase.getAppointmnet(id);

        if (appointmentOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Optional<AppointmentDTO> appointmentDTO = Optional.of(appointmentConverterDTO.convertToDTO(appointmentOptional.get()));
            return ResponseEntity.ok().body(appointmentDTO.get());
        }
    }

    //@IsAuthenticated
    //@RolesAllowed("ROLE_secretary")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable final Long id) {
        deleteAppointmentUseCase.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }

    //@IsAuthenticated
    //@RolesAllowed("ROLE_secretary")
    @GetMapping()
    @ResponseBody
    public ResponseEntity<TimeSlotsEmployeeDateDTO> getAvailableTimeSlots(@RequestParam Long id , @RequestParam int year, @RequestParam int month, @RequestParam int day) {
        LocalDate date = LocalDate.of(year, month, day);
//        List<Appointment> appointments = appointmentRepository.getAppointmentsForDateForEmployee(id, date);
        TimeSlotsEmployeeDateDTO timeslots = TimeSlotsEmployeeDateDTO.builder()
                .timeSlots(getTimeSlotsForDateForEmployeeUseCase.timeSlotsForDate(id,date))
                .build();
        return ResponseEntity.ok().body(timeslots);
    }
    @GetMapping("/employees/{lastName}")
    public ResponseEntity<EmployeesByLastNameDTO> getEmployeesByLastName(@PathVariable String lastName) {
        List<Employee> emplyees = getEmployeesByLastNameUseCase.getEmployeesByLastName(lastName);

        EmployeesByLastNameDTO employeesByLastNameDTO =
                EmployeesByLastNameDTO.builder()
                .employeeDTOList(emplyees
                        .stream()
                        .map(employee -> employeeConverterDTO.covertEmployeeToDTO(employee))
                        .toList())
                .build();

        return ResponseEntity.ok().body(employeesByLastNameDTO);
    }


    @GetMapping("/endTimeSlots")
    @ResponseBody
    public ResponseEntity<TimeSlotsEmployeeDateDTO> getEndTimeSlots(@RequestParam int hour, @RequestParam int minutes, @RequestParam Long id , @RequestParam int year, @RequestParam int month, @RequestParam int day) {
        LocalTime startTime = LocalTime.of(hour, minutes);
        LocalDate date = LocalDate.of(year, month, day);

        TimeSlotsEmployeeDateDTO appointments = TimeSlotsEmployeeDateDTO.builder()
                .timeSlots(getTimeSlotsForDateForEmployeeUseCase.endTimeSlots(startTime, id, date))
                .build();
        return ResponseEntity.ok().body(appointments);
    }
}
