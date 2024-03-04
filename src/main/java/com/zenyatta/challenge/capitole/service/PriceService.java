package com.zenyatta.challenge.capitole.service;

import com.zenyatta.challenge.capitole.dto.PriceRequestDTO;
import com.zenyatta.challenge.capitole.model.Price;
import com.zenyatta.challenge.capitole.repository.PriceRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceService {

    @Autowired
    PriceRepository repository;

    public Optional<Price> getPrice(PriceRequestDTO request) {
        log.info("Data: {}", request);
        final Optional<Price> price = repository.findHighestPriorityPrice(
                request.applicationDate(), 
                request.productId(),
                request.brandId()
        );

        if (log.isInfoEnabled() && !price.isEmpty()) {
            log.info("Retrieved price for product ID {0}, brand ID {1}, and application date {2}: {3}",
                    new Object[] { request.productId(), request.brandId(), request.applicationDate(),
                            price.orElse(null) });
        } 

        return price;
    }
}
