package com.apms.apartmentservice.apartment.service;

import com.apms.apartmentservice.apartment.model.dto.ResidentDTO;

import java.util.List;

public interface ResidentService {
    ResidentDTO createResident(ResidentDTO residentDTO);
    ResidentDTO getResidentById(String residentId);
    ResidentDTO updateResident(String residentId, ResidentDTO residentDTO);
    boolean deleteResident(String residentId);
    List<ResidentDTO> getAllResidents();
}
