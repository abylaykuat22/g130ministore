-- liquibase formatted sql

-- changeset alisher:1
-- comment создаю базу
CREATE TABLE IF NOT EXISTS categories
(
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR,
    DESCRIPTION TEXT
);

-- changeset magzhan:2
CREATE TABLE IF NOT EXISTS brands
(
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR,
    CODE VARCHAR,
    DESCRIPTION TEXT
);

-- changeset magzhan:3
CREATE TABLE IF NOT EXISTS brand_categories
(
    BRAND_ID BIGINT,
    CATEGORY_ID BIGINT,
    FOREIGN KEY (BRAND_ID) REFERENCES brands(id),
    FOREIGN KEY (CATEGORY_ID) REFERENCES categories(id)
);


