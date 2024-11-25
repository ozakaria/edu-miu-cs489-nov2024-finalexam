package edu.miu.id615881.testcodemockdemo.service.impl;

import edu.miu.id615881.testcodemockdemo.dto.request.UserRequestDto;
import edu.miu.id615881.testcodemockdemo.dto.response.UserResponseDto;
import edu.miu.id615881.testcodemockdemo.model.User;
import edu.miu.id615881.testcodemockdemo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @Test
    public void createUser_validInput_returnCreatedUser() {

        // Given: Prepare the DTO objects and user data
        UserRequestDto userRequestDto = new UserRequestDto("Osama", "Abdelhamid", "ozakaria");

        // Create user entity from userRequestDto
        User user = new User(userRequestDto.firstName(), userRequestDto.lastName(), userRequestDto.username());

        // The expected saved user (after calling save)
        User savedUser = new User(user.getFirstName(), user.getLastName(), user.getUsername());

        // Expected response DTO
        Optional<UserResponseDto> expectedResponseDto = Optional.of(new UserResponseDto(userRequestDto.username()));

        // When: Mock findByUsername to return empty (i.e., user does not exist)
        Mockito.when(userRepository.findByUsername(userRequestDto.username())).thenReturn(Optional.empty());

        // When: Mock save to return the saved user
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);

        // Then: Call the service method and assert the result
        Optional<UserResponseDto> actualResponseDto = userService.createUser(userRequestDto);
        Assertions.assertEquals(expectedResponseDto, actualResponseDto);

    }

    @Test
    public void createUser_userExists_returnEmpty() {

        // Given: Prepare the DTO object for the user that we want to create
        UserRequestDto userRequestDto = new UserRequestDto("Osama", "Abdelhamid", "ozakaria");

        // When: Mock findByUsername to return an existing user (i.e., user already exists)
        User existingUser = new User("Osama", "Abdelhamid", "ozakaria");
        Mockito.when(userRepository.findByUsername(userRequestDto.username())).thenReturn(Optional.of(existingUser));

        // Then: Call the service method and assert the result
        Optional<UserResponseDto> actualResponseDto = userService.createUser(userRequestDto);

        // Assert that the result is Optional.empty() since the user already exists
        Assertions.assertEquals(Optional.empty(), actualResponseDto);

    }

    @Test
    public void updateUser_validInput_returnUpdatedUser() {

        // Given
        String username = "ozakaria";
        UserRequestDto userRequestDto = new UserRequestDto("Osama2", "Abdelhamid2", "ozakaria2");

        // Existing user to be updated
        User existingUser = new User("Osama", "Abdelhamid", username);

        // Updated user
        User updatedUser = new User(userRequestDto.firstName(), userRequestDto.lastName(), userRequestDto.username());

        // Mocking findByUsername to return existing user
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

        // Mocking save to return updated user
        Mockito.when(userRepository.save(existingUser)).thenReturn(updatedUser);

        // Expected response DTO
        Optional<UserResponseDto> expectedResponseDto = Optional.of(new UserResponseDto(updatedUser.getUsername()));

        // When
        Optional<UserResponseDto> actualResponseDto = userService.updateUser(username, userRequestDto);

        // Then
        Assertions.assertEquals(expectedResponseDto, actualResponseDto);

    }

    @Test
    public void getUserByName_validInput_returnUser() {

        // Given: The username to search for
        String username = "ozakaria";

        // Create a User entity to be returned by the repository
        User user = new User("Osama", "Abdelhamid", username);

        // Expected response DTO
        Optional<UserResponseDto> expectedResponseDto = Optional.of(new UserResponseDto(username));

        // When: Mocking the repository to return the user when findByUsername() is called
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Then: Call the service method and assert the result
        Optional<UserResponseDto> actualResponseDto = userService.getUserByName(username);

        // Assertions
        Assertions.assertEquals(expectedResponseDto, actualResponseDto);

    }

    @Test
    public void deleteUserByName_validInput_deleteUser() {

        // Given: The username to delete
        String username = "ozakaria";

        // Mocking getUserByName to return a valid response
        User user = new User("Osama", "Abdelhamid", username);
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // When: Call the service method to delete the user
        userService.deleteUserByName(username);

        // Then: Verify that deleteByUsername was called with the correct username
        Mockito.verify(userRepository, Mockito.times(1)).deleteByUsername(username);

    }

    @Test
    public void getAllUsers_returnAllUsers() {

        // Given: Prepare a list of User entities
        List<User> users = new ArrayList<>();
        users.add(new User("Osama", "Abdelhamid", "ozakaria"));
        users.add(new User("Osama2", "Abdelhamid2", "ozakaria2"));

        // Mocking findAll() to return the list of users
        Mockito.when(userRepository.findAll()).thenReturn(users);

        // Expected list of UserResponseDto
        List<UserResponseDto> expectedUserResponseDtos = new ArrayList<>();
        expectedUserResponseDtos.add(new UserResponseDto("ozakaria"));
        expectedUserResponseDtos.add(new UserResponseDto("ozakaria2"));

        // When: Call the service method
        List<UserResponseDto> actualUserResponseDtos = userService.getAllUsers();

        // Then: Verify that the result matches the expected response
        Assertions.assertEquals(expectedUserResponseDtos, actualUserResponseDtos);

    }

}
