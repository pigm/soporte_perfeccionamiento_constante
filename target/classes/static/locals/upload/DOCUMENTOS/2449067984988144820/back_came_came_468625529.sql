--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0
-- Dumped by pg_dump version 13.0

-- Started on 2020-11-09 15:46:35

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5 (class 2615 OID 16394)
-- Name: came; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA came;


ALTER SCHEMA came OWNER TO postgres;

--
-- TOC entry 249 (class 1255 OID 16395)
-- Name: next_id(); Type: FUNCTION; Schema: came; Owner: postgres
--

CREATE FUNCTION came.next_id(OUT result bigint) RETURNS bigint
    LANGUAGE plpgsql
    AS $$
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
$$;


ALTER FUNCTION came.next_id(OUT result bigint) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 203 (class 1259 OID 16398)
-- Name: came_acciones_mejoras; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_acciones_mejoras (
    id_acciones_mejoras bigint NOT NULL,
    id_foco bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_acciones_mejoras OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16404)
-- Name: came_asesoria; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_asesoria (
    id_asesoria bigint NOT NULL,
    id_supervisor_establecimiento bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_asesoria OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16410)
-- Name: came_asignacion_tipo; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_asignacion_tipo (
    id_asignacion_tipo bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_asignacion_tipo OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16415)
-- Name: came_came_estandares_indicativos_desempenio; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_came_estandares_indicativos_desempenio (
    id_estandares_indicativos_desempenio bigint NOT NULL,
    id_foco bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_came_estandares_indicativos_desempenio OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 16421)
-- Name: came_categorizacion; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_categorizacion (
    id_categorizacion bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_categorizacion OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16426)
-- Name: came_clasificacion_sep; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_clasificacion_sep (
    id_clasificacion_sep bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_clasificacion_sep OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 16431)
-- Name: came_director; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_director (
    id_director bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_director OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16436)
-- Name: came_elemento_lista; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_elemento_lista (
    id_elemento_lista bigint NOT NULL,
    id_lista bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_elemento_lista OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16447)
-- Name: came_establecimiento; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_establecimiento (
    id_establecimiento bigint NOT NULL,
    id_categorizacion1 bigint NOT NULL,
    id_establecimiento_estado bigint,
    id_director bigint,
    id_categorizacion2 bigint NOT NULL,
    id_clasificacion_sep bigint,
    id_sostenedor bigint,
    id_deprov bigint,
    id_comuna bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_establecimiento OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16458)
-- Name: came_establecimiento_categorizacion_historica; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_establecimiento_categorizacion_historica (
    id_establecimiento_categorizacion_historica bigint NOT NULL,
    id_categorizacion bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_establecimiento_categorizacion_historica OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16466)
-- Name: came_establecimiento_estado; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_establecimiento_estado (
    id_establecimiento_estado bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_establecimiento_estado OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16445)
-- Name: came_establecimiento_id_categorizacion2_seq; Type: SEQUENCE; Schema: came; Owner: postgres
--

ALTER TABLE came.came_establecimiento ALTER COLUMN id_categorizacion2 ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME came.came_establecimiento_id_categorizacion2_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 16471)
-- Name: came_estado; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_estado (
    id_estado bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_estado OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16479)
-- Name: came_foco; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_foco (
    id_foco bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_foco OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16484)
-- Name: came_lista; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_lista (
    id_lista bigint NOT NULL,
    nombre character varying
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_lista OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16492)
-- Name: came_matricula; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_matricula (
    id_matricula bigint NOT NULL,
    id_matricula_etnia bigint,
    id_nivel bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_matricula OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16500)
-- Name: came_matricula_etnia; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_matricula_etnia (
    id_matricula_etnia bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_matricula_etnia OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16505)
-- Name: came_modulo; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_modulo (
    id_modulo bigint NOT NULL,
    id_estado bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_modulo OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16514)
-- Name: came_movimientos_claves; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_movimientos_claves (
    id_movimientos_claves bigint NOT NULL,
    id_acciones_mejoras bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_movimientos_claves OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16520)
-- Name: came_nivel; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_nivel (
    id_nivel bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_nivel OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16525)
-- Name: came_objetivo_mejora; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_objetivo_mejora (
    id_objetivo_mejora bigint NOT NULL,
    id_foco bigint,
    id_sesion_foco bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_objetivo_mejora OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16532)
-- Name: came_perfil; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_perfil (
    id_perfil bigint NOT NULL,
    id_estado bigint,
    id_perfil_nivel bigint,
    nombre character varying,
    descripcion character varying,
    habilitado boolean NOT NULL,
    ultima_modificacion timestamp without time zone NOT NULL,
    id_usuario_modificacion bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_perfil OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 16542)
-- Name: came_perfil_menu; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_perfil_menu (
    id_perfil_menu bigint NOT NULL,
    id_perfil bigint,
    id_sub_modulo bigint,
    id_perfil_menu_acceso bigint,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    status boolean
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_perfil_menu OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 16550)
-- Name: came_perfil_menu_acceso; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_perfil_menu_acceso (
    id_perfil_menu_acceso bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_perfil_menu_acceso OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16558)
-- Name: came_perfil_nivel; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_perfil_nivel (
    id_perfil_nivel bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_perfil_nivel OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16566)
-- Name: came_periodo; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_periodo (
    id_periodo bigint NOT NULL,
    anio bigint,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_periodo OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 17024)
-- Name: came_periodo_asignacion_supervisores; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_periodo_asignacion_supervisores (
    id_periodo_asignacion_supervisores bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_periodo_asignacion_supervisores OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 17018)
-- Name: came_periodo_conformacion_redes; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_periodo_conformacion_redes (
    id_periodo_conformacion_redes bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_periodo_conformacion_redes OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 17006)
-- Name: came_periodo_documentos_provinciales; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_periodo_documentos_provinciales (
    periodo_documentos_provinciales bigint NOT NULL,
    id_periodo bigint,
    id_region bigint,
    id_deprov bigint,
    visible boolean,
    es_caso_especial boolean,
    template_path character varying,
    fecha_registro timestamp without time zone,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro1 timestamp without time zone
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_periodo_documentos_provinciales OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16577)
-- Name: came_periodo_documentos_regionales; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_periodo_documentos_regionales (
    id_periodo_documentos_regionales bigint NOT NULL,
    id_periodo bigint,
    id_region bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    visible boolean,
    es_caso_especial boolean,
    template_path character varying,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_periodo_documentos_regionales OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 16583)
-- Name: came_periodo_organizacion_planificacion_provincial; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_periodo_organizacion_planificacion_provincial (
    id_periodo_organizacion_planificacion_provincial bigint NOT NULL,
    id_periodo bigint,
    id_deprov bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_periodo_organizacion_planificacion_provincial OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 16589)
-- Name: came_periodo_planificacion_implementacion_apoyo; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_periodo_planificacion_implementacion_apoyo (
    id_periodo_planificacion_implementacion_apoyo bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_periodo_planificacion_implementacion_apoyo OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 16595)
-- Name: came_red; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_red (
    id_red bigint NOT NULL,
    id_red_tipo bigint,
    id_periodo bigint,
    id_usuario bigint,
    id_deprov bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_red OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 16603)
-- Name: came_red_establecimiento; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_red_establecimiento (
    id_red_establecimiento bigint NOT NULL,
    id_red bigint,
    id_establecimiento bigint,
    id_periodo bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_red_establecimiento OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 16611)
-- Name: came_red_sostenedor; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_red_sostenedor (
    id_red_sostenedor bigint NOT NULL,
    id_red bigint,
    id_sostenedor bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_red_sostenedor OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 16618)
-- Name: came_red_tipo; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_red_tipo (
    id_red_tipo bigint NOT NULL,
    nombre character varying
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_red_tipo OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 16626)
-- Name: came_sesion; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_sesion (
    id_sesion bigint NOT NULL,
    id_asesoria bigint,
    id_session_estado bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_sesion OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 16633)
-- Name: came_sesion_estado; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_sesion_estado (
    id_sesion_estado bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_sesion_estado OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 16638)
-- Name: came_sesion_foco; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_sesion_foco (
    id_sesion_foco bigint NOT NULL,
    id_sesion bigint,
    id_foco bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_sesion_foco OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 16645)
