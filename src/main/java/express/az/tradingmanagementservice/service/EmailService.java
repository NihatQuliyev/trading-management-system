package express.az.tradingmanagementservice.service;


import express.az.tradingmanagementservice.model.dto.request.EmailRequestDto;
import express.az.tradingmanagementservice.model.dto.response.EmailResponseDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;

public interface EmailService {

    void sendEmail(EmailRequestDto emailDTO);

    GeneralResponse<EmailResponseDto> confirmEmail(String token);
}
