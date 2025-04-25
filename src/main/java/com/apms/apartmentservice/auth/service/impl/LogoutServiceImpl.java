package com.apms.apartmentservice.auth.service.impl;

import com.apms.apartmentservice.auth.model.dto.request.TokenInvalidateRequest;
import com.apms.apartmentservice.auth.service.InvalidTokenService;
import com.apms.apartmentservice.auth.service.LogoutService;
import com.apms.apartmentservice.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Service implementation for handling user logout operations.
 */
@Service
@RequiredArgsConstructor
public class LogoutServiceImpl implements LogoutService {

    private final TokenService tokenService;
    private final InvalidTokenService invalidTokenService;

    /**
     * Invalidates the user's tokens during logout.
     *
     * @param tokenInvalidateRequest the request containing token IDs to invalidate
     */
    @Override
    public void logout(TokenInvalidateRequest tokenInvalidateRequest) {

        tokenService.verifyAndValidate(
                Set.of(
                        tokenInvalidateRequest.getAccessToken(),
                        tokenInvalidateRequest.getRefreshToken()
                )
        );

        final String accessTokenId = tokenService
                .getPayload(tokenInvalidateRequest.getAccessToken())
                .getId();

        invalidTokenService.checkForInvalidityOfToken(accessTokenId);


        final String refreshTokenId = tokenService
                .getPayload(tokenInvalidateRequest.getRefreshToken())
                .getId();

        invalidTokenService.checkForInvalidityOfToken(refreshTokenId);

        invalidTokenService.invalidateTokens(Set.of(accessTokenId,refreshTokenId));

    }

}