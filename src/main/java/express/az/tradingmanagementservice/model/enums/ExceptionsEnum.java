package express.az.tradingmanagementservice.model.enums;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public enum ExceptionsEnum {

    PRODUCT_NOT_FOUND("Product not found" , HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND("Category not found",HttpStatus.NOT_FOUND),
    SUPPLIER_NOT_FOUND("Supplier not found",HttpStatus.NOT_FOUND),
    TOKEN_IS_INVALID_EXCEPTION("Token is invalid!", HttpStatus.BAD_REQUEST),
    USER_IS_ALREADY_EXISTS("User is already exists!", HttpStatus.ALREADY_REPORTED),
    TOKEN_IS_UNREACHABLE("Token is unreachable!", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime localDateTime;

    ExceptionsEnum(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.localDateTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}