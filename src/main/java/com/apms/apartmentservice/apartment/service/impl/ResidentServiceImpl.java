package com.apms.apartmentservice.apartment.service.impl;

import com.apms.apartmentservice.apartment.exception.ResidentNotFoundException;
import com.apms.apartmentservice.apartment.model.dto.ResidentDTO;
import com.apms.apartmentservice.apartment.model.mapper.ResidentDTOToResidentMapper;
import com.apms.apartmentservice.apartment.model.mapper.ResidentToResidentDTOMapper;
import com.apms.apartmentservice.apartment.repository.ResidentRepository;
import com.apms.apartmentservice.apartment.service.ResidentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final ResidentDTOToResidentMapper residentDTOToResidentMapper = ResidentDTOToResidentMapper.initialize();
    private final ResidentToResidentDTOMapper residentToResidentDTOMapper = ResidentToResidentDTOMapper.initialize();

    @Override
    public ResidentDTO createResident(ResidentDTO residentDTO) {
        // Map the DTO to entity
        var resident = residentDTOToResidentMapper.map(residentDTO);
        // Save the resident
        residentRepository.save(resident);
        return residentToResidentDTOMapper.map(resident);
    }

    @Override
    public ResidentDTO getResidentById(String residentId) {
        // Find the resident by ID and map it to DTO
        var resident = residentRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResidentNotFoundException(residentId));
        return residentToResidentDTOMapper.map(resident);
    }

    @Override
    public ResidentDTO updateResident(String residentId, ResidentDTO residentDTO) {
        // Find the existing resident
        var existingResident = residentRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResidentNotFoundException(residentId));
        // Map the updated fields from DTO to entity
        residentDTOToResidentMapper.updateEntity(residentDTO, existingResident);
        // Save the updated resident
        residentRepository.save(existingResident);
        return residentToResidentDTOMapper.map(existingResident);
    }

    @Override
    public boolean deleteResident(String residentId) {
        // Find the existing resident
        var existingResident = residentRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResidentNotFoundException(residentId));
        // Delete the resident
        existingResident.setStatus(false);
        return true;
    }

    @Override
    public List<ResidentDTO> getAllResidents() {
        return residentRepository.findAll()
                .stream()
                .map(residentToResidentDTOMapper::map)
                .toList();
    }
}
