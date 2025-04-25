package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.PaymentType;
import com.apms.apartmentservice.apartment.model.dto.PaymentTypeDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentTypeDTOToPaymentTypeMapper extends BaseMapper<PaymentTypeDTO, PaymentType> {

    @Override
    PaymentType map(PaymentTypeDTO source);

    @Override
    List<PaymentType> map(Collection<PaymentTypeDTO> sources);

    @Override
    void updateEntity(PaymentTypeDTO entity, PaymentType dto);

    static PaymentTypeDTOToPaymentTypeMapper initialize() {
        return Mappers.getMapper(PaymentTypeDTOToPaymentTypeMapper.class);
    }
}
