CREATE TABLE currency (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    base_currency TEXT DEFAULT 'RUB' NOT NULL,
    price_change_range TEXT,
    description TEXT
);