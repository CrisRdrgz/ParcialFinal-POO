-- Crear la base de datos BancoCentral
CREATE DATABASE BancoCentral;

-- Usar la base de datos BancoCentral
USE BancoCentral;

-- Crear el usuario bancoadmin con la contraseña 12345
CREATE LOGIN bancoadmin WITH PASSWORD = '12345';

-- Asignar al usuario acceso a la base de datos BancoCentral
CREATE USER bancoadmin FOR LOGIN bancoadmin;
ALTER ROLE db_owner ADD MEMBER bancoadmin;

-- Eliminar la base de datos BancoCentral
DROP DATABASE BancoCentral;
