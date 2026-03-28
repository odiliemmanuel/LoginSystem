package org.loginsystem.utils;

import org.loginsystem.data.models.User;
import org.loginsystem.dtos.requests.UserSignUpRequest;
import org.loginsystem.dtos.responses.UserLoginResponse;
import org.loginsystem.dtos.responses.UserSignUpResponse;
import org.loginsystem.exceptions.InvalidInputException;

public class Mapper {

    public static User mapToUser(UserSignUpRequest userSignUpRequest){
        User user = new User();

        if(!userSignUpRequest.getFirstName().matches("^[A-Za-z]+$")){
            throw new InvalidInputException("Enter a valid name");
        }
        else{
            user.setFirstName(userSignUpRequest.getFirstName());
        }


        if(!userSignUpRequest.getMiddleName().matches("^[A-Za-z]+$")){
            throw new InvalidInputException("Enter a valid name");
        }
        else{
            user.setMiddleName(userSignUpRequest.getMiddleName());
        }


        if(!userSignUpRequest.getLastName().matches("^[A-Za-z]+$")){
            throw new InvalidInputException("Enter a valid name");
        }
        else{
            user.setLastName(userSignUpRequest.getLastName());
        }


        if(!userSignUpRequest.getEmailAddress().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            throw new InvalidInputException("Invalid Entry");
        }
        else{
            user.setEmailAddress(userSignUpRequest.getEmailAddress());
        }
//        ^\+?[0-9]{10,15}$

        if(!userSignUpRequest.getPhoneNumber().matches("^(?:\\+234|0)[789][01][0-9]{8}$")){
            throw new InvalidInputException("Invalid number");
        }
        user.setPhoneNumber(userSignUpRequest.getPhoneNumber());

        if(!userSignUpRequest.getPassword().matches("\\d+")){
            throw new InvalidInputException("Invalid entry, password must compose of numbers");
        }
        user.setPassword(userSignUpRequest.getPassword());

        if(!userSignUpRequest.getConfirmPassword().equals(userSignUpRequest.getPassword())){
            throw new InvalidInputException("Password mismatch, try again");
        }
        user.setConfirmPassword(userSignUpRequest.getConfirmPassword());

        return user;
    }



    public static UserSignUpResponse mapUserToResponse(User user){
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();

        userSignUpResponse.setFirstName(user.getFirstName());
        userSignUpResponse.setLastName(user.getLastName());
        userSignUpResponse.setPhoneNumber(user.getPhoneNumber());
        userSignUpResponse.setEmailAddress(user.getEmailAddress());

        return userSignUpResponse;
    }

    public static UserLoginResponse mapUserLoginToResponse(User user){
        UserLoginResponse userLoginResponse = new UserLoginResponse();

        userLoginResponse.setEmailAddress(user.getEmailAddress());
        userLoginResponse.setFirstName(user.getFirstName());
        userLoginResponse.setLastName(user.getLastName());
        userLoginResponse.setPhoneNumber(user.getPhoneNumber());

        return userLoginResponse;
    }


}
