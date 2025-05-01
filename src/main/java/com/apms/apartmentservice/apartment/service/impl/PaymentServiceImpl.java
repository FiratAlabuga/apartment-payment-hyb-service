package com.apms.apartmentservice.apartment.service.impl;

import com.apms.apartmentservice.apartment.exception.PaymentNotFoundException;
import com.apms.apartmentservice.apartment.model.domain.Payment;
import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import com.apms.apartmentservice.apartment.model.mapper.PaymentMapper;
import com.apms.apartmentservice.apartment.repository.PaymentRepository;
import com.apms.apartmentservice.apartment.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper = PaymentMapper.initialize();

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toDto)
                .toList();
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        // Map the DTO to entity
        Payment payment = paymentMapper.toEntity(paymentDTO);
        // Set the payment ID
        setUniqueSignPayment(payment);
        paymentRepository.save(payment);
        return paymentMapper.toDto(payment);
    }

    private static void setUniqueSignPayment(Payment payment) {
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setTransactionId("TXN-"+ UUID.randomUUID().toString());
    }

    @Override
    public PaymentDTO getPaymentById(String paymentId) {
        // Find the payment by ID and map it to DTO
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        return paymentMapper.toDto(payment);
    }

    @Override
    public PaymentDTO getPaymentByTransactionId(String transactionId) {
        // Find the payment by transaction ID and map it to DTO
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new PaymentNotFoundException(transactionId));
        return paymentMapper.toDto(payment);
    }

    @Override
    public PaymentDTO updatePayment(String paymentId, PaymentDTO paymentDTO) {
        // Find the existing payment
        Payment existingPayment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        // Map the updated fields from DTO to entity
        paymentMapper.updateEntityFromDto(paymentDTO, existingPayment);
        // Save the updated payment
        paymentRepository.save(existingPayment);
        return paymentMapper.toDto(existingPayment);
    }

    @Override
    public boolean deletePayment(String paymentId) {
        // Find the existing payment
        Payment existingPayment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        // Delete the payment
        existingPayment.setStatus(false);
        paymentRepository.save(existingPayment);
        return true;
    }
}
