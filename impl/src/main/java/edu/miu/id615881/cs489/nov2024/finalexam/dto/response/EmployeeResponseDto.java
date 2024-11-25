package edu.miu.id615881.cs489.nov2024.finalexam.dto.response;

import java.time.LocalDate;
import java.util.List;

public record EmployeeResponseDto(
        Long id,
        String name,
        String email,
        String phone,
        LocalDate hireDate,
        List<VehicleServiceResponseDto> services
) {
}
