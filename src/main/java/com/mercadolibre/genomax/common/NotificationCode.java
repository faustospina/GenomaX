package com.mercadolibre.genomax.common;

import org.springframework.http.HttpStatus;

public enum NotificationCode {

    NOT_ARRAY_NXN("the two-dimensional array is not NxN",HttpStatus.FORBIDDEN),
    MIN_LENGTH_ARRAY("the minimum size of the matrix is 4 ",HttpStatus.FORBIDDEN),
    NULL_ENTITY("empty entity when saving in database ",HttpStatus.FORBIDDEN),
    EMPTY_DATA("empty data in dataBase",HttpStatus.FORBIDDEN),
    DIVISION_BY_ZERO("division by zero",HttpStatus.FORBIDDEN),
    DNA_NOT_MATH("THE DNA CHAIN DOES NOT MATCH ",HttpStatus.FORBIDDEN),
    EMPTY_ARRAY("this array is empty",HttpStatus.FORBIDDEN);


    private String description;
    private HttpStatus httpStatus;

    NotificationCode(String description, HttpStatus httpStatus) {
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
