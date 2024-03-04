package com.zenyatta.challenge.capitole.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.zenyatta.challenge.capitole.dto.GenericResponse;
import com.zenyatta.challenge.capitole.dto.PriceRequestDTO;
import com.zenyatta.challenge.capitole.exception.RetailException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPriceController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation, product found", 
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, 
                    schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "400", description = "Business error", 
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, 
                    schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", 
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, 
                    schema = @Schema(implementation = GenericResponse.class))),
            @ApiResponse(responseCode = "500", description = "Server error", 
                    content = @Content(mediaType = APPLICATION_JSON_VALUE, 
                    schema = @Schema(implementation = GenericResponse.class))) 
    })
    @GetMapping("/query")
    ResponseEntity<GenericResponse> getPrice(@Valid @RequestBody PriceRequestDTO priceRequestDTO)
            throws RetailException;
}
