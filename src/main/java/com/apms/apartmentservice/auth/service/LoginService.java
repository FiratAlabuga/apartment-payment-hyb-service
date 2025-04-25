package com.apms.apartmentservice.auth.service;

import com.apms.apartmentservice.auth.model.dto.TokenDTO;
import com.apms.apartmentservice.auth.model.dto.request.LoginRequest;

/**
 * Service interface for authenticating users and issuing JWT tokens.
 */
public interface LoginService {

    /**
     * Authenticates the user based on the login request and issues a new {@link TokenDTO}.
     *
     * @param loginRequest the request containing login credentials
     * @return a {@link TokenDTO} if authentication is successful
     */
    TokenDTO login(final LoginRequest loginRequest);

}
