package org.onlydevs.persistence;

import org.onlydevs.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepositoryJPA extends JpaRepository<AppointmentEntity, Long> {
}
