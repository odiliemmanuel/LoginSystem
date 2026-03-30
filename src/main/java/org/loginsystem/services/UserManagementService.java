package org.loginsystem.services;

import org.loginsystem.data.models.User;
import org.loginsystem.data.repositeries.UserRepository;
import org.loginsystem.dtos.requests.UserLoginRequest;
import org.loginsystem.dtos.requests.UserSignUpRequest;
import org.loginsystem.dtos.responses.UserLoginResponse;
import org.loginsystem.dtos.responses.UserSignUpResponse;
import org.loginsystem.exceptions.UserAlreadyExistException;
import org.loginsystem.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserManagementService {


    @Autowired
    UserRepository userRepository;

    public UserSignUpResponse registerUser(UserSignUpRequest userSignUpRequest){
       User user = Mapper.mapToUser(userSignUpRequest);
        checkDuplicateUser(user);
       userRepository.save(user);
       return Mapper.mapUserToResponse(user);
    }



    public UserLoginResponse logUserIn(UserLoginRequest userLoginRequest){
        User user = userRepository.findByEmailAddressAndPassword(userLoginRequest.getEmailAddress(), userLoginRequest.getPassword());
        if(!user.getEmailAddress().equals(userLoginRequest.getEmailAddress()) && !user.getPassword().equals(userLoginRequest.getPassword())){
            throw new NullPointerException("Invalid Entry, please signup");
        }
        return Mapper.mapUserLoginToResponse(user);
    }



    public void checkDuplicateUser(User user){
        if(userRepository.findByEmailAddress(user.getEmailAddress()) != null){
            throw new UserAlreadyExistException("Cannot sign up with an existing email");
        }
    }




}
