--insert into socio (id,nombre,apellidos,barco) values (1L,'anwgel','shdhasd',null);
--insert into barco (id,matricula,nombre,numero_amarre,cuota,socio) values (1L,'barco1','shdhasd',2,300,null);
--INSERT INTO socio (id, name,last_name, username, email, password,phone_number,ACCOUNT_NON_EXPIRED,ACCOUNT_NON_LOCKED,CREDENTIALS_NON_EXPIRED,enabled,foto_url) VALUES ('5d818565-99f9-4d80-920e-8259c6ecb8e6', 'Pedro','pepe', 'ToRechulon', 'pedro@gmail.com', '{bcrypt}$2a$10$05HASeZdtwl8NS/nWbNMJOU07tiGZ9Z/mVE2Z.FKhsyjkCK7yuLqa',383838,true,true,true,true,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQciKN1y59CDYMq-IALg7OUijN7hIiM8hdzKw&usqp=CAU');
--INSERT INTO socio_roles ( roles,usuario_id) VALUES (0,'5d818565-99f9-4d80-920e-8259c6ecb8e6');
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440000', '1234-AB', 'El Galeón', false, 10, 200.0,false);
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440001', '5678-CD', 'La Perla Negra', false, 5, 250.0,false);
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440002', '9012-EF', 'El Corsario', false, 8, 180.0,false);
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440004', '7890-IJ', 'La Espada Dorada', false, 15, 220.0,false);
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440006', '6789-MN', 'La Reina del Mar', false, 7, 190.0,false);
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440007', '1234-OP', 'El Navegante Solitario', false, 4, 280.0,false);
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440008', '5678-QR', 'La Perla del Caribe', false, 9, 210.0,false);
INSERT INTO barco (id, matricula, nombre, ocupado, numero_amarre, cuota,likes) VALUES ('550e8400-e29b-41d4-a716-446655440009', '9012-ST', 'El Aventurero', false, 6, 320.0,false);

-- Insertando en la tabla 'socio'
INSERT INTO socio (account_non_expired, account_non_locked, birth_date, credentials_non_expired, enabled, saldo, created_at, last_password_change_at, id, confirmation_token, email, foto_url, last_name, name, password, phone_number, username) VALUES (false, true, '1980-01-01', true, true, 1000.0, '2024-04-24 09:58:42', '2024-04-24 09:58:42', '550e8400-e29b-41d4-a716-446655440000', 'abc123', 'mg.aangel10@gmail.co', 'https://th.bing.com/th/id/OIP.c8IinPPprg5woSuUeFaW2QHaHZ?w=198&h=198&c=7&r=0&o=5&dpr=1.3&pid=1.7', 'Lopez', 'Juan', '{bcrypt}$2a$10$z/ZYnYVxrr1QyZv.uMVfvOkPBBcxQUWP.lVqdIiKheLTfRV3k7cL6', '1234567890', 'juan_garcia');

-- Insertando en la tabla 'socio_roles'
INSERT INTO socio_roles (roles, socio_id) VALUES (0, '550e8400-e29b-41d4-a716-446655440000');


-- Insertando en la tabla 'administrador'
INSERT INTO administrador (account_non_expired, account_non_locked, birth_date, credentials_non_expired, enabled, created_at, last_password_change_at, id, confirmation_token, email, foto_url, last_name, name, password, phone_number, puesto, username) VALUES (true, true, '1980-01-01', true, true, '2024-04-24 09:58:42', '2024-04-24 09:58:42', '123e4567-e89b-12d3-a456-426655440002', 'abc123', 'admin', 'https://th.bing.com/th/id/OIP.YWRTRwDIalUVAmXD--_RwgHaIw?w=163&h=193&c=7&r=0&o=5&dpr=1.3&pid=1.7', 'Perez', 'Carlos', '{bcrypt}$2a$10$z/ZYnYVxrr1QyZv.uMVfvOkPBBcxQUWP.lVqdIiKheLTfRV3k7cL6', '1234567890', 'Gerente', 'carlos_perez');

-- Insertando en la tabla 'administrador_roles'
INSERT INTO administrador_roles (roles, administrador_id) VALUES (1, '123e4567-e89b-12d3-a456-426655440002');
