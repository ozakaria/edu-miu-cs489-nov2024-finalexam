package edu.miu.id615881.testcodemockdemo.controller;

import edu.miu.id615881.testcodemockdemo.dto.request.UserRequestDto;
import edu.miu.id615881.testcodemockdemo.dto.response.UserResponseDto;
import edu.miu.id615881.testcodemockdemo.model.User;
import edu.miu.id615881.testcodemockdemo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest2 {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void getAllUsers_success_returnsUserList() { //create a list of UserResponseDTO

        List<UserResponseDto> userResponseDtos = List.of(new UserResponseDto("ozakaria"), new UserResponseDto("ozakaria2"));

        Mockito.when(userService.getAllUsers()).thenReturn(userResponseDtos);

        ResponseEntity<List<UserResponseDto>> responseEntity = userController.getUsers();

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Assertions.assertEquals(userResponseDtos, responseEntity.getBody());

    }

    @Test
    public void createUser_validInput_returnCreatedUser() {

        UserResponseDto userResponseDto = new UserResponseDto("ozakaria");

//        Mockito.when(userService.createUser(userRequestDto)).thenReturn(Optional.of(userResponseDto));
        Mockito.when(userService.createUser(Mockito.any(UserRequestDto.class))).thenReturn(Optional.of(userResponseDto));


        UserRequestDto userRequestDto = new UserRequestDto("Osama", "Abdelhamid", "ozakaria");

        ResponseEntity<UserResponseDto> responseEntity = userController.createUser(userRequestDto);

        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody().equals(userResponseDto);

    }

    @Test
    public void updateUser_validInput_updatesUser() {

        String username = "ozakaria";

        UserRequestDto userRequestDto = new UserRequestDto("Osama2", "Abdelhamid2", "ozakaria2");

        UserResponseDto userResponseDto = new UserResponseDto("ozakaria2");

        Mockito.when(userService.updateUser(username, userRequestDto)).thenReturn(Optional.of(userResponseDto));

        ResponseEntity<UserResponseDto> responseEntity = userController.updateUser(username, userRequestDto);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Assertions.assertEquals(userResponseDto, responseEntity.getBody());

    }

    @Test
    public void deleteUserByUsername_validUsername_deletesUser() {

        String username = "ozakaria";

        ResponseEntity<UserResponseDto> responseEntity = userController.deleteUser(username);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        Mockito.verify(userService, Mockito.times(1)).deleteUserByName(username);

    }

    @Test
    public void updateUser_invalidInput_noUpdate() {

        String username = "ozakaria";

        UserRequestDto userRequestDto = new UserRequestDto("Osama2", "Abdelhamid2", "ozakaria2");

        Optional<UserResponseDto> userResponseDto = Optional.empty();

        Mockito.when(userService.updateUser(username, userRequestDto)).thenReturn(userResponseDto);

        ResponseEntity<UserResponseDto> responseEntity = userController.updateUser(username, userRequestDto);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        Assertions.assertEquals(null, responseEntity.getBody());

    }

}
