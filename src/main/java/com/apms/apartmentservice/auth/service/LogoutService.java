package com.apms.apartmentservice.auth.service;

import com.apms.apartmentservice.auth.model.dto.request.TokenInvalidateRequest;

/**
 * Service interface for handling user logout operations.
 */
public interface LogoutService {

    /**
     * Invalidates the user's tokens during logout.
     *
     * @param tokenInvalidateRequest the request containing token IDs to invalidate
     */
    void logout(final TokenInvalidateRequest tokenInvalidateRequest);

}
