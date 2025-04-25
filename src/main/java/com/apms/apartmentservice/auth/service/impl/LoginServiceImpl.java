package com.apms.apartmentservice.auth.service.impl;

import com.apms.apartmentservice.auth.exception.PasswordNotValidException;
import com.apms.apartmentservice.auth.exception.UserNotFoundException;
import com.apms.apartmentservice.auth.model.domain.User;
import com.apms.apartmentservice.auth.model.dto.TokenDTO;
import com.apms.apartmentservice.auth.model.dto.request.LoginRequest;
import com.apms.apartmentservice.auth.repository.UserRepository;
import com.apms.apartmentservice.auth.service.LoginService;
import com.apms.apartmentservice.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for authenticating users and issuing JWT tokens.
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    /**
     * Authenticates the user based on the login request and issues a new {@link TokenDTO}.
     *
     * @param loginRequest the request containing login credentials
     * @return a {@link TokenDTO} if authentication is successful
     */
    @Override
    public TokenDTO login(LoginRequest loginRequest) {

        final User userEntityFromDB = userRepository
                .findUserByEmail(loginRequest.getEmail())
                .orElseThrow(
                        () -> new UserNotFoundException(loginRequest.getEmail())
                );

        if (Boolean.FALSE.equals(passwordEncoder.matches(
                loginRequest.getPassword(), userEntityFromDB.getPassword()))) {
            throw new PasswordNotValidException();
        }

        return tokenService.generateToken(userEntityFromDB.getClaims());
    }

}
