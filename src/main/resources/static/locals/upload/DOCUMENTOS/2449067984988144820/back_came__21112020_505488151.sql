PGDMP                      
    x            came    10.5    13.0               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    1378230    came    DATABASE     Y   CREATE DATABASE came WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'es_ES.UTF-8';
    DROP DATABASE came;
                adm_came    false                        2615    2392063    came    SCHEMA        CREATE SCHEMA came;
    DROP SCHEMA came;
                adm_came    false            �            1255    2392064 	   next_id()    FUNCTION     �  CREATE FUNCTION came.next_id(OUT result bigint) RETURNS bigint
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
 /   DROP FUNCTION came.next_id(OUT result bigint);
       came          adm_came    false    8            �            1259    2392067    came_acciones_mejoras    TABLE     f  CREATE TABLE came.came_acciones_mejoras (
    id_acciones_mejoras bigint NOT NULL,
    id_foco bigint,
    nombre character varying,
    descripcion character varying,
    ultima_modificacion timestamp without time zone NOT NULL,
    id_usuario_modificacion bigint NOT NULL,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
 '   DROP TABLE came.came_acciones_mejoras;
       came            adm_came    false    8            �            1259    2392076    came_asesoria    TABLE     �   CREATE TABLE came.came_asesoria (
    id_asesoria bigint NOT NULL,
    id_supervisor_establecimiento bigint
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_asesoria;
       came            adm_came    false    8            �            1259    2392389    came_asignacion_maxima    TABLE     �   CREATE TABLE came.came_asignacion_maxima (
    id_asignacion_maxima bigint NOT NULL,
    supervisores integer,
    foco bigint,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
 (   DROP TABLE came.came_asignacion_maxima;
       came            adm_came    false    8            �            1259    2392082    came_asignacion_tipo    TABLE     t   CREATE TABLE came.came_asignacion_tipo (
    id_asignacion_tipo bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 &   DROP TABLE came.came_asignacion_tipo;
       came            adm_came    false    8            �            1259    2392087 +   came_came_estandares_indicativos_desempenio    TABLE     �   CREATE TABLE came.came_came_estandares_indicativos_desempenio (
    id_estandares_indicativos_desempenio bigint NOT NULL,
    id_foco bigint
)
WITH (autovacuum_enabled='true');
 =   DROP TABLE came.came_came_estandares_indicativos_desempenio;
       came            adm_came    false    8            �            1259    2392093    came_categorizacion    TABLE     r   CREATE TABLE came.came_categorizacion (
    id_categorizacion bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_categorizacion;
       came            adm_came    false    8            �            1259    2392098    came_clasificacion_sep    TABLE     x   CREATE TABLE came.came_clasificacion_sep (
    id_clasificacion_sep bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 (   DROP TABLE came.came_clasificacion_sep;
       came            adm_came    false    8            �            1259    2392103    came_director    TABLE     f   CREATE TABLE came.came_director (
    id_director bigint NOT NULL
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_director;
       came            adm_came    false    8            �            1259    2392108    came_elemento_lista    TABLE     �   CREATE TABLE came.came_elemento_lista (
    id_elemento_lista bigint NOT NULL,
    id_lista bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_elemento_lista;
       came            adm_came    false    8            �            1259    2392119    came_establecimiento    TABLE     i  CREATE TABLE came.came_establecimiento (
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
 &   DROP TABLE came.came_establecimiento;
       came            adm_came    false    8            �            1259    2392130 -   came_establecimiento_categorizacion_historica    TABLE     �   CREATE TABLE came.came_establecimiento_categorizacion_historica (
    id_establecimiento_categorizacion_historica bigint NOT NULL,
    id_categorizacion bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');
 ?   DROP TABLE came.came_establecimiento_categorizacion_historica;
       came            adm_came    false    8            �            1259    2392138    came_establecimiento_estado    TABLE     �   CREATE TABLE came.came_establecimiento_estado (
    id_establecimiento_estado bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 -   DROP TABLE came.came_establecimiento_estado;
       came            adm_came    false    8            �            1259    2392117 +   came_establecimiento_id_categorizacion2_seq    SEQUENCE     �   ALTER TABLE came.came_establecimiento ALTER COLUMN id_categorizacion2 ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME came.came_establecimiento_id_categorizacion2_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            came          adm_came    false    207    8            �            1259    2392143    came_estado    TABLE     �   CREATE TABLE came.came_estado (
    id_estado bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_estado;
       came            adm_came    false    8            �            1259    2392151 	   came_foco    TABLE     O  CREATE TABLE came.came_foco (
    id_foco bigint NOT NULL,
    nombre character varying,
    descripcion character varying,
    fecha_registro timestamp without time zone,
    ultima_modificacion timestamp without time zone NOT NULL,
    id_usuario_modificacion bigint NOT NULL,
    periodo integer
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_foco;
       came            adm_came    false    8            �            1259    2392159 
   came_lista    TABLE     ~   CREATE TABLE came.came_lista (
    id_lista bigint NOT NULL,
    nombre character varying
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_lista;
       came            adm_came    false    8            �            1259    2392167    came_matricula    TABLE     �   CREATE TABLE came.came_matricula (
    id_matricula bigint NOT NULL,
    id_matricula_etnia bigint,
    id_nivel bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');
     DROP TABLE came.came_matricula;
       came            adm_came    false    8            �            1259    2392175    came_matricula_etnia    TABLE     t   CREATE TABLE came.came_matricula_etnia (
    id_matricula_etnia bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 &   DROP TABLE came.came_matricula_etnia;
       came            adm_came    false    8            �            1259    2392180    came_modulo    TABLE     �   CREATE TABLE came.came_modulo (
    id_modulo bigint NOT NULL,
    id_estado bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_modulo;
       came            adm_came    false    8            �            1259    2392189    came_movimientos_claves    TABLE     v  CREATE TABLE came.came_movimientos_claves (
    id_movimientos_claves bigint NOT NULL,
    id_acciones_mejoras bigint,
    nombre character varying,
    descripcion character varying,
    ultima_modificacion timestamp without time zone NOT NULL,
    id_usuario_modificacion bigint NOT NULL,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
 )   DROP TABLE came.came_movimientos_claves;
       came            adm_came    false    8            �            1259    2392198 
   came_nivel    TABLE     `   CREATE TABLE came.came_nivel (
    id_nivel bigint NOT NULL
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_nivel;
       came            adm_came    false    8            �            1259    2392203    came_objetivo_mejora    TABLE     �   CREATE TABLE came.came_objetivo_mejora (
    id_objetivo_mejora bigint NOT NULL,
    id_foco bigint,
    id_sesion_foco bigint
)
WITH (autovacuum_enabled='true');
 &   DROP TABLE came.came_objetivo_mejora;
       came            adm_came    false    8            �            1259    2392210    came_perfil    TABLE     a  CREATE TABLE came.came_perfil (
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
    DROP TABLE came.came_perfil;
       came            adm_came    false    8            �            1259    2392220    came_perfil_menu    TABLE     (  CREATE TABLE came.came_perfil_menu (
    id_perfil_menu bigint NOT NULL,
    id_perfil bigint,
    id_sub_modulo bigint,
    id_perfil_menu_acceso bigint,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    status boolean
)
WITH (autovacuum_enabled='true');
 "   DROP TABLE came.came_perfil_menu;
       came            adm_came    false    8            �            1259    2392228    came_perfil_menu_acceso    TABLE     �   CREATE TABLE came.came_perfil_menu_acceso (
    id_perfil_menu_acceso bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');
 )   DROP TABLE came.came_perfil_menu_acceso;
       came            adm_came    false    8            �            1259    2392236    came_perfil_nivel    TABLE     �   CREATE TABLE came.came_perfil_nivel (
    id_perfil_nivel bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');
 #   DROP TABLE came.came_perfil_nivel;
       came            adm_came    false    8            �            1259    2392244    came_periodo    TABLE     �   CREATE TABLE came.came_periodo (
    id_periodo bigint NOT NULL,
    anio bigint,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_periodo;
       came            adm_came    false    8            �            1259    2392383 $   came_periodo_asignacion_supervisores    TABLE     �  CREATE TABLE came.came_periodo_asignacion_supervisores (
    id_periodo_asignacion_supervisores bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone,
    es_caso_especial boolean,
    descripcion character varying
)
WITH (autovacuum_enabled='true');
 6   DROP TABLE came.came_periodo_asignacion_supervisores;
       came            adm_came    false    8            �            1259    2392267    came_periodo_conformacion_redes    TABLE     �  CREATE TABLE came.came_periodo_conformacion_redes (
    id_periodo_conformacion_redes bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone,
    es_caso_especial boolean,
    descripcion character varying
)
WITH (autovacuum_enabled='true');
 1   DROP TABLE came.came_periodo_conformacion_redes;
       came            adm_came    false    8            �            1259    2392249 $   came_periodo_documentos_provinciales    TABLE     �  CREATE TABLE came.came_periodo_documentos_provinciales (
    id_periodo_documentos_provinciales bigint NOT NULL,
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
    descripcion character varying
)
WITH (autovacuum_enabled='true');
 6   DROP TABLE came.came_periodo_documentos_provinciales;
       came            adm_came    false    8            �            1259    2392258 "   came_periodo_documentos_regionales    TABLE     �  CREATE TABLE came.came_periodo_documentos_regionales (
    id_periodo_documentos_regionales bigint NOT NULL,
    id_periodo bigint,
    id_region bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    visible boolean,
    es_caso_especial boolean,
    template_path character varying,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone,
    descripcion character varying
)
WITH (autovacuum_enabled='true');
 4   DROP TABLE came.came_periodo_documentos_regionales;
       came            adm_came    false    8            �            1259    2392273 /   came_periodo_planificacion_implementacion_apoyo    TABLE     �  CREATE TABLE came.came_periodo_planificacion_implementacion_apoyo (
    id_periodo_planificacion_implementacion_apoyo bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone,
    es_caso_especial boolean,
    descripcion character varying
)
WITH (autovacuum_enabled='true');
 A   DROP TABLE came.came_periodo_planificacion_implementacion_apoyo;
       came            adm_came    false    8            �            1259    2392279    came_red    TABLE     �   CREATE TABLE came.came_red (
    id_red bigint NOT NULL,
    id_red_tipo bigint,
    id_periodo bigint,
    id_usuario bigint,
    id_deprov bigint
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_red;
       came            adm_came    false    8            �            1259    2392287    came_red_establecimiento    TABLE     �   CREATE TABLE came.came_red_establecimiento (
    id_red_establecimiento bigint NOT NULL,
    id_red bigint,
    id_establecimiento bigint,
    id_periodo bigint
)
WITH (autovacuum_enabled='true');
 *   DROP TABLE came.came_red_establecimiento;
       came            adm_came    false    8            �            1259    2392295    came_red_sostenedor    TABLE     �   CREATE TABLE came.came_red_sostenedor (
    id_red_sostenedor bigint NOT NULL,
    id_red bigint,
    id_sostenedor bigint
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_red_sostenedor;
       came            adm_came    false    8            �            1259    2392302    came_red_tipo    TABLE     �   CREATE TABLE came.came_red_tipo (
    id_red_tipo bigint NOT NULL,
    nombre character varying
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_red_tipo;
       came            adm_came    false    8            �            1259    2392310    came_sesion    TABLE     �   CREATE TABLE came.came_sesion (
    id_sesion bigint NOT NULL,
    id_asesoria bigint,
    id_session_estado bigint
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_sesion;
       came            adm_came    false    8            �            1259    2392317    came_sesion_estado    TABLE     p   CREATE TABLE came.came_sesion_estado (
    id_sesion_estado bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 $   DROP TABLE came.came_sesion_estado;
       came            adm_came    false    8            �            1259    2392322    came_sesion_foco    TABLE     �   CREATE TABLE came.came_sesion_foco (
    id_sesion_foco bigint NOT NULL,
    id_sesion bigint,
    id_foco bigint
)
WITH (autovacuum_enabled='true');
 "   DROP TABLE came.came_sesion_foco;
       came            adm_came    false    8            �            1259    2392329    came_sesion_movimientos_claves    TABLE     �   CREATE TABLE came.came_sesion_movimientos_claves (
    id_sesion_movimientos_claves bigint NOT NULL,
    id_sesion_foco bigint,
    id_foco bigint,
    id_movimientos_claves bigint
)
WITH (autovacuum_enabled='true');
 0   DROP TABLE came.came_sesion_movimientos_claves;
       came            adm_came    false    8            �            1259    2392337    came_sostenedor    TABLE     j   CREATE TABLE came.came_sostenedor (
    id_sostenedor bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 !   DROP TABLE came.came_sostenedor;
       came            adm_came    false    8            �            1259    2392342    came_sub_modulo    TABLE     �   CREATE TABLE came.came_sub_modulo (
    id_sub_modulo bigint NOT NULL,
    id_estado bigint,
    id_modulo bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');
 !   DROP TABLE came.came_sub_modulo;
       came            adm_came    false    8            �            1259    2392352    came_supervisor    TABLE     �   CREATE TABLE came.came_supervisor (
    id_supervisor bigint NOT NULL,
    id_usuario bigint,
    id_deprov bigint,
    start_date date,
    end_date date,
    status boolean
)
WITH (autovacuum_enabled='true');
 !   DROP TABLE came.came_supervisor;
       came            adm_came    false    8            �            1259    2392358    came_supervisor_establecimiento    TABLE     �   CREATE TABLE came.came_supervisor_establecimiento (
    id_supervisor_establecimiento bigint NOT NULL,
    id_asignacion_tipo bigint,
    id_supervisor bigint,
    id_red bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');
 1   DROP TABLE came.came_supervisor_establecimiento;
       came            adm_came    false    8            �            1259    2392394    came_supervisor_suplencia    TABLE     i  CREATE TABLE came.came_supervisor_suplencia (
    id_supervisor_suplencia bigint NOT NULL,
    id_supervisor_ausente bigint,
    id_supervisor_suplente bigint,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    fecha_registro timestamp without time zone,
    id_usuario_registro bigint
)
WITH (autovacuum_enabled='true');
 +   DROP TABLE came.came_supervisor_suplencia;
       came            adm_came    false    8            �            1259    2392367    came_usuario    TABLE     v  CREATE TABLE came.came_usuario (
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
    DROP TABLE came.came_usuario;
       came            adm_came    false    8            �            1259    2392376    came_usuario_perfil    TABLE     	  CREATE TABLE came.came_usuario_perfil (
    id_usuario_perfil bigint NOT NULL,
    id_usuario bigint,
    id_perfil bigint,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    status boolean
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_usuario_perfil;
       came            adm_came    false    8            �            1259    2392065    secuencia_id    SEQUENCE     s   CREATE SEQUENCE came.secuencia_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE came.secuencia_id;
       came          adm_came    false    8            �          0    2392067    came_acciones_mejoras 
   TABLE DATA           �   COPY came.came_acciones_mejoras (id_acciones_mejoras, id_foco, nombre, descripcion, ultima_modificacion, id_usuario_modificacion, fecha_registro) FROM stdin;
    came          adm_came    false    198   �l      �          0    2392076    came_asesoria 
   TABLE DATA           Q   COPY came.came_asesoria (id_asesoria, id_supervisor_establecimiento) FROM stdin;
    came          adm_came    false    199   �l                0    2392389    came_asignacion_maxima 
   TABLE DATA           }   COPY came.came_asignacion_maxima (id_asignacion_maxima, supervisores, foco, id_usuario_registro, fecha_registro) FROM stdin;
    came          adm_came    false    243   �l      �          0    2392082    came_asignacion_tipo 
   TABLE DATA           @   COPY came.came_asignacion_tipo (id_asignacion_tipo) FROM stdin;
    came          adm_came    false    200   0m      �          0    2392087 +   came_came_estandares_indicativos_desempenio 
   TABLE DATA           r   COPY came.came_came_estandares_indicativos_desempenio (id_estandares_indicativos_desempenio, id_foco) FROM stdin;
    came          adm_came    false    201   Mm      �          0    2392093    came_categorizacion 
   TABLE DATA           >   COPY came.came_categorizacion (id_categorizacion) FROM stdin;
    came          adm_came    false    202   jm      �          0    2392098    came_clasificacion_sep 
   TABLE DATA           D   COPY came.came_clasificacion_sep (id_clasificacion_sep) FROM stdin;
    came          adm_came    false    203   �m      �          0    2392103    came_director 
   TABLE DATA           2   COPY came.came_director (id_director) FROM stdin;
    came          adm_came    false    204   �m      �          0    2392108    came_elemento_lista 
   TABLE DATA           X   COPY came.came_elemento_lista (id_elemento_lista, id_lista, nombre, status) FROM stdin;
    came          adm_came    false    205   �m      �          0    2392119    came_establecimiento 
   TABLE DATA           �   COPY came.came_establecimiento (id_establecimiento, id_categorizacion1, id_establecimiento_estado, id_director, id_categorizacion2, id_clasificacion_sep, id_sostenedor, id_deprov, id_comuna) FROM stdin;
    came          adm_came    false    207   o      �          0    2392130 -   came_establecimiento_categorizacion_historica 
   TABLE DATA           �   COPY came.came_establecimiento_categorizacion_historica (id_establecimiento_categorizacion_historica, id_categorizacion, id_establecimiento) FROM stdin;
    came          adm_came    false    208    o      �          0    2392138    came_establecimiento_estado 
   TABLE DATA           N   COPY came.came_establecimiento_estado (id_establecimiento_estado) FROM stdin;
    came          adm_came    false    209   =o      �          0    2392143    came_estado 
   TABLE DATA           6   COPY came.came_estado (id_estado, nombre) FROM stdin;
    came          adm_came    false    210   Zo      �          0    2392151 	   came_foco 
   TABLE DATA           �   COPY came.came_foco (id_foco, nombre, descripcion, fecha_registro, ultima_modificacion, id_usuario_modificacion, periodo) FROM stdin;
    came          adm_came    false    211   �o      �          0    2392159 
   came_lista 
   TABLE DATA           4   COPY came.came_lista (id_lista, nombre) FROM stdin;
    came          adm_came    false    212   �o      �          0    2392167    came_matricula 
   TABLE DATA           f   COPY came.came_matricula (id_matricula, id_matricula_etnia, id_nivel, id_establecimiento) FROM stdin;
    came          adm_came    false    213   7p      �          0    2392175    came_matricula_etnia 
   TABLE DATA           @   COPY came.came_matricula_etnia (id_matricula_etnia) FROM stdin;
    came          adm_came    false    214   Tp      �          0    2392180    came_modulo 
   TABLE DATA           I   COPY came.came_modulo (id_modulo, id_estado, nombre, status) FROM stdin;
    came          adm_came    false    215   qp      �          0    2392189    came_movimientos_claves 
   TABLE DATA           �   COPY came.came_movimientos_claves (id_movimientos_claves, id_acciones_mejoras, nombre, descripcion, ultima_modificacion, id_usuario_modificacion, fecha_registro) FROM stdin;
    came          adm_came    false    216   Fq      �          0    2392198 
   came_nivel 
   TABLE DATA           ,   COPY came.came_nivel (id_nivel) FROM stdin;
    came          adm_came    false    217   cq      �          0    2392203    came_objetivo_mejora 
   TABLE DATA           Y   COPY came.came_objetivo_mejora (id_objetivo_mejora, id_foco, id_sesion_foco) FROM stdin;
    came          adm_came    false    218   �q      �          0    2392210    came_perfil 
   TABLE DATA           �   COPY came.came_perfil (id_perfil, id_estado, id_perfil_nivel, nombre, descripcion, habilitado, ultima_modificacion, id_usuario_modificacion) FROM stdin;
    came          adm_came    false    219   �q      �          0    2392220    came_perfil_menu 
   TABLE DATA           �   COPY came.came_perfil_menu (id_perfil_menu, id_perfil, id_sub_modulo, id_perfil_menu_acceso, start_date, end_date, status) FROM stdin;
    came          adm_came    false    220   �r      �          0    2392228    came_perfil_menu_acceso 
   TABLE DATA           N   COPY came.came_perfil_menu_acceso (id_perfil_menu_acceso, nombre) FROM stdin;
    came          adm_came    false    221   Qu      �          0    2392236    came_perfil_nivel 
   TABLE DATA           B   COPY came.came_perfil_nivel (id_perfil_nivel, nombre) FROM stdin;
    came          adm_came    false    222   �u      �          0    2392244    came_periodo 
   TABLE DATA           [   COPY came.came_periodo (id_periodo, anio, id_usuario_registro, fecha_registro) FROM stdin;
    came          adm_came    false    223   �u                0    2392383 $   came_periodo_asignacion_supervisores 
   TABLE DATA           �   COPY came.came_periodo_asignacion_supervisores (id_periodo_asignacion_supervisores, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro, es_caso_especial, descripcion) FROM stdin;
    came          adm_came    false    242   �w                 0    2392267    came_periodo_conformacion_redes 
   TABLE DATA           �   COPY came.came_periodo_conformacion_redes (id_periodo_conformacion_redes, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro, es_caso_especial, descripcion) FROM stdin;
    came          adm_came    false    226   �y      �          0    2392249 $   came_periodo_documentos_provinciales 
   TABLE DATA           �   COPY came.came_periodo_documentos_provinciales (id_periodo_documentos_provinciales, id_periodo, id_region, id_deprov, visible, es_caso_especial, template_path, fecha_registro, fecha_inicio, fecha_fin, id_usuario_registro, descripcion) FROM stdin;
    came          adm_came    false    224   �{      �          0    2392258 "   came_periodo_documentos_regionales 
   TABLE DATA           �   COPY came.came_periodo_documentos_regionales (id_periodo_documentos_regionales, id_periodo, id_region, fecha_inicio, fecha_fin, visible, es_caso_especial, template_path, id_usuario_registro, fecha_registro, descripcion) FROM stdin;
    came          adm_came    false    225   �~                0    2392273 /   came_periodo_planificacion_implementacion_apoyo 
   TABLE DATA           �   COPY came.came_periodo_planificacion_implementacion_apoyo (id_periodo_planificacion_implementacion_apoyo, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro, es_caso_especial, descripcion) FROM stdin;
    came          adm_came    false    227   ��                0    2392279    came_red 
   TABLE DATA           X   COPY came.came_red (id_red, id_red_tipo, id_periodo, id_usuario, id_deprov) FROM stdin;
    came          adm_came    false    228   ��                0    2392287    came_red_establecimiento 
   TABLE DATA           p   COPY came.came_red_establecimiento (id_red_establecimiento, id_red, id_establecimiento, id_periodo) FROM stdin;
    came          adm_came    false    229   ȃ                0    2392295    came_red_sostenedor 
   TABLE DATA           U   COPY came.came_red_sostenedor (id_red_sostenedor, id_red, id_sostenedor) FROM stdin;
    came          adm_came    false    230   �                0    2392302    came_red_tipo 
   TABLE DATA           :   COPY came.came_red_tipo (id_red_tipo, nombre) FROM stdin;
    came          adm_came    false    231   �                0    2392310    came_sesion 
   TABLE DATA           N   COPY came.came_sesion (id_sesion, id_asesoria, id_session_estado) FROM stdin;
    came          adm_came    false    232   �                0    2392317    came_sesion_estado 
   TABLE DATA           <   COPY came.came_sesion_estado (id_sesion_estado) FROM stdin;
    came          adm_came    false    233   <�                0    2392322    came_sesion_foco 
   TABLE DATA           L   COPY came.came_sesion_foco (id_sesion_foco, id_sesion, id_foco) FROM stdin;
    came          adm_came    false    234   Y�      	          0    2392329    came_sesion_movimientos_claves 
   TABLE DATA           �   COPY came.came_sesion_movimientos_claves (id_sesion_movimientos_claves, id_sesion_foco, id_foco, id_movimientos_claves) FROM stdin;
    came          adm_came    false    235   v�      
          0    2392337    came_sostenedor 
   TABLE DATA           6   COPY came.came_sostenedor (id_sostenedor) FROM stdin;
    came          adm_came    false    236   ��                0    2392342    came_sub_modulo 
   TABLE DATA           \   COPY came.came_sub_modulo (id_sub_modulo, id_estado, id_modulo, nombre, status) FROM stdin;
    came          adm_came    false    237   ��                0    2392352    came_supervisor 
   TABLE DATA           k   COPY came.came_supervisor (id_supervisor, id_usuario, id_deprov, start_date, end_date, status) FROM stdin;
    came          adm_came    false    238   $�                0    2392358    came_supervisor_establecimiento 
   TABLE DATA           �   COPY came.came_supervisor_establecimiento (id_supervisor_establecimiento, id_asignacion_tipo, id_supervisor, id_red, id_establecimiento) FROM stdin;
    came          adm_came    false    239   ��                0    2392394    came_supervisor_suplencia 
   TABLE DATA           �   COPY came.came_supervisor_suplencia (id_supervisor_suplencia, id_supervisor_ausente, id_supervisor_suplente, start_date, end_date, fecha_registro, id_usuario_registro) FROM stdin;
    came          adm_came    false    244                   0    2392367    came_usuario 
   TABLE DATA           	  COPY came.came_usuario (id_usuario, id_estado, id_region, id_deprov, usuario_dominio, run, nombre, apellido_paterno, apellido_materno, email, habilitado, reintentos, ultima_modificacion, id_usuario_modificacion, dv, fecha_registro, id_usuario_registro) FROM stdin;
    came          adm_came    false    240   ߆                0    2392376    came_usuario_perfil 
   TABLE DATA           s   COPY came.came_usuario_perfil (id_usuario_perfil, id_usuario, id_perfil, start_date, end_date, status) FROM stdin;
    came          adm_came    false    241   ��                 0    0 +   came_establecimiento_id_categorizacion2_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('came.came_establecimiento_id_categorizacion2_seq', 1, false);
          came          adm_came    false    206                       0    0    secuencia_id    SEQUENCE SET     :   SELECT pg_catalog.setval('came.secuencia_id', 168, true);
          came          adm_came    false    197            �           2606    2392075 .   came_acciones_mejoras PK_came_acciones_mejoras 
   CONSTRAINT     }   ALTER TABLE ONLY came.came_acciones_mejoras
    ADD CONSTRAINT "PK_came_acciones_mejoras" PRIMARY KEY (id_acciones_mejoras);
 X   ALTER TABLE ONLY came.came_acciones_mejoras DROP CONSTRAINT "PK_came_acciones_mejoras";
       came            adm_came    false    198            �           2606    2392081    came_asesoria PK_came_asesoria 
   CONSTRAINT     e   ALTER TABLE ONLY came.came_asesoria
    ADD CONSTRAINT "PK_came_asesoria" PRIMARY KEY (id_asesoria);
 H   ALTER TABLE ONLY came.came_asesoria DROP CONSTRAINT "PK_came_asesoria";
       came            adm_came    false    199            +           2606    2392393 0   came_asignacion_maxima PK_came_asignacion_maxima 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_asignacion_maxima
    ADD CONSTRAINT "PK_came_asignacion_maxima" PRIMARY KEY (id_asignacion_maxima);
 Z   ALTER TABLE ONLY came.came_asignacion_maxima DROP CONSTRAINT "PK_came_asignacion_maxima";
       came            adm_came    false    243            �           2606    2392086 ,   came_asignacion_tipo PK_came_asignacion_tipo 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_asignacion_tipo
    ADD CONSTRAINT "PK_came_asignacion_tipo" PRIMARY KEY (id_asignacion_tipo);
 V   ALTER TABLE ONLY came.came_asignacion_tipo DROP CONSTRAINT "PK_came_asignacion_tipo";
       came            adm_came    false    200            �           2606    2392092 Z   came_came_estandares_indicativos_desempenio PK_came_came_estandares_indicativos_desempenio 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio
    ADD CONSTRAINT "PK_came_came_estandares_indicativos_desempenio" PRIMARY KEY (id_estandares_indicativos_desempenio);
 �   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio DROP CONSTRAINT "PK_came_came_estandares_indicativos_desempenio";
       came            adm_came    false    201            �           2606    2392097 *   came_categorizacion PK_came_categorizacion 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_categorizacion
    ADD CONSTRAINT "PK_came_categorizacion" PRIMARY KEY (id_categorizacion);
 T   ALTER TABLE ONLY came.came_categorizacion DROP CONSTRAINT "PK_came_categorizacion";
       came            adm_came    false    202            �           2606    2392102 0   came_clasificacion_sep PK_came_clasificacion_sep 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_clasificacion_sep
    ADD CONSTRAINT "PK_came_clasificacion_sep" PRIMARY KEY (id_clasificacion_sep);
 Z   ALTER TABLE ONLY came.came_clasificacion_sep DROP CONSTRAINT "PK_came_clasificacion_sep";
       came            adm_came    false    203            �           2606    2392107    came_director PK_came_director 
   CONSTRAINT     e   ALTER TABLE ONLY came.came_director
    ADD CONSTRAINT "PK_came_director" PRIMARY KEY (id_director);
 H   ALTER TABLE ONLY came.came_director DROP CONSTRAINT "PK_came_director";
       came            adm_came    false    204            �           2606    2392116 *   came_elemento_lista PK_came_elemento_lista 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_elemento_lista
    ADD CONSTRAINT "PK_came_elemento_lista" PRIMARY KEY (id_elemento_lista);
 T   ALTER TABLE ONLY came.came_elemento_lista DROP CONSTRAINT "PK_came_elemento_lista";
       came            adm_came    false    205            �           2606    2392129 ,   came_establecimiento PK_came_establecimiento 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "PK_came_establecimiento" PRIMARY KEY (id_establecimiento);
 V   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "PK_came_establecimiento";
       came            adm_came    false    207            �           2606    2392137 ^   came_establecimiento_categorizacion_historica PK_came_establecimiento_categorizacion_historica 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "PK_came_establecimiento_categorizacion_historica" PRIMARY KEY (id_establecimiento_categorizacion_historica);
 �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "PK_came_establecimiento_categorizacion_historica";
       came            adm_came    false    208            �           2606    2392142 :   came_establecimiento_estado PK_came_establecimiento_estado 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_estado
    ADD CONSTRAINT "PK_came_establecimiento_estado" PRIMARY KEY (id_establecimiento_estado);
 d   ALTER TABLE ONLY came.came_establecimiento_estado DROP CONSTRAINT "PK_came_establecimiento_estado";
       came            adm_came    false    209            �           2606    2392150    came_estado PK_came_estado 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_estado
    ADD CONSTRAINT "PK_came_estado" PRIMARY KEY (id_estado);
 D   ALTER TABLE ONLY came.came_estado DROP CONSTRAINT "PK_came_estado";
       came            adm_came    false    210            �           2606    2392158    came_foco PK_came_foco 
   CONSTRAINT     Y   ALTER TABLE ONLY came.came_foco
    ADD CONSTRAINT "PK_came_foco" PRIMARY KEY (id_foco);
 @   ALTER TABLE ONLY came.came_foco DROP CONSTRAINT "PK_came_foco";
       came            adm_came    false    211            �           2606    2392166    came_lista PK_came_lista 
   CONSTRAINT     \   ALTER TABLE ONLY came.came_lista
    ADD CONSTRAINT "PK_came_lista" PRIMARY KEY (id_lista);
 B   ALTER TABLE ONLY came.came_lista DROP CONSTRAINT "PK_came_lista";
       came            adm_came    false    212            �           2606    2392174     came_matricula PK_came_matricula 
   CONSTRAINT     h   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "PK_came_matricula" PRIMARY KEY (id_matricula);
 J   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "PK_came_matricula";
       came            adm_came    false    213            �           2606    2392179 ,   came_matricula_etnia PK_came_matricula_etnia 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_matricula_etnia
    ADD CONSTRAINT "PK_came_matricula_etnia" PRIMARY KEY (id_matricula_etnia);
 V   ALTER TABLE ONLY came.came_matricula_etnia DROP CONSTRAINT "PK_came_matricula_etnia";
       came            adm_came    false    214            �           2606    2392188    came_modulo PK_came_modulo 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_modulo
    ADD CONSTRAINT "PK_came_modulo" PRIMARY KEY (id_modulo);
 D   ALTER TABLE ONLY came.came_modulo DROP CONSTRAINT "PK_came_modulo";
       came            adm_came    false    215            �           2606    2392197 2   came_movimientos_claves PK_came_movimientos_claves 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_movimientos_claves
    ADD CONSTRAINT "PK_came_movimientos_claves" PRIMARY KEY (id_movimientos_claves);
 \   ALTER TABLE ONLY came.came_movimientos_claves DROP CONSTRAINT "PK_came_movimientos_claves";
       came            adm_came    false    216            �           2606    2392202    came_nivel PK_came_nivel 
   CONSTRAINT     \   ALTER TABLE ONLY came.came_nivel
    ADD CONSTRAINT "PK_came_nivel" PRIMARY KEY (id_nivel);
 B   ALTER TABLE ONLY came.came_nivel DROP CONSTRAINT "PK_came_nivel";
       came            adm_came    false    217            �           2606    2392209 ,   came_objetivo_mejora PK_came_objetivo_mejora 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "PK_came_objetivo_mejora" PRIMARY KEY (id_objetivo_mejora);
 V   ALTER TABLE ONLY came.came_objetivo_mejora DROP CONSTRAINT "PK_came_objetivo_mejora";
       came            adm_came    false    218            �           2606    2392219    came_perfil PK_came_perfil 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "PK_came_perfil" PRIMARY KEY (id_perfil);
 D   ALTER TABLE ONLY came.came_perfil DROP CONSTRAINT "PK_came_perfil";
       came            adm_came    false    219            �           2606    2392227 $   came_perfil_menu PK_came_perfil_menu 
   CONSTRAINT     n   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "PK_came_perfil_menu" PRIMARY KEY (id_perfil_menu);
 N   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "PK_came_perfil_menu";
       came            adm_came    false    220            �           2606    2392235 2   came_perfil_menu_acceso PK_came_perfil_menu_acceso 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu_acceso
    ADD CONSTRAINT "PK_came_perfil_menu_acceso" PRIMARY KEY (id_perfil_menu_acceso);
 \   ALTER TABLE ONLY came.came_perfil_menu_acceso DROP CONSTRAINT "PK_came_perfil_menu_acceso";
       came            adm_came    false    221            �           2606    2392243 &   came_perfil_nivel PK_came_perfil_nivel 
   CONSTRAINT     q   ALTER TABLE ONLY came.came_perfil_nivel
    ADD CONSTRAINT "PK_came_perfil_nivel" PRIMARY KEY (id_perfil_nivel);
 P   ALTER TABLE ONLY came.came_perfil_nivel DROP CONSTRAINT "PK_came_perfil_nivel";
       came            adm_came    false    222            �           2606    2392248    came_periodo PK_came_periodo 
   CONSTRAINT     b   ALTER TABLE ONLY came.came_periodo
    ADD CONSTRAINT "PK_came_periodo" PRIMARY KEY (id_periodo);
 F   ALTER TABLE ONLY came.came_periodo DROP CONSTRAINT "PK_came_periodo";
       came            adm_came    false    223            )           2606    2392388 L   came_periodo_asignacion_supervisores PK_came_periodo_asignacion_supervisores 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores
    ADD CONSTRAINT "PK_came_periodo_asignacion_supervisores" PRIMARY KEY (id_periodo_asignacion_supervisores);
 v   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores DROP CONSTRAINT "PK_came_periodo_asignacion_supervisores";
       came            adm_came    false    242            �           2606    2392257 L   came_periodo_documentos_provinciales PK_came_periodo_documentos_provinciales 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_provinciales
    ADD CONSTRAINT "PK_came_periodo_documentos_provinciales" PRIMARY KEY (id_periodo_documentos_provinciales);
 v   ALTER TABLE ONLY came.came_periodo_documentos_provinciales DROP CONSTRAINT "PK_came_periodo_documentos_provinciales";
       came            adm_came    false    224            �           2606    2392266 H   came_periodo_documentos_regionales PK_came_periodo_documentos_regionales 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_regionales
    ADD CONSTRAINT "PK_came_periodo_documentos_regionales" PRIMARY KEY (id_periodo_documentos_regionales);
 r   ALTER TABLE ONLY came.came_periodo_documentos_regionales DROP CONSTRAINT "PK_came_periodo_documentos_regionales";
       came            adm_came    false    225            �           2606    2392272 U   came_periodo_conformacion_redes PK_came_periodo_organizacion_planificacion_provincial 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_conformacion_redes
    ADD CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial" PRIMARY KEY (id_periodo_conformacion_redes);
    ALTER TABLE ONLY came.came_periodo_conformacion_redes DROP CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial";
       came            adm_came    false    226            �           2606    2392278 b   came_periodo_planificacion_implementacion_apoyo PK_came_periodo_planificacion_implementacion_apoyo 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo
    ADD CONSTRAINT "PK_came_periodo_planificacion_implementacion_apoyo" PRIMARY KEY (id_periodo_planificacion_implementacion_apoyo);
 �   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo DROP CONSTRAINT "PK_came_periodo_planificacion_implementacion_apoyo";
       came            adm_came    false    227            �           2606    2392286    came_red PK_came_red 
   CONSTRAINT     V   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "PK_came_red" PRIMARY KEY (id_red);
 >   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "PK_came_red";
       came            adm_came    false    228            �           2606    2392294 4   came_red_establecimiento PK_came_red_establecimiento 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "PK_came_red_establecimiento" PRIMARY KEY (id_red_establecimiento);
 ^   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "PK_came_red_establecimiento";
       came            adm_came    false    229            �           2606    2392301 *   came_red_sostenedor PK_came_red_sostenedor 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "PK_came_red_sostenedor" PRIMARY KEY (id_red_sostenedor);
 T   ALTER TABLE ONLY came.came_red_sostenedor DROP CONSTRAINT "PK_came_red_sostenedor";
       came            adm_came    false    230                       2606    2392309    came_red_tipo PK_came_red_tipo 
   CONSTRAINT     e   ALTER TABLE ONLY came.came_red_tipo
    ADD CONSTRAINT "PK_came_red_tipo" PRIMARY KEY (id_red_tipo);
 H   ALTER TABLE ONLY came.came_red_tipo DROP CONSTRAINT "PK_came_red_tipo";
       came            adm_came    false    231                       2606    2392316    came_sesion PK_came_sesion 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "PK_came_sesion" PRIMARY KEY (id_sesion);
 D   ALTER TABLE ONLY came.came_sesion DROP CONSTRAINT "PK_came_sesion";
       came            adm_came    false    232                       2606    2392321 (   came_sesion_estado PK_came_sesion_estado 
   CONSTRAINT     t   ALTER TABLE ONLY came.came_sesion_estado
    ADD CONSTRAINT "PK_came_sesion_estado" PRIMARY KEY (id_sesion_estado);
 R   ALTER TABLE ONLY came.came_sesion_estado DROP CONSTRAINT "PK_came_sesion_estado";
       came            adm_came    false    233                       2606    2392328 $   came_sesion_foco PK_came_sesion_foco 
   CONSTRAINT     n   ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "PK_came_sesion_foco" PRIMARY KEY (id_sesion_foco);
 N   ALTER TABLE ONLY came.came_sesion_foco DROP CONSTRAINT "PK_came_sesion_foco";
       came            adm_came    false    234                       2606    2392336 @   came_sesion_movimientos_claves PK_came_sesion_movimientos_claves 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "PK_came_sesion_movimientos_claves" PRIMARY KEY (id_sesion_movimientos_claves);
 j   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "PK_came_sesion_movimientos_claves";
       came            adm_came    false    235                       2606    2392341 "   came_sostenedor PK_came_sostenedor 
   CONSTRAINT     k   ALTER TABLE ONLY came.came_sostenedor
    ADD CONSTRAINT "PK_came_sostenedor" PRIMARY KEY (id_sostenedor);
 L   ALTER TABLE ONLY came.came_sostenedor DROP CONSTRAINT "PK_came_sostenedor";
       came            adm_came    false    236                       2606    2392351 "   came_sub_modulo PK_came_sub_modulo 
   CONSTRAINT     k   ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "PK_came_sub_modulo" PRIMARY KEY (id_sub_modulo);
 L   ALTER TABLE ONLY came.came_sub_modulo DROP CONSTRAINT "PK_came_sub_modulo";
       came            adm_came    false    237                       2606    2392357 "   came_supervisor PK_came_supervisor 
   CONSTRAINT     k   ALTER TABLE ONLY came.came_supervisor
    ADD CONSTRAINT "PK_came_supervisor" PRIMARY KEY (id_supervisor);
 L   ALTER TABLE ONLY came.came_supervisor DROP CONSTRAINT "PK_came_supervisor";
       came            adm_came    false    238                       2606    2392366 B   came_supervisor_establecimiento PK_came_supervisor_establecimiento 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "PK_came_supervisor_establecimiento" PRIMARY KEY (id_supervisor_establecimiento);
 l   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "PK_came_supervisor_establecimiento";
       came            adm_came    false    239            /           2606    2392400 6   came_supervisor_suplencia PK_came_supervisor_suplencia 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_suplencia
    ADD CONSTRAINT "PK_came_supervisor_suplencia" PRIMARY KEY (id_supervisor_suplencia);
 `   ALTER TABLE ONLY came.came_supervisor_suplencia DROP CONSTRAINT "PK_came_supervisor_suplencia";
       came            adm_came    false    244            "           2606    2392375    came_usuario PK_came_usuario 
   CONSTRAINT     b   ALTER TABLE ONLY came.came_usuario
    ADD CONSTRAINT "PK_came_usuario" PRIMARY KEY (id_usuario);
 F   ALTER TABLE ONLY came.came_usuario DROP CONSTRAINT "PK_came_usuario";
       came            adm_came    false    240            &           2606    2392382 *   came_usuario_perfil PK_came_usuario_perfil 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "PK_came_usuario_perfil" PRIMARY KEY (id_usuario_perfil);
 T   ALTER TABLE ONLY came.came_usuario_perfil DROP CONSTRAINT "PK_came_usuario_perfil";
       came            adm_came    false    241                       1259    2392355    IX_Relationship100    INDEX     T   CREATE INDEX "IX_Relationship100" ON came.came_supervisor USING btree (id_usuario);
 &   DROP INDEX came."IX_Relationship100";
       came            adm_came    false    238                       1259    2392363    IX_Relationship102    INDEX     `   CREATE INDEX "IX_Relationship102" ON came.came_supervisor_establecimiento USING btree (id_red);
 &   DROP INDEX came."IX_Relationship102";
       came            adm_came    false    239            �           1259    2392298    IX_Relationship105    INDEX     T   CREATE INDEX "IX_Relationship105" ON came.came_red_sostenedor USING btree (id_red);
 &   DROP INDEX came."IX_Relationship105";
       came            adm_came    false    230            �           1259    2392282    IX_Relationship106    INDEX     N   CREATE INDEX "IX_Relationship106" ON came.came_red USING btree (id_red_tipo);
 &   DROP INDEX came."IX_Relationship106";
       came            adm_came    false    228            �           1259    2392290    IX_Relationship107    INDEX     Y   CREATE INDEX "IX_Relationship107" ON came.came_red_establecimiento USING btree (id_red);
 &   DROP INDEX came."IX_Relationship107";
       came            adm_came    false    229            �           1259    2392223    IX_Relationship109    INDEX     T   CREATE INDEX "IX_Relationship109" ON came.came_perfil_menu USING btree (id_perfil);
 &   DROP INDEX came."IX_Relationship109";
       came            adm_came    false    220            �           1259    2392299    IX_Relationship110    INDEX     [   CREATE INDEX "IX_Relationship110" ON came.came_red_sostenedor USING btree (id_sostenedor);
 &   DROP INDEX came."IX_Relationship110";
       came            adm_came    false    230            �           1259    2392276    IX_Relationship113    INDEX     t   CREATE INDEX "IX_Relationship113" ON came.came_periodo_planificacion_implementacion_apoyo USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship113";
       came            adm_came    false    227            �           1259    2392283    IX_Relationship114    INDEX     M   CREATE INDEX "IX_Relationship114" ON came.came_red USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship114";
       came            adm_came    false    228            �           1259    2392291    IX_Relationship115    INDEX     e   CREATE INDEX "IX_Relationship115" ON came.came_red_establecimiento USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship115";
       came            adm_came    false    229            �           1259    2392122    IX_Relationship116    INDEX     h   CREATE INDEX "IX_Relationship116" ON came.came_establecimiento USING btree (id_establecimiento_estado);
 &   DROP INDEX came."IX_Relationship116";
       came            adm_came    false    207            �           1259    2392172    IX_Relationship117    INDEX     [   CREATE INDEX "IX_Relationship117" ON came.came_matricula USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship117";
       came            adm_came    false    213            �           1259    2392123    IX_Relationship118    INDEX     Z   CREATE INDEX "IX_Relationship118" ON came.came_establecimiento USING btree (id_director);
 &   DROP INDEX came."IX_Relationship118";
       came            adm_came    false    207                       1259    2392349    IX_Relationship122    INDEX     S   CREATE INDEX "IX_Relationship122" ON came.came_sub_modulo USING btree (id_modulo);
 &   DROP INDEX came."IX_Relationship122";
       came            adm_came    false    237            �           1259    2392186    IX_Relationship123    INDEX     O   CREATE INDEX "IX_Relationship123" ON came.came_modulo USING btree (id_estado);
 &   DROP INDEX came."IX_Relationship123";
       came            adm_came    false    215            �           1259    2392224    IX_Relationship124    INDEX     X   CREATE INDEX "IX_Relationship124" ON came.came_perfil_menu USING btree (id_sub_modulo);
 &   DROP INDEX came."IX_Relationship124";
       came            adm_came    false    220            �           1259    2392114    IX_Relationship126    INDEX     V   CREATE INDEX "IX_Relationship126" ON came.came_elemento_lista USING btree (id_lista);
 &   DROP INDEX came."IX_Relationship126";
       came            adm_came    false    205            �           1259    2392264    IX_Relationship127    INDEX     g   CREATE INDEX "IX_Relationship127" ON came.came_periodo_documentos_regionales USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship127";
       came            adm_came    false    225            �           1259    2392134    IX_Relationship128    INDEX     z   CREATE INDEX "IX_Relationship128" ON came.came_establecimiento_categorizacion_historica USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship128";
       came            adm_came    false    208            �           1259    2392124    IX_Relationship129    INDEX     a   CREATE INDEX "IX_Relationship129" ON came.came_establecimiento USING btree (id_categorizacion2);
 &   DROP INDEX came."IX_Relationship129";
       came            adm_came    false    207            �           1259    2392125    IX_Relationship130    INDEX     c   CREATE INDEX "IX_Relationship130" ON came.came_establecimiento USING btree (id_clasificacion_sep);
 &   DROP INDEX came."IX_Relationship130";
       came            adm_came    false    207            �           1259    2392126    IX_Relationship131    INDEX     a   CREATE INDEX "IX_Relationship131" ON came.came_establecimiento USING btree (id_categorizacion2);
 &   DROP INDEX came."IX_Relationship131";
       came            adm_came    false    207            �           1259    2392270    IX_Relationship132    INDEX     d   CREATE INDEX "IX_Relationship132" ON came.came_periodo_conformacion_redes USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship132";
       came            adm_came    false    226            �           1259    2392255    IX_Relationship133    INDEX     i   CREATE INDEX "IX_Relationship133" ON came.came_periodo_documentos_provinciales USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship133";
       came            adm_came    false    224                       1259    2392364    IX_Relationship134    INDEX     l   CREATE INDEX "IX_Relationship134" ON came.came_supervisor_establecimiento USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship134";
       came            adm_came    false    239            �           1259    2392127    IX_Relationship135    INDEX     \   CREATE INDEX "IX_Relationship135" ON came.came_establecimiento USING btree (id_sostenedor);
 &   DROP INDEX came."IX_Relationship135";
       came            adm_came    false    207            �           1259    2392284    IX_Relationship137    INDEX     M   CREATE INDEX "IX_Relationship137" ON came.came_red USING btree (id_usuario);
 &   DROP INDEX came."IX_Relationship137";
       came            adm_came    false    228            �           1259    2392073    IX_Relationship138    INDEX     W   CREATE INDEX "IX_Relationship138" ON came.came_acciones_mejoras USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship138";
       came            adm_came    false    198            �           1259    2392090    IX_Relationship139    INDEX     m   CREATE INDEX "IX_Relationship139" ON came.came_came_estandares_indicativos_desempenio USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship139";
       came            adm_came    false    201            �           1259    2392195    IX_Relationship140    INDEX     e   CREATE INDEX "IX_Relationship140" ON came.came_movimientos_claves USING btree (id_acciones_mejoras);
 &   DROP INDEX came."IX_Relationship140";
       came            adm_came    false    216                       1259    2392325    IX_Relationship141    INDEX     T   CREATE INDEX "IX_Relationship141" ON came.came_sesion_foco USING btree (id_sesion);
 &   DROP INDEX came."IX_Relationship141";
       came            adm_came    false    234            	           1259    2392326    IX_Relationship142    INDEX     R   CREATE INDEX "IX_Relationship142" ON came.came_sesion_foco USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship142";
       came            adm_came    false    234                       1259    2392332    IX_Relationship143    INDEX     g   CREATE INDEX "IX_Relationship143" ON came.came_sesion_movimientos_claves USING btree (id_sesion_foco);
 &   DROP INDEX came."IX_Relationship143";
       came            adm_came    false    235                       1259    2392333    IX_Relationship144    INDEX     `   CREATE INDEX "IX_Relationship144" ON came.came_sesion_movimientos_claves USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship144";
       came            adm_came    false    235                       1259    2392334    IX_Relationship145    INDEX     n   CREATE INDEX "IX_Relationship145" ON came.came_sesion_movimientos_claves USING btree (id_movimientos_claves);
 &   DROP INDEX came."IX_Relationship145";
       came            adm_came    false    235            �           1259    2392206    IX_Relationship146    INDEX     V   CREATE INDEX "IX_Relationship146" ON came.came_objetivo_mejora USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship146";
       came            adm_came    false    218            �           1259    2392207    IX_Relationship147    INDEX     ]   CREATE INDEX "IX_Relationship147" ON came.came_objetivo_mejora USING btree (id_sesion_foco);
 &   DROP INDEX came."IX_Relationship147";
       came            adm_came    false    218            #           1259    2392379    IX_Relationship148    INDEX     X   CREATE INDEX "IX_Relationship148" ON came.came_usuario_perfil USING btree (id_usuario);
 &   DROP INDEX came."IX_Relationship148";
       came            adm_came    false    241            $           1259    2392380    IX_Relationship149    INDEX     W   CREATE INDEX "IX_Relationship149" ON came.came_usuario_perfil USING btree (id_perfil);
 &   DROP INDEX came."IX_Relationship149";
       came            adm_came    false    241            �           1259    2392135    IX_Relationship151    INDEX     z   CREATE INDEX "IX_Relationship151" ON came.came_establecimiento_categorizacion_historica USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship151";
       came            adm_came    false    208            �           1259    2392225    IX_Relationship152    INDEX     `   CREATE INDEX "IX_Relationship152" ON came.came_perfil_menu USING btree (id_perfil_menu_acceso);
 &   DROP INDEX came."IX_Relationship152";
       came            adm_came    false    220            �           1259    2392292    IX_Relationship154    INDEX     ]   CREATE INDEX "IX_Relationship154" ON came.came_red_establecimiento USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship154";
       came            adm_came    false    229            '           1259    2392386    IX_Relationship155    INDEX     i   CREATE INDEX "IX_Relationship155" ON came.came_periodo_asignacion_supervisores USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship155";
       came            adm_came    false    242            ,           1259    2392397    IX_Relationship158    INDEX     i   CREATE INDEX "IX_Relationship158" ON came.came_supervisor_suplencia USING btree (id_supervisor_ausente);
 &   DROP INDEX came."IX_Relationship158";
       came            adm_came    false    244            -           1259    2392398    IX_Relationship159    INDEX     j   CREATE INDEX "IX_Relationship159" ON came.came_supervisor_suplencia USING btree (id_supervisor_suplente);
 &   DROP INDEX came."IX_Relationship159";
       came            adm_came    false    244            �           1259    2392217    IX_Relationship67    INDEX     T   CREATE INDEX "IX_Relationship67" ON came.came_perfil USING btree (id_perfil_nivel);
 %   DROP INDEX came."IX_Relationship67";
       came            adm_came    false    219                       1259    2392361    IX_Relationship81    INDEX     k   CREATE INDEX "IX_Relationship81" ON came.came_supervisor_establecimiento USING btree (id_asignacion_tipo);
 %   DROP INDEX came."IX_Relationship81";
       came            adm_came    false    239            �           1259    2392079    IX_Relationship82    INDEX     d   CREATE INDEX "IX_Relationship82" ON came.came_asesoria USING btree (id_supervisor_establecimiento);
 %   DROP INDEX came."IX_Relationship82";
       came            adm_came    false    199                       1259    2392313    IX_Relationship83    INDEX     P   CREATE INDEX "IX_Relationship83" ON came.came_sesion USING btree (id_asesoria);
 %   DROP INDEX came."IX_Relationship83";
       came            adm_came    false    232                       1259    2392362    IX_Relationship84    INDEX     f   CREATE INDEX "IX_Relationship84" ON came.came_supervisor_establecimiento USING btree (id_supervisor);
 %   DROP INDEX came."IX_Relationship84";
       came            adm_came    false    239                       1259    2392314    IX_Relationship85    INDEX     V   CREATE INDEX "IX_Relationship85" ON came.came_sesion USING btree (id_session_estado);
 %   DROP INDEX came."IX_Relationship85";
       came            adm_came    false    232            �           1259    2392170    IX_Relationship92    INDEX     Z   CREATE INDEX "IX_Relationship92" ON came.came_matricula USING btree (id_matricula_etnia);
 %   DROP INDEX came."IX_Relationship92";
       came            adm_came    false    213            �           1259    2392171    IX_Relationship93    INDEX     P   CREATE INDEX "IX_Relationship93" ON came.came_matricula USING btree (id_nivel);
 %   DROP INDEX came."IX_Relationship93";
       came            adm_came    false    213            �           1259    2392133    IX_Relationship95    INDEX     x   CREATE INDEX "IX_Relationship95" ON came.came_establecimiento_categorizacion_historica USING btree (id_categorizacion);
 %   DROP INDEX came."IX_Relationship95";
       came            adm_came    false    208                       1259    2392348    IX_Relationship97    INDEX     R   CREATE INDEX "IX_Relationship97" ON came.came_sub_modulo USING btree (id_estado);
 %   DROP INDEX came."IX_Relationship97";
       came            adm_came    false    237            �           1259    2392216    IX_Relationship98    INDEX     N   CREATE INDEX "IX_Relationship98" ON came.came_perfil USING btree (id_estado);
 %   DROP INDEX came."IX_Relationship98";
       came            adm_came    false    219                        1259    2392373    IX_Relationship99    INDEX     O   CREATE INDEX "IX_Relationship99" ON came.came_usuario USING btree (id_estado);
 %   DROP INDEX came."IX_Relationship99";
       came            adm_came    false    240            _           2606    2392636    came_supervisor Relationship100    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor
    ADD CONSTRAINT "Relationship100" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);
 I   ALTER TABLE ONLY came.came_supervisor DROP CONSTRAINT "Relationship100";
       came          adm_came    false    3874    240    238            a           2606    2392651 /   came_supervisor_establecimiento Relationship102    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship102" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);
 Y   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship102";
       came          adm_came    false    228    239    3830            U           2606    2392581 #   came_red_sostenedor Relationship105    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "Relationship105" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);
 M   ALTER TABLE ONLY came.came_red_sostenedor DROP CONSTRAINT "Relationship105";
       came          adm_came    false    3830    228    230            O           2606    2392556    came_red Relationship106    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship106" FOREIGN KEY (id_red_tipo) REFERENCES came.came_red_tipo(id_red_tipo);
 B   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "Relationship106";
       came          adm_came    false    231    3841    228            Q           2606    2392576 (   came_red_establecimiento Relationship107    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship107" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);
 R   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "Relationship107";
       came          adm_came    false    3830    229    228            I           2606    2392516     came_perfil_menu Relationship109    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship109" FOREIGN KEY (id_perfil) REFERENCES came.came_perfil(id_perfil);
 J   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "Relationship109";
       came          adm_came    false    220    219    3802            T           2606    2392586 #   came_red_sostenedor Relationship110    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "Relationship110" FOREIGN KEY (id_sostenedor) REFERENCES came.came_sostenedor(id_sostenedor);
 M   ALTER TABLE ONLY came.came_red_sostenedor DROP CONSTRAINT "Relationship110";
       came          adm_came    false    236    230    3858            M           2606    2392546 ?   came_periodo_planificacion_implementacion_apoyo Relationship113    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo
    ADD CONSTRAINT "Relationship113" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 i   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo DROP CONSTRAINT "Relationship113";
       came          adm_came    false    223    227    3813            P           2606    2392551    came_red Relationship114    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship114" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 B   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "Relationship114";
       came          adm_came    false    223    3813    228            S           2606    2392566 (   came_red_establecimiento Relationship115    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship115" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 R   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "Relationship115";
       came          adm_came    false    207    229    3766            5           2606    2392446 $   came_establecimiento Relationship116    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship116" FOREIGN KEY (id_establecimiento_estado) REFERENCES came.came_establecimiento_estado(id_establecimiento_estado);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship116";
       came          adm_came    false    209    3773    207            @           2606    2392471    came_matricula Relationship117    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship117" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 H   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "Relationship117";
       came          adm_came    false    3766    207    213            6           2606    2392441 $   came_establecimiento Relationship118    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship118" FOREIGN KEY (id_director) REFERENCES came.came_director(id_director);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship118";
       came          adm_came    false    204    207    3755            ]           2606    2392631    came_sub_modulo Relationship122    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "Relationship122" FOREIGN KEY (id_modulo) REFERENCES came.came_modulo(id_modulo);
 I   ALTER TABLE ONLY came.came_sub_modulo DROP CONSTRAINT "Relationship122";
       came          adm_came    false    3789    237    215            A           2606    2392486    came_modulo Relationship123    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_modulo
    ADD CONSTRAINT "Relationship123" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 E   ALTER TABLE ONLY came.came_modulo DROP CONSTRAINT "Relationship123";
       came          adm_came    false    3775    215    210            G           2606    2392526     came_perfil_menu Relationship124    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship124" FOREIGN KEY (id_sub_modulo) REFERENCES came.came_sub_modulo(id_sub_modulo);
 J   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "Relationship124";
       came          adm_came    false    3862    237    220            3           2606    2392416 #   came_elemento_lista Relationship126    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_elemento_lista
    ADD CONSTRAINT "Relationship126" FOREIGN KEY (id_lista) REFERENCES came.came_lista(id_lista);
 M   ALTER TABLE ONLY came.came_elemento_lista DROP CONSTRAINT "Relationship126";
       came          adm_came    false    205    212    3779            K           2606    2392536 2   came_periodo_documentos_regionales Relationship127    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_regionales
    ADD CONSTRAINT "Relationship127" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 \   ALTER TABLE ONLY came.came_periodo_documentos_regionales DROP CONSTRAINT "Relationship127";
       came          adm_came    false    3813    223    225            <           2606    2392461 =   came_establecimiento_categorizacion_historica Relationship128    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship128" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 g   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "Relationship128";
       came          adm_came    false    3766    208    207            :           2606    2392421 $   came_establecimiento Relationship129    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship129" FOREIGN KEY (id_categorizacion2) REFERENCES came.came_categorizacion(id_categorizacion);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship129";
       came          adm_came    false    207    3751    202            7           2606    2392436 $   came_establecimiento Relationship130    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship130" FOREIGN KEY (id_clasificacion_sep) REFERENCES came.came_clasificacion_sep(id_clasificacion_sep);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship130";
       came          adm_came    false    207    3753    203            9           2606    2392426 $   came_establecimiento Relationship131    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship131" FOREIGN KEY (id_categorizacion2) REFERENCES came.came_categorizacion(id_categorizacion);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship131";
       came          adm_came    false    202    3751    207            L           2606    2392541 /   came_periodo_conformacion_redes Relationship132    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_conformacion_redes
    ADD CONSTRAINT "Relationship132" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 Y   ALTER TABLE ONLY came.came_periodo_conformacion_redes DROP CONSTRAINT "Relationship132";
       came          adm_came    false    3813    226    223            J           2606    2392531 4   came_periodo_documentos_provinciales Relationship133    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_provinciales
    ADD CONSTRAINT "Relationship133" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 ^   ALTER TABLE ONLY came.came_periodo_documentos_provinciales DROP CONSTRAINT "Relationship133";
       came          adm_came    false    3813    224    223            b           2606    2392646 /   came_supervisor_establecimiento Relationship134    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship134" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 Y   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship134";
       came          adm_came    false    239    3766    207            4           2606    2392451 $   came_establecimiento Relationship135    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship135" FOREIGN KEY (id_sostenedor) REFERENCES came.came_sostenedor(id_sostenedor);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship135";
       came          adm_came    false    3858    207    236            N           2606    2392561    came_red Relationship137    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship137" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);
 B   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "Relationship137";
       came          adm_came    false    228    3874    240            0           2606    2392401 %   came_acciones_mejoras Relationship138    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_acciones_mejoras
    ADD CONSTRAINT "Relationship138" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 O   ALTER TABLE ONLY came.came_acciones_mejoras DROP CONSTRAINT "Relationship138";
       came          adm_came    false    211    3777    198            2           2606    2392411 ;   came_came_estandares_indicativos_desempenio Relationship139    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio
    ADD CONSTRAINT "Relationship139" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 e   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio DROP CONSTRAINT "Relationship139";
       came          adm_came    false    201    3777    211            B           2606    2392491 '   came_movimientos_claves Relationship140    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_movimientos_claves
    ADD CONSTRAINT "Relationship140" FOREIGN KEY (id_acciones_mejoras) REFERENCES came.came_acciones_mejoras(id_acciones_mejoras);
 Q   ALTER TABLE ONLY came.came_movimientos_claves DROP CONSTRAINT "Relationship140";
       came          adm_came    false    3741    216    198            X           2606    2392606     came_sesion_foco Relationship141    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "Relationship141" FOREIGN KEY (id_sesion) REFERENCES came.came_sesion(id_sesion);
 J   ALTER TABLE ONLY came.came_sesion_foco DROP CONSTRAINT "Relationship141";
       came          adm_came    false    232    3845    234            Y           2606    2392601     came_sesion_foco Relationship142    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "Relationship142" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 J   ALTER TABLE ONLY came.came_sesion_foco DROP CONSTRAINT "Relationship142";
       came          adm_came    false    211    234    3777            Z           2606    2392621 .   came_sesion_movimientos_claves Relationship143    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship143" FOREIGN KEY (id_sesion_foco) REFERENCES came.came_sesion_foco(id_sesion_foco);
 X   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "Relationship143";
       came          adm_came    false    234    235    3851            \           2606    2392611 .   came_sesion_movimientos_claves Relationship144    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship144" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 X   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "Relationship144";
       came          adm_came    false    235    211    3777            [           2606    2392616 .   came_sesion_movimientos_claves Relationship145    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship145" FOREIGN KEY (id_movimientos_claves) REFERENCES came.came_movimientos_claves(id_movimientos_claves);
 X   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "Relationship145";
       came          adm_came    false    235    216    3792            D           2606    2392496 $   came_objetivo_mejora Relationship146    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "Relationship146" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 N   ALTER TABLE ONLY came.came_objetivo_mejora DROP CONSTRAINT "Relationship146";
       came          adm_came    false    211    218    3777            C           2606    2392501 $   came_objetivo_mejora Relationship147    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "Relationship147" FOREIGN KEY (id_sesion_foco) REFERENCES came.came_sesion_foco(id_sesion_foco);
 N   ALTER TABLE ONLY came.came_objetivo_mejora DROP CONSTRAINT "Relationship147";
       came          adm_came    false    3851    234    218            e           2606    2392671 #   came_usuario_perfil Relationship148    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "Relationship148" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);
 M   ALTER TABLE ONLY came.came_usuario_perfil DROP CONSTRAINT "Relationship148";
       came          adm_came    false    3874    240    241            f           2606    2392666 #   came_usuario_perfil Relationship149    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "Relationship149" FOREIGN KEY (id_perfil) REFERENCES came.came_perfil(id_perfil);
 M   ALTER TABLE ONLY came.came_usuario_perfil DROP CONSTRAINT "Relationship149";
       came          adm_came    false    3802    219    241            ;           2606    2392466 =   came_establecimiento_categorizacion_historica Relationship151    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship151" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 g   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "Relationship151";
       came          adm_came    false    3766    208    207            H           2606    2392521     came_perfil_menu Relationship152    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship152" FOREIGN KEY (id_perfil_menu_acceso) REFERENCES came.came_perfil_menu_acceso(id_perfil_menu_acceso);
 J   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "Relationship152";
       came          adm_came    false    3809    220    221            R           2606    2392571 (   came_red_establecimiento Relationship154    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship154" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 R   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "Relationship154";
       came          adm_came    false    229    3813    223            g           2606    2392676 4   came_periodo_asignacion_supervisores Relationship155    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores
    ADD CONSTRAINT "Relationship155" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 ^   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores DROP CONSTRAINT "Relationship155";
       came          adm_came    false    3813    223    242            i           2606    2392681 )   came_supervisor_suplencia Relationship158    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_suplencia
    ADD CONSTRAINT "Relationship158" FOREIGN KEY (id_supervisor_ausente) REFERENCES came.came_supervisor(id_supervisor);
 S   ALTER TABLE ONLY came.came_supervisor_suplencia DROP CONSTRAINT "Relationship158";
       came          adm_came    false    3865    244    238            h           2606    2392686 )   came_supervisor_suplencia Relationship159    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_suplencia
    ADD CONSTRAINT "Relationship159" FOREIGN KEY (id_supervisor_suplente) REFERENCES came.came_supervisor(id_supervisor);
 S   ALTER TABLE ONLY came.came_supervisor_suplencia DROP CONSTRAINT "Relationship159";
       came          adm_came    false    3865    238    244            8           2606    2392431 #   came_establecimiento Relationship67    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship67" FOREIGN KEY (id_categorizacion1) REFERENCES came.came_categorizacion(id_categorizacion);
 M   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship67";
       came          adm_came    false    202    3751    207            E           2606    2392511    came_perfil Relationship67    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "Relationship67" FOREIGN KEY (id_perfil_nivel) REFERENCES came.came_perfil_nivel(id_perfil_nivel);
 D   ALTER TABLE ONLY came.came_perfil DROP CONSTRAINT "Relationship67";
       came          adm_came    false    222    219    3811            c           2606    2392641 .   came_supervisor_establecimiento Relationship81    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship81" FOREIGN KEY (id_asignacion_tipo) REFERENCES came.came_asignacion_tipo(id_asignacion_tipo);
 X   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship81";
       came          adm_came    false    3746    200    239            1           2606    2392406    came_asesoria Relationship82    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_asesoria
    ADD CONSTRAINT "Relationship82" FOREIGN KEY (id_supervisor_establecimiento) REFERENCES came.came_supervisor_establecimiento(id_supervisor_establecimiento);
 F   ALTER TABLE ONLY came.came_asesoria DROP CONSTRAINT "Relationship82";
       came          adm_came    false    239    3871    199            W           2606    2392591    came_sesion Relationship83    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "Relationship83" FOREIGN KEY (id_asesoria) REFERENCES came.came_asesoria(id_asesoria);
 D   ALTER TABLE ONLY came.came_sesion DROP CONSTRAINT "Relationship83";
       came          adm_came    false    3744    232    199            `           2606    2392656 .   came_supervisor_establecimiento Relationship84    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship84" FOREIGN KEY (id_supervisor) REFERENCES came.came_supervisor(id_supervisor);
 X   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship84";
       came          adm_came    false    238    239    3865            V           2606    2392596    came_sesion Relationship85    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "Relationship85" FOREIGN KEY (id_session_estado) REFERENCES came.came_sesion_estado(id_sesion_estado);
 D   ALTER TABLE ONLY came.came_sesion DROP CONSTRAINT "Relationship85";
       came          adm_came    false    233    232    3847            ?           2606    2392476    came_matricula Relationship92    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship92" FOREIGN KEY (id_matricula_etnia) REFERENCES came.came_matricula_etnia(id_matricula_etnia);
 G   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "Relationship92";
       came          adm_came    false    213    214    3786            >           2606    2392481    came_matricula Relationship93    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship93" FOREIGN KEY (id_nivel) REFERENCES came.came_nivel(id_nivel);
 G   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "Relationship93";
       came          adm_came    false    3794    217    213            =           2606    2392456 <   came_establecimiento_categorizacion_historica Relationship95    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship95" FOREIGN KEY (id_categorizacion) REFERENCES came.came_categorizacion(id_categorizacion);
 f   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "Relationship95";
       came          adm_came    false    208    202    3751            ^           2606    2392626    came_sub_modulo Relationship97    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "Relationship97" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 H   ALTER TABLE ONLY came.came_sub_modulo DROP CONSTRAINT "Relationship97";
       came          adm_came    false    3775    210    237            F           2606    2392506    came_perfil Relationship98    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "Relationship98" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 D   ALTER TABLE ONLY came.came_perfil DROP CONSTRAINT "Relationship98";
       came          adm_came    false    3775    219    210            d           2606    2392661    came_usuario Relationship99    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_usuario
    ADD CONSTRAINT "Relationship99" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 E   ALTER TABLE ONLY came.came_usuario DROP CONSTRAINT "Relationship99";
       came          adm_came    false    240    3775    210            �      x������ � �      �      x������ � �         B   x��I�0�������#j��?���:� �1Z�ZEAٍQj��X�b<���F�>�o�� �x      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   2  x�}�=N�@Fk�nh����l�P� �IAc#b6r�r���J���m�����!�D�h	2(p��@��m��M�7�j���%H��C<:&��(��5oC���~)� � S�e!Z�Rqs?{���őI���h8�l��橮�ʯJcT�HPKd�PG )X�ј㤊;W04���ЮF\���´�y����]��D�p�O1@������y�}ݵ?*g�U���iJR���(���r�����.������hރv���z����@�H�?�H��#).F�D^ ���ҿ\z��,�/��0      �      x������ � �      �      x������ � �      �      x������ � �      �   5   x�32143�4��0��4661766��HL���,IL��26���CR���� T^�      �      x������ � �      �   k   x�M���0 ��LP��N�]����@��$���S�;#�q6M�(kK��m+��3K64�pն�9��O,��P(���}Z}j�>E��Q�;��ҽ�6�~�u�#�      �      x������ � �      �      x������ � �      �   �   x���M�0��5��h�i�&�4�nLLI)&x'W��	���1n'y�oX��L�2J�,,���
���<�8O�m�iԃ���=�/���h%���`

k.+�Z2���ܞ��=�q�,Q���}�{x��,�鋟O~�jh՛ɐ	I&J�u0P��$ٙj� ��QD���`�"#����������o      �      x������ � �      �      x������ � �      �      x������ � �      �   !  x����J�0�s�y�-�d�Lzً �z�R��XIw��<��b�.��*r���_� ���$e@A%�I�R��R�3Ba��c���i㇐b�c�!���.f�
8�+����!86F�Jw5. B���J�F�����������_�`8&��rb1�O��p��ͺ��z���:No�R� I�$�y��!�P�cZ/�1�<�^].M� 4�px�����O�C�5i������ð��{�T_�L�T+L#��Aŭ2�������������      �   s  x���[n�0E��Ud1���"��,��Gǉۂ&�� �Æ`�\j.�H%	��R�C�B�F&A�fba�_�T41�����B(P� ��w�w�7��ڝ󦬗�_��W�`@G�h¨3�`�N���Ψ� 󁝙�����/�x�4�j͈�l�� ,_au�(vw�}XL�Z�%�Y3��yg��<�4�r#�a��:���~c�A�e*A����ZA=�A��J�PS��l�I#���M��� >*Ȥ�@S�P�K2��)H��m�Wɱ�#��0�P��g��<Ἂ���uFJ0�ft��*cv
b� GjY;ܸyd<�.�T������Lj*X�����(O��s}�����C�u�X��!�T���~o�"�(0wP��#8���@��4=�)�@ʮ1*�G��'�@ʺ'Բ��+11�*���N�e݀����=~�R����ΰ��*��jV�����VՀn�����Y��j"��(�2��<�jk�g:�6�,2k"�����XL;sb��T�n�~�a>��`�3-oK�`Q/HY�Dw���5��P��s�ld;�d�P�vv�v?Ah�%�+�ҙ���M�z��L���'��F�	pvk�OŧA>n�����N      �   A   x�3214571241 �F�Ɯřy
��ɩ��\F�&�e�EX�MA�
�
�)�%�E\1z\\\ ���      �   @   x�32143�0112��0�06277��KL���K��24�,JM�%i�YP�_����	������ 7�O      �   �  x����u�0�Vn�|��Z�Yيό��`	w3Ae�W����pu�Mm�v< ���K�	9�N��6�o�[�Z��Kmc�tY^�?Ndu����S��r�nN��t�f{%��-��9j�B����h\����Cb�5+�ͩS}ٴ�8@�AME��.�8��4q���rH�j�����5�Ƹ��s���X��KL�� ��C�G5yJ-^�-#����L�m�V\��-߮��+L�lKv��%��9ŭ��67���%Y��'�m��b��~���)���`����+|bک�S�}s"%�5eI�.�C!gB�8�WCl�i��8c�R�o=<�ޝY�ls�G�����ɪ������������vD:S�N�r O=��B����g��.��           x��Uّ�@��(H W߇���?�'cs�c�K���=�Z�F�T��\�����bDF��"�!�3�Y�$<��0������e��t]~���糆k�5^����H�����d�)&�8E����XV���Dp�wN�Yz�H�8f�`��7��<���L��)g�=N��IՓFN�h��ձqQ#Ъnٛ�{N�	��Sx�6i�81�(WK��g��RN͑S��K��o>��AG(��53M)�5�\*��b�J'�&+1ܧ�k�8w8�f�I2P����%���o��^�Wr�}��!O�Գ���nRR��:�C�Ӗg���S�	Cٍ�Up	s�CD��g���"E�B�$(Rغ$V,Wש�!�1.���\����$t	�-cQǛr�8"i��7���D����&'Q�a���y�cq`Y����0���o�Ƃ��*�w̪���N|��4	?/���N�/�5�C9�y�$�tK|a^&��j���庽G'���������L���~�b�            x��U�m�`�S� ѽ����	$U	��S'���w��*��3k���I�Q���#�+�U�"<�`��n?O���.Sf�xz�9߿�5\�̍��bJl"Qǜ���3���a;N�&+rͤZ|�T��2�~Ω:KO����a&�:I��b��`L&�|�
�2)Õ�8�M�"�2WN#jZ�-_�	Nω0Y��S�;j<Y81�(WK��g�S���)%� jb�5N(�@����53M�6ʹ�7���9��
�+1��
�p�l�xr�+Ԍ���S��"�Z�Wr���>q�'Z��:�X��r�w��������W���r%�`�c�1���򗈺Pϔc_q�}+B��Y�݉E����A���2���wDH�b+<��Dd��!Xl/"<pE�j$�L�]�.��.� �:p#�������>�
�;"��q4��V�Ǽ�B����Ԡ����I�["�	��ph6q�Y���ኡ�MJH��E��$vq��	��]f���=��W�ݜ��mq����/�Hbt      �   �  x���KjA��=��\�[�ކlC �@0y���!v`�#�b���r;=�dp/�*����K%f�T��&N�lmDF��"���w���ןw_nf�n���������ٯ�G�Q�	kR��Z�G���.q�=GZ=G`�I��+�{�h%�b�n�7G�>R4S�֗�h�νD{��U��,%�8g}�n�7KW����
q	�H'ӎ_Ha;[J�d�v�n�7G�:JoJf|��,�ߥ{:T;�#f2���o���&���.��;1RF��Sl���wY軩�	:��O����o��ζ�C��\-m1w�Y��7'�e�9bx�^�]vt��@CF(/�b�7KW#U#-b��C�P�L(p�w�ؑ������o��hdk�!.�w�yUD��ߞ.�L�䂞�&~�t�h.����9{�v�����M�+'˫��;į+�#�fޢ?0�G�3�����1e��K��@*�ϱ����-1�1X��2����D-̏	��<(!y,S,�Ⱦ� �/�Hr[Ҿٗ��҅��k�3�*P*(�6M;m�)��j��~7��n?��Dڞ�O��H�3�_��S�<|X�$��D16�J��p:�?�����L�|�'5��̖����G��Ħ�(M�d8� �N؝X�'�_�ݼ��}��v����ݟ�߯>�}��� �`o����˅����֠J��-�LgO�K"W�~@c|�RX��'��5��Udw���}����w����x�}�g���{.�L�a�^}l���/�>�      �   �  x���In�f���)|Q5��"ȦOЀa���i����(:����J��^�3U*'s.Q�ҕ�cDF��"��o_WBBk������!�k�)�W�=�����usAl��?//�"N�m�2$Yq�߻����J�@��1��&e1���>��q/����ط����H�j�� ��!V�¨#!���bh�[�Aq kBSdc�zW���5����B�k�h���fbKh��H�rj���Dp��͈�Q�-z+�Ll�s �gUs°����G4�9���n��Ll	-�CS�3�I�6���Us6a��;�&���Zm���f�	m����ǪA��%`�',,���fbhF[�A�3�КEӸ���Є5�+��n����f�����<�9{�&
����ǴZ9YΫ���Tm< w��s`�3��2�B��W�d�i�:(�K3G_ˁf�%��H��Y�#�������C�2�35��b�\]��O
0:�٭�~�=�����������{�q����W@"4�~W�[l^��=��CXm����Pt8��>��2�����T5v�TΊ̌�O�N1FW��zs�\�Ql���$_����^n<N�zQ�	`.�N>��1�
,�p��2���b�Ax `"-�r
��a��P>���ީ��YE�SoR2�٢����#`^&��h��c]���)n���]p�H�m�������           x���ّ�@D��〈����-!�lh�W��eee��L���"Z$Hj�G�Ȩ�TDu�2]ſ�"<k�����{+��S[\攰����}�������4=kJl�Q�`�	�}���8HȢ���)R���Dh��������C�����ab$�ӳ�ib2�3�ϘhSf%���4�8MBV�5�Y�-��s&��>0���)��7&�r������bnQ92�*4%
oce:��DE(��Z��$F(��Pl�Ȗ<�	\��Jx���:A-l�h��8�E��"�����J.��i'�Ĺ���Y�� `�V�j�d��Y?�r%�ՐFv�p�c	s���r�+L̬��B��V���[�I�!���H=����Ԙ{Q�"<�vD��ɥ����������r�T���q�މ�OE�qh�g�E�7��e��u�YU ��t��[�0d�f�؉�w�Xvܝx��&\�#'���"��A��v5~}G_�qC6g��U�m�\. ��bb            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �            x������ � �      	      x������ � �      
      x������ � �         d  x���AN�0еs�� %�;˪	�*�+6&v������g�����C�b�Oc�����i�+Y���
Z�ʦ��Ɇ1.��w�6ES�B�V����&�1�GE3�aR�/���`�j�i�R�@+7Frm¶7�z<z�^�hq����ݛ͇��3*)6h�n����NU��+���:�y5���7���^�$���Z�����8�d����~������t+������t�uetҬ�Sj��O���;΂���s,��� ����y�de��Ȩ'���&�[�Ǚ�"W
+��j����ŗ��c�tn827�^*�)���Oܓ���'dp"��f�@�$��?
A�""�B.�,{�퍇         q   x�}���@��]�%�/�	<��Gd�p� ,<%ryV��T��َJ��5�4�x*m\�_��B���g�RmH����
˸=���B6���f��x��{��j%�            x������ � �            x������ � �         �  x�uR�j�0}� B���)˒���}ʋj���/A�����xכ�B[>g�h<B)��FI���x�(��ÔS��Ⱥ����@��υ+��6֡��S������#|!���Y�+V�0�Fǒ ��7�7�K�6�o�a�PXO|!�2Bh��x�5wg�ې����f��/��o3�'��p�~%�[���21��z��{m��B
{���2C�c= ��v3\n�:���)֩p!a�mӅԲj�����>@�r��G�u��5�qJ�gc��X�t؝�r�Ʒ@!�57��.EW沲��ơ��b���jm��ց5aJU��+-<�h�8�߱��#I��+�b��b�?�Q�Vh��@tze�9N��P�̅�����`{ �����z�����a��-�
�s֪�E��W�#         W  x����m1D�w�p��)�
\F�G������ �5�hH
�0�N�t��Fu�(����i+�@G�#�sH���膡�`�|�E�,x�<����U���=:n��A�uw���A�����v��Ȕ��-W�%�?1p�o���a�)*� ��?������a>X[�]bxU�yQJ�wn����]
Mf����i�K�,Q˹%�L�)��ޥr���n�SlT�c�Uɹ7�y.�5�mP��<�G괚条�xL-�7F�Wq��S��-�P��l�J�O��M��"���a�(jRӨ�?*���{#���3�k����S����u�۹ӽ     