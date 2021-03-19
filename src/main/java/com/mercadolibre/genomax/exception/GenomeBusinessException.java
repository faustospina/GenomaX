package com.mercadolibre.genomax.exception;

import com.mercadolibre.genomax.common.NotificationCode;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * clase para el menejo de errores
 */
public class GenomeBusinessException extends Exception implements Serializable {

    private final NotificationCode errorCode;


    public GenomeBusinessException(NotificationCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public NotificationCode getErrorCode() {
        return errorCode;
    }
}
