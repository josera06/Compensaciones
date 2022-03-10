DROP TABLE compensaciones.tb_solicitud;
DROP TABLE compensaciones.tb_trabajador;

CREATE TABLE compensaciones.tb_trabajador (
	id_trabajador serial primary key,
	nombre character varying,
	ap_paterno character varying,
	ap_materno character varying,
	email character varying,
	matricula character varying,
	categoria character varying,
	sueldo_quincenal numeric,
	horario_discontinuo boolean,
	calle character varying,
	colonia character varying,
	codigo_postal character varying,
	municipio character varying,
	estado character varying,
	telefono character varying,
	confirmado boolean
);

CREATE TABLE compensaciones.tb_solicitud(
    id_solicitud serial primary key,
    id_trabajador integer REFERENCES compensaciones.tb_trabajador(id_trabajador),
    fecha timestamp,
    numero_control character varying,
    importe_quincenal numeric,
    fec_ini date,
    observaciones character varying,
    matricula character varying,
    concepto character varying,
    inc_unidades character varying,
    inc_importe numeric,
    inc_quincena_inicial character varying,
    inc_quincena_final character varying,
    inc_no_control character varying,
    inc_cifra_control character varying,
    inc_responsable_reporte character varying,
    retro_unidades character varying,
    retro_importe numeric,
    retro_quincena_inicial character varying,
    retro_quincena_final character varying,
    retro_no_control character varying,
    retro_cifra_control character varying,
    retro_responsable_reporte character varying
);

CREATE DATABASE projects;
CREATE SCHEMA compensaciones;
CREATE USER compensacion WITH password '1234';
alter user compensacion with encrypted password '1234' login;
GRANT USAGE ON SCHEMA compensaciones TO compensacion;
SET search_path TO compensacion;
ALTER ROLE compensacion SET search_path TO compensaciones;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA compensaciones TO usercomp;

ALTER TABLE public.myname_record OWNER to rndb;

 tb_adscripcion
 tb_adscripcion_id_adscripcion_seq
 compensaciones | tb_dictamen

 createdb compensacion
 create user usercomp  with password '1234'
 psql compensacion < compensacion.sql -U usercomp -h localhost

 --Hosting
 --godaddy

--Prueba reporte
--http://localhost:8080/report/ventas/download?tipo=PDF&confirmado=true
