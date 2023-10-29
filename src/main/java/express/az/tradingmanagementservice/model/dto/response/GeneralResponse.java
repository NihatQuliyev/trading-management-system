package express.az.tradingmanagementservice.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralResponse<T> {

    private String message;
    private HttpStatus httpStatus;
    private T data;
    private LocalDateTime responseTime;

    public static <T> GeneralResponse<T> of(String message,HttpStatus httpStatus, T data){

        return build(message,httpStatus,data);
    }

    public static <T> GeneralResponse<T> build(String message, HttpStatus httpStatus, T data){

        return GeneralResponse.<T>builder()
                .message(message)
                .httpStatus(httpStatus)
                .data(data)
                .responseTime(LocalDateTime.now().withNano(0))
                .build();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }
}
