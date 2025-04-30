package com.apms.apartmentservice.common.model.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Abstract base class for domain models that require auditing fields.
 * Contains metadata for creation and update operations, including user and timestamp.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseDTO {
    protected String createdBy;
    protected LocalDateTime createdAt;
    protected String updatedBy;
    protected LocalDateTime updatedAt;
    protected Boolean status;
    protected Integer version;
}