-- Name: came_sesion_movimientos_claves; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_sesion_movimientos_claves (
    id_sesion_movimientos_claves bigint NOT NULL,
    id_sesion_foco bigint,
    id_foco bigint,
    id_movimientos_claves bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_sesion_movimientos_claves OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 16653)
-- Name: came_sostenedor; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_sostenedor (
    id_sostenedor bigint NOT NULL
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_sostenedor OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 16658)
-- Name: came_sub_modulo; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_sub_modulo (
    id_sub_modulo bigint NOT NULL,
    id_estado bigint,
    id_modulo bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_sub_modulo OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 16668)
-- Name: came_supervisor; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_supervisor (
    id_supervisor bigint NOT NULL,
    id_usuario bigint,
    id_deprov bigint,
    start_date date,
    end_date date,
    status boolean
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_supervisor OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 16674)
-- Name: came_supervisor_establecimiento; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_supervisor_establecimiento (
    id_supervisor_establecimiento bigint NOT NULL,
    id_asignacion_tipo bigint,
    id_supervisor bigint,
    id_red bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_supervisor_establecimiento OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 16683)
-- Name: came_usuario; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_usuario (
    id_usuario bigint NOT NULL,
    id_estado bigint,
    id_region bigint,
    id_deprov bigint,
    usuario_dominio character varying NOT NULL,
    run character varying(12),
    nombre character varying,
    apellido_paterno character varying,
    apellido_materno character varying,
    email character varying,
    habilitado boolean,
    reintentos integer,
    ultima_modificacion timestamp without time zone,
    id_usuario_modificacion bigint,
    dv character varying(1),
    fecha_registro timestamp without time zone,
    id_usuario_registro bigint
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_usuario OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 16692)
-- Name: came_usuario_perfil; Type: TABLE; Schema: came; Owner: postgres
--

CREATE TABLE came.came_usuario_perfil (
    id_usuario_perfil bigint NOT NULL,
    id_usuario bigint,
    id_perfil bigint,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    status boolean
)
WITH (autovacuum_enabled='true');


ALTER TABLE came.came_usuario_perfil OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16396)
-- Name: secuencia_id; Type: SEQUENCE; Schema: came; Owner: postgres
--

CREATE SEQUENCE came.secuencia_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE came.secuencia_id OWNER TO postgres;

--
-- TOC entry 3377 (class 0 OID 16398)
-- Dependencies: 203
-- Data for Name: came_acciones_mejoras; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_acciones_mejoras (id_acciones_mejoras, id_foco) FROM stdin;
\.


--
-- TOC entry 3378 (class 0 OID 16404)
-- Dependencies: 204
-- Data for Name: came_asesoria; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_asesoria (id_asesoria, id_supervisor_establecimiento) FROM stdin;
\.


--
-- TOC entry 3379 (class 0 OID 16410)
-- Dependencies: 205
-- Data for Name: came_asignacion_tipo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_asignacion_tipo (id_asignacion_tipo) FROM stdin;
\.


--
-- TOC entry 3380 (class 0 OID 16415)
-- Dependencies: 206
-- Data for Name: came_came_estandares_indicativos_desempenio; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_came_estandares_indicativos_desempenio (id_estandares_indicativos_desempenio, id_foco) FROM stdin;
\.


--
-- TOC entry 3381 (class 0 OID 16421)
-- Dependencies: 207
-- Data for Name: came_categorizacion; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_categorizacion (id_categorizacion) FROM stdin;
\.


--
-- TOC entry 3382 (class 0 OID 16426)
-- Dependencies: 208
-- Data for Name: came_clasificacion_sep; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_clasificacion_sep (id_clasificacion_sep) FROM stdin;
\.


--
-- TOC entry 3383 (class 0 OID 16431)
-- Dependencies: 209
-- Data for Name: came_director; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_director (id_director) FROM stdin;
\.


--
-- TOC entry 3384 (class 0 OID 16436)
-- Dependencies: 210
-- Data for Name: came_elemento_lista; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_elemento_lista (id_elemento_lista, id_lista, nombre, status) FROM stdin;
2435191082942727169	2420130603857871959	Estudio	t
2435236300836570115	2420130603857871959	dededede	t
2435843024157148182	2420130603857871959	ewew wewe	t
\.


--
-- TOC entry 3386 (class 0 OID 16447)
-- Dependencies: 212
-- Data for Name: came_establecimiento; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_establecimiento (id_establecimiento, id_categorizacion1, id_establecimiento_estado, id_director, id_categorizacion2, id_clasificacion_sep, id_sostenedor, id_deprov, id_comuna) FROM stdin;
\.


--
-- TOC entry 3387 (class 0 OID 16458)
-- Dependencies: 213
-- Data for Name: came_establecimiento_categorizacion_historica; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_establecimiento_categorizacion_historica (id_establecimiento_categorizacion_historica, id_categorizacion, id_establecimiento) FROM stdin;
\.


--
-- TOC entry 3388 (class 0 OID 16466)
-- Dependencies: 214
-- Data for Name: came_establecimiento_estado; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_establecimiento_estado (id_establecimiento_estado) FROM stdin;
\.


--
-- TOC entry 3389 (class 0 OID 16471)
-- Dependencies: 215
-- Data for Name: came_estado; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_estado (id_estado, nombre) FROM stdin;
2416190883893347335	habilitado
2416190883893347336	inhabilitado
\.


--
-- TOC entry 3390 (class 0 OID 16479)
-- Dependencies: 216
-- Data for Name: came_foco; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_foco (id_foco) FROM stdin;
\.


--
-- TOC entry 3391 (class 0 OID 16484)
-- Dependencies: 217
-- Data for Name: came_lista; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_lista (id_lista, nombre) FROM stdin;
2420130603857871959	categorias
2420130603924980824	porcentaje
2420130603941758041	tipos de red
2420130603950146650	momentos asesoria
\.


--
-- TOC entry 3392 (class 0 OID 16492)
-- Dependencies: 218
-- Data for Name: came_matricula; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_matricula (id_matricula, id_matricula_etnia, id_nivel, id_establecimiento) FROM stdin;
\.


--
-- TOC entry 3393 (class 0 OID 16500)
-- Dependencies: 219
-- Data for Name: came_matricula_etnia; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_matricula_etnia (id_matricula_etnia) FROM stdin;
\.


--
-- TOC entry 3394 (class 0 OID 16505)
-- Dependencies: 220
-- Data for Name: came_modulo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_modulo (id_modulo, id_estado, nombre, status) FROM stdin;
2416198290916770825	2416190883893347335	Administracion	t
2416198290916770831	2416190883893347335	Mensajería	t
2416198290916770832	2416190883893347335	Encuesta	t
2416198290916770827	2416190883893347335	Retroalimentación	t
2416198290916770828	2416190883893347335	Directo	t
2416198290916770829	2416190883893347335	Documentos	t
2416198290916770830	2416190883893347335	Reportes	t
2416198290916770826	2416190883893347335	Establecimientos	t
\.


--
-- TOC entry 3395 (class 0 OID 16514)
-- Dependencies: 221
-- Data for Name: came_movimientos_claves; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_movimientos_claves (id_movimientos_claves, id_acciones_mejoras) FROM stdin;
\.


--
-- TOC entry 3396 (class 0 OID 16520)
-- Dependencies: 222
-- Data for Name: came_nivel; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_nivel (id_nivel) FROM stdin;
\.


--
-- TOC entry 3397 (class 0 OID 16525)
-- Dependencies: 223
-- Data for Name: came_objetivo_mejora; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_objetivo_mejora (id_objetivo_mejora, id_foco, id_sesion_foco) FROM stdin;
\.


