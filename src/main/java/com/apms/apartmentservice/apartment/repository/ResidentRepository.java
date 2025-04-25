package com.apms.apartmentservice.apartment.repository;

import com.apms.apartmentservice.apartment.model.domain.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
    // Custom query methods can be defined here if needed
    Optional<Resident> findByResidentId(String residentId);
}
