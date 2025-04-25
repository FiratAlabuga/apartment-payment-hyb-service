package com.apms.apartmentservice.apartment.service.impl;

import com.apms.apartmentservice.apartment.exception.ApartmentNotFoundException;
import com.apms.apartmentservice.apartment.model.domain.Apartment;
import com.apms.apartmentservice.apartment.model.dto.ApartmentDTO;
import com.apms.apartmentservice.apartment.model.mapper.ApartmentDTOToApartmentMapper;
import com.apms.apartmentservice.apartment.model.mapper.ApartmentToApartmentDTOMapper;
import com.apms.apartmentservice.apartment.repository.ApartmentRepository;
import com.apms.apartmentservice.apartment.service.ApartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
    private final ApartmentRepository apartmentRepository;
    private final ApartmentDTOToApartmentMapper apartmentDTOToApartmentMapper = ApartmentDTOToApartmentMapper.initialize();
    private final ApartmentToApartmentDTOMapper apartmentToApartmentDTOMapper = ApartmentToApartmentDTOMapper.initialize();

    @Override
    public List<ApartmentDTO> getAllApartments() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentToApartmentDTOMapper::map)
                .toList();
    }

    @Override
    public ApartmentDTO getApartmentById(String apartmentId) {
        // Find the apartment by ID and map it to DTO
        // If not found, throw an exception
        Apartment apartment = apartmentRepository.findByApartmentId(apartmentId)
                .orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
        return apartmentToApartmentDTOMapper.map(apartment);
    }

    @Override
    public ApartmentDTO createApartment(ApartmentDTO apartmentDTO) {
        // Map the DTO to entity
        Apartment apartment = apartmentDTOToApartmentMapper.map(apartmentDTO);
        // Check if the apartment ID is null
        apartmentRepository.save(apartment);
        return apartmentToApartmentDTOMapper.map(apartment);
    }

    @Override
    public ApartmentDTO updateApartment(String apartmentId, ApartmentDTO apartmentDTO) {
        // Find the existing apartment
        Apartment existingApartment = apartmentRepository.findByApartmentId(apartmentId)
                .orElseThrow(() -> new ApartmentNotFoundException(apartmentId));
        // Map the updated fields from DTO to entity
        apartmentDTOToApartmentMapper.updateEntity(apartmentDTO, existingApartment);
        // Save the updated apartment
        apartmentRepository.save(existingApartment);
        return apartmentToApartmentDTOMapper.map(existingApartment);
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
