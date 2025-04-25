package com.apms.apartmentservice.apartment.service;

import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    List<PaymentDTO> getAllPayments();
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(String paymentId);
    PaymentDTO getPaymentByTransactionId(String transactionId);
    PaymentDTO updatePayment(String paymentId, PaymentDTO paymentDTO);
    boolean deletePayment(String paymentId);
}
