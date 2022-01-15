
INSERT INTO roles (id, role) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
INSERT INTO users (id, email, enabled, first_name, last_name, password) VALUES (1, 'viktor@mail.ru', true, 'Виктор', 'Селезнев', '$2a$12$P4XBNgCiYzT/OavtHlQ8X.2Bd/XpoA4C1ZTzdA3vLu6j6LDT0zCJC');
INSERT INTO users_roles VALUES (1, 1), (1, 2);
