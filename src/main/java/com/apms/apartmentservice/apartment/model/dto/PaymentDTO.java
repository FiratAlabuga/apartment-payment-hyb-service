package com.apms.apartmentservice.apartment.model.dto;

import com.apms.apartmentservice.common.model.dto.BaseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Domain model representing a user in the system.
 * <p>
 * Inherits common auditing fields from {@link BaseDTO}.
 * </p>
 */
@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class PaymentDTO extends BaseDTO {
    private String paymentId;
    private String userId;
    private String transactionId;
    private BigDecimal amount;
    private String currency;
}
