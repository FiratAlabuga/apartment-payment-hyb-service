package com.apms.apartmentservice.auth.model.mapper;

import com.apms.apartmentservice.auth.model.domain.User;
import com.apms.apartmentservice.auth.model.dto.UserDTO;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.factory.Mappers;

public interface UserToUserDTOMapper extends BaseMapper<User, UserDTO> {
    /**
     * Maps a {@link User} to a {@link UserDTO}.
     *
     * @param user the user entity
     * @return the mapped {@link UserDTO}
     */
    UserDTO map(User user);

    /**
     * Initializes the mapper instance.
     *
     * @return the initialized {@link UserToUserDTOMapper} instance
     */
    static UserToUserDTOMapper initialize() {
        return Mappers.getMapper(UserToUserDTOMapper.class);
    }
}
