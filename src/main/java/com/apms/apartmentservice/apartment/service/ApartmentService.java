package com.apms.apartmentservice.apartment.service;

import com.apms.apartmentservice.apartment.model.dto.ApartmentDTO;

import java.util.List;

public interface ApartmentService {
    List<ApartmentDTO> getAllApartments();
    ApartmentDTO getApartmentById(String apartmentId);
    ApartmentDTO createApartment(ApartmentDTO apartmentDTO);
    ApartmentDTO updateApartment(String apartmentId, ApartmentDTO apartmentDTO);
    boolean deleteApartment(String apartmentId);
}
