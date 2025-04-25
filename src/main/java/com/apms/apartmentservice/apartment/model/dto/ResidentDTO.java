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
public class ResidentDTO extends BaseDTO {
    private String residentId;
    private String apartmentId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String block;
    private String number;
    private BigDecimal balance;
}
