package com.apms.apartmentservice.auth.model.domain;

import com.apms.apartmentservice.common.model.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Entity representing a JWT token that has been invalidated (e.g., due to logout or blacklisting).
 * Stores the unique token ID (jti) to prevent reuse of previously issued tokens.
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "INVALID_TOKEN")
public class InvalidToken extends BaseEntity {

    @Column(name = "TOKEN_ID")
    private String tokenId;
}
