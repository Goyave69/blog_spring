INSERT INTO role (type) VALUES ('admin');

INSERT INTO "user" (email, first_name, is_enabled, last_name, password, username, role_id)
VALUES ('john@doe.com', 'John', true, 'Doe', '$2a$12$S/VXri58yQkBCEIk9CnRtOSXZLFEa03dd5gJ5YwfqFH8wR6Lbfq8S', 'johndoe', 1);