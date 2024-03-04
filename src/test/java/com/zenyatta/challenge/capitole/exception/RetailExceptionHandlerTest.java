package com.zenyatta.challenge.capitole.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

class RetailExceptionHandlerTest {
    private static final String TEST_ERROR_MSG = "test-error-message";

    private final RetailExceptionHandler exceptionHandler = new RetailExceptionHandler();

    private final HttpStatus testHttpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private final RetailException testException = new RetailException(testHttpStatus, TEST_ERROR_MSG);

    private final MethodArgumentTypeMismatchException testMismatchException = new MethodArgumentTypeMismatchException(
            null, null, "test-name", null, null);

    @Test
    void handleMethodArgumentTypeMismatchException() {
        final ResponseEntity<Object> responseEntity = exceptionHandler
                .handleMethodArgumentTypeMismatchException(testMismatchException);

        assertEquals(HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()),
                responseEntity.getStatusCode(),
                "should return 400 status for MethodArgumentTypeMismatchException");
    }

    @Test
    void handleRetailException() {
        final ResponseEntity<Object> responseEntity = exceptionHandler.handleRetailException(testException);

        assertEquals(HttpStatusCode.valueOf(testHttpStatus.value()), responseEntity.getStatusCode(),
                "should return matched status for RetailException");
        assertNotNull(responseEntity.getBody());
        assertTrue(responseEntity.getBody().toString().contains(TEST_ERROR_MSG),
                "should return expected message for RetailException");
    }

    @Test
    void handleFallbackExceptionsShouldReturn500() {
        final ResponseEntity<Object> responseEntity = exceptionHandler.handleFallbackExceptions(new Exception());

        assertEquals(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                responseEntity.getStatusCode(),
                "should return 500 for RetailException");
    }
}