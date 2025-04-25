package com.apms.apartmentservice.apartment.service;

import com.apms.apartmentservice.apartment.model.dto.PaymentTypeDTO;

import java.util.List;

public interface PaymentTypeService {
    List<PaymentTypeDTO> getAllPaymentTypes();
    PaymentTypeDTO createPaymentType(PaymentTypeDTO paymentTypeDTO);
    PaymentTypeDTO getPaymentTypeById(String paymentTypeId);
    PaymentTypeDTO updatePaymentType(String paymentTypeId, PaymentTypeDTO paymentTypeDTO);
    boolean deletePaymentType(String paymentTypeId);
}
