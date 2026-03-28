package org.loginsystem.controllers;

import org.loginsystem.dtos.requests.UserSignUpRequest;
import org.loginsystem.dtos.responses.UserSignUpResponse;
import org.loginsystem.exceptions.InvalidInputException;
import org.loginsystem.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/users")
public class UserManagementController {

    @Autowired
    UserManagementService userManagementService;


    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody UserSignUpRequest userSignUpRequest){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(userManagementService.registerUser(userSignUpRequest));
        }
        catch(InvalidInputException error){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error.getMessage());
        }
    }



}
