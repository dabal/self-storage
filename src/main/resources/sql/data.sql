INSERT INTO roles (id, name) VALUES (1, 'ROLE_USER');
insert into users (id, username, password, enabled ) values (1,'username', '$2a$10$5kAj3LNwLG3HQbOqN.6Wju3GPf9Rjjqt5yFsUHL3uXx8eYLBNjYFO',true);
insert into user_role(user_id, role_id) values (1,1);