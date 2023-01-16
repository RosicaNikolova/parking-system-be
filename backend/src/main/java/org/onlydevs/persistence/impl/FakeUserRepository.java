package org.onlydevs.persistence.impl;

import io.swagger.v3.oas.annotations.servers.Server;
import lombok.AllArgsConstructor;
import org.onlydevs.domain.User;

import org.onlydevs.persistence.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    private final List<User> users;
    private final PasswordEncoder passwordEncoder;

    public FakeUserRepository()
    {
        passwordEncoder = new BCryptPasswordEncoder();
        users = new ArrayList<>();
        String password = "12345678";
        List<String> role = new ArrayList<>();
        role.add("secretary");
        users.add(User.builder()
                .email("secretary@mail.com")
                .id(1L)
                .roles(role)
                .password(passwordEncoder.encode(password))
                .build());
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Long save(User user) {
        user.setId(2L);
        users.add(user);
        return user.getId();
    }
}
