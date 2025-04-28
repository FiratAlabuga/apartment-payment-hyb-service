package com.apms.apartmentservice.auth.repository;

import com.apms.apartmentservice.auth.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for user data persistence and retrieval.
 * Provides custom query methods for operations based on user email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Checks whether a user exists with the given email address.
     *
     * @param email the email address to check
     * @return {@code true} if a user with the given email exists; {@code false} otherwise
     */
    boolean existsUserByEmail(final String email);

    /**
     * Retrieves a user entity by their email address.
     *
     * @param email the email of the user to retrieve
     * @return an {@link Optional} containing the {@link User} if found, or empty otherwise
     */
    Optional<User> findUserByEmail(final String email);

    Optional<User> findById(final Long id);
    Optional<User> findByUserId(final String id);

}