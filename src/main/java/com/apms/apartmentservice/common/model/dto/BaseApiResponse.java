package com.apms.apartmentservice.common.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Represents a standard API response structure.
 *
 * @param <T> the type of response payload
 */
@Getter
@Builder
public class BaseApiResponse<T> {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();

    private HttpStatus httpStatus;

    private Boolean isSuccess;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T response;

    /**
     * A constant success response without a payload.
     */
    public static final BaseApiResponse<Void> SUCCESS = BaseApiResponse.<Void>builder()
            .httpStatus(HttpStatus.OK)
            .isSuccess(true)
            .build();

    /**
     * Creates a successful response with a payload.
     *
     * @param response the response dat
     * @param <T> the type of the response
     * @return a {@link BaseApiResponse} with status 200 OK
     */
    public static <T> BaseApiResponse<T> successOf(final T response) {
        return BaseApiResponse.<T>builder()
                .httpStatus(HttpStatus.OK)
                .isSuccess(true)
                .response(response)
                .build();
    }

}
