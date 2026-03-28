package org.loginsystem.dtos.requests;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String emailAddress;
    private String password;
}
