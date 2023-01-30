package com.boilerplate.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorRs {

    private String code;

    private String message;

    private List<ValidationError> errors;

    public static ErrorRs of(String code, String message) {
        ErrorRs rs = new ErrorRs();
        rs.code = code;
        rs.message = message;

        return rs;
    }

    public static ErrorRs of(String code, List<ValidationError> errors) {
        ErrorRs rs = new ErrorRs();
        rs.code = code;
        rs.errors = errors;

        return rs;
    }

    public static ErrorRs ofException(Exception e) {
        return ErrorRs.of(
                e.getClass().getSimpleName(),
                e.getMessage()
        );
    }

    public static ErrorRs ofBindException(BindException e) {
        List<ErrorRs.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorRs.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorRs.of(
                e.getClass().getSimpleName(),
                validationErrorList
        );
    }

    @Getter
    @AllArgsConstructor
    private static class ValidationError {

        private String field;
        private String message;

        public static ValidationError of(final FieldError fieldError) {
            return new ValidationError(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
        }
    }
}