package org.demo.gifts.handlers;

import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ErrorDetails {
    @Getter
    private LocalDateTime timestamp;
    @Getter
    private String message;
    @Getter
    private List<FieldErrorDetail> details;

    public ErrorDetails(LocalDateTime timestamp, String message, List<FieldError> errors) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = errors != null ? errors.stream().map(FieldErrorDetail::new).collect(Collectors.toList()) : new ArrayList<>();
    }
}
