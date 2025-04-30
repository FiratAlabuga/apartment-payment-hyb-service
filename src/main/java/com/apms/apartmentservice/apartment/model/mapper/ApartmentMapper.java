package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.dto.ApartmentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import com.apms.apartmentservice.apartment.model.domain.Apartment;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ApartmentMapper extends BaseMapper<Apartment, ApartmentDTO> {
    @Override
    Apartment toEntity(ApartmentDTO dto);

    @Override
    ApartmentDTO toDto(Apartment entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ApartmentDTO dto,@MappingTarget Apartment entity);

    static ApartmentMapper initialize() {
        return Mappers.getMapper(ApartmentMapper.class);
    }
}
