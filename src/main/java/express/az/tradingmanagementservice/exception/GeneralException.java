package express.az.tradingmanagementservice.exception;

import express.az.tradingmanagementservice.model.enums.ExceptionsEnum;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final ExceptionsEnum exceptionsEnum;

    public GeneralException(ExceptionsEnum exceptions) {
        super(exceptions.toString());
        this.exceptionsEnum = exceptions;
    }
}
