package com.apms.apartmentservice.apartment.api.controller;

import com.apms.apartmentservice.apartment.model.dto.ResidentDTO;
import com.apms.apartmentservice.apartment.service.ResidentService;
import com.apms.apartmentservice.common.model.dto.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resident")
@RequiredArgsConstructor
@Validated
@Tag(name = "Resident", description = "Handles resident operations.")
public class ResidentApiController {
    private final ResidentService residentService;
    /**
     * Endpoint to create a new resident.
     *
     * @param residentDTO The {@link ResidentDTO} object containing user registration details.
     * @return A {@link BaseApiResponse} indicating the success of the registration operation.
     */
    @Operation(
            summary = "Create Resident",
            description = "Create resident in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create resident successfully"),
                    @ApiResponse(responseCode = "400", description = "Create resident failed"),
                    @ApiResponse(responseCode = "409", description = "Create resident already exists")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-resident")
    public BaseApiResponse<?> createResident(@Validated @RequestBody ResidentDTO residentDTO) {
        ResidentDTO resident = residentService.createResident(residentDTO);
        return BaseApiResponse.successOf(resident);
    }

    /**
     * Endpoint to get a resident by ID.
     *
     * @param residentId The ID of the resident to retrieve.
     * @return A {@link BaseApiResponse} containing the resident details.
     */
    @Operation(
            summary = "Get Resident by ID",
            description = "Retrieve resident details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resident found"),
                    @ApiResponse(responseCode = "404", description = "Resident not found")
            })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/get-resident/{residentId}")
    public BaseApiResponse<?> getResidentById(@PathVariable(name = "residentId") String residentId) {
        ResidentDTO resident = residentService.getResidentById(residentId);
        return BaseApiResponse.successOf(resident);
    }
    /**
     * Endpoint to get all residents.
     *
     * @return A {@link BaseApiResponse} containing the list of all residents.
     */
    @Operation(
            summary = "Get All Residents",
            description = "Retrieve all residents.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Residents found"),
                    @ApiResponse(responseCode = "404", description = "Residents not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get-all-residents")
    public BaseApiResponse<?> getAllPayments() {
        return BaseApiResponse.successOf(residentService.getAllResidents());
    }
    /**
     * Endpoint to update a resident by ID.
     *
     * @param residentId The ID of the resident to update.
     * @param residentDTO The updated resident details.
     * @return A {@link BaseApiResponse} containing the updated resident details.
     */
    @Operation(
            summary = "Update Resident by ID",
            description = "Update resident details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resident updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Resident not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update-resident/{residentId}")
    public BaseApiResponse<?> updateResident(@PathVariable(name = "residentId") String residentId,@Validated @RequestBody ResidentDTO residentDTO) {
        ResidentDTO updatedResident = residentService.updateResident(residentId, residentDTO);
        return BaseApiResponse.successOf(updatedResident);
    }
    /**
     * Endpoint to delete a resident by ID.
     *
     * @param residentId The ID of the resident to delete.
     * @return A {@link BaseApiResponse} indicating the success of the deletion operation.
     */
    @Operation(
            summary = "Delete Resident by ID",
            description = "Delete resident by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resident deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Resident not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-resident")
    public BaseApiResponse<?> deleteResident(String residentId) {
        boolean isDeleted = residentService.deleteResident(residentId);
        return BaseApiResponse.successOf(isDeleted);
    }
}
