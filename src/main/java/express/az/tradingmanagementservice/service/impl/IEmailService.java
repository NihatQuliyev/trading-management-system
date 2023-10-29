package express.az.tradingmanagementservice.service.impl;

import express.az.tradingmanagementservice.exception.GeneralException;
import express.az.tradingmanagementservice.model.constant.Constants;
import express.az.tradingmanagementservice.model.dto.request.EmailRequestDto;
import express.az.tradingmanagementservice.model.dto.response.EmailResponseDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.model.entity.ConfirmationToken;
import express.az.tradingmanagementservice.model.entity.User;
import express.az.tradingmanagementservice.model.enums.ExceptionsEnum;
import express.az.tradingmanagementservice.repository.ConfirmationTokenRepository;
import express.az.tradingmanagementservice.repository.UserRepository;
import express.az.tradingmanagementservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;


@Slf4j
@Service
@RequiredArgsConstructor
public class IEmailService implements EmailService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final ConfirmationTokenRepository confirmationRepo;

    @SneakyThrows
    public void sendEmail(EmailRequestDto emailDTO) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(emailDTO.getTo());
        mimeMessageHelper.setSubject(emailDTO.getSubject());
        mimeMessageHelper.setText(emailDTO.getText(), true);

        mailSender.send(mimeMessage);
    }

    public GeneralResponse<EmailResponseDto> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationRepo.findByToken(confirmationToken);

        log.info("confirmation token : " + confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Username nor found " + token.getUser().getEmail()));

            log.info("Find email : " + token.getUser().getEmail());
            user.setEnable(true);
            userRepository.save(user);
            log.info("Email verified successfully!");

            return GeneralResponse.of(Constants.USER_ACTIVATION_SUCCESSFULLY, HttpStatus.ACCEPTED,
                    EmailResponseDto.builder()
                    .to(user.getEmail())
                            .build());
        }

        throw new GeneralException(ExceptionsEnum.TOKEN_IS_UNREACHABLE);
    }

}
