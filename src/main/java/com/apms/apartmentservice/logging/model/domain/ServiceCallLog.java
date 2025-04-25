package com.apms.apartmentservice.logging.model.domain;

import com.apms.apartmentservice.common.model.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Entity representing a log record in the system.
 * Logs are typically captured by AOP for auditing requests, responses, exceptions,
 * and user operations across the application.
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "SERVICE_CALL_LOG")
public class ServiceCallLog extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String message;

    private String endpoint;

    private String method;

    @Enumerated(EnumType.STRING)
    private HttpStatus status;

    private String userInfo;

    private String errorType;

    @Column(columnDefinition = "TEXT")
    private String response;

    private String operation;

    private LocalDateTime time;
}
