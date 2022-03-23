INSERT INTO compensaciones.tb_trabajador(nombre, ap_paterno, ap_materno) VALUES ('Juan', 'Dominguez', 'Martinez');
INSERT INTO compensaciones.tb_trabajador(nombre, ap_paterno, ap_materno) VALUES ('Karin', 'Fabela', 'Hernandez');

INSERT INTO compensaciones.tb_usuario(username, password, id_trabajador) VALUES ('admin', '$2a$10$6iGASi9s15UG9uZDi7kg2.pHev58B5WR055EZSzaOI5eJiLs2jqki', 1);
INSERT INTO compensaciones.tb_usuario(username, password, id_trabajador) VALUES ('user', '$2a$10$KNop05QtwhHIYgKL6kVjheDdP7ItDNVi2eubbKsqrIwo3lDhll7WO', 2);

INSERT INTO compensaciones.tb_rol(nombre, id_usuario) VALUES ('ROLE_ADMIN', 1);
INSERT INTO compensaciones.tb_rol(nombre, id_usuario) VALUES ('ROLE_USER', 1);
INSERT INTO compensaciones.tb_rol(nombre, id_usuario) VALUES ('ROLE_USER', 2);



CREATE DATABASE projects;
CREATE SCHEMA compensaciones;
CREATE USER compensacion WITH password '1234';
alter user compensacion with encrypted password '1234' login;
GRANT USAGE ON SCHEMA compensaciones TO usercomp;
SET search_path TO compensacion;
ALTER ROLE compensacion SET search_path TO compensaciones;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA compensaciones TO usercomp;

ALTER TABLE public.myname_record OWNER to rndb;

 createdb compensacion
 create user usercomp  with password '1234'
 psql compensacion < compensacion.sql -U usercomp -h localhost

--Prueba reporte
--http://localhost:8080/report/ventas/download?tipo=PDF&confirmado=true
