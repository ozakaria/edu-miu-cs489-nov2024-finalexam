package edu.miu.id615881.cs489.nov2024.finalexam.model.dtos;

import lombok.*;

public record UserRequestDTO(
        String username,
        String password,
        ProfileRequestDTO profileRequestDTO
) {
}
