package com.apms.apartmentservice.apartment.service.impl;

import com.apms.apartmentservice.apartment.exception.PaymentTypeNotFoundException;
import com.apms.apartmentservice.apartment.model.dto.PaymentTypeDTO;
import com.apms.apartmentservice.apartment.model.mapper.PaymentTypeMapper;
import com.apms.apartmentservice.apartment.repository.PaymentTypeRepository;
import com.apms.apartmentservice.apartment.service.PaymentTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentTypeServiceImpl implements PaymentTypeService {
    private final PaymentTypeRepository paymentTypeRepository;
    private final PaymentTypeMapper paymentTypeMapper = PaymentTypeMapper.initialize();

    @Override
    public List<PaymentTypeDTO> getAllPaymentTypes() {
        return paymentTypeRepository.findAll()
                .stream()
                .map(paymentTypeMapper::toDto)
                .toList();
    }

    @Override
    public PaymentTypeDTO createPaymentType(PaymentTypeDTO paymentTypeDTO) {
        // Map the DTO to entity
        var paymentType = paymentTypeMapper.toEntity(paymentTypeDTO);
        // Save the payment type
        paymentTypeRepository.save(paymentType);
        // Convert to DTO and return
        var savedPaymentTypeDTO = paymentTypeMapper.toDto(paymentType);
        return savedPaymentTypeDTO;
    }

    @Override
    public PaymentTypeDTO getPaymentTypeById(String paymentTypeId) {
        // Find the payment type by ID and map it to DTO
        var paymentType = paymentTypeRepository.findByPaymentTypeId(paymentTypeId)
                .orElseThrow(() -> new PaymentTypeNotFoundException(paymentTypeId));
        return paymentTypeMapper.toDto(paymentType);
    }

    @Override
    public PaymentTypeDTO updatePaymentType(String paymentTypeId, PaymentTypeDTO paymentTypeDTO) {
        // Find the existing payment type
        var existingPaymentType = paymentTypeRepository.findByPaymentTypeId(paymentTypeId)
                .orElseThrow(() -> new PaymentTypeNotFoundException(paymentTypeId));
        // Map the updated fields from DTO to entity
        paymentTypeMapper.updateEntityFromDto(paymentTypeDTO, existingPaymentType);
        // Save the updated payment type
        paymentTypeRepository.save(existingPaymentType);
        return paymentTypeMapper.toDto(existingPaymentType);
    }

    @Override
    public boolean deletePaymentType(String paymentTypeId) {
        // Find the existing payment type
        var existingPaymentType = paymentTypeRepository.findByPaymentTypeId(paymentTypeId)
                .orElseThrow(() -> new PaymentTypeNotFoundException(paymentTypeId));
        // Delete the payment type
        existingPaymentType.setStatus(false);
        return true;
    }
}
