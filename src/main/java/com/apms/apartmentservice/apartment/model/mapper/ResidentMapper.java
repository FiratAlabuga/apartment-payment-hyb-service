package com.apms.apartmentservice.apartment.model.mapper;

import com.apms.apartmentservice.apartment.model.domain.Resident;
import com.apms.apartmentservice.apartment.model.dto.ResidentDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ResidentMapper extends BaseMapper<Resident, ResidentDTO> {
    @Override
    ResidentDTO toDto(Resident entity);

    @Override
    Resident toEntity(ResidentDTO dto);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ResidentDTO dto,@MappingTarget Resident entity);

    static ResidentMapper initialize() {
        return Mappers.getMapper(ResidentMapper.class);
    }
}
