create table
    prices (
        entry_id bigint AUTO_INCREMENT PRIMARY KEY,
        brand_id bigint not null,
        currency varchar(255) not null check (currency in ('USD', 'EUR')),
        end_date timestamp(6) not null,
        start_date timestamp(6) not null,
        price float (53) not null,
        price_list bigint not null,
        priority integer not null,
        product_id bigint not null,
        primary key (entry_id)
    );
