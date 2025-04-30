package com.apms.apartmentservice.common.mapper;

import com.apms.apartmentservice.apartment.model.domain.Payment;
import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;
import java.util.List;

/**
 * Generic interface for mapping between source and target types.
 *
 * <p>This interface defines the contract for mapping a single object or a collection
 * of objects from a source type {@code S} to a target type {@code T}.</p>
 *
 * @param <S> the source type
 * @param <T> the target type
 */
public interface BaseMapper<S, T> {

    /**
     * Maps a single source object to a target object.
     *
     * @param source the source object to map
     * @return the mapped target object
     */
    T map(S source);


    T toDto(S entity);

    S toEntity(T dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(T dto, @MappingTarget S entity);
}