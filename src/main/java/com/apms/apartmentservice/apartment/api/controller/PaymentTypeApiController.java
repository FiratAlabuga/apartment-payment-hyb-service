package com.apms.apartmentservice.apartment.api.controller;

import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import com.apms.apartmentservice.apartment.model.dto.PaymentTypeDTO;
import com.apms.apartmentservice.apartment.service.PaymentTypeService;
import com.apms.apartmentservice.common.model.dto.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment-type")
@RequiredArgsConstructor
@Validated
@Tag(name = "Payment Type", description = "Handles payment types operations.")
public class PaymentTypeApiController {
    private final PaymentTypeService paymentTypeService;
    /**
     * Endpoint to create a new payment.
     *
     * @param paymentTypeDTO The {@link PaymentTypeDTO} object containing user registration details.
     * @return A {@link BaseApiResponse} indicating the success of the registration operation.
     */
    @Operation(
            summary = "Create Payment Type",
            description = "Create Payment Type in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create Payment type successfully"),
                    @ApiResponse(responseCode = "400", description = "Create Payment type failed"),
                    @ApiResponse(responseCode = "409", description = "Create Payment type already exists")
            }
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create-payment-type")
    public BaseApiResponse<?> createPayment(@Validated PaymentTypeDTO paymentTypeDTO) {
        PaymentTypeDTO paymentDTO = paymentTypeService.createPaymentType(paymentTypeDTO);
        return BaseApiResponse.successOf(paymentDTO);
    }
    /**
     * Endpoint to get a payment by ID.
     *
     * @param paymentTypeId The ID of the payment to retrieve.
     * @return A {@link BaseApiResponse} containing the payment details.
     */
    @Operation(
            summary = "Get Payment Type by ID",
            description = "Retrieve payment type details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment type found"),
                    @ApiResponse(responseCode = "404", description = "Payment type not found")
            })
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/get-payment-type")
    public BaseApiResponse<?> getPaymentTypeById(String paymentTypeId) {
        PaymentTypeDTO paymentTypeDTO = paymentTypeService.getPaymentTypeById(paymentTypeId);
        return BaseApiResponse.successOf(paymentTypeDTO);
    }
    /**
     * Endpoint to update a payment type by ID.
     *
     * @param paymentTypeId The ID of the payment type to update.
     * @param paymentTypeDTO The updated {@link PaymentTypeDTO} object.
     * @return A {@link BaseApiResponse} indicating the success of the update operation.
     */
    @Operation(
            summary = "Update Payment Type",
            description = "Update payment type details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment type updated successfully"),
                    @ApiResponse(responseCode = "400", description = "Payment type update failed"),
                    @ApiResponse(responseCode = "404", description = "Payment type not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/update-payment-type")
    public BaseApiResponse<?> updatePaymentType(String paymentTypeId, PaymentTypeDTO paymentTypeDTO) {
        PaymentTypeDTO updatedPaymentType = paymentTypeService.updatePaymentType(paymentTypeId, paymentTypeDTO);
        return BaseApiResponse.successOf(updatedPaymentType);
    }
    /**
     * Endpoint to delete a payment type by ID.
     *
     * @param paymentTypeId The ID of the payment type to delete.
     * @return A {@link BaseApiResponse} indicating the success of the deletion operation.
     */
    @Operation(
            summary = "Delete Payment Type",
            description = "Delete payment type by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment type deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Payment type not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete-payment-type")
    public BaseApiResponse<?> deletePaymentType(String paymentTypeId) {
        boolean isDeleted = paymentTypeService.deletePaymentType(paymentTypeId);
        return BaseApiResponse.successOf(isDeleted);
    }
}
