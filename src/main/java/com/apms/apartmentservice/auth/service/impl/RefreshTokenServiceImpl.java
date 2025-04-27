package com.apms.apartmentservice.auth.service.impl;

import com.apms.apartmentservice.auth.exception.UserNotFoundException;
import com.apms.apartmentservice.auth.exception.UserStatusNotValidException;
import com.apms.apartmentservice.auth.model.domain.User;
import com.apms.apartmentservice.auth.model.dto.TokenDTO;
import com.apms.apartmentservice.auth.model.dto.request.TokenRefreshRequest;
import com.apms.apartmentservice.auth.model.enums.TokenClaims;
import com.apms.apartmentservice.auth.model.enums.UserStatus;
import com.apms.apartmentservice.auth.repository.UserRepository;
import com.apms.apartmentservice.auth.service.RefreshTokenService;
import com.apms.apartmentservice.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service implementation for refreshing JWT access tokens using a valid refresh token.
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    /**
     * Generates a new access token using the provided refresh token.
     *
     * @param tokenRefreshRequest the request containing the refresh token
     * @return a new {@link TokenDTO} containing the refreshed access and refresh tokens
     */
    @Override
    public TokenDTO refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String adminId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final User userEntityFromDB = userRepository
                .findById(adminId)
                .orElseThrow(UserNotFoundException::new);

        this.validateAdminStatus(userEntityFromDB);

        return tokenService.generateToken(
                userEntityFromDB.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        );
    }

    private void validateAdminStatus(final User userEntity) {
        if (!(UserStatus.ACTIVE.equals(userEntity.getUserStatus()))) {
            throw new UserStatusNotValidException("UserStatus = " + userEntity.getUserStatus());
        }
    }

}
