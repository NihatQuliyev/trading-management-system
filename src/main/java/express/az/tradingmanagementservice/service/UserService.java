package express.az.tradingmanagementservice.service;

import express.az.tradingmanagementservice.model.dto.request.UserRequestDto;
import express.az.tradingmanagementservice.model.dto.response.AuthenticationResponse;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;

public interface UserService {

    GeneralResponse<AuthenticationResponse> signUp(UserRequestDto userRequestDto);
    GeneralResponse<AuthenticationResponse> login(UserRequestDto userRequestDto);
    GeneralResponse<AuthenticationResponse> refreshToken(String authHeader);

}
