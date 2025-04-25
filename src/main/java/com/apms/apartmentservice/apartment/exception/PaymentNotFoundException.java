package com.apms.apartmentservice.apartment.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class PaymentNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2569721915183083303L;

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    private static final String DEFAULT_MESSAGE = """
            Payment not found!
            """;

    /**
     * Constructs a {@code PaymentNotFoundException} with a default message.
     */
    public PaymentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a {@code PaymentNotFoundException} with additional details.
     *
     * @param message additional description for debugging
     */
    public PaymentNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
