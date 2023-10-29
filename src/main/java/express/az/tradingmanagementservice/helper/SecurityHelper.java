package express.az.tradingmanagementservice.helper;

import org.springframework.stereotype.Service;

@Service
public class SecurityHelper {

    public boolean authHeaderIsValid(String authorizationHeader) {
        return authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
    }
}
