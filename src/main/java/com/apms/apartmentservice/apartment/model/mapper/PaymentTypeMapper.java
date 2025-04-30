package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.PaymentType;
import com.apms.apartmentservice.apartment.model.dto.PaymentTypeDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PaymentTypeMapper extends BaseMapper<PaymentType, PaymentTypeDTO> {
    @Override
    PaymentTypeDTO toDto(PaymentType entity);

    @Override
    PaymentType toEntity(PaymentTypeDTO dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(PaymentTypeDTO dto,@MappingTarget PaymentType entity);

    static PaymentTypeMapper initialize() {
        return Mappers.getMapper(PaymentTypeMapper.class);
    }
}
