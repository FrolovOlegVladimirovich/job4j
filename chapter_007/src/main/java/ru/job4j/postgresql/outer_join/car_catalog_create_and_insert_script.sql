--Задание:
--
--Нужно написать SQL скрипты:
--
--Создать структур данных в базе.
--Таблицы.
--   Кузов. Двигатель, Коробка передач.
--Создать структуру Машина. Машина не может существовать без данных из п.1.
--Заполнить таблицы через insert.

CREATE DATABASE car_catalog;
\c car_catalog

CREATE TABLE car_body (
id serial PRIMARY KEY,
name VARCHAR(200)
);

CREATE TABLE car_engine (
id serial PRIMARY KEY,
name VARCHAR(200)
);

CREATE TABLE car_transmission (
id serial PRIMARY KEY,
name VARCHAR(200)
);

CREATE TABLE car_model (
id serial PRIMARY KEY,
brand_name VARCHAR(20),
model_name VARCHAR(40),
id_body INT REFERENCES car_body(id),
id_engine INT REFERENCES car_engine(id),
id_transmission INT REFERENCES car_transmission(id)
);

INSERT INTO car_body (name)
VALUES ('Body BMW 320i');
INSERT INTO car_body (name)
VALUES ('Body BMW 530d');
INSERT INTO car_body (name)
VALUES ('Body BMW X5M');

INSERT INTO car_body (name)
VALUES ('Body TOYOTA RAV4');
INSERT INTO car_body (name)
VALUES ('Body TOYOTA Camry');
INSERT INTO car_body (name)
VALUES ('Body TOYOTA LC Prado');

INSERT INTO car_body (name)
VALUES ('Body Hyundai Creta');
INSERT INTO car_body (name)
VALUES ('Body Hyundai Tucson');
INSERT INTO car_body (name)
VALUES ('Body Hyundai Elantra');

INSERT INTO car_engine (name)
VALUES ('Engine BMW 320i');
INSERT INTO car_engine (name)
VALUES ('Engine BMW 530d');
INSERT INTO car_engine (name)
VALUES ('Engine BMW X5M');

INSERT INTO car_engine (name)
VALUES ('Engine TOYOTA RAV4');
INSERT INTO car_engine (name)
VALUES ('Engine TOYOTA Camry');
INSERT INTO car_engine (name)
VALUES ('Engine TOYOTA LC Prado');

INSERT INTO car_engine (name)
VALUES ('Engine Hyundai Creta');
INSERT INTO car_engine (name)
VALUES ('Engine Hyundai Tucson');
INSERT INTO car_engine (name)
VALUES ('Engine Hyundai Elantra');

INSERT INTO car_transmission (name)
VALUES ('Transmission BMW 320i');
INSERT INTO car_transmission (name)
VALUES ('Transmission BMW 530d');
INSERT INTO car_transmission (name)
VALUES ('Transmission BMW X5M');

INSERT INTO car_transmission (name)
VALUES ('Transmission TOYOTA RAV4');
INSERT INTO car_transmission (name)
VALUES ('Transmission TOYOTA Camry');
INSERT INTO car_transmission (name)
VALUES ('Transmission TOYOTA LC Prado');

INSERT INTO car_transmission (name)
VALUES ('Transmission Hyundai Creta');
INSERT INTO car_transmission (name)
VALUES ('Transmission Hyundai Tucson');
INSERT INTO car_transmission (name)
VALUES ('Transmission Hyundai Elantra');

INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('BMW', '320i', 1, 1, 1);
INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('BMW', '530d', 2, 2, 2);
INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('BMW', 'X5M', 3, 3, 3);

INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('TOYOTA', 'RAV4', 4, 4, 4);
INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('TOYOTA', 'Camry', 5, 5, 5);
INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('TOYOTA', 'LC Prado', 6, 6, 6);

INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('Hyundai', 'Creta', 7, 7, 7);
INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('Hyundai', 'Tucson', 8, 8, 8);
INSERT INTO car_model (brand_name, model_name, id_body, id_engine, id_transmission)
VALUES ('Hyundai', 'Elantra', 9, 9, 9);

INSERT INTO car_body (name)
VALUES ('Body Mercedes C180');
INSERT INTO car_body (name)
VALUES ('Body Honda CRV');
INSERT INTO car_body (name)
VALUES ('Body Jaguar XF');

INSERT INTO car_engine (name)
VALUES ('Engine BMW 318i');
INSERT INTO car_engine (name)
VALUES ('Engine BMW 320d');
INSERT INTO car_engine (name)
VALUES ('Engine Skoda Octavia');

INSERT INTO car_transmission (name)
VALUES ('Transmission VW Tiguan');
INSERT INTO car_transmission (name)
VALUES ('Transmission Audi A4');
INSERT INTO car_transmission (name)
VALUES ('Transmission Ford Focus');

--Создать SQL запросы:
--
--1. Вывести список всех машин и все привязанные к ним детали.

SELECT car.brand_name, car.model_name, body.name, engine.name, transmission.name FROM car_model AS car
INNER JOIN car_body AS body ON car.id_body = body.id
INNER JOIN car_engine AS engine ON car.id_engine = engine.id
INNER JOIN car_transmission AS transmission ON car.id_transmission = transmission.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

SELECT body.name FROM car_body AS body
LEFT OUTER JOIN car_model AS car ON body.id = car.id_body
WHERE car.id_body IS NULL;

SELECT engine.name FROM car_engine AS engine
LEFT OUTER JOIN car_model AS car ON engine.id = car.id_engine
WHERE car.id_engine IS NULL;

SELECT trans.name FROM car_transmission AS trans
LEFT OUTER JOIN car_model AS car ON trans.id = car.id_transmission
WHERE car.id_transmission IS NULL;