package com.mercadolibre.genomax.common;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY
)
public class ApiResponseDF<T> {
    private T data;
    private Notification notification;

    private ApiResponseDF() {
    }

    public ApiResponseDF(Notification notification) {
        this.notification = notification;
    }

    public ApiResponseDF(T data, Notification notification) {
        this.data = data;
        this.notification = notification;
    }

    public T getData() {
        return this.data;
    }

    public Notification getNotification() {
        return this.notification;
    }
}
