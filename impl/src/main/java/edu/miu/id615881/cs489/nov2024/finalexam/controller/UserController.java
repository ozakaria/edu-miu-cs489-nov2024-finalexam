package edu.miu.id615881.cs489.nov2024.finalexam.controller;

import edu.miu.id615881.cs489.nov2024.finalexam.model.dtos.UserRequestDTO;
import edu.miu.id615881.cs489.nov2024.finalexam.model.dtos.UserResponseDTO;
import edu.miu.id615881.cs489.nov2024.finalexam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        List<UserResponseDTO> userResponseDTOS = userService.getAllUsers();
        model.addAttribute("userResponseDTOS", userResponseDTOS);
        return "users_page";
    }

    @PostMapping
    public String addUser(@RequestBody UserRequestDTO userRequestDTO) {

        userService.addUser(userRequestDTO);

        return "users_page";

    }

}
