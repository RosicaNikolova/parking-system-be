package org.onlydevs.persistence.entity;

import lombok.*;
import org.onlydevs.domain.Employee;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="appointment")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "outlookappointmentid")
    private String outlookAppointmentId;

    @Column(name = "comesbycar")
    private Boolean comesByCar;

    @Column(name = "licenseplate")
    private String licensePlate;

    @Column(name = "datetime", columnDefinition = "TIMESTAMP")
    private LocalDateTime dateTime;

    @Column(name = "endtime", columnDefinition = "TIMESTAMP")
    private LocalTime endTime;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phonenumber")
    private String phoneNumber;

//    @ManyToOne
//    @JoinColumn(name = "visitor_id")
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    private VisitorEntity visitor;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EmployeeEntity employee;
}
