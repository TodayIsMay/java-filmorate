# java-filmorate
Template repository for Filmorate project.
![image](https://user-images.githubusercontent.com/95217085/170009213-a76bc2c0-7e99-4632-a611-4f62ddd40715.png)

INSERT INTO Friends (user_id, friend_id, is_accepted) VALUES (1, 2, true); - добавление в друзья
-----------------------------------------------------------------
DELETE FROM Friends WHERE user_id = 1; - удаление из друзей
-----------------------------------------------------------------
SELECT * FROM User; - возвращаем список пользователей
-----------------------------------------------------------------
SELECT * FROM Film; - GET allFilms
-----------------------------------------------------------------
SELECT u.name 
FROM User AS u 
WHERE id IN (SELECT friend_id FROM Friends WHERE friend_id = u.id); = u.id); - список друзей, общих с другим пользователем
-----------------------------------------------------------------
INSERT INTO Likes (film_id, user_id) VALUES (1, 2); - добавление лайка
-----------------------------------------------------------------
DELETE FROM Likes 
WHERE film_id=1 AND user_id=2; - удаление лайка
-----------------------------------------------------------------
SELECT f.name, COUNT(l.film_id) amount_of_likes FROM film AS f
INNER JOIN likes AS l ON l.film_id=f.id
GROUP BY l.film_id
ORDER BY amount_of_likes
LIMIT 10; - показать самые популярные фильмы
-----------------------------------------------------------------
