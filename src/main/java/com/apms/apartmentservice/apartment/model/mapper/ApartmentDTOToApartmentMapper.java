package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Apartment;
import com.apms.apartmentservice.apartment.model.dto.ApartmentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ApartmentDTOToApartmentMapper extends BaseMapper<ApartmentDTO, Apartment> {

    @Override
    Apartment map(ApartmentDTO source);

    @Override
    List<Apartment> map(Collection<ApartmentDTO> sources);

    static ApartmentDTOToApartmentMapper initialize() {
        return Mappers.getMapper(ApartmentDTOToApartmentMapper.class);
    }
}
