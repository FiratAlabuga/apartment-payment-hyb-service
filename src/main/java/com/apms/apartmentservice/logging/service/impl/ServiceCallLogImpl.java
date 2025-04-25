package com.apms.apartmentservice.logging.service.impl;

import com.apms.apartmentservice.logging.model.domain.ServiceCallLog;
import com.apms.apartmentservice.logging.repository.ServiceCallLogRepository;
import com.apms.apartmentservice.logging.service.ServiceCallLogService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceCallLogImpl implements ServiceCallLogService {
    private final ServiceCallLogRepository serviceCallLogRepository;
    @Override
    public void saveLogToDatabase(ServiceCallLog serviceCallLog) {
        // Here you would typically call the repository to save the log
        serviceCallLog.setTime(LocalDateTime.now());
        serviceCallLogRepository.save(serviceCallLog);
        // This is a placeholder implementation
        System.out.println("Saving log to database: " + serviceCallLog);
    }
}
