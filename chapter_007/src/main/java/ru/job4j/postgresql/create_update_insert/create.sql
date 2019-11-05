CREATE DATABASE tracker;
\c tracker;

CREATE TABLE users (
user_id serial primary key,
name character varying(100),
phone character varying(15)
);

CREATE TABLE item (
item_id serial primary key,
item_name character varying(100),
item_date timestamp not null default now()
);

CREATE TABLE user_item (
item_id int REFERENCES item(item_id),
user_id int REFERENCES users(user_id)
);

CREATE TABLE role (
role_type character varying(20) primary key,
user_id int REFERENCES users(user_id),
role_description character varying(160)
);

CREATE TABLE rule (
rule_type character varying(20) primary key,
rule_description character varying(160)
);

CREATE TABLE role_rule (
role_type character varying(20) REFERENCES role(role_type),
rule_type character varying(20) REFERENCES rule(rule_type)
);

CREATE TABLE category (
category_name character varying(20),
item_id int REFERENCES item(item_id)
);

CREATE TABLE status (
status_name character varying(20),
item_id int REFERENCES item(item_id)
);

CREATE TABLE comment (
comment_id serial primary key,
item_id int REFERENCES item(item_id),
comment_text character varying(6000),
comment_date timestamp not null default now()
);

CREATE TABLE attach (
attach_id serial primary key,
item_id int REFERENCES item(item_id),
attach_file bytea
);

INSERT INTO users (name, phone) values ('Oleg Frolov', '+79999999999');
INSERT INTO users (name, phone) values ('Arsentev Petr', '+79091111111');

INSERT INTO item (item_name) values ('bug#1');
INSERT INTO item (item_name) values ('bug#2');
INSERT INTO item (item_name) values ('bug#3');

INSERT INTO comment (item_id, comment_text) values ('1', 'comment#1');

INSERT INTO role (role_type, user_id) values ('user', '1');
INSERT INTO role (role_type, user_id) values ('admin', '2');

INSERT INTO rule (rule_type) values ('admin_rules');
INSERT INTO rule (rule_type) values ('user_rules');

INSERT INTO role_rule (role_type, rule_type) values ('user', 'user_rules');
INSERT INTO role_rule (role_type, rule_type) values ('admin', 'admin_rules');

INSERT INTO category (category_name, item_id) values ('bugs', '1');
INSERT INTO category (category_name, item_id) values ('bugs', '2');
INSERT INTO category (category_name, item_id) values ('bugs', '3');

INSERT INTO category (category_name) values ('offers');
INSERT INTO category (category_name) values ('info');

INSERT INTO status (status_name, item_id) values ('accepted', '1');
INSERT INTO status (status_name, item_id) values ('awaiting', '2');
INSERT INTO status (status_name, item_id) values ('awaiting', '3');

INSERT INTO status (status_name) values ('completed');

UPDATE users SET name = 'Frolov Oleg' where user_id = 1;