package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Payment;
import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentMapper extends BaseMapper<Payment, PaymentDTO> {
    @Override
    PaymentDTO toDto(Payment entity);

    @Override
    Payment toEntity(PaymentDTO dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PaymentDTO dto,@MappingTarget Payment entity);

    static PaymentMapper initialize() {
        return Mappers.getMapper(PaymentMapper.class);
    }
}
