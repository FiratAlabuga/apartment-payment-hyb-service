package com.apms.apartmentservice.apartment.service.impl;

import com.apms.apartmentservice.apartment.exception.PaymentNotFoundException;
import com.apms.apartmentservice.apartment.model.domain.Payment;
import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import com.apms.apartmentservice.apartment.model.mapper.PaymentDTOToPaymentMapper;
import com.apms.apartmentservice.apartment.model.mapper.PaymentToPaymentDTOMapper;
import com.apms.apartmentservice.apartment.repository.PaymentRepository;
import com.apms.apartmentservice.apartment.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentDTOToPaymentMapper paymentDTOToPaymentMapper = PaymentDTOToPaymentMapper.initialize();
    private final PaymentToPaymentDTOMapper paymentToPaymentDTOMapper = PaymentToPaymentDTOMapper.initialize();

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(paymentToPaymentDTOMapper::map)
                .toList();
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        // Map the DTO to entity
        Payment payment = paymentDTOToPaymentMapper.map(paymentDTO);
        paymentRepository.save(payment);
        return paymentToPaymentDTOMapper.map(payment);
    }

    @Override
    public PaymentDTO getPaymentById(String paymentId) {
        // Find the payment by ID and map it to DTO
        Payment payment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        return paymentToPaymentDTOMapper.map(payment);
    }

    @Override
    public PaymentDTO getPaymentByTransactionId(String transactionId) {
        // Find the payment by transaction ID and map it to DTO
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new PaymentNotFoundException(transactionId));
        return paymentToPaymentDTOMapper.map(payment);
    }

    @Override
    public PaymentDTO updatePayment(String paymentId, PaymentDTO paymentDTO) {
        // Find the existing payment
        Payment existingPayment = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        // Map the updated fields from DTO to entity
        paymentDTOToPaymentMapper.updateEntity(paymentDTO, existingPayment);
        // Save the updated payment
        paymentRepository.save(existingPayment);
        return paymentToPaymentDTOMapper.map(existingPayment);
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
