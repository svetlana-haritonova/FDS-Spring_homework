CREATE TABLE currency (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    base_currency VARCHAR(255) DEFAULT 'RUB' NOT NULL,
    price_change_range VARCHAR(255),
    description VARCHAR(255)
);