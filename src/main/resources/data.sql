INSERT INTO prices (
    brand_id,
    currency,
    start_date,
    end_date,
    price,
    product_id,
    priority,
    price_list
) 
VALUES 
    (1, 'EUR', TIMESTAMP '2020-06-14T00:00:00', TIMESTAMP '2020-12-31T23:59:59', 35.50, 35455, 0, 1),
    (1, 'EUR', TIMESTAMP '2020-06-14T15:00:00', TIMESTAMP '2020-06-14T18:30:00', 25.45, 35455, 1, 2),
    (1, 'EUR', TIMESTAMP '2020-06-15T00:00:00', TIMESTAMP '2020-06-15T11:00:00', 30.50, 35455, 1, 3),
    (1, 'EUR', TIMESTAMP '2020-06-15T16:00:00', TIMESTAMP '2020-12-31T23:59:59', 38.95, 35455, 1, 4);
