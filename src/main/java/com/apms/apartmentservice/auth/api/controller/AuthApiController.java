package com.apms.apartmentservice.auth.api.controller;

import com.apms.apartmentservice.auth.model.dto.TokenDTO;
import com.apms.apartmentservice.auth.model.dto.request.LoginRequest;
import com.apms.apartmentservice.auth.model.dto.request.RegisterRequest;
import com.apms.apartmentservice.auth.model.dto.request.TokenInvalidateRequest;
import com.apms.apartmentservice.auth.model.dto.request.TokenRefreshRequest;
import com.apms.apartmentservice.auth.model.dto.response.TokenResponse;
import com.apms.apartmentservice.auth.model.mapper.TokenDTOToTokenResponseMapper;
import com.apms.apartmentservice.auth.service.LoginService;
import com.apms.apartmentservice.auth.service.LogoutService;
import com.apms.apartmentservice.auth.service.RefreshTokenService;
import com.apms.apartmentservice.auth.service.RegisterService;
import com.apms.apartmentservice.common.model.dto.BaseApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling user authentication and authorization operations.
 * Provides endpoints for registering new users, logging in, refreshing access tokens,
 * and logging out by invalidating tokens.
 *
 * <p>All endpoints are prefixed with {@code /api/v1/authentication/user}</p>
 *
 * @see RegisterService
 * @see LoginService
 * @see RefreshTokenService
 * @see LogoutService
 * @see TokenDTOToTokenResponseMapper
 */
@RestController
@RequestMapping("/api/v1/authentication/user")
@RequiredArgsConstructor
@Validated
@Tag(name = "Authentication", description = "Handles user authentication and authorization operations.")
public class AuthApiController {

    private final RegisterService registerService;

    private final LoginService loginService;

    private final RefreshTokenService refreshTokenService;

    private final LogoutService logoutService;

    private final TokenDTOToTokenResponseMapper tokenToTokenResponseMapper = TokenDTOToTokenResponseMapper.initialize();

    /**
     * Endpoint to register a new user.
     *
     * @param registerRequest The {@link RegisterRequest} object containing user registration details.
     * @return A {@link BaseApiResponse} indicating the success of the registration operation.
     */
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User successfully registered"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data"),
                    @ApiResponse(responseCode = "409", description = "User already exists")
            }
    )
    @PostMapping("/register")
    public BaseApiResponse<Void> registerAdmin(@RequestBody @Valid final RegisterRequest registerRequest) {
        registerService.registerUser(registerRequest);
        return BaseApiResponse.SUCCESS;
    }

    /**
     * Endpoint for user login.
     *
     * @param loginRequest The {@link LoginRequest} object containing user login credentials.
     * @return A {@link BaseApiResponse} containing a {@link TokenResponse} with the generated tokens.
     */
    @Operation(
            summary = "User login",
            description = "Authenticates a user and returns an access and refresh token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful"),
                    @ApiResponse(responseCode = "401", description = "Invalid login credentials")
            }
    )
    @PostMapping("/login")
    public BaseApiResponse<TokenResponse> loginAdmin(@RequestBody @Valid final LoginRequest loginRequest) {
        final TokenDTO token = loginService.login(loginRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return BaseApiResponse.successOf(tokenResponse);
    }

    /**
     * Endpoint to refresh an expired access token.
     *
     * @param tokenRefreshRequest The {@link TokenRefreshRequest} object containing the refresh token.
     * @return A {@link BaseApiResponse} containing a {@link TokenResponse} with the refreshed tokens.
     */
    @Operation(
            summary = "Refresh access token",
            description = "Refreshes an expired access token using a valid refresh token.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Token successfully refreshed"),
                    @ApiResponse(responseCode = "400", description = "Invalid refresh token"),
                    @ApiResponse(responseCode = "401", description = "Refresh token expired or invalid")
            }
    )
    @PostMapping("/refresh-token")
    public BaseApiResponse<TokenResponse> refreshToken(@RequestBody @Valid final TokenRefreshRequest tokenRefreshRequest) {
        final TokenDTO token = refreshTokenService.refreshToken(tokenRefreshRequest);
        final TokenResponse tokenResponse = tokenToTokenResponseMapper.map(token);
        return BaseApiResponse.successOf(tokenResponse);
    }

    /**
     * Endpoint to log out a user.
     *
     * @param tokenInvalidateRequest The {@link TokenInvalidateRequest} object containing the token to be invalidated.
     * @return A {@link BaseApiResponse} indicating the success of the logout operation.
     */
    @Operation(
            summary = "Log out a user",
            description = "Invalidates the provided token, effectively logging out the user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Logout successful"),
                    @ApiResponse(responseCode = "400", description = "Invalid token provided")
            }
    )
    @PostMapping("/logout")
    public BaseApiResponse<Void> logout(@RequestBody @Valid final TokenInvalidateRequest tokenInvalidateRequest) {
        logoutService.logout(tokenInvalidateRequest);
        return BaseApiResponse.SUCCESS;
    }

}