package express.az.tradingmanagementservice.service.impl;

import express.az.tradingmanagementservice.exception.GeneralException;
import express.az.tradingmanagementservice.helper.EmailServiceHelper;
import express.az.tradingmanagementservice.helper.SecurityHelper;
import express.az.tradingmanagementservice.helper.UserServiceHelper;
import express.az.tradingmanagementservice.mapper.UserMapper;
import express.az.tradingmanagementservice.model.constant.Constants;
import express.az.tradingmanagementservice.model.dto.request.UserRequestDto;
import express.az.tradingmanagementservice.model.dto.response.AuthenticationResponse;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.entity.User;
import express.az.tradingmanagementservice.model.enums.ExceptionsEnum;
import express.az.tradingmanagementservice.repository.UserRepository;
import express.az.tradingmanagementservice.service.JwtService;
import express.az.tradingmanagementservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class IUserService implements UserService {

    private final UserRepository appUserRepository;
    private final EmailServiceHelper emailService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserServiceHelper userServiceHelper;
    private final SecurityHelper securityHelper;

    @SneakyThrows
    @Override
    public GeneralResponse<AuthenticationResponse> signUp(UserRequestDto userRequestDto) {
        User user = userMapper.userDtoToUser(userRequestDto);

        if (appUserRepository.existsByEmail(user.getEmail())) {
            throw new GeneralException(ExceptionsEnum.USER_IS_ALREADY_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
        emailService.confirmationToken(user);

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        userServiceHelper.saveUserToken(user, accessToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return GeneralResponse.of(Constants.USER_REGISTER_SUCCESSFULLY, HttpStatus.CREATED, authenticationResponse);

    }

    @Override
    public GeneralResponse<AuthenticationResponse> login(UserRequestDto userRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequestDto.getEmail(), userRequestDto.getPassword()));

        User user = appUserRepository.findByEmailIgnoreCase(userRequestDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + userRequestDto.getEmail()));

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        userServiceHelper.revokedAllUserTokens(user);
        userServiceHelper.saveUserToken(user, accessToken);

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return GeneralResponse.of(Constants.USER_LOGIN_SUCCESSFULLY, HttpStatus.OK, authenticationResponse);

    }

    @Override
    public GeneralResponse<AuthenticationResponse> refreshToken(String authHeader) {
        if (!securityHelper.authHeaderIsValid(authHeader)) {
            throw new GeneralException(ExceptionsEnum.TOKEN_IS_INVALID_EXCEPTION);
        }

        String jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        if (username != null) {
            User user = appUserRepository.findByEmailIgnoreCase(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Username doesn't exist: " + username));

            if (jwtService.isTokenValid(jwt, user)) {
                String accessToken = jwtService.generateToken(user);
                String refreshToken = jwtService.generateRefreshToken(user);

                userServiceHelper.revokedAllUserTokens(user);
                userServiceHelper.saveUserToken(user, accessToken);

                AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();

                return GeneralResponse.of(Constants.USER_LOGIN_SUCCESSFULLY, HttpStatus.OK, authenticationResponse);
            }
        }

        throw new GeneralException(ExceptionsEnum.TOKEN_IS_INVALID_EXCEPTION);
    }


}
