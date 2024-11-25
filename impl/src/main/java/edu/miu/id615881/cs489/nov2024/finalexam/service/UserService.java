package edu.miu.id615881.cs489.nov2024.finalexam.service;

import edu.miu.id615881.cs489.nov2024.finalexam.model.dtos.UserRequestDTO;
import edu.miu.id615881.cs489.nov2024.finalexam.model.dtos.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Optional<UserResponseDTO> addUser(UserRequestDTO userRequestDTO);
    List<UserResponseDTO> getAllUsers();
}
