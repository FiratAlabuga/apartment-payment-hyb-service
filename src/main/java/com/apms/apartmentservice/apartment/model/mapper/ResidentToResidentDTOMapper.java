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
    // This interface can be used to define custom mapping methods if needed
    // For example, if you want to add additional mapping logic or transformations
    // between Resident and ResidentDTO objects, you can define them here.


    @Override
    ResidentDTO map(Resident source);

    @Override
    List<ResidentDTO> map(Collection<Resident> sources);

    @Override
    void updateEntity(Resident entity, ResidentDTO dto);

    static ResidentToResidentDTOMapper initialize() {
        return Mappers.getMapper(ResidentToResidentDTOMapper.class);
    }
}