--
-- TOC entry 3398 (class 0 OID 16532)
-- Dependencies: 224
-- Data for Name: came_perfil; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_perfil (id_perfil, id_estado, id_perfil_nivel, nombre, descripcion, habilitado, ultima_modificacion, id_usuario_modificacion) FROM stdin;
2416264829774857252	2416190883893347335	2416184421980832772	Profesional Mineduc	Profesional Mineduc	t	2020-11-04 10:51:19.372532	2414154165161821185
2416264829774857253	2416190883893347335	2416184421980832772	Encargados Regionales	Encargados Regionales	t	2020-11-04 10:51:19.372532	2414154165161821185
2416264829774857254	2416190883893347335	2416184421980832773	Coordinador Regional	Coordinador Regional	t	2020-11-04 10:51:19.372532	2414154165161821185
2416264829774857255	2416190883893347335	2416184421980832773	Jefe de Educación	Jefe de Educación	t	2020-11-04 10:51:19.372532	2414154165161821185
2416264829774857256	2416190883893347335	2416184421980832774	Jefe Provincial	Jefe Provincial	t	2020-11-04 10:51:19.372532	2414154165161821185
2416264829774857257	2416190883893347335	2416184421980832774	Jefe Técnico	Jefe Técnico	t	2020-11-04 10:51:19.372532	2414154165161821185
2416264829774857258	2416190883893347335	2416184421980832774	Supervisor	qweqweqweqweqweeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeqwdqwdsdsdedwefewfwedwedwedwedweefwefwefwefwefwefwefw	t	2020-11-04 18:07:16.329	2414154165161821185
2416264829766468643	2416190883893347335	2416184421980832772	Administrador	Administrador	t	2020-11-06 18:55:12.008	2414154165161821185
\.


--
-- TOC entry 3399 (class 0 OID 16542)
-- Dependencies: 225
-- Data for Name: came_perfil_menu; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_perfil_menu (id_perfil_menu, id_perfil, id_sub_modulo, id_perfil_menu_acceso, start_date, end_date, status) FROM stdin;
2435246579632833540	2416264829774857258	2416254586118472721	2415742140157002753	2020-11-04 18:07:16.329	\N	t
2435246579834160133	2416264829774857258	2416254586118472722	2415742140157002753	2020-11-04 18:07:16.376	\N	t
2435246579943212038	2416264829774857258	2416254586126861331	2415742140157002753	2020-11-04 18:07:16.392	\N	t
2435246580027098119	2416264829774857258	2416254586126861332	2415742140157002753	2020-11-04 18:07:16.392	\N	t
2435246580127761416	2416264829774857258	2416254586126861333	2415742140157002753	2020-11-04 18:07:16.408	\N	t
2435246580756907017	2416264829774857258	2416254586126861334	2415742140157002753	2020-11-04 18:07:16.424	\N	t
2435246580882736138	2416264829774857258	2416254586126861335	2415742140157002753	2020-11-04 18:07:16.501	\N	t
2435246581058896907	2416264829774857258	2416254586126861336	2415742140157002753	2020-11-04 18:07:16.517	\N	t
2435246581142782988	2416264829774857258	2416254586126861337	2415742140157002753	2020-11-04 18:07:16.533	\N	t
2435246581184726029	2416264829774857258	2416254586126861338	2415742140157002753	2020-11-04 18:07:16.533	\N	t
2435246581226669070	2416264829774857258	2416254586126861339	2415742140157002753	2020-11-04 18:07:16.533	\N	t
2435246581377664015	2416264829774857258	2416254586126861340	2415742140157002753	2020-11-04 18:07:16.564	\N	t
2435246581478327312	2416264829774857258	2416254586126861341	2415742140157002753	2020-11-04 18:07:16.564	\N	t
2435246581528658961	2416264829774857258	2416254586126861342	2415742140157002753	2020-11-04 18:07:16.579	\N	t
2435246581570602002	2416264829774857258	2416254586126861343	2415742140157002753	2020-11-04 18:07:16.579	\N	t
2435246581604156435	2416264829774857258	2416254586126861344	2415742140157002753	2020-11-04 18:07:16.579	\N	t
2435246581780317204	2416264829774857258	2416254586126861345	2415742140157002753	2020-11-04 18:07:16.611	\N	t
2435246581822260245	2416264829774857258	2416254586126861346	2415742140157002753	2020-11-04 18:07:16.611	\N	t
2436518503881114647	2416264829766468643	2416254586118472721	2415742140157002755	2020-11-06 12:14:21.513	\N	t
2436518504090829850	2416264829766468643	2416254586126861347	2415742140157002755	2020-11-06 12:14:21.568	\N	t
2436518504166327323	2416264829766468643	2416254586126861348	2415742140157002755	2020-11-06 12:14:21.574	\N	t
2436518504208270364	2416264829766468643	2416254586126861349	2415742140157002755	2020-11-06 12:14:21.583	\N	t
2436518504283767837	2416264829766468643	2416254586126861350	2415742140157002755	2020-11-06 12:14:21.592	\N	t
2436518504426374176	2416264829766468643	2416254586126861334	2415742140157002755	2020-11-06 12:14:21.61	\N	t
2436518504493483041	2416264829766468643	2416254586126861335	2415742140157002755	2020-11-06 12:14:21.613	\N	t
2436518504527037474	2416264829766468643	2416254586126861336	2415742140157002755	2020-11-06 12:14:21.621	\N	t
2436518504560591907	2416264829766468643	2416254586126861337	2415742140157002755	2020-11-06 12:14:21.626	\N	t
2436518504627700772	2416264829766468643	2416254586126861338	2415742140157002755	2020-11-06 12:14:21.634	\N	t
2436518504661255205	2416264829766468643	2416254586126861339	2415742140157002755	2020-11-06 12:14:21.637	\N	t
2436518504745141286	2416264829766468643	2416254586126861340	2415742140157002755	2020-11-06 12:14:21.646	\N	t
2436518504787084327	2416264829766468643	2416254586126861341	2415742140157002755	2020-11-06 12:14:21.652	\N	t
2436518504845804584	2416264829766468643	2416254586126861342	2415742140157002755	2020-11-06 12:14:21.659	\N	t
2436518504896136233	2416264829766468643	2416254586126861343	2415742140157002755	2020-11-06 12:14:21.665	\N	t
2436518504980022314	2416264829766468643	2416254586126861344	2415742140157002755	2020-11-06 12:14:21.675	\N	t
2436518505013576747	2416264829766468643	2416254586126861345	2415742140157002755	2020-11-06 12:14:21.68	\N	t
2436518505089074220	2416264829766468643	2416254586126861346	2415742140157002755	2020-11-06 12:14:21.688	\N	t
2436668501159576625	2416264829766468643	2416254586126861332	2415742140157002753	2020-11-06 17:12:22.612	\N	t
2436668501235074098	2416264829766468643	2416254586126861333	2415742140157002753	2020-11-06 17:12:22.621	\N	t
2436518503973389336	2416264829766468643	2416254586118472722	2415742140157002755	2020-11-06 12:14:21.555	2020-11-06 18:55:12.01	t
2436518504048886809	2416264829766468643	2416254586126861331	2415742140157002755	2020-11-06 12:14:21.56	2020-11-06 18:55:12.015	t
2436518504317322270	2416264829766468643	2416254586126861332	2415742140157002755	2020-11-06 12:14:21.597	2020-11-06 18:55:12.017	t
2436518504392819743	2416264829766468643	2416254586126861333	2415742140157002755	2020-11-06 12:14:21.6	2020-11-06 18:55:12.019	t
2436666072624006189	2416264829766468643	2416254586126861332	2415742140157002753	2020-11-06 17:07:33.081	2020-11-06 18:55:12.021	t
2436666072766612526	2416264829766468643	2416254586126861333	2415742140157002753	2020-11-06 17:07:33.113	2020-11-06 18:55:12.024	t
2436668331156046895	2416264829766468643	2416254586126861332	2415742140157002755	2020-11-06 17:12:02.345	2020-11-06 18:55:12.026	t
2436668331214767152	2416264829766468643	2416254586126861333	2415742140157002755	2020-11-06 17:12:02.353	2020-11-06 18:55:12.028	t
2436719980427871283	2416264829766468643	2416254586118472722	2415742140157002753	2020-11-06 18:54:39.418	2020-11-06 18:55:12.031	t
2436719980469814324	2416264829766468643	2416254586126861331	2415742140157002753	2020-11-06 18:54:39.425	2020-11-06 18:55:12.034	t
2436720254030709813	2416264829766468643	2416254586118472722	2415742140157002755	2020-11-06 18:55:12.036	\N	t
2436720254064264246	2416264829766468643	2416254586126861331	2415742140157002755	2020-11-06 18:55:12.041	\N	t
\.


