package org.loginsystem.UserManagementServiceTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loginsystem.data.repositeries.UserRepository;
import org.loginsystem.dtos.requests.UserLoginRequest;
import org.loginsystem.dtos.requests.UserSignUpRequest;
import org.loginsystem.dtos.responses.UserLoginResponse;
import org.loginsystem.exceptions.InvalidInputException;
import org.loginsystem.exceptions.UserAlreadyExistException;
import org.loginsystem.exceptions.UserDoesNotExistException;
import org.loginsystem.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserManagementTest{

    @Autowired
    UserManagementService userManagementService;
    UserSignUpRequest userSignUpRequest;
    UserLoginRequest userLoginRequest;


    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
        userSignUpRequest = new UserSignUpRequest();
        userLoginRequest = new UserLoginRequest();

        userSignUpRequest.setFirstName("Esther");
        userSignUpRequest.setMiddleName("Onumsinachi");
        userSignUpRequest.setLastName("Ejeh");
        userSignUpRequest.setEmailAddress("estheremmanuel12@gmail.com");
        userSignUpRequest.setPhoneNumber("09155675321");
        userSignUpRequest.setPassword("5678");
        userSignUpRequest.setConfirmPassword("5678");

        userManagementService.registerUser(userSignUpRequest);
    }



    @Test
    public void testThatIRegisterUser_NumberOfUsersIncreases(){

        assertEquals(1, userRepository.count());

    }


    @Test
    public void testThatIRegisterUserWithInvalidEntriesErrorIsThrown(){
//
        userSignUpRequest.setFirstName("Kaod1lichi");
        userSignUpRequest.setMiddleName("Em8anuel");
        userSignUpRequest.setLastName("Ej4h");
        userSignUpRequest.setEmailAddress("odiliejeh09gmail.com");
        userSignUpRequest.setPhoneNumber("7046731194");
        userSignUpRequest.setPassword("31A24");
        userSignUpRequest.setConfirmPassword("31A24");
        assertThrows(InvalidInputException.class,
                () -> userManagementService.registerUser(userSignUpRequest));

        assertEquals(1, userRepository.count());


    }

    @Test
    public void testThatWhileRegisteringUser_IfUserAlreadyExistWithTheSameEmailAddress_ErrorIsThrown(){

        UserSignUpRequest newUser = new UserSignUpRequest();
        newUser.setFirstName("Ebubechukwu");
        newUser.setMiddleName("Jerome");
        newUser.setLastName("Ejeh");
        newUser.setEmailAddress("jeromejeh@gmail.com");
        newUser.setPhoneNumber("07046731194");
        newUser.setPassword("1093");
        newUser.setConfirmPassword("1093");
        userManagementService.registerUser(newUser);


        userSignUpRequest.setFirstName("Khid");
        userSignUpRequest.setMiddleName("Black");
        userSignUpRequest.setLastName("Test");
        userSignUpRequest.setEmailAddress("jeromejeh@gmail.com");
        userSignUpRequest.setPhoneNumber("09034445500");
        userSignUpRequest.setPassword("1234");
        userSignUpRequest.setConfirmPassword("1234");

        assertThrows(UserAlreadyExistException.class,
                () -> userManagementService.registerUser(userSignUpRequest));

        assertEquals(2, userRepository.count());

    }



    @Test
    public void testThatUserProvideEmailAndPassword_EntryAccessAllowed(){

        userSignUpRequest.setFirstName("Chinaza");
        userSignUpRequest.setMiddleName("Esther");
        userSignUpRequest.setLastName("Ikechukwu");
        userSignUpRequest.setEmailAddress("goodnessesther78@gmail.com");
        userSignUpRequest.setPhoneNumber("07013569768");
        userSignUpRequest.setPassword("9067");
        userSignUpRequest.setConfirmPassword("9067");
        userManagementService.registerUser(userSignUpRequest);


        userLoginRequest.setEmailAddress("goodnessesther78@gmail.com");
        userLoginRequest.setPassword("9067");
        UserLoginResponse response = userManagementService.logUserIn(userLoginRequest);

        assertEquals("07013569768", response.getPhoneNumber());

    }

    @Test
    public void testThatWhileLoggingUserInIfPasswordAndEmailDoesMatchErrorIsThrown(){
        userSignUpRequest.setFirstName("Chinaza");
        userSignUpRequest.setMiddleName("Esther");
        userSignUpRequest.setLastName("Ikechukwu");
        userSignUpRequest.setEmailAddress("goodnessesther78@gmail.com");
        userSignUpRequest.setPhoneNumber("07013569768");
        userSignUpRequest.setPassword("9067");
        userSignUpRequest.setConfirmPassword("9067");
        userManagementService.registerUser(userSignUpRequest);


        userLoginRequest.setEmailAddress("goodnesssther78@gmail.com");
        userLoginRequest.setPassword("9067");
        assertThrows(NullPointerException.class,
                () ->  userManagementService.logUserIn(userLoginRequest));



    }

}
