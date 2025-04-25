package com.apms.apartmentservice.auth.service;

import com.apms.apartmentservice.auth.model.dto.TokenDTO;
import com.apms.apartmentservice.auth.model.dto.request.TokenRefreshRequest;

/**
 * Service interface for refreshing JWT access tokens using a valid refresh token.
 */
public interface RefreshTokenService {

    /**
     * Generates a new access token using the provided refresh token.
     *
     * @param tokenRefreshRequest the request containing the refresh token
     * @return a new {@link TokenDTO} containing the refreshed access and refresh tokens
     */
    TokenDTO refreshToken(final TokenRefreshRequest tokenRefreshRequest);

}