package com.apms.apartmentservice.auth.model.dto;

import com.apms.apartmentservice.auth.model.enums.UserStatus;
import com.apms.apartmentservice.auth.model.enums.UserType;
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
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserType userType;
    private UserStatus userStatus;
}
