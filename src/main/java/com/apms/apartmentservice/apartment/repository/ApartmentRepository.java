package com.apms.apartmentservice.apartment.repository;

import com.apms.apartmentservice.apartment.model.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    // Custom query methods can be defined here if needed
    Optional<Apartment> findByApartmentId(String apartmentId);
}
