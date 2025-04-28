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
    @Override
    Resident map(ResidentDTO source);

    @Override
    List<Resident> map(Collection<ResidentDTO> sources);

    static ResidentDTOToResidentMapper initialize() {
        return Mappers.getMapper(ResidentDTOToResidentMapper.class);
    }
}
