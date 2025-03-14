CREATE TABLE IF NOT EXISTS products
(
    id         BIGSERIAL PRIMARY KEY,
    account_id BIGINT         NOT NULL,
    balance    DECIMAL(10, 2) NOT NULL,
    type       VARCHAR(100)   NOT NULL,
    user_id    BIGINT         NOT NULL REFERENCES users (id) ON DELETE CASCADE
);