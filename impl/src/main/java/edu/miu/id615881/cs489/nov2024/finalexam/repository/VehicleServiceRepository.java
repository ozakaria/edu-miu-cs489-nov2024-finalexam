package edu.miu.id615881.cs489.nov2024.finalexam.repository;

import edu.miu.id615881.cs489.nov2024.finalexam.model.VService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleServiceRepository extends JpaRepository<VService,Long> {
}
