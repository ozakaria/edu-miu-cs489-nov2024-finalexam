package edu.miu.id615881.testcodemockdemo.controller;

import com.google.gson.Gson;
import edu.miu.id615881.testcodemockdemo.dto.request.UserRequestDto;
import edu.miu.id615881.testcodemockdemo.dto.response.UserResponseDto;
import edu.miu.id615881.testcodemockdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(UserController.class) // from spring : it loads the application context, but not fully
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void createUser() throws Exception {

        UserRequestDto userRequestDto = new UserRequestDto("Osama", "Abdelhamid", "ozakaria");

        UserResponseDto userResponseDto = new UserResponseDto("ozakaria");

        Mockito.when(userService.createUser(userRequestDto)).thenReturn(Optional.of(userResponseDto));

//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/v1/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\n" +
//                                "\"firstName\":\"Osama\",\n" +
//                                "\"lastName\":\"Abdelhamid\",\n" +
//                                "\"username\":\"ozakaria\"\n" +
//                                "}")
//        )
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.content().json("{\n" +
//                        "\"username\":\"ozakaria\"\n" +
//                        "}"))
////                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("ozakaria")) // Validate JSON response
//                .andDo(MockMvcResultHandlers.print());

        //Using Gson to parse string

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(userRequestDto))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.content().json(new Gson().toJson(userResponseDto))
                );

    }

    @Test
    void createUser_BadRequest() throws Exception {

        UserRequestDto userRequestDto = new UserRequestDto("Osama", "Abdelhamid", null);

        Mockito.when(userService.createUser(userRequestDto)).thenReturn(Optional.empty());

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Gson().toJson(userRequestDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isBadRequest()
                );

    }

    @Test
    void getUsers() throws Exception {

        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userResponseDtos.add(new UserResponseDto("ozakaria1"));
        userResponseDtos.add(new UserResponseDto("ozakaria2"));
        userResponseDtos.add(new UserResponseDto("ozakaria2"));
        userResponseDtos.add(new UserResponseDto("ozakaria3"));

        Mockito.when(userService.getAllUsers()).thenReturn(userResponseDtos);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/users")
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.content().json(new Gson().toJson(userResponseDtos))
                );

    }

    @Test
    void updateUser() throws Exception {

        UserRequestDto userRequestDto = new UserRequestDto("Osama1", "Abdelhamid1", "ozakaria1");
        String username = "ozakaria";

        UserResponseDto userResponseDto = new UserResponseDto("ozakaria1");

        Mockito.when(userService.updateUser(username, userRequestDto)).thenReturn(Optional.of(userResponseDto));

        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/users/{username}", username)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Gson().toJson(userRequestDto))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpectAll(
                        MockMvcResultMatchers.content().json(new Gson().toJson(userResponseDto))
                );

    }

    @Test
    void deleteUser() throws Exception {

        String username = "ozakaria";

        Mockito.doNothing().when(userService).deleteUserByName(username);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/users/{username}", username)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

}