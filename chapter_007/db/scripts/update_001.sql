CREATE TABLE items (
id SERIAL PRIMARY KEY NOT NULL,
name VARCHAR(30) NOT NULL,
description VARCHAR(600) NOT NULL,
create_date TIMESTAMP NOT NULL DEFAULT NOW()
);