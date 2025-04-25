package com.apms.apartmentservice.apartment.model.dto;

import com.apms.apartmentservice.common.model.dto.BaseDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class PaymentTypeDTO extends BaseDTO {
    private String paymentTypeId;
    private String type;
    private String description;
    private String icon;
    private String provider;
    private String providerUrl;
    private String providerApiKey;
    private String providerApiSecret;
}
