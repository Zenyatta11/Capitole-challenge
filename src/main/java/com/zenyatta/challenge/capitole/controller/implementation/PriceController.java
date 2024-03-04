package com.zenyatta.challenge.capitole.controller.implementation;

import com.zenyatta.challenge.capitole.controller.IPriceController;
import com.zenyatta.challenge.capitole.dto.GenericResponse;
import com.zenyatta.challenge.capitole.dto.PriceRequestDTO;
import com.zenyatta.challenge.capitole.dto.ResponseStatus;
import com.zenyatta.challenge.capitole.exception.RetailException;
import com.zenyatta.challenge.capitole.model.Price;
import com.zenyatta.challenge.capitole.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PriceController implements IPriceController {

    private final PriceService service;

    @Override
    public ResponseEntity<GenericResponse> getPrice(PriceRequestDTO request) throws RetailException {
        final Price responseData = service.getPrice(request).orElseThrow(
                () -> new RetailException(HttpStatus.NOT_FOUND, "Product not found")
        );
        
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(GenericResponse.buildResponse(
                    responseData,
                    ResponseStatus.SUCCEEDED,
                    ""
                )
            );
    }

}
