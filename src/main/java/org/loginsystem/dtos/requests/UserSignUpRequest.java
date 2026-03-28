package org.loginsystem.dtos.requests;
import lombok.Data;

@Data
public class UserSignUpRequest {


    private String firstName;
    private String middleName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String password;
    private String confirmPassword;
}
