package com.apms.apartmentservice.auth.repository;

import com.apms.apartmentservice.auth.model.domain.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing invalidated JWT tokens.
 * Provides data access methods for {@link InvalidToken}, such as finding by token ID.
 */
@Repository
public interface InvalidTokenRepository extends JpaRepository<InvalidToken, Long> {

    /**
     * Finds an {@link InvalidToken} by its token ID.
     *
     * @param tokenId the unique identifier of the JWT token (jti claim)
     * @return an {@link Optional} containing the {@link InvalidToken} if found, or empty otherwise
     */
    Optional<InvalidToken> findByTokenId(final String tokenId);

}
