package com.apms.apartmentservice.auth.service.impl;

import com.apms.apartmentservice.auth.exception.UserAlreadyExistException;
import com.apms.apartmentservice.auth.model.domain.User;
import com.apms.apartmentservice.auth.model.dto.UserDTO;
import com.apms.apartmentservice.auth.model.dto.request.RegisterRequest;
import com.apms.apartmentservice.auth.model.mapper.RegisterRequestToUserMapper;
import com.apms.apartmentservice.auth.model.mapper.UserToUserDTOMapper;
import com.apms.apartmentservice.auth.repository.UserRepository;
import com.apms.apartmentservice.auth.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service implementation for handling user registration operations.
 */
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;

    private final RegisterRequestToUserMapper registerRequestToUserEntityMapper =
            RegisterRequestToUserMapper.initialize();

    private final UserToUserDTOMapper userToUserDTOMapper = UserToUserDTOMapper.initialize();

    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param registerRequest the request containing user registration details
     * @return the newly registered {@link UserDTO}
     */
    @Override
    public UserDTO registerUser(RegisterRequest registerRequest) {

        if (userRepository.existsUserByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistException("The email is already used for another user : " + registerRequest.getEmail());
        }

        final User userEntityToBeSaved = registerRequestToUserEntityMapper.mapForSaving(registerRequest);

        userEntityToBeSaved.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        final User savedUserEntity = userRepository.save(userEntityToBeSaved);

        return userToUserDTOMapper.map(savedUserEntity);

    }

}
