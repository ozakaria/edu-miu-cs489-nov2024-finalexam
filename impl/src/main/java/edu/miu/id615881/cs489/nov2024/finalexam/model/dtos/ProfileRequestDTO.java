package edu.miu.id615881.cs489.nov2024.finalexam.model.dtos;

import java.time.LocalDate;

public record ProfileRequestDTO(
        String bio,
        String location,
        LocalDate dob
) {
}
