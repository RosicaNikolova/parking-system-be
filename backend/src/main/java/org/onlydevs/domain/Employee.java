package org.onlydevs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Employee {
    private String firstName;
    private String lastName;
    private String email;
}
