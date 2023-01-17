package org.onlydevs.controller;

import lombok.RequiredArgsConstructor;
import org.onlydevs.business.LoginUseCase;
import org.onlydevs.controller.DTO.LoginRequestDTO;
import org.onlydevs.controller.DTO.LoginResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LoginController {
    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequest) {
        LoginResponseDTO loginResponse = loginUseCase.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }


}
