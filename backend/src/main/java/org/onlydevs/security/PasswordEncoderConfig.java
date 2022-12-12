package org.onlydevs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class PasswordEncoderConfig {

    //Use the BCryptPassword Encoder when you need a password Encoder
    @Bean
    public PasswordEncoder createBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
