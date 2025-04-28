package com.apms.apartmentservice.auth.model.mapper;

import com.apms.apartmentservice.auth.model.dto.TokenDTO;
import com.apms.apartmentservice.auth.model.dto.response.TokenResponse;
import com.apms.apartmentservice.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TokenDTOToTokenResponseMapper extends BaseMapper<TokenDTO, TokenResponse> {
    /**
     * Maps a {@link TokenDTO} to a {@link TokenResponse}.
     *
     * @param tokenDTO the token data transfer object
     * @return the mapped {@link TokenResponse}
     */
    TokenResponse map(TokenDTO tokenDTO);

    /**
     * Initializes the mapper instance.
     *
     * @return the initialized {@link TokenDTOToTokenResponseMapper} instance
     */
    static TokenDTOToTokenResponseMapper initialize() {
        return Mappers.getMapper(TokenDTOToTokenResponseMapper.class);
    }
}
