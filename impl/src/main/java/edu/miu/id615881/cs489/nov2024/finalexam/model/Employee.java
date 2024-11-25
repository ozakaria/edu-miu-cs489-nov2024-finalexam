package edu.miu.id615881.cs489.nov2024.finalexam.model;

import edu.miu.id615881.cs489.nov2024.finalexam.service.VehicleService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employees")
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private LocalDate hireDate;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private List<VService> vServices = new ArrayList<>();

    public Employee(String name, String email, String phone, LocalDate hireDate) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
    }
}
