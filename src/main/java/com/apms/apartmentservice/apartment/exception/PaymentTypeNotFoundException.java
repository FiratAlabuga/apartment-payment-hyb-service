package com.apms.apartmentservice.apartment.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class PaymentTypeNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2569721915183083303L;

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    private static final String DEFAULT_MESSAGE = """
            PaymentType not found!
            """;

    /**
     * Constructs a {@code PaymentTypeNotFoundException} with a default message.
     */
    public PaymentTypeNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a {@code PaymentTypeNotFoundException} with additional details.
     *
     * @param message additional description for debugging
     */
    public PaymentTypeNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
