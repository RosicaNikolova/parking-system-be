package org.onlydevs.business.impl;

import lombok.AllArgsConstructor;
import org.onlydevs.business.AccessTokenEncoder;
import org.onlydevs.business.LoginUseCase;
import org.onlydevs.business.exceptions.InvalidCredentialsException;
import org.onlydevs.controller.DTO.LoginRequestDTO;
import org.onlydevs.controller.DTO.LoginResponseDTO;
import org.onlydevs.domain.AccessToken;
import org.onlydevs.domain.User;
import org.onlydevs.persistence.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class Login implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Optional<User> userOptional = userRepository.findUserByEmail(loginRequest.getEmail());
        if(userOptional.isEmpty()){
            throw new InvalidCredentialsException();
        }
        User user = userOptional.get();
        if(!matchesPassword(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }
        String accessToken = generateAccessToken(user);
        return LoginResponseDTO.builder().accessToken(accessToken)
                .roles(user.getRoles())
                .build();
    }

    private String generateAccessToken(User user) {

        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getEmail())
                        .roles(user.getRoles())
                        .userId(user.getId())
                        .build());
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
