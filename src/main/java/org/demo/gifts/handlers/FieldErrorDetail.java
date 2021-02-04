package org.demo.gifts.handlers;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.Objects;

public class FieldErrorDetail {
    @Getter
    private String field;
    @Getter
    private String value;
    @Getter
    private String reason;


    public FieldErrorDetail(FieldError error) {
        this.value = error.getRejectedValue() == null ? null : error.getRejectedValue().toString();
        this.field = error.getField();
        this.reason = error.getDefaultMessage();
    }
}
