package com.apms.apartmentservice.auth.service.impl;

import com.apms.apartmentservice.auth.exception.TokenAlreadyInvalidatedException;
import com.apms.apartmentservice.auth.model.domain.InvalidToken;
import com.apms.apartmentservice.auth.repository.InvalidTokenRepository;
import com.apms.apartmentservice.auth.service.InvalidTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvalidTokenServiceImpl implements InvalidTokenService {
    private final InvalidTokenRepository invalidTokenRepository;

    @Override
    public void invalidateTokens(Set<String> tokenIds) {
        final Set<InvalidToken> invalidTokenEntities = tokenIds.stream()
                .map(tokenId -> InvalidToken.builder()
                        .tokenId(tokenId)
                        .build()
                )
                .collect(Collectors.toSet());

        invalidTokenRepository.saveAll(invalidTokenEntities);

    }

    @Override
    public void checkForInvalidityOfToken(String tokenId) {
        final boolean isTokenInvalid = invalidTokenRepository.findByTokenId(tokenId).isPresent();

        if (isTokenInvalid) {
            throw new TokenAlreadyInvalidatedException(tokenId);
        }
    }
}
