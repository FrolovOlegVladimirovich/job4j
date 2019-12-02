/*
Задача:

Есть таблица встреч(id, name), есть таблица юзеров(id, name).

Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах.
У каждого участника встречи может быть разный статус участия - например потвердил участие, отклонил.

После доработки модели:
написать запрос который выводит названия ВСЕХ встреч и количество участников, которые потвердили участие в них.

Вывести названия всех встреч, в которых никто из участников не подтвердил участие.
*/

CREATE TABLE users
(id SERIAL PRIMARY KEY,
name VARCHAR (100));

CREATE TABLE meeting
(id SERIAL PRIMARY KEY,
name VARCHAR (100));

CREATE TABLE user_meeting
(id_user INTEGER REFERENCES users(id),
 id_meeting INTEGER REFERENCES meeting(id),
  status VARCHAR(10));

INSERT INTO users(name) VALUES ('User1');
INSERT INTO users(name) VALUES ('User2');
INSERT INTO users(name) VALUES ('User3');

INSERT INTO meeting(name) VALUES ('Meeting1');
INSERT INTO meeting(name) VALUES ('Meeting2');
INSERT INTO meeting(name) VALUES ('Meeting3');
INSERT INTO meeting(name) VALUES ('Meeting4');

INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (1, 1, 'OK');
INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (1, 2, 'NO');
INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (1, 3, 'NO');
INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (2, 1, 'OK');
INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (2, 2, 'OK');
INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (3, 1, 'NO');
INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (3, 2, 'NO');
INSERT INTO user_meeting(id_user, id_meeting, status) VALUES (3, 3, 'NO');
INSERT INTO user_meeting(id_user, id_meeting) VALUES (1, 4);
INSERT INTO user_meeting(id_user, id_meeting) VALUES (2, 4);
INSERT INTO user_meeting(id_user, id_meeting) VALUES (3, 4);

-- написать запрос который выводит названия ВСЕХ встреч и количество участников, которые потвердили участие в них:
SELECT m.name, COUNT(m.name) FROM meeting AS m
INNER JOIN user_meeting AS um ON m.id = um.id_meeting AND um.status = 'OK' GROUP BY m.name;

-- вывести названия всех встреч, в которых никто из участников не потвердил участие:
SELECT m.name FROM meeting AS m
INNER JOIN user_meeting AS um ON m.id = um.id_meeting AND um.status IS NULL GROUP BY m.name;