--
-- TOC entry 3400 (class 0 OID 16550)
-- Dependencies: 226
-- Data for Name: came_perfil_menu_acceso; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_perfil_menu_acceso (id_perfil_menu_acceso, nombre) FROM stdin;
2415742140157002753	sin acceso
2415742140157002754	ver
2415742140157002755	ver y editar
\.


--
-- TOC entry 3401 (class 0 OID 16558)
-- Dependencies: 227
-- Data for Name: came_perfil_nivel; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_perfil_nivel (id_perfil_nivel, nombre) FROM stdin;
2416184421980832772	nacional
2416184421980832773	regional
2416184421980832774	provincial
\.


--
-- TOC entry 3402 (class 0 OID 16566)
-- Dependencies: 228
-- Data for Name: came_periodo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo (id_periodo, anio, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3422 (class 0 OID 17024)
-- Dependencies: 248
-- Data for Name: came_periodo_asignacion_supervisores; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_asignacion_supervisores (id_periodo_asignacion_supervisores, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3421 (class 0 OID 17018)
-- Dependencies: 247
-- Data for Name: came_periodo_conformacion_redes; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_conformacion_redes (id_periodo_conformacion_redes, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3420 (class 0 OID 17006)
-- Dependencies: 246
-- Data for Name: came_periodo_documentos_provinciales; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_documentos_provinciales (periodo_documentos_provinciales, id_periodo, id_region, id_deprov, visible, es_caso_especial, template_path, fecha_registro, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro1) FROM stdin;
\.


--
-- TOC entry 3403 (class 0 OID 16577)
-- Dependencies: 229
-- Data for Name: came_periodo_documentos_regionales; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_documentos_regionales (id_periodo_documentos_regionales, id_periodo, id_region, fecha_inicio, fecha_fin, visible, es_caso_especial, template_path, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3404 (class 0 OID 16583)
-- Dependencies: 230
-- Data for Name: came_periodo_organizacion_planificacion_provincial; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_organizacion_planificacion_provincial (id_periodo_organizacion_planificacion_provincial, id_periodo, id_deprov) FROM stdin;
\.


--
-- TOC entry 3405 (class 0 OID 16589)
-- Dependencies: 231
-- Data for Name: came_periodo_planificacion_implementacion_apoyo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_planificacion_implementacion_apoyo (id_periodo_planificacion_implementacion_apoyo, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3406 (class 0 OID 16595)
-- Dependencies: 232
-- Data for Name: came_red; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red (id_red, id_red_tipo, id_periodo, id_usuario, id_deprov) FROM stdin;
\.


--
-- TOC entry 3407 (class 0 OID 16603)
-- Dependencies: 233
-- Data for Name: came_red_establecimiento; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red_establecimiento (id_red_establecimiento, id_red, id_establecimiento, id_periodo) FROM stdin;
\.


--
-- TOC entry 3408 (class 0 OID 16611)
-- Dependencies: 234
-- Data for Name: came_red_sostenedor; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red_sostenedor (id_red_sostenedor, id_red, id_sostenedor) FROM stdin;
\.


--
-- TOC entry 3409 (class 0 OID 16618)
-- Dependencies: 235
-- Data for Name: came_red_tipo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red_tipo (id_red_tipo, nombre) FROM stdin;
\.


--
-- TOC entry 3410 (class 0 OID 16626)
-- Dependencies: 236
-- Data for Name: came_sesion; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion (id_sesion, id_asesoria, id_session_estado) FROM stdin;
\.


--
-- TOC entry 3411 (class 0 OID 16633)
-- Dependencies: 237
-- Data for Name: came_sesion_estado; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion_estado (id_sesion_estado) FROM stdin;
\.


--
-- TOC entry 3412 (class 0 OID 16638)
-- Dependencies: 238
-- Data for Name: came_sesion_foco; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion_foco (id_sesion_foco, id_sesion, id_foco) FROM stdin;
\.


--
-- TOC entry 3413 (class 0 OID 16645)
-- Dependencies: 239
-- Data for Name: came_sesion_movimientos_claves; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion_movimientos_claves (id_sesion_movimientos_claves, id_sesion_foco, id_foco, id_movimientos_claves) FROM stdin;
\.


--
-- TOC entry 3414 (class 0 OID 16653)
-- Dependencies: 240
-- Data for Name: came_sostenedor; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sostenedor (id_sostenedor) FROM stdin;
\.


--
-- TOC entry 3415 (class 0 OID 16658)
-- Dependencies: 241
-- Data for Name: came_sub_modulo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sub_modulo (id_sub_modulo, id_estado, id_modulo, nombre, status) FROM stdin;
2416254586118472721	2416190883893347335	2416198290916770825	Perfiles	t
2416254586118472722	2416190883893347335	2416198290916770825	Usuarios	t
2416254586126861331	2416190883893347335	2416198290916770825	Periodo	t
2416254586126861332	2416190883893347335	2416198290916770826	Ingreso plan	t
2416254586126861333	2416190883893347335	2416198290916770826	Validar plan	t
2416254586126861334	2416190883893347335	2416198290916770827	Administración de redes	t
2416254586126861335	2416190883893347335	2416198290916770827	Configuración de Red	t
2416254586126861336	2416190883893347335	2416198290916770827	Administración de Supervisores	t
2416254586126861337	2416190883893347335	2416198290916770827	Supervisores	t
2416254586126861338	2416190883893347335	2416198290916770828	Administración de Sesiones	t
2416254586126861339	2416190883893347335	2416198290916770828	Registro de Sesión	t
2416254586126861340	2416190883893347335	2416198290916770829	Reportes	t
2416254586126861341	2416190883893347335	2416198290916770830	Visualización de Documentos	t
2416254586126861342	2416190883893347335	2416198290916770830	Carga de Documentos	t
2416254586126861343	2416190883893347335	2416198290916770831	Listar	t
2416254586126861344	2416190883893347335	2416198290916770831	Crear	t
2416254586126861345	2416190883893347335	2416198290916770832	Listar	t
2416254586126861346	2416190883893347335	2416198290916770832	Crear	t
2416254586126861347	2416190883893347335	2416198290916770825	Listas desplegables	t
2416254586126861348	2416190883893347335	2416198290916770825	Asignación máxima	t
2416254586126861349	2416190883893347335	2416198290916770825	Suplencia	t
2416254586126861350	2416190883893347335	2416198290916770825	Focos	t
\.


--
-- TOC entry 3416 (class 0 OID 16668)
-- Dependencies: 242
-- Data for Name: came_supervisor; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_supervisor (id_supervisor, id_usuario, id_deprov, start_date, end_date, status) FROM stdin;
\.


--
-- TOC entry 3417 (class 0 OID 16674)
-- Dependencies: 243
-- Data for Name: came_supervisor_establecimiento; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_supervisor_establecimiento (id_supervisor_establecimiento, id_asignacion_tipo, id_supervisor, id_red, id_establecimiento) FROM stdin;
\.


--
-- TOC entry 3418 (class 0 OID 16683)
-- Dependencies: 244
-- Data for Name: came_usuario; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_usuario (id_usuario, id_estado, id_region, id_deprov, usuario_dominio, run, nombre, apellido_paterno, apellido_materno, email, habilitado, reintentos, ultima_modificacion, id_usuario_modificacion, dv, fecha_registro, id_usuario_registro) FROM stdin;
2414154165161821185	2416190883893347335	1	11	loreto.arias	15898260	Loreto Andrea	Arias	Fuentes	loreto.arias@mineduc.cl	t	0	2020-09-28 00:00:00	2414154453067236357	9	2020-09-28 00:00:00	2414154516208288774
\.


--
-- TOC entry 3419 (class 0 OID 16692)
-- Dependencies: 245
-- Data for Name: came_usuario_perfil; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_usuario_perfil (id_usuario_perfil, id_usuario, id_perfil, start_date, end_date, status) FROM stdin;
2414158840820925447	2414154165161821185	2416264829766468643	2020-11-04 10:51:19.372532	\N	t
\.


--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 211
-- Name: came_establecimiento_id_categorizacion2_seq; Type: SEQUENCE SET; Schema: came; Owner: postgres
--

SELECT pg_catalog.setval('came.came_establecimiento_id_categorizacion2_seq', 1, false);


--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 202
-- Name: secuencia_id; Type: SEQUENCE SET; Schema: came; Owner: postgres
--

SELECT pg_catalog.setval('came.secuencia_id', 54, true);


--
-- TOC entry 3045 (class 2606 OID 16403)
-- Name: came_acciones_mejoras PK_came_acciones_mejoras; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_acciones_mejoras
    ADD CONSTRAINT "PK_came_acciones_mejoras" PRIMARY KEY (id_acciones_mejoras);


--
-- TOC entry 3048 (class 2606 OID 16409)
-- Name: came_asesoria PK_came_asesoria; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_asesoria
    ADD CONSTRAINT "PK_came_asesoria" PRIMARY KEY (id_asesoria);


--
-- TOC entry 3050 (class 2606 OID 16414)
-- Name: came_asignacion_tipo PK_came_asignacion_tipo; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_asignacion_tipo
    ADD CONSTRAINT "PK_came_asignacion_tipo" PRIMARY KEY (id_asignacion_tipo);


--
-- TOC entry 3053 (class 2606 OID 16420)
-- Name: came_came_estandares_indicativos_desempenio PK_came_came_estandares_indicativos_desempenio; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio
    ADD CONSTRAINT "PK_came_came_estandares_indicativos_desempenio" PRIMARY KEY (id_estandares_indicativos_desempenio);


--
-- TOC entry 3055 (class 2606 OID 16425)
-- Name: came_categorizacion PK_came_categorizacion; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_categorizacion
    ADD CONSTRAINT "PK_came_categorizacion" PRIMARY KEY (id_categorizacion);


--
-- TOC entry 3057 (class 2606 OID 16430)
-- Name: came_clasificacion_sep PK_came_clasificacion_sep; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_clasificacion_sep
    ADD CONSTRAINT "PK_came_clasificacion_sep" PRIMARY KEY (id_clasificacion_sep);


--
-- TOC entry 3059 (class 2606 OID 16435)
-- Name: came_director PK_came_director; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_director
    ADD CONSTRAINT "PK_came_director" PRIMARY KEY (id_director);


--
-- TOC entry 3062 (class 2606 OID 16444)
-- Name: came_elemento_lista PK_came_elemento_lista; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_elemento_lista
    ADD CONSTRAINT "PK_came_elemento_lista" PRIMARY KEY (id_elemento_lista);


--
-- TOC entry 3070 (class 2606 OID 16457)
-- Name: came_establecimiento PK_came_establecimiento; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "PK_came_establecimiento" PRIMARY KEY (id_establecimiento);


--
-- TOC entry 3075 (class 2606 OID 16465)
-- Name: came_establecimiento_categorizacion_historica PK_came_establecimiento_categorizacion_historica; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "PK_came_establecimiento_categorizacion_historica" PRIMARY KEY (id_establecimiento_categorizacion_historica);


--
-- TOC entry 3077 (class 2606 OID 16470)
-- Name: came_establecimiento_estado PK_came_establecimiento_estado; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento_estado
    ADD CONSTRAINT "PK_came_establecimiento_estado" PRIMARY KEY (id_establecimiento_estado);


--
-- TOC entry 3079 (class 2606 OID 16478)
-- Name: came_estado PK_came_estado; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_estado
    ADD CONSTRAINT "PK_came_estado" PRIMARY KEY (id_estado);


--
-- TOC entry 3081 (class 2606 OID 16483)
-- Name: came_foco PK_came_foco; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_foco
    ADD CONSTRAINT "PK_came_foco" PRIMARY KEY (id_foco);


--
-- TOC entry 3083 (class 2606 OID 16491)
-- Name: came_lista PK_came_lista; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_lista
    ADD CONSTRAINT "PK_came_lista" PRIMARY KEY (id_lista);


--
-- TOC entry 3088 (class 2606 OID 16499)
-- Name: came_matricula PK_came_matricula; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "PK_came_matricula" PRIMARY KEY (id_matricula);


--
-- TOC entry 3090 (class 2606 OID 16504)
-- Name: came_matricula_etnia PK_came_matricula_etnia; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_matricula_etnia
    ADD CONSTRAINT "PK_came_matricula_etnia" PRIMARY KEY (id_matricula_etnia);


--
-- TOC entry 3093 (class 2606 OID 16513)
-- Name: came_modulo PK_came_modulo; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_modulo
    ADD CONSTRAINT "PK_came_modulo" PRIMARY KEY (id_modulo);


--
-- TOC entry 3096 (class 2606 OID 16519)
-- Name: came_movimientos_claves PK_came_movimientos_claves; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_movimientos_claves
    ADD CONSTRAINT "PK_came_movimientos_claves" PRIMARY KEY (id_movimientos_claves);


--
-- TOC entry 3098 (class 2606 OID 16524)
-- Name: came_nivel PK_came_nivel; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_nivel
    ADD CONSTRAINT "PK_came_nivel" PRIMARY KEY (id_nivel);


--
-- TOC entry 3102 (class 2606 OID 16531)
-- Name: came_objetivo_mejora PK_came_objetivo_mejora; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "PK_came_objetivo_mejora" PRIMARY KEY (id_objetivo_mejora);


--
-- TOC entry 3106 (class 2606 OID 16541)
-- Name: came_perfil PK_came_perfil; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "PK_came_perfil" PRIMARY KEY (id_perfil);


--
-- TOC entry 3111 (class 2606 OID 16549)
-- Name: came_perfil_menu PK_came_perfil_menu; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "PK_came_perfil_menu" PRIMARY KEY (id_perfil_menu);


--
-- TOC entry 3113 (class 2606 OID 16557)
-- Name: came_perfil_menu_acceso PK_came_perfil_menu_acceso; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil_menu_acceso
    ADD CONSTRAINT "PK_came_perfil_menu_acceso" PRIMARY KEY (id_perfil_menu_acceso);


--
-- TOC entry 3115 (class 2606 OID 16565)
-- Name: came_perfil_nivel PK_came_perfil_nivel; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil_nivel
    ADD CONSTRAINT "PK_came_perfil_nivel" PRIMARY KEY (id_perfil_nivel);


--
-- TOC entry 3117 (class 2606 OID 16570)
-- Name: came_periodo PK_came_periodo; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo
    ADD CONSTRAINT "PK_came_periodo" PRIMARY KEY (id_periodo);


--
-- TOC entry 3188 (class 2606 OID 17029)
-- Name: came_periodo_asignacion_supervisores PK_came_periodo_asignacion_supervisores; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_asignacion_supervisores
    ADD CONSTRAINT "PK_came_periodo_asignacion_supervisores" PRIMARY KEY (id_periodo_asignacion_supervisores);


--
-- TOC entry 3182 (class 2606 OID 17014)
-- Name: came_periodo_documentos_provinciales PK_came_periodo_documentos_provinciales; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_documentos_provinciales
    ADD CONSTRAINT "PK_came_periodo_documentos_provinciales" PRIMARY KEY (periodo_documentos_provinciales);


--
-- TOC entry 3120 (class 2606 OID 16582)
-- Name: came_periodo_documentos_regionales PK_came_periodo_documentos_regionales; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_documentos_regionales
    ADD CONSTRAINT "PK_came_periodo_documentos_regionales" PRIMARY KEY (id_periodo_documentos_regionales);


--
-- TOC entry 3123 (class 2606 OID 16588)
-- Name: came_periodo_organizacion_planificacion_provincial PK_came_periodo_organizacion_planificacion_provincial; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_organizacion_planificacion_provincial
    ADD CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial" PRIMARY KEY (id_periodo_organizacion_planificacion_provincial);


--
-- TOC entry 3185 (class 2606 OID 17023)
-- Name: came_periodo_conformacion_redes PK_came_periodo_organizacion_planificacion_provincial_test; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_conformacion_redes
    ADD CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial_test" PRIMARY KEY (id_periodo_conformacion_redes);


--
-- TOC entry 3126 (class 2606 OID 16594)
-- Name: came_periodo_planificacion_implementacion_apoyo PK_came_periodo_planificacion_implementacion_apoyo; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo
    ADD CONSTRAINT "PK_came_periodo_planificacion_implementacion_apoyo" PRIMARY KEY (id_periodo_planificacion_implementacion_apoyo);


--
-- TOC entry 3131 (class 2606 OID 16602)
-- Name: came_red PK_came_red; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "PK_came_red" PRIMARY KEY (id_red);


--
-- TOC entry 3136 (class 2606 OID 16610)
-- Name: came_red_establecimiento PK_came_red_establecimiento; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "PK_came_red_establecimiento" PRIMARY KEY (id_red_establecimiento);


--
-- TOC entry 3140 (class 2606 OID 16617)
-- Name: came_red_sostenedor PK_came_red_sostenedor; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "PK_came_red_sostenedor" PRIMARY KEY (id_red_sostenedor);


--
-- TOC entry 3142 (class 2606 OID 16625)
-- Name: came_red_tipo PK_came_red_tipo; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_tipo
    ADD CONSTRAINT "PK_came_red_tipo" PRIMARY KEY (id_red_tipo);


--
-- TOC entry 3146 (class 2606 OID 16632)
-- Name: came_sesion PK_came_sesion; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "PK_came_sesion" PRIMARY KEY (id_sesion);


--
-- TOC entry 3148 (class 2606 OID 16637)
-- Name: came_sesion_estado PK_came_sesion_estado; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_estado
    ADD CONSTRAINT "PK_came_sesion_estado" PRIMARY KEY (id_sesion_estado);


--
-- TOC entry 3152 (class 2606 OID 16644)
-- Name: came_sesion_foco PK_came_sesion_foco; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "PK_came_sesion_foco" PRIMARY KEY (id_sesion_foco);


--
-- TOC entry 3157 (class 2606 OID 16652)
-- Name: came_sesion_movimientos_claves PK_came_sesion_movimientos_claves; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "PK_came_sesion_movimientos_claves" PRIMARY KEY (id_sesion_movimientos_claves);


--
-- TOC entry 3159 (class 2606 OID 16657)
-- Name: came_sostenedor PK_came_sostenedor; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sostenedor
    ADD CONSTRAINT "PK_came_sostenedor" PRIMARY KEY (id_sostenedor);


--
-- TOC entry 3163 (class 2606 OID 16667)
-- Name: came_sub_modulo PK_came_sub_modulo; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "PK_came_sub_modulo" PRIMARY KEY (id_sub_modulo);


--
-- TOC entry 3166 (class 2606 OID 16673)
-- Name: came_supervisor PK_came_supervisor; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_supervisor
    ADD CONSTRAINT "PK_came_supervisor" PRIMARY KEY (id_supervisor);


--
-- TOC entry 3172 (class 2606 OID 16682)
-- Name: came_supervisor_establecimiento PK_came_supervisor_establecimiento; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "PK_came_supervisor_establecimiento" PRIMARY KEY (id_supervisor_establecimiento);


--
-- TOC entry 3175 (class 2606 OID 16691)
-- Name: came_usuario PK_came_usuario; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_usuario
    ADD CONSTRAINT "PK_came_usuario" PRIMARY KEY (id_usuario);


--
-- TOC entry 3179 (class 2606 OID 16698)
-- Name: came_usuario_perfil PK_came_usuario_perfil; Type: CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "PK_came_usuario_perfil" PRIMARY KEY (id_usuario_perfil);


--
-- TOC entry 3164 (class 1259 OID 16671)
-- Name: IX_Relationship100; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship100" ON came.came_supervisor USING btree (id_usuario);


--
-- TOC entry 3167 (class 1259 OID 16679)
-- Name: IX_Relationship102; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship102" ON came.came_supervisor_establecimiento USING btree (id_red);


--
-- TOC entry 3137 (class 1259 OID 16614)
-- Name: IX_Relationship105; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship105" ON came.came_red_sostenedor USING btree (id_red);


--
-- TOC entry 3127 (class 1259 OID 16598)
-- Name: IX_Relationship106; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship106" ON came.came_red USING btree (id_red_tipo);


--
-- TOC entry 3132 (class 1259 OID 16606)
-- Name: IX_Relationship107; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship107" ON came.came_red_establecimiento USING btree (id_red);


--
-- TOC entry 3107 (class 1259 OID 16545)
-- Name: IX_Relationship109; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship109" ON came.came_perfil_menu USING btree (id_perfil);


--
-- TOC entry 3138 (class 1259 OID 16615)
-- Name: IX_Relationship110; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship110" ON came.came_red_sostenedor USING btree (id_sostenedor);


--
-- TOC entry 3124 (class 1259 OID 16592)
-- Name: IX_Relationship113; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship113" ON came.came_periodo_planificacion_implementacion_apoyo USING btree (id_periodo);


--
-- TOC entry 3128 (class 1259 OID 16599)
-- Name: IX_Relationship114; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship114" ON came.came_red USING btree (id_periodo);


--
-- TOC entry 3133 (class 1259 OID 16607)
-- Name: IX_Relationship115; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship115" ON came.came_red_establecimiento USING btree (id_establecimiento);


--
-- TOC entry 3063 (class 1259 OID 16450)
-- Name: IX_Relationship116; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship116" ON came.came_establecimiento USING btree (id_establecimiento_estado);


--
-- TOC entry 3084 (class 1259 OID 16497)
-- Name: IX_Relationship117; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship117" ON came.came_matricula USING btree (id_establecimiento);


--
-- TOC entry 3064 (class 1259 OID 16451)
-- Name: IX_Relationship118; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship118" ON came.came_establecimiento USING btree (id_director);


--
-- TOC entry 3160 (class 1259 OID 16665)
-- Name: IX_Relationship122; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship122" ON came.came_sub_modulo USING btree (id_modulo);


--
-- TOC entry 3091 (class 1259 OID 16511)
-- Name: IX_Relationship123; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship123" ON came.came_modulo USING btree (id_estado);


--
-- TOC entry 3108 (class 1259 OID 16546)
-- Name: IX_Relationship124; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship124" ON came.came_perfil_menu USING btree (id_sub_modulo);


--
-- TOC entry 3060 (class 1259 OID 16442)
-- Name: IX_Relationship126; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship126" ON came.came_elemento_lista USING btree (id_lista);


--
-- TOC entry 3118 (class 1259 OID 16580)
-- Name: IX_Relationship127; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship127" ON came.came_periodo_documentos_regionales USING btree (id_periodo);


--
-- TOC entry 3071 (class 1259 OID 16462)
-- Name: IX_Relationship128; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship128" ON came.came_establecimiento_categorizacion_historica USING btree (id_establecimiento);


--
-- TOC entry 3065 (class 1259 OID 16452)
-- Name: IX_Relationship129; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship129" ON came.came_establecimiento USING btree (id_categorizacion2);


--
-- TOC entry 3066 (class 1259 OID 16453)
-- Name: IX_Relationship130; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship130" ON came.came_establecimiento USING btree (id_clasificacion_sep);


--
-- TOC entry 3067 (class 1259 OID 16454)
-- Name: IX_Relationship131; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship131" ON came.came_establecimiento USING btree (id_categorizacion2);


--
-- TOC entry 3121 (class 1259 OID 16586)
-- Name: IX_Relationship132; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship132" ON came.came_periodo_organizacion_planificacion_provincial USING btree (id_periodo);


--
-- TOC entry 3180 (class 1259 OID 17012)
-- Name: IX_Relationship133; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship133" ON came.came_periodo_documentos_provinciales USING btree (id_periodo);


--
-- TOC entry 3168 (class 1259 OID 16680)
-- Name: IX_Relationship134; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship134" ON came.came_supervisor_establecimiento USING btree (id_establecimiento);


--
-- TOC entry 3068 (class 1259 OID 16455)
-- Name: IX_Relationship135; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship135" ON came.came_establecimiento USING btree (id_sostenedor);


--
-- TOC entry 3129 (class 1259 OID 16600)
-- Name: IX_Relationship137; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship137" ON came.came_red USING btree (id_usuario);


--
-- TOC entry 3043 (class 1259 OID 16401)
-- Name: IX_Relationship138; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship138" ON came.came_acciones_mejoras USING btree (id_foco);


--
-- TOC entry 3051 (class 1259 OID 16418)
-- Name: IX_Relationship139; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship139" ON came.came_came_estandares_indicativos_desempenio USING btree (id_foco);


--
-- TOC entry 3094 (class 1259 OID 16517)
-- Name: IX_Relationship140; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship140" ON came.came_movimientos_claves USING btree (id_acciones_mejoras);


--
-- TOC entry 3149 (class 1259 OID 16641)
-- Name: IX_Relationship141; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship141" ON came.came_sesion_foco USING btree (id_sesion);


--
-- TOC entry 3150 (class 1259 OID 16642)
-- Name: IX_Relationship142; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship142" ON came.came_sesion_foco USING btree (id_foco);


--
-- TOC entry 3153 (class 1259 OID 16648)
-- Name: IX_Relationship143; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship143" ON came.came_sesion_movimientos_claves USING btree (id_sesion_foco);


--
-- TOC entry 3154 (class 1259 OID 16649)
-- Name: IX_Relationship144; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship144" ON came.came_sesion_movimientos_claves USING btree (id_foco);


--
-- TOC entry 3155 (class 1259 OID 16650)
-- Name: IX_Relationship145; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship145" ON came.came_sesion_movimientos_claves USING btree (id_movimientos_claves);


--
-- TOC entry 3099 (class 1259 OID 16528)
-- Name: IX_Relationship146; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship146" ON came.came_objetivo_mejora USING btree (id_foco);


--
-- TOC entry 3100 (class 1259 OID 16529)
-- Name: IX_Relationship147; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship147" ON came.came_objetivo_mejora USING btree (id_sesion_foco);


--
-- TOC entry 3176 (class 1259 OID 16695)
-- Name: IX_Relationship148; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship148" ON came.came_usuario_perfil USING btree (id_usuario);


--
-- TOC entry 3177 (class 1259 OID 16696)
-- Name: IX_Relationship149; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship149" ON came.came_usuario_perfil USING btree (id_perfil);


--
-- TOC entry 3072 (class 1259 OID 16463)
-- Name: IX_Relationship151; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship151" ON came.came_establecimiento_categorizacion_historica USING btree (id_establecimiento);


--
-- TOC entry 3109 (class 1259 OID 16547)
-- Name: IX_Relationship152; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship152" ON came.came_perfil_menu USING btree (id_perfil_menu_acceso);


--
-- TOC entry 3134 (class 1259 OID 16608)
-- Name: IX_Relationship154; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship154" ON came.came_red_establecimiento USING btree (id_periodo);


--
-- TOC entry 3186 (class 1259 OID 17027)
-- Name: IX_Relationship155; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship155" ON came.came_periodo_asignacion_supervisores USING btree (id_periodo);


--
-- TOC entry 3183 (class 1259 OID 17021)
-- Name: IX_Relationship232; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship232" ON came.came_periodo_conformacion_redes USING btree (id_periodo);


--
-- TOC entry 3103 (class 1259 OID 16539)
-- Name: IX_Relationship67; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship67" ON came.came_perfil USING btree (id_perfil_nivel);


--
-- TOC entry 3169 (class 1259 OID 16677)
-- Name: IX_Relationship81; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship81" ON came.came_supervisor_establecimiento USING btree (id_asignacion_tipo);


--
-- TOC entry 3046 (class 1259 OID 16407)
-- Name: IX_Relationship82; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship82" ON came.came_asesoria USING btree (id_supervisor_establecimiento);


--
-- TOC entry 3143 (class 1259 OID 16629)
-- Name: IX_Relationship83; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship83" ON came.came_sesion USING btree (id_asesoria);


--
-- TOC entry 3170 (class 1259 OID 16678)
-- Name: IX_Relationship84; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship84" ON came.came_supervisor_establecimiento USING btree (id_supervisor);


--
-- TOC entry 3144 (class 1259 OID 16630)
-- Name: IX_Relationship85; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship85" ON came.came_sesion USING btree (id_session_estado);


--
-- TOC entry 3085 (class 1259 OID 16495)
-- Name: IX_Relationship92; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship92" ON came.came_matricula USING btree (id_matricula_etnia);


--
-- TOC entry 3086 (class 1259 OID 16496)
-- Name: IX_Relationship93; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship93" ON came.came_matricula USING btree (id_nivel);


--
-- TOC entry 3073 (class 1259 OID 16461)
-- Name: IX_Relationship95; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship95" ON came.came_establecimiento_categorizacion_historica USING btree (id_categorizacion);


--
-- TOC entry 3161 (class 1259 OID 16664)
-- Name: IX_Relationship97; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship97" ON came.came_sub_modulo USING btree (id_estado);


--
-- TOC entry 3104 (class 1259 OID 16538)
-- Name: IX_Relationship98; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship98" ON came.came_perfil USING btree (id_estado);


--
-- TOC entry 3173 (class 1259 OID 16689)
-- Name: IX_Relationship99; Type: INDEX; Schema: came; Owner: postgres
--

CREATE INDEX "IX_Relationship99" ON came.came_usuario USING btree (id_estado);


--
-- TOC entry 3235 (class 2606 OID 16934)
-- Name: came_supervisor Relationship100; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_supervisor
    ADD CONSTRAINT "Relationship100" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);


--
-- TOC entry 3238 (class 2606 OID 16949)
-- Name: came_supervisor_establecimiento Relationship102; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship102" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);


--
-- TOC entry 3224 (class 2606 OID 16879)
-- Name: came_red_sostenedor Relationship105; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "Relationship105" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);


--
-- TOC entry 3219 (class 2606 OID 16854)
-- Name: came_red Relationship106; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship106" FOREIGN KEY (id_red_tipo) REFERENCES came.came_red_tipo(id_red_tipo);


--
-- TOC entry 3223 (class 2606 OID 16874)
-- Name: came_red_establecimiento Relationship107; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship107" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);


--
-- TOC entry 3212 (class 2606 OID 16814)
-- Name: came_perfil_menu Relationship109; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship109" FOREIGN KEY (id_perfil) REFERENCES came.came_perfil(id_perfil);


--
-- TOC entry 3225 (class 2606 OID 16884)
-- Name: came_red_sostenedor Relationship110; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "Relationship110" FOREIGN KEY (id_sostenedor) REFERENCES came.came_sostenedor(id_sostenedor);


--
-- TOC entry 3217 (class 2606 OID 16844)
-- Name: came_periodo_planificacion_implementacion_apoyo Relationship113; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo
    ADD CONSTRAINT "Relationship113" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3218 (class 2606 OID 16849)
-- Name: came_red Relationship114; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship114" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3221 (class 2606 OID 16864)
-- Name: came_red_establecimiento Relationship115; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship115" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);


--
-- TOC entry 3198 (class 2606 OID 16744)
-- Name: came_establecimiento Relationship116; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship116" FOREIGN KEY (id_establecimiento_estado) REFERENCES came.came_establecimiento_estado(id_establecimiento_estado);


--
-- TOC entry 3203 (class 2606 OID 16769)
-- Name: came_matricula Relationship117; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship117" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);


--
-- TOC entry 3197 (class 2606 OID 16739)
-- Name: came_establecimiento Relationship118; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship118" FOREIGN KEY (id_director) REFERENCES came.came_director(id_director);


--
-- TOC entry 3234 (class 2606 OID 16929)
-- Name: came_sub_modulo Relationship122; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "Relationship122" FOREIGN KEY (id_modulo) REFERENCES came.came_modulo(id_modulo);


--
-- TOC entry 3206 (class 2606 OID 16784)
-- Name: came_modulo Relationship123; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_modulo
    ADD CONSTRAINT "Relationship123" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);


--
-- TOC entry 3214 (class 2606 OID 16824)
-- Name: came_perfil_menu Relationship124; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship124" FOREIGN KEY (id_sub_modulo) REFERENCES came.came_sub_modulo(id_sub_modulo);


--
-- TOC entry 3192 (class 2606 OID 16714)
-- Name: came_elemento_lista Relationship126; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_elemento_lista
    ADD CONSTRAINT "Relationship126" FOREIGN KEY (id_lista) REFERENCES came.came_lista(id_lista);


--
-- TOC entry 3215 (class 2606 OID 16834)
-- Name: came_periodo_documentos_regionales Relationship127; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_documentos_regionales
    ADD CONSTRAINT "Relationship127" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3201 (class 2606 OID 16759)
-- Name: came_establecimiento_categorizacion_historica Relationship128; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship128" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);


--
-- TOC entry 3193 (class 2606 OID 16719)
-- Name: came_establecimiento Relationship129; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship129" FOREIGN KEY (id_categorizacion2) REFERENCES came.came_categorizacion(id_categorizacion);


--
-- TOC entry 3196 (class 2606 OID 16734)
-- Name: came_establecimiento Relationship130; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship130" FOREIGN KEY (id_clasificacion_sep) REFERENCES came.came_clasificacion_sep(id_clasificacion_sep);


--
-- TOC entry 3194 (class 2606 OID 16724)
-- Name: came_establecimiento Relationship131; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship131" FOREIGN KEY (id_categorizacion2) REFERENCES came.came_categorizacion(id_categorizacion);


--
-- TOC entry 3216 (class 2606 OID 16839)
-- Name: came_periodo_organizacion_planificacion_provincial Relationship132; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_organizacion_planificacion_provincial
    ADD CONSTRAINT "Relationship132" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3244 (class 2606 OID 17035)
-- Name: came_periodo_conformacion_redes Relationship132; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_conformacion_redes
    ADD CONSTRAINT "Relationship132" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3243 (class 2606 OID 17030)
-- Name: came_periodo_documentos_provinciales Relationship133; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_documentos_provinciales
    ADD CONSTRAINT "Relationship133" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3237 (class 2606 OID 16944)
-- Name: came_supervisor_establecimiento Relationship134; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship134" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);


--
-- TOC entry 3199 (class 2606 OID 16749)
-- Name: came_establecimiento Relationship135; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship135" FOREIGN KEY (id_sostenedor) REFERENCES came.came_sostenedor(id_sostenedor);


--
-- TOC entry 3220 (class 2606 OID 16859)
-- Name: came_red Relationship137; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship137" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);


--
-- TOC entry 3189 (class 2606 OID 16699)
-- Name: came_acciones_mejoras Relationship138; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_acciones_mejoras
    ADD CONSTRAINT "Relationship138" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);


--
-- TOC entry 3191 (class 2606 OID 16709)
-- Name: came_came_estandares_indicativos_desempenio Relationship139; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio
    ADD CONSTRAINT "Relationship139" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);


