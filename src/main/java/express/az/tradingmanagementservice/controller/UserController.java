package express.az.tradingmanagementservice.controller;

import express.az.tradingmanagementservice.model.dto.request.UserRequestDto;
import express.az.tradingmanagementservice.model.dto.response.GeneralResponse;
import express.az.tradingmanagementservice.service.EmailService;
import express.az.tradingmanagementservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trading/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/registration")
    public GeneralResponse<?> registerUser(@RequestBody UserRequestDto user) {
        log.info("Registering user: {}", user.getName());
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public GeneralResponse<?> login(@RequestBody @Valid UserRequestDto user) {
        log.info("Login user: {}", user.getName());
        return userService.login(user);
    }


    @GetMapping("/refresh-token")
    public GeneralResponse<?> refreshToken(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        return userService.refreshToken(token);
    }

    @RequestMapping(value="/confirmation-account", method= {RequestMethod.GET, RequestMethod.POST})
    public GeneralResponse<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        log.info("Confirming user account with token: {}", confirmationToken);
        return emailService.confirmEmail(confirmationToken);
    }

}
