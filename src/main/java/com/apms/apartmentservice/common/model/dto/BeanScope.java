package com.apms.apartmentservice.common.model.dto;

import lombok.experimental.UtilityClass;

/**
 * Utility class for defining Spring bean scope constants.
 */
@UtilityClass
public class BeanScope {

    /**
     * Bean instances defined with "request" scope will create an instance that is available for the duration of an HTTP request.
     */
    public static final String SCOPE_REQUEST = "request";

}