INSERT INTO roles (id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_REGISTER');
insert into users (id, username, password, enabled)
values (1, 'u@username.com', '$2a$10$5kAj3LNwLG3HQbOqN.6Wju3GPf9Rjjqt5yFsUHL3uXx8eYLBNjYFO', true);
insert into user_role(user_id, role_id)
values (1, 1),
       (1, 2);


insert into storages(id, name, user_id)
values (1, 'default', 1);
insert into metrics(id, name)
values (1, 'Brak'),
       (2, 'kg'),
       (3, 'g'),
       (4, 'l');

insert into categories(id, name)
values (1, 'Brak'),
       (2, 'żywność'),
       (3, 'napoje'),
       (4, 'chemia');

insert into items (id, name, quantity, category_id, metric_id, storage_id)
values (1, 'nazwa produktu', 1.0, 1, 1, 1);