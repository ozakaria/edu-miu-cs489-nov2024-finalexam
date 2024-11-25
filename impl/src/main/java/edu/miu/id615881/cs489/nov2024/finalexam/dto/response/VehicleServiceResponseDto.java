package edu.miu.id615881.cs489.nov2024.finalexam.dto.response;

public record VehicleServiceResponseDto(
        Long id,
        String serviceName,
        double cost,
        String vehicleType
) {
}
