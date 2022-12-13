package org.onlydevs.controller;


import lombok.AllArgsConstructor;
import org.onlydevs.business.CreateAccountUseCase;
import org.onlydevs.controller.DTO.CreateAccountRequestDTO;
import org.onlydevs.controller.DTO.CreateAccountResponseDTO;
import org.onlydevs.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/register")
@AllArgsConstructor
public class CreateAccountController {


    final private CreateAccountUseCase createAccountUseCase;

    @PostMapping
    public ResponseEntity<CreateAccountResponseDTO> register(@RequestBody @Valid CreateAccountRequestDTO registerRequest) {

        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
        CreateAccountResponseDTO registerResponse = CreateAccountResponseDTO.builder()
                .id(createAccountUseCase.register(user))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }
}
