package edu.miu.id615881.cs489.nov2024.finalexam.service;

import edu.miu.id615881.cs489.nov2024.finalexam.dto.request.ServiceRequestDto;
import edu.miu.id615881.cs489.nov2024.finalexam.dto.response.VehicleServiceResponseDto;

public interface VehicleService {
    VehicleServiceResponseDto assignService(ServiceRequestDto request);
}
