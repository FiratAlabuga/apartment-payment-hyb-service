package com.apms.apartmentservice.auth.model.mapper;

import com.apms.apartmentservice.auth.model.domain.User;
import com.apms.apartmentservice.auth.model.dto.request.RegisterRequest;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RegisterRequestToUserMapper extends BaseMapper<RegisterRequest, User> {
    /**
     * Maps a {@link RegisterRequest} to a {@link User} for saving in the database.
     *
     * @param registerRequest the request object containing user registration details
     * @return a mapped {@link User} instance
     */
    @Named("mapForSaving")
    default User mapForSaving(RegisterRequest registerRequest) {
        return User.builder()
                .userId(UUID.randomUUID().toString())
                .email(registerRequest.getEmail())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .userType(registerRequest.getUserType())
                .build();
    }

    /**
     * Initializes the mapper instance using MapStruct's generated implementation.
     *
     * @return the initialized {@link RegisterRequestToUserMapper} instance
     */
    static RegisterRequestToUserMapper initialize() {
        return Mappers.getMapper(RegisterRequestToUserMapper.class);
    }

}
