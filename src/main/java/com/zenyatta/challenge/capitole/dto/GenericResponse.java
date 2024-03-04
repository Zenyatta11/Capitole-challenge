package com.zenyatta.challenge.capitole.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GenericResponse(
        @JsonProperty("payload") Object payload,
        @JsonProperty("response_status") ResponseStatus responseStatus,
        @JsonProperty("error_message") String errorMessage
) {
    public static GenericResponse buildResponse(
            @JsonProperty("payload") final Object payload,
            @JsonProperty("response_status") final ResponseStatus responseStatus,
            @JsonProperty("error_message") final String errorMessage
    ) {
        return new GenericResponse(payload, responseStatus, errorMessage);
    }
}
