INSERT INTO users(username) VALUES ('Иваньков Александр Сергеевич');
INSERT INTO users(username) VALUES ('Лучаева Анна Валерьевна');
INSERT INTO users(username) VALUES ('Крутов Юрий Владимирович');

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 4070281000000000001, 1234.56, 'ACCOUNT' FROM users us
WHERE us.username = 'Иваньков Александр Сергеевич';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 4070281000000000002, 6543.21, 'ACCOUNT' FROM users us
WHERE us.username = 'Иваньков Александр Сергеевич';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 123456789001, 9876.54, 'CARD' FROM users us
WHERE us.username = 'Иваньков Александр Сергеевич';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 4070281000000000003, 3456.78, 'ACCOUNT' FROM users us
WHERE us.username = 'Лучаева Анна Валерьевна';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 4070281000000000004, 8765.43, 'ACCOUNT' FROM users us
WHERE us.username = 'Лучаева Анна Валерьевна';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 102938475601, 5432.10, 'CARD' FROM users us
WHERE us.username = 'Лучаева Анна Валерьевна';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 302963847501, 2109.87, 'CARD' FROM users us
WHERE us.username = 'Лучаева Анна Валерьевна';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 4070281000000000005, 50000.00, 'ACCOUNT' FROM users us
WHERE us.username = 'Крутов Юрий Владимирович';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 67458790001, 25000.00, 'CARD' FROM users us
WHERE us.username = 'Крутов Юрий Владимирович';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 4070281000000000006, 15000.00, 'ACCOUNT' FROM users us
WHERE us.username = 'Крутов Юрий Владимирович';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 98765432111, 12000.00, 'CARD' FROM users us
WHERE us.username = 'Крутов Юрий Владимирович';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 12345678910111213, 8000.00, 'CARD' FROM users us
WHERE us.username = 'Крутов Юрий Владимирович';

INSERT INTO products(user_id, account_id, balance, type)
SELECT us.id, 4070281099999999999, 60000.00, 'ACCOUNT' FROM users us
WHERE us.username = 'Крутов Юрий Владимирович';

COMMIT;
