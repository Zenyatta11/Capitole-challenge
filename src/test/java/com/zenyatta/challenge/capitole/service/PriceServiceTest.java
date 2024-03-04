package com.zenyatta.challenge.capitole.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.zenyatta.challenge.capitole.dto.PriceRequestDTO;
import com.zenyatta.challenge.capitole.model.Price;
import com.zenyatta.challenge.capitole.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceService service;

    @Test
    void testGetPriceReturnsPriceSuccess() {
        final PriceRequestDTO request = new PriceRequestDTO(1L, 1L, LocalDateTime.now());
        final Price mockPrice = new Price(); // Create a mock Price object as needed
        
        when(repository.findHighestPriorityPrice(any(), any(), any())).thenReturn(Optional.of(mockPrice));

        final Optional<Price> result = service.getPrice(request);

        assertEquals(Optional.of(mockPrice), result);
    }

    @Test
    void testGetPriceNoPriceFound() {
        final PriceRequestDTO request = new PriceRequestDTO(1L, 1L, LocalDateTime.now());
        when(repository.findHighestPriorityPrice(any(), any(), any())).thenReturn(Optional.empty());

        final Optional<Price> result = service.getPrice(request);

        assertEquals(Optional.empty(), result);
    }
}
