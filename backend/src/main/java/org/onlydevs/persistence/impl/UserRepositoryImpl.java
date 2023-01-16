package org.onlydevs.persistence.impl;

import lombok.RequiredArgsConstructor;
import org.onlydevs.domain.User;
import org.onlydevs.persistence.UserRepository;
import org.onlydevs.persistence.UserRepositoryJPA;
import org.onlydevs.persistence.entity.RoleEnum;
import org.onlydevs.persistence.entity.UserEntity;
import org.onlydevs.persistence.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJPA userRepositoryJPA;
    @Override
    public Optional<User> findUserByEmail(String email) {
        UserEntity ue = userRepositoryJPA.findByEmail(email);

        List<String> roles = ue.getUserRoles().stream()
                .map(userRole -> userRole.getRole().toString())
                .toList();

        User u = User.builder()
                .email(ue.getEmail())
                .password(ue.getPassword())
                .roles(roles)
                .build();
        return Optional.of(u);
    }

    @Override
    public Long save(User user) {
        UserEntity ue = UserEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        //Needs to be changed
        if(user.getRoles().contains("SECRETARY"))
        {
            ue.setUserRoles(Set.of(
                    UserRoleEntity.builder()
                            .user(ue)
                            .role(RoleEnum.SECRETARY)
                            .build()));
        }
        else if(user.getRoles().contains("ADMIN"))
        {
            ue.setUserRoles(Set.of(
                    UserRoleEntity.builder()
                            .user(ue)
                            .role(RoleEnum.ADMIN)
                            .build()));
        }

        UserEntity userToCheck = userRepositoryJPA.findByEmail(ue.getEmail());

        if (userToCheck == null)
        {
            return userRepositoryJPA.save(ue).getId();
        }
        return null;
    }
}
