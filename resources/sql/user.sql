-- name: sql-get-user-by-fb-id<!
SELECT *
FROM users
WHERE fb_id = :fb_id;

-- name: sql-create-key<!
INSERT INTO logins (user_id, login_key, expires) VALUES (:user_id, :login_key, :expires);

-- name: sql-insert-user<!
INSERT INTO users (username, name, fb_id, fb_user, last_login) VALUES (:username, :name, :fb_id, :fb_user, :last_login);

-- name: sql-update-user<!
UPDATE users
SET email = :email, last_login = :last_login, fb_user = :fb_user
WHERE id = id;