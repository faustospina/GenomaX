package com.mercadolibre.genomax.exception;

import com.mercadolibre.genomax.common.NotificationCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class GenomeBusinessException extends Exception implements Serializable {

    private static final long SerialVersion = -7475455454548484L;

    private final NotificationCode errorCode;


    public GenomeBusinessException(NotificationCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public GenomeBusinessException(NotificationCode errorCode, Throwable cause) {
        super(errorCode.getDescription(), cause);
        this.errorCode = errorCode;
    }

    public GenomeBusinessException(NotificationCode errorCode, String message) {
        super(String.format("%s %s",errorCode.getDescription(), message));
        this.errorCode = errorCode;
    }
    public GenomeBusinessException(NotificationCode errorCode, String message, Throwable cause, HttpStatus status) {
        super(String.format("%s %s",errorCode.getDescription(), message,cause,status));
        this.errorCode = errorCode;
    }

    public NotificationCode getErrorCode() {
        return errorCode;
    }
}
