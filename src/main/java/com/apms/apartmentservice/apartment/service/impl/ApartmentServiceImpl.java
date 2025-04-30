package com.apms.apartmentservice.apartment.service.impl;

import com.apms.apartmentservice.apartment.exception.ApartmentNotFoundException;
import com.apms.apartmentservice.apartment.model.domain.Apartment;
import com.apms.apartmentservice.apartment.model.dto.ApartmentDTO;
import com.apms.apartmentservice.apartment.model.mapper.ApartmentMapper;
import com.apms.apartmentservice.apartment.repository.ApartmentRepository;
import com.apms.apartmentservice.apartment.service.ApartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    //private final ApartmentDTOToApartmentMapper apartmentDTOToApartmentMapper = ApartmentDTOToApartmentMapper.initialize();
    //private final ApartmentToApartmentDTOMapper apartmentToApartmentDTOMapper = ApartmentToApartmentDTOMapper.initialize();
    private final ApartmentMapper apartmentMapper = ApartmentMapper.initialize();

    @Override
    public List<ApartmentDTO> getAllApartments() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentMapper::toDto)
                .toList();
    }

    @Override
    public ApartmentDTO getApartmentById(String apartmentId) {
        // Find the apartment by ID and map it to DTO
        // If not found, throw an exception
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId)
                .orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
        return apartmentMapper.toDto(apartment);
    }

    @Override
    public ApartmentDTO createApartment(ApartmentDTO apartmentDTO) {
        // Map the DTO to entity
        Apartment apartment = apartmentMapper.toEntity(apartmentDTO);
        apartment.setApartmentId(UUID.randomUUID().toString());
        // Check if the apartment ID is null
        apartmentRepository.save(apartment);
        return apartmentMapper.toDto(apartment);
    }



    @Override
    public ApartmentDTO updateApartment(String apartmentId, ApartmentDTO apartmentDTO) {
        // Find the existing apartment
        Apartment existingApartment = apartmentRepository.findByApartmentId(apartmentId)
                .orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
        // Map the updated fields from DTO to entity
        apartmentMapper.updateEntityFromDto(apartmentDTO, existingApartment);
        // Save the updated apartment
        apartmentRepository.save(existingApartment);
        return apartmentMapper.toDto(existingApartment);
    }

    @Override
    public boolean deleteApartment(String apartmentId) {
        // Find the apartment by ID
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId)
                .orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
        // Check if the apartment has any residents
        if (apartment.getResidents() == null || apartment.getResidents().isEmpty()) {
            // Delete the apartment
            apartment.setStatus(false);
            apartmentRepository.save(apartment);
            return true;
        }
        return false;
    }
}
