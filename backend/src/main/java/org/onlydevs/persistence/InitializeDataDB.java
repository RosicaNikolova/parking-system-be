package org.onlydevs.persistence;

import lombok.AllArgsConstructor;
import org.onlydevs.business.CreateAccountUseCase;
import org.onlydevs.business.CreateAppointmentUseCase;
import org.onlydevs.business.CreateEmployeeUseCase;
import org.onlydevs.business.impl.CreateAccount;
import org.onlydevs.business.impl.CreateAppointment;
import org.onlydevs.business.impl.GetEmployees;
import org.onlydevs.domain.Appointment;
import org.onlydevs.domain.Employee;
import org.onlydevs.domain.User;
import org.onlydevs.domain.Visitor;
import org.onlydevs.outlook.OutlookCalendarService;
import org.onlydevs.persistence.entity.RoleEnum;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class InitializeDataDB implements ApplicationRunner {
    private final CreateAccountUseCase createAccountUseCase;
    private final CreateAppointmentUseCase createAppointmentUseCase;
    private final CreateEmployeeUseCase createEmployeeUseCase;
    private final OutlookCalendarService outlookCalendarService;
    private final GetEmployees employees;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        createEmployee();
//        registerAdminAccount();
//        registerSecretaryAccount();
//        createAppointment();
    }

    public Long registerSecretaryAccount()
    {
        User user = User.builder()
                .email("secretary@gmail.com")
                .password("secretary")
                .roles(List.of(RoleEnum.SECRETARY.toString()))
                .build();
        return createAccountUseCase.register(user);
    }

    public Long registerAdminAccount()
    {
        User user = User.builder()
                .email("admin@gmail.com")
                .password("secretary")
                .roles(List.of(RoleEnum.ADMIN.toString()))
                .build();
        return createAccountUseCase.register(user);
    }

    public Appointment createAppointment() throws IOException {
        Visitor v = Visitor.builder()
                .email("visitoremail")
                .phoneNumber("0123456789")
                .firstName("Visitor1FirstName")
                .lastName("Visitor2LastName")
                .build();
        Appointment ap =
                Appointment.builder()
                        .visitor(v)
                        .employee(employees.getEmployees().get(0))
                        .comesByCar(true)
                        .licensePlate("XXX-000-XXX")
                        .dateTime(LocalDateTime.of(2023, 01, 20, 15, 0, 0))
                        .endTime(LocalTime.of(15,30, 0))
                        .build();
        Appointment a =
                Appointment.builder()
                .visitor(ap.getVisitor())
                .employee(ap.getEmployee())
                .comesByCar(ap.getComesByCar())
                .outlookAppointmentId(outlookCalendarService.createAppointment(ap).getId())
                .licensePlate(ap.getLicensePlate())
                .dateTime(ap.getDateTime())
                .endTime(ap.getEndTime())
                .build();

        return createAppointmentUseCase.CreateAppointment(a);
    }
    public Long createEmployee()
    {
        Employee e = Employee.builder()
                .email("employee@hotmail.com")
                .firstName("employee1FirstName")
                .lastName("employee1LastName")
                .build();
        return createEmployeeUseCase.createEmployee(e);
    }
}

