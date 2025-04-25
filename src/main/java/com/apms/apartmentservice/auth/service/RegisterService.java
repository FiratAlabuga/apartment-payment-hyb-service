package com.apms.apartmentservice.auth.service;

import com.apms.apartmentservice.auth.model.dto.UserDTO;
import com.apms.apartmentservice.auth.model.dto.request.RegisterRequest;

/**
 * Service interface for handling user registration operations.
 */
public interface RegisterService {

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param registerRequest the request containing user registration details
     * @return the newly registered {@link UserDTO}
     */
    UserDTO registerUser(final RegisterRequest registerRequest);
}