--
-- TOC entry 3207 (class 2606 OID 16789)
-- Name: came_movimientos_claves Relationship140; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_movimientos_claves
    ADD CONSTRAINT "Relationship140" FOREIGN KEY (id_acciones_mejoras) REFERENCES came.came_acciones_mejoras(id_acciones_mejoras);


--
-- TOC entry 3229 (class 2606 OID 16904)
-- Name: came_sesion_foco Relationship141; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "Relationship141" FOREIGN KEY (id_sesion) REFERENCES came.came_sesion(id_sesion);


--
-- TOC entry 3228 (class 2606 OID 16899)
-- Name: came_sesion_foco Relationship142; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "Relationship142" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);


--
-- TOC entry 3232 (class 2606 OID 16919)
-- Name: came_sesion_movimientos_claves Relationship143; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship143" FOREIGN KEY (id_sesion_foco) REFERENCES came.came_sesion_foco(id_sesion_foco);


--
-- TOC entry 3230 (class 2606 OID 16909)
-- Name: came_sesion_movimientos_claves Relationship144; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship144" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);


--
-- TOC entry 3231 (class 2606 OID 16914)
-- Name: came_sesion_movimientos_claves Relationship145; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship145" FOREIGN KEY (id_movimientos_claves) REFERENCES came.came_movimientos_claves(id_movimientos_claves);


