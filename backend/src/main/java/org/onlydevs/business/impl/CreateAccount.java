package org.onlydevs.business.impl;

import com.nimbusds.openid.connect.sdk.UserInfoResponse;
import lombok.AllArgsConstructor;
import org.onlydevs.business.CreateAccountUseCase;
import org.onlydevs.domain.User;
import org.onlydevs.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CreateAccount implements CreateAccountUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Long register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        List<String> roles = new ArrayList<>();
//        roles.add("secretary");
//        user.setRoles(roles);
        return userRepository.save(user);
    }
}
