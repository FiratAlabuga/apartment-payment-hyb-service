package com.apms.apartmentservice.apartment.api.controller;

import com.apms.apartmentservice.apartment.model.dto.ApartmentDTO;
import com.apms.apartmentservice.apartment.service.ApartmentService;
import com.apms.apartmentservice.common.model.dto.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/apartment")
@RequiredArgsConstructor
@Validated
@Tag(name = "Apartment", description = "Handles apartments operations.")
public class ApartmentApiController {
    private final ApartmentService apartmentService;

    /**
     * Endpoint to create a new apartment.
     *
     * @param apartmentDTO The {@link ApartmentDTO} object containing user registration details.
     * @return A {@link BaseApiResponse} indicating the success of the registration operation.
     */
    @Operation(
            summary = "Create Apartment",
            description = "Create apartment in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create apartment successfully"),
                    @ApiResponse(responseCode = "400", description = "Create apartment failed"),
                    @ApiResponse(responseCode = "409", description = "Create apartment already exists")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-apartment")
    public BaseApiResponse<?> createApartment(@Validated @RequestBody ApartmentDTO apartmentDTO) {
        ApartmentDTO apartment = apartmentService.createApartment(apartmentDTO);
        return BaseApiResponse.successOf(apartment);
    }
    /**
     * Endpoint to get an apartment by ID.
     *
     * @param apartmentId The ID of the apartment to retrieve.
     * @return A {@link BaseApiResponse} containing the apartment details.
     */
    @Operation(
            summary = "Get Apartment by ID",
            description = "Retrieve apartment details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Apartment found"),
                    @ApiResponse(responseCode = "404", description = "Apartment not found")
            })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/get-apartment/{apartmentId}")
    public BaseApiResponse<?> getApartmentById(@PathVariable(name = "apartmentId") String apartmentId) {
        ApartmentDTO apartment = apartmentService.getApartmentById(apartmentId);
        return BaseApiResponse.successOf(apartment);
    }
    /**
     * Endpoint to get all apartments.
     *
     * @return A {@link BaseApiResponse} containing the list of apartments.
     */
    @Operation(
            summary = "Get All Apartments",
            description = "Retrieve all apartments.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Apartments found"),
                    @ApiResponse(responseCode = "404", description = "Apartments not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get-all-apartments")
    public BaseApiResponse<?> getAllApartments() {
        return BaseApiResponse.successOf(apartmentService.getAllApartments());
    }
    /**
     * Endpoint to update an apartment by ID.
     *
     * @param apartmentId The ID of the apartment to update.
     * @param apartmentDTO The updated apartment details.
     * @return A {@link BaseApiResponse} containing the updated apartment details.
     */
    @Operation(
            summary = "Update Apartment by ID",
            description = "Update apartment details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Apartment updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Apartment not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update-apartment/{apartmentId}")
    public BaseApiResponse<?> updateApartment(@PathVariable(name = "apartmentId") String apartmentId, @Validated @RequestBody  ApartmentDTO apartmentDTO) {
        ApartmentDTO updatedApartment = apartmentService.updateApartment(apartmentId, apartmentDTO);
        return BaseApiResponse.successOf(updatedApartment);
    }
    /**
     * Endpoint to delete an apartment by ID.
     *
     * @param apartmentId The ID of the apartment to delete.
     * @return A {@link BaseApiResponse} indicating the success of the deletion operation.
     */
    @Operation(
            summary = "Delete Apartment by ID",
            description = "Delete apartment by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Apartment deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Apartment not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-apartment")
    public BaseApiResponse<?> deleteApartment(String apartmentId) {
        boolean isDeleted = apartmentService.deleteApartment(apartmentId);
        return BaseApiResponse.successOf(isDeleted);
    }
}
