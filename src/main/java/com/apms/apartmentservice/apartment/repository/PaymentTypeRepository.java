package com.apms.apartmentservice.apartment.repository;

import com.apms.apartmentservice.apartment.model.domain.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
    // Custom query methods can be defined here if needed
    Optional<PaymentType> findByPaymentTypeId(String paymentTypeId);
}