--
-- TOC entry 3208 (class 2606 OID 16794)
-- Name: came_objetivo_mejora Relationship146; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "Relationship146" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);


--
-- TOC entry 3209 (class 2606 OID 16799)
-- Name: came_objetivo_mejora Relationship147; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "Relationship147" FOREIGN KEY (id_sesion_foco) REFERENCES came.came_sesion_foco(id_sesion_foco);


--
-- TOC entry 3242 (class 2606 OID 16969)
-- Name: came_usuario_perfil Relationship148; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "Relationship148" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);


--
-- TOC entry 3241 (class 2606 OID 16964)
-- Name: came_usuario_perfil Relationship149; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "Relationship149" FOREIGN KEY (id_perfil) REFERENCES came.came_perfil(id_perfil);


--
-- TOC entry 3202 (class 2606 OID 16764)
-- Name: came_establecimiento_categorizacion_historica Relationship151; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship151" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);


--
-- TOC entry 3213 (class 2606 OID 16819)
-- Name: came_perfil_menu Relationship152; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship152" FOREIGN KEY (id_perfil_menu_acceso) REFERENCES came.came_perfil_menu_acceso(id_perfil_menu_acceso);


--
-- TOC entry 3222 (class 2606 OID 16869)
-- Name: came_red_establecimiento Relationship154; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship154" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3245 (class 2606 OID 17040)
-- Name: came_periodo_asignacion_supervisores Relationship155; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_periodo_asignacion_supervisores
    ADD CONSTRAINT "Relationship155" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);


