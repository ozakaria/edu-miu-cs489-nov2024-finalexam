package edu.miu.id615881.cs489.nov2024.finalexam.service;

import edu.miu.id615881.cs489.nov2024.finalexam.dto.request.EmployeeRequestDto;
import edu.miu.id615881.cs489.nov2024.finalexam.dto.response.EmployeeResponseDto;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    EmployeeResponseDto addEmployee(EmployeeRequestDto request);
    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeById(Long id);
    EmployeeResponseDto partiallyUpdateEmployee(Long id, Map<String, Object> updates);
}
