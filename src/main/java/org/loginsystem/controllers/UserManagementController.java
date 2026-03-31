package org.loginsystem.controllers;

import org.loginsystem.dtos.requests.UserLoginRequest;
import org.loginsystem.dtos.requests.UserSignUpRequest;
import org.loginsystem.exceptions.InvalidInputException;
import org.loginsystem.exceptions.UserDoesNotExistException;
import org.loginsystem.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/log-user-in")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest userLoginRequest){
        try{
            return ResponseEntity.status(HttpStatus.FOUND).body(userManagementService.logUserIn(userLoginRequest));
        }
        catch(UserDoesNotExistException error){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.getMessage());
        }
    }


}
