package com.apms.apartmentservice.apartment.repository;

import com.apms.apartmentservice.apartment.model.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Custom query methods can be defined here if needed
    Optional<Payment> findByPaymentId(String paymentId);
    Optional<Payment> findByTransactionId(String transactionId);
}
