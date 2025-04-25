package com.apms.apartmentservice.logging.service;

import com.apms.apartmentservice.logging.model.domain.ServiceCallLog;

public interface ServiceCallLogService {
    /**
     * Saves the provided {@link ServiceCallLog} to the database.
     *
     * @param serviceCallLog the log entity to persist
     */
    void saveLogToDatabase(final ServiceCallLog serviceCallLog);
}
