package com.boilerplate.global.advice;

import com.boilerplate.global.exception.BusinessException;
import com.boilerplate.global.exception.ErrorRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * Business Exception - 사용자 에러
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorRs> handleBusinessException(BusinessException e) {
        log.warn("handleBusinessException", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorRs.ofException(e));
    }

    /**
     * Runtime Exception
     */
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorRs> handleRuntimeException(RuntimeException e) {
        log.warn("RuntimeException", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorRs.ofException(e));
    }

    /**
     * Annotation Exception - @Valid
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorRs> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("handleMethodArgumentNotValidException", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorRs.ofBindException(e));
    }
}

