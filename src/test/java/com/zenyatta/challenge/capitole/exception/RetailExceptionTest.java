package com.zenyatta.challenge.capitole.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class RetailExceptionTest {

    @Test
    void retailExceptionCreationTest() {
        final String testErrorMessage = "test-error-message";
        final RetailException exception = new RetailException(
                HttpStatus.BAD_REQUEST, testErrorMessage);

        assertEquals(testErrorMessage, exception.getMessage(),
                "Error message should be propagated");
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode(),
                "Status code should match");
    }
}