--
-- TOC entry 3195 (class 2606 OID 16729)
-- Name: came_establecimiento Relationship67; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship67" FOREIGN KEY (id_categorizacion1) REFERENCES came.came_categorizacion(id_categorizacion);


--
-- TOC entry 3211 (class 2606 OID 16809)
-- Name: came_perfil Relationship67; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "Relationship67" FOREIGN KEY (id_perfil_nivel) REFERENCES came.came_perfil_nivel(id_perfil_nivel);


--
-- TOC entry 3236 (class 2606 OID 16939)
-- Name: came_supervisor_establecimiento Relationship81; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship81" FOREIGN KEY (id_asignacion_tipo) REFERENCES came.came_asignacion_tipo(id_asignacion_tipo);


--
-- TOC entry 3190 (class 2606 OID 16704)
-- Name: came_asesoria Relationship82; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_asesoria
    ADD CONSTRAINT "Relationship82" FOREIGN KEY (id_supervisor_establecimiento) REFERENCES came.came_supervisor_establecimiento(id_supervisor_establecimiento);


--
-- TOC entry 3226 (class 2606 OID 16889)
-- Name: came_sesion Relationship83; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "Relationship83" FOREIGN KEY (id_asesoria) REFERENCES came.came_asesoria(id_asesoria);


