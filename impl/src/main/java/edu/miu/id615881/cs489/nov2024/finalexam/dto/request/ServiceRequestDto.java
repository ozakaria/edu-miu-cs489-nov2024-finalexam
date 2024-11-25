package edu.miu.id615881.cs489.nov2024.finalexam.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ServiceRequestDto(
        @NotBlank(message = "blank - null - empty are not accepted")
        Long employeeId,
        @NotBlank(message = "blank - null - empty are not accepted")
        String serviceName,
        double cost,
        String vehicleType
) {
}
