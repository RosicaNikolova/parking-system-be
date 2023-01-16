package org.onlydevs.persistence;

import org.onlydevs.persistence.entity.EmployeeEntity;
import org.onlydevs.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
