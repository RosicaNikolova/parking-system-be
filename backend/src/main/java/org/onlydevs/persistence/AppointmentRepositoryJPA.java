package org.onlydevs.persistence;

import org.onlydevs.persistence.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepositoryJPA extends JpaRepository<AppointmentEntity, Long> {

}
