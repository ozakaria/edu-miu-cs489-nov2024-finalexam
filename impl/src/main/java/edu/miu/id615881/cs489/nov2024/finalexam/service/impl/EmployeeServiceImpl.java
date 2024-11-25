package edu.miu.id615881.cs489.nov2024.finalexam.service.impl;

import edu.miu.id615881.cs489.nov2024.finalexam.dto.request.EmployeeRequestDto;
import edu.miu.id615881.cs489.nov2024.finalexam.dto.response.EmployeeResponseDto;
import edu.miu.id615881.cs489.nov2024.finalexam.dto.response.VehicleServiceResponseDto;
import edu.miu.id615881.cs489.nov2024.finalexam.exception.ResourceNotFoundException;
import edu.miu.id615881.cs489.nov2024.finalexam.model.Employee;
import edu.miu.id615881.cs489.nov2024.finalexam.repository.EmployeeRepository;
import edu.miu.id615881.cs489.nov2024.finalexam.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto addEmployee(EmployeeRequestDto request) {
        // Write your code here

        Employee employee = new Employee(
                request.name(),
                request.email(),
                request.phone(),
                request.hireDate()
        );

        Employee createdEmployee = employeeRepository.save(employee);

        EmployeeResponseDto employeeResponseDto = mapToResponseDto(createdEmployee);

        return employeeResponseDto;

    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees() {
        // Write your code here

        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseDto> employeeResponseDtos = new ArrayList<>();

        for (Employee employee : employees) {

            EmployeeResponseDto employeeResponseDto = mapToResponseDto(employee);

            employeeResponseDtos.add(employeeResponseDto);

        }

        return employeeResponseDtos;

    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        // Write your code here

        Optional<Employee> foundEmployee = employeeRepository.findById(id);

        if (foundEmployee.isPresent()) {

            Employee employee = foundEmployee.get();

            EmployeeResponseDto employeeResponseDto = mapToResponseDto(employee);

            return employeeResponseDto;
        }

        throw new ResourceNotFoundException("Employee not found with id " + id);

    }

    @Override
    public EmployeeResponseDto partiallyUpdateEmployee(Long id, Map<String, Object> updates) {
        // Fetch the employee by ID or throw an exception if not found

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));

        // Apply each update based on the key
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    // Write your code here

                    employee.setName(value + "");

                    break;
                case "email":
                    // Write your code here

                    employee.setEmail(value + "");

                    break;
                case "phone":
                    // Write your code here

                    employee.setPhone(value + "");

                    break;
                case "hireDate":
                    // Write your code here

                    employee.setHireDate(LocalDate.parse(value + ""));

                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });
        // Write your code here

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeResponseDto employeeResponseDto = mapToResponseDto(savedEmployee);

        return employeeResponseDto;

    }

    private EmployeeResponseDto mapToResponseDto(Employee employee) {
        List<VehicleServiceResponseDto> serviceDtos = employee.getVServices().stream()
                .map(service -> new VehicleServiceResponseDto(
                        service.getId(),
                        service.getServiceName(),
                        service.getCost(),
                        service.getVehicleType()
                )).toList();

        return new EmployeeResponseDto(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getHireDate(),
                serviceDtos
        );
    }
}
