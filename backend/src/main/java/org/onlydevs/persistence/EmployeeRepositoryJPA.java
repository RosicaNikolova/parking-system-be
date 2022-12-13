package org.onlydevs.persistence;

import org.onlydevs.persistence.entity.AppointmentEntity;
import org.onlydevs.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositoryJPA extends JpaRepository<EmployeeEntity, Long> {
}
