package express.az.tradingmanagementservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime responseTime;

    public static ExceptionResponse of(String message,HttpStatus httpStatus ){

        return build(message,httpStatus);
    }

    public static ExceptionResponse build(String message, HttpStatus httpStatus){

        return ExceptionResponse.builder()
                .message(message)
                .httpStatus(httpStatus)
                .responseTime(LocalDateTime.now().withNano(0))
                .build();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }
}