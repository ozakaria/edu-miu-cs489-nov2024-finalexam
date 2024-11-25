package edu.miu.id615881.cs489.nov2024.finalexam.controller;

import edu.miu.id615881.cs489.nov2024.finalexam.dto.request.EmployeeRequestDto;
import edu.miu.id615881.cs489.nov2024.finalexam.dto.response.EmployeeResponseDto;
import edu.miu.id615881.cs489.nov2024.finalexam.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void createEmployee_validInput_returnCreatedEmploye() {

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto(
                1L,
                "Osama",
                "osama@email.com",
                "1234567891",
                LocalDate.parse("2024-01-20"),
                new ArrayList<>()
        );

        Mockito.when(employeeService.addEmployee(Mockito.any(EmployeeRequestDto.class))).thenReturn(employeeResponseDto);


        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(
                "Osama",
                "osama@email.com",
                "1234567891",
                LocalDate.parse("2024-01-20")
        );

        ResponseEntity<EmployeeResponseDto> responseEntity = employeeController.addEmployee(employeeRequestDto);

        assert responseEntity.getStatusCode() == HttpStatus.CREATED;
        assert responseEntity.getBody().equals(employeeResponseDto);

    }

}
