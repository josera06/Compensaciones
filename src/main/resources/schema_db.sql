DROP TABLE compensaciones.tb_adscripcion;
CREATE TABLE compensaciones.tb_adscripcion (
	id_adscripcion serial primary key,
	calle character varying,
	colonia character varying,
	codigo_postal character varying,
	municipio character varying,
	estado character varying,
	telefono character varying
);

DROP TABLE compensaciones.tb_trabajador;
CREATE TABLE compensaciones.tb_trabajador (
	id_trabajador serial primary key,
	nombre character varying,
	ap_paterno character varying,
	ap_materno character varying,
	matricula character varying,
	categoria character varying,
	sueldo_quincenal numeric,
	id_adscripcion integer REFERENCES compensaciones.tb_adscripcion(id_adscripcion),
	horario_discontinuo boolean,
	calle character varying,
	colonia character varying,
	codigo_postal character varying,
	municipio character varying,
	estado character varying,
	telefono character varying,
	representante_imss boolean,
	representante_sntss boolean
);

DROP TABLE compensaciones.tb_solicitud;
CREATE TABLE compensaciones.tb_solicitud(
    id_solicitud serial primary key,
    fecha timestamp,
    numero_control character varying,
    id_trabajador integer REFERENCES compensaciones.tb_trabajador(id_trabajador)
);

DROP TABLE compensaciones.tb_dictamen;
CREATE TABLE compensaciones.tb_dictamen(
    id_dictamen serial primary key,
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
    retro_responsable_reporte character varying,
    id_trabajador integer REFERENCES compensaciones.tb_trabajador(id_trabajador)
);

CREATE DATABASE projects;
CREATE SCHEMA compensaciones;
CREATE USER compensacion WITH password '1234';
alter user compensacion with encrypted password '1234' login;
GRANT USAGE ON SCHEMA compensaciones TO compensacion;
SET search_path TO compensacion;
ALTER ROLE compensacion SET search_path TO compensaciones;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA compensaciones TO compensacion;

ALTER TABLE public.myname_record OWNER to rndb;

 tb_adscripcion
 tb_adscripcion_id_adscripcion_seq
 compensaciones | tb_dictamen