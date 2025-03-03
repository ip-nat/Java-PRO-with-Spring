CREATE DATABASE ipp_db;
\connect ipp_db;

CREATE TABLE users
(
    id       bigserial PRIMARY KEY,
    username varchar(255) UNIQUE
);