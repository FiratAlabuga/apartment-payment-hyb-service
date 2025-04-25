package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Apartment;
import com.apms.apartmentservice.apartment.model.dto.ApartmentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ApartmentToApartmentDTOMapper extends BaseMapper<Apartment, ApartmentDTO> {
    // This interface can be used to define custom mapping methods if needed
    // For example, if you need to map specific fields differently or perform additional transformations
    // You can add those methods here


    @Override
    ApartmentDTO map(Apartment source);

    @Override
    List<ApartmentDTO> map(Collection<Apartment> sources);

    @Override
    void updateEntity(Apartment entity, ApartmentDTO dto);

    static ApartmentToApartmentDTOMapper initialize() {
        return Mappers.getMapper(ApartmentToApartmentDTOMapper.class);
    }
}
