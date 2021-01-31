/*
Created: 11/2/2020
Modified: 11/12/2020
Model: RE PostgreSQL 12
Database: PostgreSQL 12
*/


CREATE SCHEMA "came" AUTHORIZATION "postgres"
;

-- Create functions section -------------------------------------------------

CREATE FUNCTION "came"."next_id"(OUT "result" int8)
RETURNS int8
  LANGUAGE plpgsql
  VOLATILE
AS
$$
DECLARE
    our_epoch bigint := 1314220021721;
    seq_id bigint;
    now_millis bigint;
    shard_id int := 1;
BEGIN
    SELECT nextval('came.secuencia_id') % 1024 INTO seq_id;
    SELECT FLOOR(EXTRACT(EPOCH FROM clock_timestamp()) * 1000) INTO now_millis;
    result := (now_millis - our_epoch) << 23;
    result := result | (shard_id << 10);
    result := result | (seq_id);
END;
$$
;

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE "came"."secuencia_id"
  INCREMENT BY 1
  START WITH 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1
;

-- Create tables section -------------------------------------------------

-- Table came.came_acciones_mejoras

CREATE TABLE "came"."came_acciones_mejoras"
(
  "id_acciones_mejoras" Bigint NOT NULL,
  "id_foco" Bigint,
  "nombre" Character varying,
  "descripcion" Character varying,
  "ultima_modificacion" Timestamp NOT NULL,
  "id_usuario_modificacion" Bigint NOT NULL,
  "cracion" Date
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship138" ON "came"."came_acciones_mejoras" ("id_foco")
;

ALTER TABLE "came"."came_acciones_mejoras" ADD CONSTRAINT "PK_came_acciones_mejoras" PRIMARY KEY ("id_acciones_mejoras")
;

-- Table came.came_asesoria

CREATE TABLE "came"."came_asesoria"
(
  "id_asesoria" Bigint NOT NULL,
  "id_supervisor_establecimiento" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship82" ON "came"."came_asesoria" ("id_supervisor_establecimiento")
;

ALTER TABLE "came"."came_asesoria" ADD CONSTRAINT "PK_came_asesoria" PRIMARY KEY ("id_asesoria")
;

-- Table came.came_asignacion_tipo

CREATE TABLE "came"."came_asignacion_tipo"
(
  "id_asignacion_tipo" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_asignacion_tipo" ADD CONSTRAINT "PK_came_asignacion_tipo" PRIMARY KEY ("id_asignacion_tipo")
;

-- Table came.came_came_estandares_indicativos_desempenio

CREATE TABLE "came"."came_came_estandares_indicativos_desempenio"
(
  "id_estandares_indicativos_desempenio" Bigint NOT NULL,
  "id_foco" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship139" ON "came"."came_came_estandares_indicativos_desempenio" ("id_foco")
;

ALTER TABLE "came"."came_came_estandares_indicativos_desempenio" ADD CONSTRAINT "PK_came_came_estandares_indicativos_desempenio" PRIMARY KEY ("id_estandares_indicativos_desempenio")
;

-- Table came.came_categorizacion

CREATE TABLE "came"."came_categorizacion"
(
  "id_categorizacion" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_categorizacion" ADD CONSTRAINT "PK_came_categorizacion" PRIMARY KEY ("id_categorizacion")
;

-- Table came.came_clasificacion_sep

CREATE TABLE "came"."came_clasificacion_sep"
(
  "id_clasificacion_sep" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_clasificacion_sep" ADD CONSTRAINT "PK_came_clasificacion_sep" PRIMARY KEY ("id_clasificacion_sep")
;

-- Table came.came_director

CREATE TABLE "came"."came_director"
(
  "id_director" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_director" ADD CONSTRAINT "PK_came_director" PRIMARY KEY ("id_director")
;

-- Table came.came_elemento_lista

CREATE TABLE "came"."came_elemento_lista"
(
  "id_elemento_lista" Bigint NOT NULL,
  "id_lista" Bigint,
  "nombre" Character varying,
  "status" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship126" ON "came"."came_elemento_lista" ("id_lista")
;

ALTER TABLE "came"."came_elemento_lista" ADD CONSTRAINT "PK_came_elemento_lista" PRIMARY KEY ("id_elemento_lista")
;

-- Table came.came_establecimiento

CREATE TABLE "came"."came_establecimiento"
(
  "id_establecimiento" Bigint NOT NULL,
  "id_categorizacion1" Bigint NOT NULL,
  "id_establecimiento_estado" Bigint,
  "id_director" Bigint,
  "id_categorizacion2" Bigint NOT NULL GENERATED ALWAYS AS IDENTITY
    (INCREMENT BY 1 NO MINVALUE NO MAXVALUE START WITH 1 CACHE 1),
  "id_clasificacion_sep" Bigint,
  "id_sostenedor" Bigint,
  "id_deprov" Bigint,
  "id_comuna" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship116" ON "came"."came_establecimiento" ("id_establecimiento_estado")
;

CREATE INDEX "IX_Relationship118" ON "came"."came_establecimiento" ("id_director")
;

CREATE INDEX "IX_Relationship129" ON "came"."came_establecimiento" ("id_categorizacion2")
;

CREATE INDEX "IX_Relationship130" ON "came"."came_establecimiento" ("id_clasificacion_sep")
;

CREATE INDEX "IX_Relationship131" ON "came"."came_establecimiento" ("id_categorizacion2")
;

CREATE INDEX "IX_Relationship135" ON "came"."came_establecimiento" ("id_sostenedor")
;

ALTER TABLE "came"."came_establecimiento" ADD CONSTRAINT "PK_came_establecimiento" PRIMARY KEY ("id_establecimiento")
;

-- Table came.came_establecimiento_categorizacion_historica

CREATE TABLE "came"."came_establecimiento_categorizacion_historica"
(
  "id_establecimiento_categorizacion_historica" Bigint NOT NULL,
  "id_categorizacion" Bigint,
  "id_establecimiento" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship95" ON "came"."came_establecimiento_categorizacion_historica" ("id_categorizacion")
;

CREATE INDEX "IX_Relationship128" ON "came"."came_establecimiento_categorizacion_historica" ("id_establecimiento")
;

CREATE INDEX "IX_Relationship151" ON "came"."came_establecimiento_categorizacion_historica" ("id_establecimiento")
;

ALTER TABLE "came"."came_establecimiento_categorizacion_historica" ADD CONSTRAINT "PK_came_establecimiento_categorizacion_historica" PRIMARY KEY ("id_establecimiento_categorizacion_historica")
;

-- Table came.came_establecimiento_estado

CREATE TABLE "came"."came_establecimiento_estado"
(
  "id_establecimiento_estado" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_establecimiento_estado" ADD CONSTRAINT "PK_came_establecimiento_estado" PRIMARY KEY ("id_establecimiento_estado")
;

-- Table came.came_estado

CREATE TABLE "came"."came_estado"
(
  "id_estado" Bigint NOT NULL,
  "nombre" Character varying NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_estado" ADD CONSTRAINT "PK_came_estado" PRIMARY KEY ("id_estado")
;

-- Table came.came_foco

CREATE TABLE "came"."came_foco"
(
  "id_foco" Bigint NOT NULL,
  "nombre" Character varying,
  "descripcion" Character varying,
  "cracion" Date,
  "ultima_modificacion" Timestamp NOT NULL,
  "id_usuario_modificacion" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_foco" ADD CONSTRAINT "PK_came_foco" PRIMARY KEY ("id_foco")
;

-- Table came.came_lista

CREATE TABLE "came"."came_lista"
(
  "id_lista" Bigint NOT NULL,
  "nombre" Character varying
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_lista" ADD CONSTRAINT "PK_came_lista" PRIMARY KEY ("id_lista")
;

-- Table came.came_matricula

CREATE TABLE "came"."came_matricula"
(
  "id_matricula" Bigint NOT NULL,
  "id_matricula_etnia" Bigint,
  "id_nivel" Bigint,
  "id_establecimiento" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship92" ON "came"."came_matricula" ("id_matricula_etnia")
;

CREATE INDEX "IX_Relationship93" ON "came"."came_matricula" ("id_nivel")
;

CREATE INDEX "IX_Relationship117" ON "came"."came_matricula" ("id_establecimiento")
;

ALTER TABLE "came"."came_matricula" ADD CONSTRAINT "PK_came_matricula" PRIMARY KEY ("id_matricula")
;

-- Table came.came_matricula_etnia

CREATE TABLE "came"."came_matricula_etnia"
(
  "id_matricula_etnia" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_matricula_etnia" ADD CONSTRAINT "PK_came_matricula_etnia" PRIMARY KEY ("id_matricula_etnia")
;

-- Table came.came_modulo

CREATE TABLE "came"."came_modulo"
(
  "id_modulo" Bigint NOT NULL,
  "id_estado" Bigint,
  "nombre" Character varying,
  "status" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship123" ON "came"."came_modulo" ("id_estado")
;

ALTER TABLE "came"."came_modulo" ADD CONSTRAINT "PK_came_modulo" PRIMARY KEY ("id_modulo")
;

-- Table came.came_movimientos_claves

CREATE TABLE "came"."came_movimientos_claves"
(
  "id_movimientos_claves" Bigint NOT NULL,
  "id_acciones_mejoras" Bigint,
  "nombre" Character varying,
  "descripcion" Character varying,
  "ultima_modificacion" Timestamp NOT NULL,
  "id_usuario_modificacion" Bigint NOT NULL,
  "cracion" Date
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship140" ON "came"."came_movimientos_claves" ("id_acciones_mejoras")
;

ALTER TABLE "came"."came_movimientos_claves" ADD CONSTRAINT "PK_came_movimientos_claves" PRIMARY KEY ("id_movimientos_claves")
;

-- Table came.came_nivel

CREATE TABLE "came"."came_nivel"
(
  "id_nivel" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_nivel" ADD CONSTRAINT "PK_came_nivel" PRIMARY KEY ("id_nivel")
;

-- Table came.came_objetivo_mejora

CREATE TABLE "came"."came_objetivo_mejora"
(
  "id_objetivo_mejora" Bigint NOT NULL,
  "id_foco" Bigint,
  "id_sesion_foco" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship146" ON "came"."came_objetivo_mejora" ("id_foco")
;

CREATE INDEX "IX_Relationship147" ON "came"."came_objetivo_mejora" ("id_sesion_foco")
;

ALTER TABLE "came"."came_objetivo_mejora" ADD CONSTRAINT "PK_came_objetivo_mejora" PRIMARY KEY ("id_objetivo_mejora")
;

-- Table came.came_perfil

CREATE TABLE "came"."came_perfil"
(
  "id_perfil" Bigint NOT NULL,
  "id_estado" Bigint,
  "id_perfil_nivel" Bigint,
  "nombre" Character varying,
  "descripcion" Character varying,
  "habilitado" Boolean NOT NULL,
  "ultima_modificacion" Timestamp NOT NULL,
  "id_usuario_modificacion" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship98" ON "came"."came_perfil" ("id_estado")
;

CREATE INDEX "IX_Relationship67" ON "came"."came_perfil" ("id_perfil_nivel")
;

ALTER TABLE "came"."came_perfil" ADD CONSTRAINT "PK_came_perfil" PRIMARY KEY ("id_perfil")
;

-- Table came.came_perfil_menu

CREATE TABLE "came"."came_perfil_menu"
(
  "id_perfil_menu" Bigint NOT NULL,
  "id_perfil" Bigint,
  "id_sub_modulo" Bigint,
  "id_perfil_menu_acceso" Bigint,
  "start_date" Timestamp,
  "end_date" Timestamp,
  "status" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship109" ON "came"."came_perfil_menu" ("id_perfil")
;

CREATE INDEX "IX_Relationship124" ON "came"."came_perfil_menu" ("id_sub_modulo")
;

CREATE INDEX "IX_Relationship152" ON "came"."came_perfil_menu" ("id_perfil_menu_acceso")
;

ALTER TABLE "came"."came_perfil_menu" ADD CONSTRAINT "PK_came_perfil_menu" PRIMARY KEY ("id_perfil_menu")
;

-- Table came.came_perfil_menu_acceso

CREATE TABLE "came"."came_perfil_menu_acceso"
(
  "id_perfil_menu_acceso" Bigint NOT NULL,
  "nombre" Character varying NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_perfil_menu_acceso" ADD CONSTRAINT "PK_came_perfil_menu_acceso" PRIMARY KEY ("id_perfil_menu_acceso")
;

-- Table came.came_perfil_nivel

CREATE TABLE "came"."came_perfil_nivel"
(
  "id_perfil_nivel" Bigint NOT NULL,
  "nombre" Character varying NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_perfil_nivel" ADD CONSTRAINT "PK_came_perfil_nivel" PRIMARY KEY ("id_perfil_nivel")
;

-- Table came.came_periodo

CREATE TABLE "came"."came_periodo"
(
  "id_periodo" Bigint NOT NULL,
  "anio" Bigint,
  "id_usuario_registro" Bigint,
  "fecha_registro" Timestamp
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_periodo" ADD CONSTRAINT "PK_came_periodo" PRIMARY KEY ("id_periodo")
;

-- Table came.came_periodo_documentos_provinciales

CREATE TABLE "came"."came_periodo_documentos_provinciales"
(
  "periodo_documentos_provinciales" Bigint NOT NULL,
  "id_periodo" Bigint,
  "id_region" Bigint,
  "id_deprov" Bigint,
  "visible" Boolean,
  "es_caso_especial" Boolean,
  "template_path" Character varying,
  "fecha_registro" Timestamp,
  "fecha_inicio" Timestamp,
  "fecha_fin" Timestamp,
  "id_usuario_registro" Bigint,
  "fecha_registro1" Timestamp
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship133" ON "came"."came_periodo_documentos_provinciales" ("id_periodo")
;

ALTER TABLE "came"."came_periodo_documentos_provinciales" ADD CONSTRAINT "PK_came_periodo_documentos_provinciales" PRIMARY KEY ("periodo_documentos_provinciales")
;

-- Table came.came_periodo_documentos_regionales

CREATE TABLE "came"."came_periodo_documentos_regionales"
(
  "id_periodo_documentos_regionales" Bigint NOT NULL,
  "id_periodo" Bigint,
  "id_region" Bigint,
  "fecha_inicio" Timestamp,
  "fecha_fin" Timestamp,
  "visible" Boolean,
  "es_caso_especial" Boolean,
  "template_path" Character varying,
  "id_usuario_registro" Bigint,
  "fecha_registro" Timestamp
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship127" ON "came"."came_periodo_documentos_regionales" ("id_periodo")
;

ALTER TABLE "came"."came_periodo_documentos_regionales" ADD CONSTRAINT "PK_came_periodo_documentos_regionales" PRIMARY KEY ("id_periodo_documentos_regionales")
;

-- Table came.came_periodo_conformacion_redes

CREATE TABLE "came"."came_periodo_conformacion_redes"
(
  "id_periodo_conformacion_redes" Bigint NOT NULL,
  "id_periodo" Bigint,
  "fecha_inicio" Timestamp,
  "fecha_fin" Timestamp,
  "id_usuario_registro" Bigint,
  "fecha_registro" Timestamp,
  "es_caso_especial" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship132" ON "came"."came_periodo_conformacion_redes" ("id_periodo")
;

ALTER TABLE "came"."came_periodo_conformacion_redes" ADD CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial" PRIMARY KEY ("id_periodo_conformacion_redes")
;

-- Table came.came_periodo_planificacion_implementacion_apoyo

CREATE TABLE "came"."came_periodo_planificacion_implementacion_apoyo"
(
  "id_periodo_planificacion_implementacion_apoyo" Bigint NOT NULL,
  "id_periodo" Bigint,
  "fecha_inicio" Timestamp,
  "fecha_fin" Timestamp,
  "id_usuario_registro" Bigint,
  "fecha_registro" Timestamp,
  "es_caso_especial" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship113" ON "came"."came_periodo_planificacion_implementacion_apoyo" ("id_periodo")
;

ALTER TABLE "came"."came_periodo_planificacion_implementacion_apoyo" ADD CONSTRAINT "PK_came_periodo_planificacion_implementacion_apoyo" PRIMARY KEY ("id_periodo_planificacion_implementacion_apoyo")
;

-- Table came.came_red

CREATE TABLE "came"."came_red"
(
  "id_red" Bigint NOT NULL,
  "id_red_tipo" Bigint,
  "id_periodo" Bigint,
  "id_usuario" Bigint,
  "id_deprov" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship106" ON "came"."came_red" ("id_red_tipo")
;

CREATE INDEX "IX_Relationship114" ON "came"."came_red" ("id_periodo")
;

CREATE INDEX "IX_Relationship137" ON "came"."came_red" ("id_usuario")
;

ALTER TABLE "came"."came_red" ADD CONSTRAINT "PK_came_red" PRIMARY KEY ("id_red")
;

-- Table came.came_red_establecimiento

CREATE TABLE "came"."came_red_establecimiento"
(
  "id_red_establecimiento" Bigint NOT NULL,
  "id_red" Bigint,
  "id_establecimiento" Bigint,
  "id_periodo" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship107" ON "came"."came_red_establecimiento" ("id_red")
;

CREATE INDEX "IX_Relationship115" ON "came"."came_red_establecimiento" ("id_establecimiento")
;

CREATE INDEX "IX_Relationship154" ON "came"."came_red_establecimiento" ("id_periodo")
;

ALTER TABLE "came"."came_red_establecimiento" ADD CONSTRAINT "PK_came_red_establecimiento" PRIMARY KEY ("id_red_establecimiento")
;

-- Table came.came_red_sostenedor

CREATE TABLE "came"."came_red_sostenedor"
(
  "id_red_sostenedor" Bigint NOT NULL,
  "id_red" Bigint,
  "id_sostenedor" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship105" ON "came"."came_red_sostenedor" ("id_red")
;

CREATE INDEX "IX_Relationship110" ON "came"."came_red_sostenedor" ("id_sostenedor")
;

ALTER TABLE "came"."came_red_sostenedor" ADD CONSTRAINT "PK_came_red_sostenedor" PRIMARY KEY ("id_red_sostenedor")
;

-- Table came.came_red_tipo

CREATE TABLE "came"."came_red_tipo"
(
  "id_red_tipo" Bigint NOT NULL,
  "nombre" Character varying
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_red_tipo" ADD CONSTRAINT "PK_came_red_tipo" PRIMARY KEY ("id_red_tipo")
;

-- Table came.came_sesion

CREATE TABLE "came"."came_sesion"
(
  "id_sesion" Bigint NOT NULL,
  "id_asesoria" Bigint,
  "id_session_estado" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship83" ON "came"."came_sesion" ("id_asesoria")
;

CREATE INDEX "IX_Relationship85" ON "came"."came_sesion" ("id_session_estado")
;

ALTER TABLE "came"."came_sesion" ADD CONSTRAINT "PK_came_sesion" PRIMARY KEY ("id_sesion")
;

-- Table came.came_sesion_estado

CREATE TABLE "came"."came_sesion_estado"
(
  "id_sesion_estado" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_sesion_estado" ADD CONSTRAINT "PK_came_sesion_estado" PRIMARY KEY ("id_sesion_estado")
;

-- Table came.came_sesion_foco

CREATE TABLE "came"."came_sesion_foco"
(
  "id_sesion_foco" Bigint NOT NULL,
  "id_sesion" Bigint,
  "id_foco" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship141" ON "came"."came_sesion_foco" ("id_sesion")
;

CREATE INDEX "IX_Relationship142" ON "came"."came_sesion_foco" ("id_foco")
;

ALTER TABLE "came"."came_sesion_foco" ADD CONSTRAINT "PK_came_sesion_foco" PRIMARY KEY ("id_sesion_foco")
;

-- Table came.came_sesion_movimientos_claves

CREATE TABLE "came"."came_sesion_movimientos_claves"
(
  "id_sesion_movimientos_claves" Bigint NOT NULL,
  "id_sesion_foco" Bigint,
  "id_foco" Bigint,
  "id_movimientos_claves" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship143" ON "came"."came_sesion_movimientos_claves" ("id_sesion_foco")
;

CREATE INDEX "IX_Relationship144" ON "came"."came_sesion_movimientos_claves" ("id_foco")
;

CREATE INDEX "IX_Relationship145" ON "came"."came_sesion_movimientos_claves" ("id_movimientos_claves")
;

ALTER TABLE "came"."came_sesion_movimientos_claves" ADD CONSTRAINT "PK_came_sesion_movimientos_claves" PRIMARY KEY ("id_sesion_movimientos_claves")
;

-- Table came.came_sostenedor

CREATE TABLE "came"."came_sostenedor"
(
  "id_sostenedor" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_sostenedor" ADD CONSTRAINT "PK_came_sostenedor" PRIMARY KEY ("id_sostenedor")
;

-- Table came.came_sub_modulo

CREATE TABLE "came"."came_sub_modulo"
(
  "id_sub_modulo" Bigint NOT NULL,
  "id_estado" Bigint,
  "id_modulo" Bigint,
  "nombre" Character varying,
  "status" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship97" ON "came"."came_sub_modulo" ("id_estado")
;

CREATE INDEX "IX_Relationship122" ON "came"."came_sub_modulo" ("id_modulo")
;

ALTER TABLE "came"."came_sub_modulo" ADD CONSTRAINT "PK_came_sub_modulo" PRIMARY KEY ("id_sub_modulo")
;

-- Table came.came_supervisor

CREATE TABLE "came"."came_supervisor"
(
  "id_supervisor" Bigint NOT NULL,
  "id_usuario" Bigint,
  "id_deprov" Bigint,
  "start_date" Date,
  "end_date" Date,
  "status" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship100" ON "came"."came_supervisor" ("id_usuario")
;

ALTER TABLE "came"."came_supervisor" ADD CONSTRAINT "PK_came_supervisor" PRIMARY KEY ("id_supervisor")
;

-- Table came.came_supervisor_establecimiento

CREATE TABLE "came"."came_supervisor_establecimiento"
(
  "id_supervisor_establecimiento" Bigint NOT NULL,
  "id_asignacion_tipo" Bigint,
  "id_supervisor" Bigint,
  "id_red" Bigint,
  "id_establecimiento" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship81" ON "came"."came_supervisor_establecimiento" ("id_asignacion_tipo")
;

CREATE INDEX "IX_Relationship84" ON "came"."came_supervisor_establecimiento" ("id_supervisor")
;

CREATE INDEX "IX_Relationship102" ON "came"."came_supervisor_establecimiento" ("id_red")
;

CREATE INDEX "IX_Relationship134" ON "came"."came_supervisor_establecimiento" ("id_establecimiento")
;

ALTER TABLE "came"."came_supervisor_establecimiento" ADD CONSTRAINT "PK_came_supervisor_establecimiento" PRIMARY KEY ("id_supervisor_establecimiento")
;

-- Table came.came_usuario

CREATE TABLE "came"."came_usuario"
(
  "id_usuario" Bigint NOT NULL,
  "id_estado" Bigint,
  "id_region" Bigint,
  "id_deprov" Bigint,
  "usuario_dominio" Character varying NOT NULL,
  "run" Character varying(12),
  "nombre" Character varying,
  "apellido_paterno" Character varying,
  "apellido_materno" Character varying,
  "email" Character varying,
  "habilitado" Boolean,
  "reintentos" Integer,
  "ultima_modificacion" Timestamp,
  "id_usuario_modificacion" Bigint,
  "dv" Character varying(1),
  "fecha_registro" Timestamp,
  "id_usuario_registro" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship99" ON "came"."came_usuario" ("id_estado")
;

ALTER TABLE "came"."came_usuario" ADD CONSTRAINT "PK_came_usuario" PRIMARY KEY ("id_usuario")
;

-- Table came.came_usuario_perfil

CREATE TABLE "came"."came_usuario_perfil"
(
  "id_usuario_perfil" Bigint NOT NULL,
  "id_usuario" Bigint,
  "id_perfil" Bigint,
  "start_date" Timestamp,
  "end_date" Timestamp,
  "status" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship148" ON "came"."came_usuario_perfil" ("id_usuario")
;

CREATE INDEX "IX_Relationship149" ON "came"."came_usuario_perfil" ("id_perfil")
;

ALTER TABLE "came"."came_usuario_perfil" ADD CONSTRAINT "PK_came_usuario_perfil" PRIMARY KEY ("id_usuario_perfil")
;

-- Table came.came_periodo_asignacion_supervisores

CREATE TABLE "came"."came_periodo_asignacion_supervisores"
(
  "id_periodo_asignacion_supervisores" Bigint NOT NULL,
  "id_periodo" Bigint,
  "fecha_inicio" Timestamp,
  "fecha_fin" Timestamp,
  "id_usuario_registro" Bigint,
  "fecha_registro" Timestamp,
  "es_caso_especial" Boolean
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship155" ON "came"."came_periodo_asignacion_supervisores" ("id_periodo")
;

ALTER TABLE "came"."came_periodo_asignacion_supervisores" ADD CONSTRAINT "PK_came_periodo_asignacion_supervisores" PRIMARY KEY ("id_periodo_asignacion_supervisores")
;

-- Table came.came_asignacion_maxima

CREATE TABLE "came"."came_asignacion_maxima"
(
  "id_asignacion_maxima" Bigint NOT NULL,
  "supervisores" Integer,
  "foco" Bigint,
  "id_usuario_registro" Bigint,
  "fecha_registro" Timestamp
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "came"."came_asignacion_maxima" ADD CONSTRAINT "PK_came_asignacion_maxima" PRIMARY KEY ("id_asignacion_maxima")
;

-- Table came.came_supervisor_suplencia

CREATE TABLE "came"."came_supervisor_suplencia"
(
  "id_supervisor_suplencia" Bigint NOT NULL,
  "id_supervisor_ausente" Bigint,
  "id_supervisor_suplente" Bigint,
  "start_date" Timestamp,
  "end_date" Timestamp,
  "fecha_registro" Timestamp,
  "id_usuario_registro" Bigint
)
WITH (
  autovacuum_enabled=true)
;

CREATE INDEX "IX_Relationship158" ON "came"."came_supervisor_suplencia" ("id_supervisor_ausente")
;

CREATE INDEX "IX_Relationship159" ON "came"."came_supervisor_suplencia" ("id_supervisor_suplente")
;

ALTER TABLE "came"."came_supervisor_suplencia" ADD CONSTRAINT "PK_came_supervisor_suplencia" PRIMARY KEY ("id_supervisor_suplencia")
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE "came"."came_acciones_mejoras"
  ADD CONSTRAINT "Relationship138"
    FOREIGN KEY ("id_foco")
    REFERENCES "came"."came_foco" ("id_foco")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_asesoria"
  ADD CONSTRAINT "Relationship82"
    FOREIGN KEY ("id_supervisor_establecimiento")
    REFERENCES "came"."came_supervisor_establecimiento" ("id_supervisor_establecimiento")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_came_estandares_indicativos_desempenio"
  ADD CONSTRAINT "Relationship139"
    FOREIGN KEY ("id_foco")
    REFERENCES "came"."came_foco" ("id_foco")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_elemento_lista"
  ADD CONSTRAINT "Relationship126"
    FOREIGN KEY ("id_lista")
    REFERENCES "came"."came_lista" ("id_lista")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento"
  ADD CONSTRAINT "Relationship129"
    FOREIGN KEY ("id_categorizacion2")
    REFERENCES "came"."came_categorizacion" ("id_categorizacion")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento"
  ADD CONSTRAINT "Relationship131"
    FOREIGN KEY ("id_categorizacion2")
    REFERENCES "came"."came_categorizacion" ("id_categorizacion")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento"
  ADD CONSTRAINT "Relationship67"
    FOREIGN KEY ("id_categorizacion1")
    REFERENCES "came"."came_categorizacion" ("id_categorizacion")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento"
  ADD CONSTRAINT "Relationship130"
    FOREIGN KEY ("id_clasificacion_sep")
    REFERENCES "came"."came_clasificacion_sep" ("id_clasificacion_sep")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento"
  ADD CONSTRAINT "Relationship118"
    FOREIGN KEY ("id_director")
    REFERENCES "came"."came_director" ("id_director")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento"
  ADD CONSTRAINT "Relationship116"
    FOREIGN KEY ("id_establecimiento_estado")
    REFERENCES "came"."came_establecimiento_estado" ("id_establecimiento_estado")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento"
  ADD CONSTRAINT "Relationship135"
    FOREIGN KEY ("id_sostenedor")
    REFERENCES "came"."came_sostenedor" ("id_sostenedor")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento_categorizacion_historica"
  ADD CONSTRAINT "Relationship95"
    FOREIGN KEY ("id_categorizacion")
    REFERENCES "came"."came_categorizacion" ("id_categorizacion")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento_categorizacion_historica"
  ADD CONSTRAINT "Relationship128"
    FOREIGN KEY ("id_establecimiento")
    REFERENCES "came"."came_establecimiento" ("id_establecimiento")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_establecimiento_categorizacion_historica"
  ADD CONSTRAINT "Relationship151"
    FOREIGN KEY ("id_establecimiento")
    REFERENCES "came"."came_establecimiento" ("id_establecimiento")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_matricula"
  ADD CONSTRAINT "Relationship117"
    FOREIGN KEY ("id_establecimiento")
    REFERENCES "came"."came_establecimiento" ("id_establecimiento")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_matricula"
  ADD CONSTRAINT "Relationship92"
    FOREIGN KEY ("id_matricula_etnia")
    REFERENCES "came"."came_matricula_etnia" ("id_matricula_etnia")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_matricula"
  ADD CONSTRAINT "Relationship93"
    FOREIGN KEY ("id_nivel")
    REFERENCES "came"."came_nivel" ("id_nivel")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_modulo"
  ADD CONSTRAINT "Relationship123"
    FOREIGN KEY ("id_estado")
    REFERENCES "came"."came_estado" ("id_estado")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_movimientos_claves"
  ADD CONSTRAINT "Relationship140"
    FOREIGN KEY ("id_acciones_mejoras")
    REFERENCES "came"."came_acciones_mejoras" ("id_acciones_mejoras")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_objetivo_mejora"
  ADD CONSTRAINT "Relationship146"
    FOREIGN KEY ("id_foco")
    REFERENCES "came"."came_foco" ("id_foco")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_objetivo_mejora"
  ADD CONSTRAINT "Relationship147"
    FOREIGN KEY ("id_sesion_foco")
    REFERENCES "came"."came_sesion_foco" ("id_sesion_foco")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_perfil"
  ADD CONSTRAINT "Relationship98"
    FOREIGN KEY ("id_estado")
    REFERENCES "came"."came_estado" ("id_estado")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_perfil"
  ADD CONSTRAINT "Relationship67"
    FOREIGN KEY ("id_perfil_nivel")
    REFERENCES "came"."came_perfil_nivel" ("id_perfil_nivel")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_perfil_menu"
  ADD CONSTRAINT "Relationship109"
    FOREIGN KEY ("id_perfil")
    REFERENCES "came"."came_perfil" ("id_perfil")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_perfil_menu"
  ADD CONSTRAINT "Relationship152"
    FOREIGN KEY ("id_perfil_menu_acceso")
    REFERENCES "came"."came_perfil_menu_acceso" ("id_perfil_menu_acceso")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_perfil_menu"
  ADD CONSTRAINT "Relationship124"
    FOREIGN KEY ("id_sub_modulo")
    REFERENCES "came"."came_sub_modulo" ("id_sub_modulo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_periodo_documentos_provinciales"
  ADD CONSTRAINT "Relationship133"
    FOREIGN KEY ("id_periodo")
    REFERENCES "came"."came_periodo" ("id_periodo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_periodo_documentos_regionales"
  ADD CONSTRAINT "Relationship127"
    FOREIGN KEY ("id_periodo")
    REFERENCES "came"."came_periodo" ("id_periodo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_periodo_conformacion_redes"
  ADD CONSTRAINT "Relationship132"
    FOREIGN KEY ("id_periodo")
    REFERENCES "came"."came_periodo" ("id_periodo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_periodo_planificacion_implementacion_apoyo"
  ADD CONSTRAINT "Relationship113"
    FOREIGN KEY ("id_periodo")
    REFERENCES "came"."came_periodo" ("id_periodo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red"
  ADD CONSTRAINT "Relationship114"
    FOREIGN KEY ("id_periodo")
    REFERENCES "came"."came_periodo" ("id_periodo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red"
  ADD CONSTRAINT "Relationship106"
    FOREIGN KEY ("id_red_tipo")
    REFERENCES "came"."came_red_tipo" ("id_red_tipo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red"
  ADD CONSTRAINT "Relationship137"
    FOREIGN KEY ("id_usuario")
    REFERENCES "came"."came_usuario" ("id_usuario")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red_establecimiento"
  ADD CONSTRAINT "Relationship115"
    FOREIGN KEY ("id_establecimiento")
    REFERENCES "came"."came_establecimiento" ("id_establecimiento")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red_establecimiento"
  ADD CONSTRAINT "Relationship154"
    FOREIGN KEY ("id_periodo")
    REFERENCES "came"."came_periodo" ("id_periodo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red_establecimiento"
  ADD CONSTRAINT "Relationship107"
    FOREIGN KEY ("id_red")
    REFERENCES "came"."came_red" ("id_red")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red_sostenedor"
  ADD CONSTRAINT "Relationship105"
    FOREIGN KEY ("id_red")
    REFERENCES "came"."came_red" ("id_red")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_red_sostenedor"
  ADD CONSTRAINT "Relationship110"
    FOREIGN KEY ("id_sostenedor")
    REFERENCES "came"."came_sostenedor" ("id_sostenedor")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sesion"
  ADD CONSTRAINT "Relationship83"
    FOREIGN KEY ("id_asesoria")
    REFERENCES "came"."came_asesoria" ("id_asesoria")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sesion"
  ADD CONSTRAINT "Relationship85"
    FOREIGN KEY ("id_session_estado")
    REFERENCES "came"."came_sesion_estado" ("id_sesion_estado")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sesion_foco"
  ADD CONSTRAINT "Relationship142"
    FOREIGN KEY ("id_foco")
    REFERENCES "came"."came_foco" ("id_foco")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sesion_foco"
  ADD CONSTRAINT "Relationship141"
    FOREIGN KEY ("id_sesion")
    REFERENCES "came"."came_sesion" ("id_sesion")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sesion_movimientos_claves"
  ADD CONSTRAINT "Relationship144"
    FOREIGN KEY ("id_foco")
    REFERENCES "came"."came_foco" ("id_foco")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sesion_movimientos_claves"
  ADD CONSTRAINT "Relationship145"
    FOREIGN KEY ("id_movimientos_claves")
    REFERENCES "came"."came_movimientos_claves" ("id_movimientos_claves")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sesion_movimientos_claves"
  ADD CONSTRAINT "Relationship143"
    FOREIGN KEY ("id_sesion_foco")
    REFERENCES "came"."came_sesion_foco" ("id_sesion_foco")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sub_modulo"
  ADD CONSTRAINT "Relationship97"
    FOREIGN KEY ("id_estado")
    REFERENCES "came"."came_estado" ("id_estado")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_sub_modulo"
  ADD CONSTRAINT "Relationship122"
    FOREIGN KEY ("id_modulo")
    REFERENCES "came"."came_modulo" ("id_modulo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_supervisor"
  ADD CONSTRAINT "Relationship100"
    FOREIGN KEY ("id_usuario")
    REFERENCES "came"."came_usuario" ("id_usuario")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_supervisor_establecimiento"
  ADD CONSTRAINT "Relationship81"
    FOREIGN KEY ("id_asignacion_tipo")
    REFERENCES "came"."came_asignacion_tipo" ("id_asignacion_tipo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_supervisor_establecimiento"
  ADD CONSTRAINT "Relationship134"
    FOREIGN KEY ("id_establecimiento")
    REFERENCES "came"."came_establecimiento" ("id_establecimiento")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_supervisor_establecimiento"
  ADD CONSTRAINT "Relationship102"
    FOREIGN KEY ("id_red")
    REFERENCES "came"."came_red" ("id_red")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_supervisor_establecimiento"
  ADD CONSTRAINT "Relationship84"
    FOREIGN KEY ("id_supervisor")
    REFERENCES "came"."came_supervisor" ("id_supervisor")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_usuario"
  ADD CONSTRAINT "Relationship99"
    FOREIGN KEY ("id_estado")
    REFERENCES "came"."came_estado" ("id_estado")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_usuario_perfil"
  ADD CONSTRAINT "Relationship149"
    FOREIGN KEY ("id_perfil")
    REFERENCES "came"."came_perfil" ("id_perfil")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_usuario_perfil"
  ADD CONSTRAINT "Relationship148"
    FOREIGN KEY ("id_usuario")
    REFERENCES "came"."came_usuario" ("id_usuario")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_periodo_asignacion_supervisores"
  ADD CONSTRAINT "Relationship155"
    FOREIGN KEY ("id_periodo")
    REFERENCES "came"."came_periodo" ("id_periodo")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_supervisor_suplencia"
  ADD CONSTRAINT "Relationship158"
    FOREIGN KEY ("id_supervisor_ausente")
    REFERENCES "came"."came_supervisor" ("id_supervisor")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "came"."came_supervisor_suplencia"
  ADD CONSTRAINT "Relationship159"
    FOREIGN KEY ("id_supervisor_suplente")
    REFERENCES "came"."came_supervisor" ("id_supervisor")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

-- Grant permissions section -------------------------------------------------

GRANT "pg_read_all_settings" TO "pg_monitor"
;
GRANT "pg_read_all_stats" TO "pg_monitor"
;
GRANT "pg_stat_scan_tables" TO "pg_monitor"
;

