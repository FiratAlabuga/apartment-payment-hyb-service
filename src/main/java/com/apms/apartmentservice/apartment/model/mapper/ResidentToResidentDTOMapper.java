package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Resident;
import com.apms.apartmentservice.apartment.model.dto.ResidentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ResidentToResidentDTOMapper extends BaseMapper<Resident, ResidentDTO> {
    @Override
    ResidentDTO map(Resident source);

    @Override
    List<ResidentDTO> map(Collection<Resident> sources);

    @Override
    Resident updateEntity(Resident entity, ResidentDTO dto);

    static ResidentToResidentDTOMapper initialize() {
        return Mappers.getMapper(ResidentToResidentDTOMapper.class);
    }
}
