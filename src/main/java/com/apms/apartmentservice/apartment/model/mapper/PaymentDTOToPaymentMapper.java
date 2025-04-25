package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Payment;
import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentDTOToPaymentMapper extends BaseMapper<PaymentDTO, Payment> {

    @Override
    Payment map(PaymentDTO source);

    @Override
    List<Payment> map(Collection<PaymentDTO> sources);

    @Override
    void updateEntity(PaymentDTO entity, Payment dto);

    static PaymentDTOToPaymentMapper initialize() {
        return Mappers.getMapper(PaymentDTOToPaymentMapper.class);
    }
}
