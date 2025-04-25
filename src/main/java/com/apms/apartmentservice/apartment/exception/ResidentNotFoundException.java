package com.apms.apartmentservice.apartment.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ResidentNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2569721915183083303L;

    public static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    private static final String DEFAULT_MESSAGE = """
            Resident not found!
            """;

    /**
     * Constructs a {@code ResidentNotFoundException} with a default message.
     */
    public ResidentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructs a {@code ResidentNotFoundException} with additional details.
     *
     * @param message additional description for debugging
     */
    public ResidentNotFoundException(final String message) {
        super(DEFAULT_MESSAGE + " " + message);
    }
}
