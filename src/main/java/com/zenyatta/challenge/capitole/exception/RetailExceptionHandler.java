package com.zenyatta.challenge.capitole.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class RetailExceptionHandler {

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentConversionNotSupportedException.class
    })
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            final Exception exception) {
        if (log.isErrorEnabled()) {
            log.error(exception.getMessage(), exception);
        }

        String returnMessage = MDC.get("message");

        if (returnMessage == null) {
            returnMessage = "Argument mismatch.";
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(returnMessage);
    }

    @ExceptionHandler(RetailException.class)
    protected ResponseEntity<Object> handleRetailException(final RetailException exception) {
        final HttpStatusCode code = exception.getStatusCode();

        if (log.isErrorEnabled()) {
            MDC.put("exception", exception.getMessage());

            String description = "Information";

            if (code.is2xxSuccessful()) {
                description = "Success";
            } else if (code.is3xxRedirection()) {
                description = "Redirect";
            } else if (code.is4xxClientError()) {
                description = "Client Error";
            } else if (code.is5xxServerError()) {
                description = "Server Error";
            }

            log.error(description, exception);
        }

        return ResponseEntity
                .status(code)
                .body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleFallbackExceptions(final Exception exception) {
        if (log.isErrorEnabled()) {
            MDC.put("exception", exception.getMessage());
            log.error("Internal server error", exception);
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
