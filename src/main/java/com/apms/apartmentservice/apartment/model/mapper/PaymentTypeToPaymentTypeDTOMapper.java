package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.PaymentType;
import com.apms.apartmentservice.apartment.model.dto.PaymentTypeDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

public interface PaymentTypeToPaymentTypeDTOMapper extends BaseMapper<PaymentType, PaymentTypeDTO> {
    @Override
    PaymentTypeDTO map(PaymentType source);

    @Override
    List<PaymentTypeDTO> map(Collection<PaymentType> sources);

    @Override
    void updateEntity(PaymentType entity, PaymentTypeDTO dto);

    static PaymentTypeToPaymentTypeDTOMapper initialize() {
        return Mappers.getMapper(PaymentTypeToPaymentTypeDTOMapper.class);
    }
}
