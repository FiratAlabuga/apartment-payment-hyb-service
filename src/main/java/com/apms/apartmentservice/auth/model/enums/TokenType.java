package com.apms.apartmentservice.auth.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum representing supported token types.
 */
@Getter
@RequiredArgsConstructor
public enum TokenType {

    BEARER("Bearer");

    private final String value;

}
