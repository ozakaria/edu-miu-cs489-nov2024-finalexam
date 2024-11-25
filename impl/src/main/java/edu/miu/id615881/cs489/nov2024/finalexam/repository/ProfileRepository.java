package edu.miu.id615881.cs489.nov2024.finalexam.repository;

import edu.miu.id615881.cs489.nov2024.finalexam.model.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
