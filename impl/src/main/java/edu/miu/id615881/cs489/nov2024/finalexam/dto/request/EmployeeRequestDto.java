package edu.miu.id615881.cs489.nov2024.finalexam.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record EmployeeRequestDto(
        @NotBlank(message = "blank - null - empty are not accepted")
        String name,
        @NotBlank(message = "blank - null - empty are not accepted")
        String email,
        @NotBlank(message = "blank - null - empty are not accepted")
        String phone,
        @NotBlank(message = "blank - null - empty are not accepted")
        LocalDate hireDate
) {
}
