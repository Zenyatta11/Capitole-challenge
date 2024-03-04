package com.zenyatta.challenge.capitole.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime ;

public record PriceRequestDTO(
        @JsonProperty("product_id") Long productId,
        @JsonProperty("brand_id") Long brandId,
        @JsonProperty("application_date") LocalDateTime  applicationDate
) {}
