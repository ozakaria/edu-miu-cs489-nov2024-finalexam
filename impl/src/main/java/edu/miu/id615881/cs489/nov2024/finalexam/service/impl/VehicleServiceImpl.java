package edu.miu.id615881.cs489.nov2024.finalexam.service.impl;

import edu.miu.id615881.cs489.nov2024.finalexam.dto.request.ServiceRequestDto;
import edu.miu.id615881.cs489.nov2024.finalexam.dto.response.EmployeeResponseDto;
import edu.miu.id615881.cs489.nov2024.finalexam.dto.response.VehicleServiceResponseDto;
import edu.miu.id615881.cs489.nov2024.finalexam.exception.ResourceNotFoundException;
import edu.miu.id615881.cs489.nov2024.finalexam.model.Employee;
import edu.miu.id615881.cs489.nov2024.finalexam.model.VService;
import edu.miu.id615881.cs489.nov2024.finalexam.repository.EmployeeRepository;
import edu.miu.id615881.cs489.nov2024.finalexam.repository.VehicleServiceRepository;
import edu.miu.id615881.cs489.nov2024.finalexam.service.VehicleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleServiceRepository vehicleServiceRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public VehicleServiceResponseDto assignService(ServiceRequestDto request) {
        // Write your code here

        Employee employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + request.employeeId()));

        VService vService = new VService(
                request.serviceName(),
                request.cost(),
                request.vehicleType(),
                employee
        );

        VService assignedService = vehicleServiceRepository.save(vService);

        VehicleServiceResponseDto vehicleServiceResponseDto = new VehicleServiceResponseDto(
                assignedService.getId(),
                assignedService.getServiceName(),
                assignedService.getCost(),
                assignedService.getVehicleType()
        );

        return vehicleServiceResponseDto;

    }
}
