package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Resident;
import com.apms.apartmentservice.apartment.model.dto.ResidentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ResidentDTOToResidentMapper extends BaseMapper<ResidentDTO, Resident> {
    // This interface can be used to define custom mapping methods if needed
    // For example, you can add methods to map specific fields or handle complex mappings
    // But for now, it extends BaseMapper which provides basic mapping functionality


    @Override
    Resident map(ResidentDTO source);

    @Override
    List<Resident> map(Collection<ResidentDTO> sources);

    @Override
    void updateEntity(ResidentDTO entity, Resident dto);

    static ResidentDTOToResidentMapper initialize() {
        return Mappers.getMapper(ResidentDTOToResidentMapper.class);
    }
}
