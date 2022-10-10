package org.onlydevs.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class Visitor {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
