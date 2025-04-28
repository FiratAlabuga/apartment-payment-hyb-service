package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Payment;
import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentToPaymentDTOMapper extends BaseMapper<Payment, PaymentDTO> {

    @Override
    PaymentDTO map(Payment source);

    @Override
    List<PaymentDTO> map(Collection<Payment> sources);

    @Override
    Payment updateEntity(Payment entity, PaymentDTO dto);

    static PaymentToPaymentDTOMapper initialize() {
        return Mappers.getMapper(PaymentToPaymentDTOMapper.class);
    }
}
