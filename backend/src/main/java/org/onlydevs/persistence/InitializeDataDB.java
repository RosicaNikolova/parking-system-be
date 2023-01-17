package org.onlydevs.persistence;

import lombok.AllArgsConstructor;
import org.onlydevs.business.impl.CreateAccount;
import org.onlydevs.business.impl.CreateAppointment;
import org.onlydevs.domain.User;
import org.onlydevs.persistence.entity.RoleEnum;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class InitializeDataDB implements ApplicationRunner {
    private final CreateAccount createAccount;

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    public Long registerSecretaryAccount()
    {
        User user = User.builder()
                .email("secretary@gmail.com")
                .password("secretary")
                .roles(List.of(RoleEnum.SECRETARY.toString()))
                .build();
        return createAccount.register(user);
    }

    public Long registerAdminAccount()
    {
        User user = User.builder()
                .email("admin@gmail.com")
                .password("secretary")
                .roles(List.of(RoleEnum.ADMIN.toString()))
                .build();
        return createAccount.register(user);
    }
}

