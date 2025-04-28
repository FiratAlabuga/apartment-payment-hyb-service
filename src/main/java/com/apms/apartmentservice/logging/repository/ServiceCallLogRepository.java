package com.apms.apartmentservice.logging.repository;

import com.apms.apartmentservice.logging.model.domain.ServiceCallLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.Optional;

@Repository
public interface ServiceCallLogRepository extends JpaRepository<ServiceCallLog, Long> {

}
