package org.loginsystem.dtos.responses;

import lombok.Data;

@Data
public class UserSignUpResponse {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
}
