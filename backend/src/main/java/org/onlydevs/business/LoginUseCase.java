package org.onlydevs.business;

import org.onlydevs.controller.DTO.LoginRequestDTO;
import org.onlydevs.controller.DTO.LoginResponseDTO;

public interface LoginUseCase {

    LoginResponseDTO login(LoginRequestDTO loginRequest);

}