--
-- TOC entry 3239 (class 2606 OID 16954)
-- Name: came_supervisor_establecimiento Relationship84; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship84" FOREIGN KEY (id_supervisor) REFERENCES came.came_supervisor(id_supervisor);


--
-- TOC entry 3227 (class 2606 OID 16894)
-- Name: came_sesion Relationship85; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "Relationship85" FOREIGN KEY (id_session_estado) REFERENCES came.came_sesion_estado(id_sesion_estado);


--
-- TOC entry 3204 (class 2606 OID 16774)
-- Name: came_matricula Relationship92; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship92" FOREIGN KEY (id_matricula_etnia) REFERENCES came.came_matricula_etnia(id_matricula_etnia);


--
-- TOC entry 3205 (class 2606 OID 16779)
-- Name: came_matricula Relationship93; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship93" FOREIGN KEY (id_nivel) REFERENCES came.came_nivel(id_nivel);


--
-- TOC entry 3200 (class 2606 OID 16754)
-- Name: came_establecimiento_categorizacion_historica Relationship95; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship95" FOREIGN KEY (id_categorizacion) REFERENCES came.came_categorizacion(id_categorizacion);


--
-- TOC entry 3233 (class 2606 OID 16924)
-- Name: came_sub_modulo Relationship97; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "Relationship97" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);


--
-- TOC entry 3210 (class 2606 OID 16804)
-- Name: came_perfil Relationship98; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "Relationship98" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);


--
-- TOC entry 3240 (class 2606 OID 16959)
-- Name: came_usuario Relationship99; Type: FK CONSTRAINT; Schema: came; Owner: postgres
--

ALTER TABLE ONLY came.came_usuario
    ADD CONSTRAINT "Relationship99" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);


-- Completed on 2020-11-09 15:46:35

--
-- PostgreSQL database dump complete
--

