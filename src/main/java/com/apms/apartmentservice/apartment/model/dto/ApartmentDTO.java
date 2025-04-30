package com.apms.apartmentservice.apartment.model.dto;

import com.apms.apartmentservice.common.model.dto.BaseDTO;
import jakarta.persistence.MappedSuperclass;
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
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public class ApartmentDTO extends BaseDTO {
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String block;
    private String number;
    private int totalUnits;
    private int availableUnits;
}
