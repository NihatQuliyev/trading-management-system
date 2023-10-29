package express.az.tradingmanagementservice.helper;

import express.az.tradingmanagementservice.model.dto.request.EmailRequestDto;
import express.az.tradingmanagementservice.model.entity.ConfirmationToken;
import express.az.tradingmanagementservice.model.entity.User;
import express.az.tradingmanagementservice.repository.ConfirmationTokenRepository;
import express.az.tradingmanagementservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailServiceHelper {

    private final EmailService emailService;
    private final ConfirmationTokenRepository confirmationTokenRepository;


    public void confirmationToken(User user) {
        ConfirmationToken confirmationToken = confirmationTokenBuild(user);
        confirmationTokenRepository.save(confirmationToken);
        confirmationTokenRepository.save(confirmationToken);
        emailService.sendEmail(confirmationTokenSendEmail(user,confirmationToken));
    }


    public EmailRequestDto confirmationTokenSendEmail(User user , ConfirmationToken confirmationToken){
        String url = "http://localhost:8080/trading/user/confirmation-account?token=" + confirmationToken.getToken();
        return EmailRequestDto
                .builder()
                .to(user.getEmail())
                .subject("Registration")
                .text("<p> Hi, " + user.getName() + ", <p>" +
                        "<p>Thank you for registering with us," + "" +
                        "Please, follow the link below to complete your registration.<p>" +
                        "<a href=\"" + url + "\">Verify your email to active your account<a>" +
                        "<p> Thank you <br> Users Registration Portal Service")
                .build();
    }

    public ConfirmationToken confirmationTokenBuild (User user){
        String token = UUID.randomUUID().toString();

        return ConfirmationToken
                .builder()
                .confirm(true)
                .token(token)
                .user(user)
                .confirmedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();


    }

}
