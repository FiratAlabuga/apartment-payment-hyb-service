package com.apms.apartmentservice.common.model.domain;

import com.apms.apartmentservice.auth.model.enums.TokenClaims;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Abstract base class for JPA entities, providing common auditing fields.
 * This class should be extended by all entities that require auditing.
 */
@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    @CreatedBy
    private String createdBy;

    @Column(name = "UPDATED_AT")
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    @LastModifiedBy
    private String updatedBy;

    @Column(name = "STATUS", nullable = false)
    private Boolean status = true;

    @Version
    @Column(name = "VERSION")
    private Integer version;

    /**
     * JPA callback that sets {@code createdAt} and {@code createdBy} before persisting the entity.
     * Retrieves the current user's email from the {@link SecurityContextHolder}. If the user is anonymous,
     * sets the createdBy field to "anonymousUser".
     */
    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = true;
        }
        this.createdBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(user -> !"anonymousUser".equals(user))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(TokenClaims.USER_EMAIL.getValue()).toString())
                .orElse("anonymousUser");
        this.createdAt = LocalDateTime.now();
    }

    /**
     * JPA callback that sets {@code updatedAt} and {@code updatedBy} before updating the entity.
     * Retrieves the current user's email from the {@link SecurityContextHolder}. If the user is anonymous,
     * sets the updatedBy field to "anonymousUser".
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(user -> !"anonymousUser".equals(user))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(TokenClaims.USER_EMAIL.getValue()).toString())
                .orElse("anonymousUser");
        this.updatedAt = LocalDateTime.now();
    }
}
