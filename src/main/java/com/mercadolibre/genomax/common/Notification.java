package com.mercadolibre.genomax.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Notification {
    private transient SimpleDateFormat colombianDateFormat;
    private static final String COLOMBIA_TIME_ZONE = "America/Bogota";
    private String description;
    private String responseTime;
    private String code;
    private String source;
    private String reference;

    private Notification() {
        this.colombianDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms z");
    }

    private Notification(Notification.Builder builder) {
        this.colombianDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.ms z");
        this.description = builder.description;
        this.code = builder.code;
        this.reference = builder.reference;
        this.colombianDateFormat.setTimeZone(TimeZone.getTimeZone("America/Bogota"));
        this.responseTime = this.colombianDateFormat.format(new Date());
        this.source = builder.source;
    }

    public static Notification.Builder builder(String description, String code) {
        return new Notification.Builder(description, code);
    }

    public String getDescription() {
        return this.description;
    }

    public String getResponseTime() {
        return this.responseTime;
    }

    public String getCode() {
        return this.code;
    }

    public String getSource() {
        return this.source;
    }

    public String getReference() {
        return this.reference;
    }

    public static class Builder {
        private String description;
        private String code;
        private String source;
        private String reference;

        public Builder(String description, String code) {
            this.description = description;
            this.code = code;
        }

        public Notification.Builder source(String source) {
            this.source = source;
            return this;
        }

        public Notification.Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }


    }
}
