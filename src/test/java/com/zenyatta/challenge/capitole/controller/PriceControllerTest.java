package com.zenyatta.challenge.capitole.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.zenyatta.challenge.capitole.controller.implementation.PriceController;
import com.zenyatta.challenge.capitole.dto.PriceRequestDTO;
import com.zenyatta.challenge.capitole.exception.RetailException;
import com.zenyatta.challenge.capitole.model.Price;
import com.zenyatta.challenge.capitole.service.PriceService;
import jakarta.servlet.ServletException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {

    @Mock
    private PriceService service;

    @InjectMocks
    private PriceController controller;

    private MockMvc mockMvc;

    @Test
    void testGetPriceSuccessfulOperationPriceFound() throws Exception {
        final Price mockPrice = new Price();
        when(service.getPrice(any(PriceRequestDTO.class))).thenReturn(Optional.of(mockPrice));

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"product_id\": 1, \"brand_id\": 1, \"application_date\": \"2022-01-01T00:00:00\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetPriceProductNotFound() throws Exception {
        when(service.getPrice(any(PriceRequestDTO.class))).thenReturn(Optional.empty());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        final Exception exception = assertThrows(ServletException.class,
                () -> mockMvc.perform(MockMvcRequestBuilders.get("/api/query")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"product_id\": 1, \"brand_id\": 1, \"application_date\": \"2022-01-01T00:00:00\"}"))
                        .andExpect(MockMvcResultMatchers.status().isNotFound())
                        .andExpect(MockMvcResultMatchers.content().string("Product not found")));

        final Throwable cause = exception.getCause();
        assertTrue(cause instanceof RetailException);
        assertEquals("Product not found", cause.getMessage());
    }

    @Test
    void testGetPriceWithInvalidDateTimeFormat() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/query")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"product_id\": 1, \"brand_id\": 1, \"application_date\": \"2022-01-01\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
