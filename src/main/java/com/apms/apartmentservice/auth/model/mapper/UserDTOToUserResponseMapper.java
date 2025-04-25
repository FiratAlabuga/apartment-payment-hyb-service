package com.apms.apartmentservice.auth.model.mapper;

import com.apms.apartmentservice.auth.model.domain.User;
import com.apms.apartmentservice.auth.model.dto.UserDTO;
import com.apms.apartmentservice.auth.model.dto.response.UserResponse;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.factory.Mappers;

public interface UserDTOToUserResponseMapper extends BaseMapper<UserDTO, UserResponse> {
    /**
     * Maps a {@link UserDTO} to a {@link User}.
     *
     * @param userDTO the user data transfer object
     * @return the mapped {@link User}
     */
    UserResponse map(UserDTO userDTO);

    /**
     * Initializes the mapper instance.
     *
     * @return the initialized {@link UserDTOToUserResponseMapper} instance
     */
    static UserDTOToUserResponseMapper initialize() {
        return Mappers.getMapper(UserDTOToUserResponseMapper.class);
    }
}
