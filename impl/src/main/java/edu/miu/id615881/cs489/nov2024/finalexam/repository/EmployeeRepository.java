package edu.miu.id615881.cs489.nov2024.finalexam.repository;

import edu.miu.id615881.cs489.nov2024.finalexam.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
