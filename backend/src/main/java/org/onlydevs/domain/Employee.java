package org.onlydevs.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
