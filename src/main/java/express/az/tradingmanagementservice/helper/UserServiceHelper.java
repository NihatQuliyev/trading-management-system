package express.az.tradingmanagementservice.helper;

import express.az.tradingmanagementservice.model.entity.Token;
import express.az.tradingmanagementservice.model.entity.User;
import express.az.tradingmanagementservice.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceHelper {

    private final TokenRepository tokenRepository;

    public void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(token);
    }

    public void revokedAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());

        if (validUserTokens.isEmpty()) return;

        validUserTokens.forEach(t -> {t.setExpired(true); t.setRevoked(true);});
        tokenRepository.saveAll(validUserTokens);
    }

}
