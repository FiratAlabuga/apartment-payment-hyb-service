package com.apms.apartmentservice.apartment.api.controller;

import com.apms.apartmentservice.apartment.model.dto.PaymentDTO;
import com.apms.apartmentservice.apartment.service.PaymentService;
import com.apms.apartmentservice.common.model.dto.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@Validated
@Tag(name = "Payment", description = "Handles payments operations.")
public class PaymentApiController {
    private final PaymentService paymentService;

    /**
     * Endpoint to create a new payment.
     *
     * @param paymentDTO The {@link PaymentDTO} object containing user registration details.
     * @return A {@link BaseApiResponse} indicating the success of the registration operation.
     */
    @Operation(
            summary = "Create Payment",
            description = "Create Payment in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Create Payment successfully"),
                    @ApiResponse(responseCode = "400", description = "Create Payment failed"),
                    @ApiResponse(responseCode = "409", description = "Create Payment already exists")
            }
    )
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/create-payment")
    public BaseApiResponse<?> createPayment(@Validated @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO payment = paymentService.createPayment(paymentDTO);
        return BaseApiResponse.successOf(payment);
    }
    /**
     * Endpoint to get a payment by ID.
     *
     * @param paymentId The ID of the payment to retrieve.
     * @return A {@link BaseApiResponse} containing the payment details.
     */
    @Operation(
            summary = "Get Payment by ID",
            description = "Retrieve payment details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment found"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            })
    @GetMapping("/get-payment/{paymentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public BaseApiResponse<?> getPaymentById(@PathVariable(name = "paymentId") String paymentId) {
        PaymentDTO payment = paymentService.getPaymentById(paymentId);
        return BaseApiResponse.successOf(payment);
    }
    /**
     * Endpoint to get a payment by ID.
     *
     * @param transactionId The ID of the payment to retrieve.
     * @return A {@link BaseApiResponse} containing the payment details.
     */
    @Operation(
            summary = "Get Payment by ID",
            description = "Retrieve payment details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment found"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            })
    @GetMapping("/get-payment/transaction/{transactionId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public BaseApiResponse<?> getPaymentByTrxId(@PathVariable(name = "transactionId") String transactionId) {
        PaymentDTO payment = paymentService.getPaymentByTransactionId(transactionId);
        return BaseApiResponse.successOf(payment);
    }
    /**
     * Endpoint to get all payments.
     *
     * @return A {@link BaseApiResponse} containing the list of all payments.
     */
    @Operation(
            summary = "Get All Payments",
            description = "Retrieve all payments.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payments found"),
                    @ApiResponse(responseCode = "404", description = "Payments not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get-all-payments")
    public BaseApiResponse<?> getAllPayments() {
        return BaseApiResponse.successOf(paymentService.getAllPayments());
    }
    /**
     * Endpoint to update a payment by ID.
     *
     * @param paymentId The ID of the payment to update.
     * @param paymentDTO The updated payment details.
     * @return A {@link BaseApiResponse} containing the updated payment details.
     */
    @Operation(
            summary = "Update Payment by ID",
            description = "Update payment details by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/update-payment/{paymentId}")
    public BaseApiResponse<?> updatePayment(@PathVariable(name = "paymentId") String paymentId, @Validated @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updatedPayment = paymentService.updatePayment(paymentId, paymentDTO);
        return BaseApiResponse.successOf(updatedPayment);
    }
    /**
     * Endpoint to delete a payment by ID.
     *
     * @param paymentId The ID of the payment to delete.
     * @return A {@link BaseApiResponse} indicating the success of the deletion operation.
     */
    @Operation(
            summary = "Delete Payment by ID",
            description = "Delete payment by ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Payment deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Payment not found")
            })
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete-payment")
    public BaseApiResponse<?> deletePayment(String paymentId) {
        paymentService.deletePayment(paymentId);
        return BaseApiResponse.SUCCESS;
    }
}
