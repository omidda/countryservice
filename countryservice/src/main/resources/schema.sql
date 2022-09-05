DROP TABLE IF EXISTS COUNTRIES;
CREATE TABLE COUNTRIES (
    country_code VARCHAR(4) PRIMARY KEY,
    country_name VARCHAR(50) NOT NULL,
    country_capital VARCHAR(50) NOT NULL,
    country_population BIGINT NOT NULL,
    country_flag_url VARCHAR(150) NOT NULL
);