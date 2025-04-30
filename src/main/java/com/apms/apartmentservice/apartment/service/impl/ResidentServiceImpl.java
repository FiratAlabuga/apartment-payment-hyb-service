package com.apms.apartmentservice.apartment.service.impl;

import com.apms.apartmentservice.apartment.exception.ResidentNotFoundException;
import com.apms.apartmentservice.apartment.model.dto.ResidentDTO;
import com.apms.apartmentservice.apartment.model.mapper.ResidentMapper;
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
    private final ResidentMapper residentMapper = ResidentMapper.initialize();

    @Override
    public ResidentDTO createResident(ResidentDTO residentDTO) {
        // Map the DTO to entity
        var resident = residentMapper.toEntity(residentDTO);
        // Save the resident
        residentRepository.save(resident);
        return residentMapper.toDto(resident);
    }

    @Override
    public ResidentDTO getResidentById(String residentId) {
        // Find the resident by ID and map it to DTO
        var resident = residentRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResidentNotFoundException(residentId));
        return residentMapper.toDto(resident);
    }

    @Override
    public ResidentDTO updateResident(String residentId, ResidentDTO residentDTO) {
        // Find the existing resident
        var existingResident = residentRepository.findByResidentId(residentId)
                .orElseThrow(() -> new ResidentNotFoundException(residentId));
        // Map the updated fields from DTO to entity
        residentMapper.updateEntityFromDto(residentDTO, existingResident);
        // Save the updated resident
        residentRepository.save(existingResident);
        return residentMapper.toDto(existingResident);
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
                .map(residentMapper::toDto)
                .toList();
    }
}
