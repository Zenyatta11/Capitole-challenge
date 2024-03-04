package com.zenyatta.challenge.capitole.repository;

import com.zenyatta.challenge.capitole.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends CrudRepository<Price, Long> {
    @Query("SELECT p FROM Price p " +
            "WHERE :date BETWEEN p.dateStart AND p.dateEnd " +
            "AND p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "ORDER BY p.priority DESC " +
            "LIMIT 1")
    Optional<Price> findHighestPriorityPrice(
            @Param("date") LocalDateTime date,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId
    );
}
