--Задание:

-- В системе заданы таблицы

-- product(id, name, type_id, expired_date, price)

-- type(id, name)

-- Задание.

-- 1. Написать запрос получение всех продуктов с типом "СЫР"

 SELECT * from product as p
 INNER JOIN type as t on p.type_id = t.id
 where t.name = 'СЫР';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"

 SELECT * from product
 where name like '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

 SELECT * from product
 where expired_date between '2019-12-01 00:00:00.00000' and '2020-01-01 00:00:00.00000';

-- 4. Написать запрос, который выводит самый дорогой продукт.

 SELECT * from product
 WHERE price = (SELECT MAX(price) from product);

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.

 SELECT count(p.name) from product as p
 INNER JOIN type as t on p.type_id = t.id
 WHERE t.name = 'ПИВО';

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

 SELECT * from product as p
 INNER JOIN type as t on p.type_id = t.id
 WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО';

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.

 SELECT t.name from type as t
 INNER JOIN product as p on p.type_id = t.id
 GROUP BY t.name HAVING count(t.name) < 10;

-- 8. Вывести все продукты и их тип.

 SELECT p.name, t.name from product as p
 INNER JOIN type as t on p.type_id = t.id;