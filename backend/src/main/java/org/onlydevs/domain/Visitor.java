package org.onlydevs.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class Visitor {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
