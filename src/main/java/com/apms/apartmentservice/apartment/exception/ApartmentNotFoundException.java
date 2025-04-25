package com.apms.apartmentservice.apartment.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApartmentNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2569721915183083303L;

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    private static final String DEFAULT_MESSAGE = """
             Apartment not found!
            """;

    /**
     * Constructs a {@code ApartmentNotFoundException} with a default message.
     */
    public ApartmentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a {@code ApartmentNotFoundException} with additional details.
     *
     * @param message additional description for debugging
     */
    public ApartmentNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
