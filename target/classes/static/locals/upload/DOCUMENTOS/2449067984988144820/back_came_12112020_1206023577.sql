PGDMP         ,            
    x            postgres    13.0    13.0    s           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            t           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            u           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            v           1262    13442    postgres    DATABASE     l   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE postgres;
                postgres    false            w           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3446                        2615    16394    came    SCHEMA        CREATE SCHEMA came;
    DROP SCHEMA came;
                postgres    false                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            x           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            �            1255    16395 	   next_id()    FUNCTION     �  CREATE FUNCTION came.next_id(OUT result bigint) RETURNS bigint
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
       came          postgres    false    5            �            1259    16398    came_acciones_mejoras    TABLE     �   CREATE TABLE came.came_acciones_mejoras (
    id_acciones_mejoras bigint NOT NULL,
    id_foco bigint
)
WITH (autovacuum_enabled='true');
 '   DROP TABLE came.came_acciones_mejoras;
       came         heap    postgres    false    5            �            1259    16404    came_asesoria    TABLE     �   CREATE TABLE came.came_asesoria (
    id_asesoria bigint NOT NULL,
    id_supervisor_establecimiento bigint
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_asesoria;
       came         heap    postgres    false    5            �            1259    17051    came_asignacion_maxima    TABLE     �   CREATE TABLE came.came_asignacion_maxima (
    id_asignacion_maxima bigint NOT NULL,
    supervisores integer,
    foco integer,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
 (   DROP TABLE came.came_asignacion_maxima;
       came         heap    postgres    false    5            �            1259    16410    came_asignacion_tipo    TABLE     t   CREATE TABLE came.came_asignacion_tipo (
    id_asignacion_tipo bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 &   DROP TABLE came.came_asignacion_tipo;
       came         heap    postgres    false    5            �            1259    16415 +   came_came_estandares_indicativos_desempenio    TABLE     �   CREATE TABLE came.came_came_estandares_indicativos_desempenio (
    id_estandares_indicativos_desempenio bigint NOT NULL,
    id_foco bigint
)
WITH (autovacuum_enabled='true');
 =   DROP TABLE came.came_came_estandares_indicativos_desempenio;
       came         heap    postgres    false    5            �            1259    16421    came_categorizacion    TABLE     r   CREATE TABLE came.came_categorizacion (
    id_categorizacion bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_categorizacion;
       came         heap    postgres    false    5            �            1259    16426    came_clasificacion_sep    TABLE     x   CREATE TABLE came.came_clasificacion_sep (
    id_clasificacion_sep bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 (   DROP TABLE came.came_clasificacion_sep;
       came         heap    postgres    false    5            �            1259    16431    came_director    TABLE     f   CREATE TABLE came.came_director (
    id_director bigint NOT NULL
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_director;
       came         heap    postgres    false    5            �            1259    16436    came_elemento_lista    TABLE     �   CREATE TABLE came.came_elemento_lista (
    id_elemento_lista bigint NOT NULL,
    id_lista bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_elemento_lista;
       came         heap    postgres    false    5            �            1259    16447    came_establecimiento    TABLE     i  CREATE TABLE came.came_establecimiento (
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
       came         heap    postgres    false    5            �            1259    16458 -   came_establecimiento_categorizacion_historica    TABLE     �   CREATE TABLE came.came_establecimiento_categorizacion_historica (
    id_establecimiento_categorizacion_historica bigint NOT NULL,
    id_categorizacion bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');
 ?   DROP TABLE came.came_establecimiento_categorizacion_historica;
       came         heap    postgres    false    5            �            1259    16466    came_establecimiento_estado    TABLE     �   CREATE TABLE came.came_establecimiento_estado (
    id_establecimiento_estado bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 -   DROP TABLE came.came_establecimiento_estado;
       came         heap    postgres    false    5            �            1259    16445 +   came_establecimiento_id_categorizacion2_seq    SEQUENCE     �   ALTER TABLE came.came_establecimiento ALTER COLUMN id_categorizacion2 ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME came.came_establecimiento_id_categorizacion2_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            came          postgres    false    5    212            �            1259    16471    came_estado    TABLE     �   CREATE TABLE came.came_estado (
    id_estado bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_estado;
       came         heap    postgres    false    5            �            1259    16479 	   came_foco    TABLE     ^   CREATE TABLE came.came_foco (
    id_foco bigint NOT NULL
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_foco;
       came         heap    postgres    false    5            �            1259    16484 
   came_lista    TABLE     ~   CREATE TABLE came.came_lista (
    id_lista bigint NOT NULL,
    nombre character varying
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_lista;
       came         heap    postgres    false    5            �            1259    16492    came_matricula    TABLE     �   CREATE TABLE came.came_matricula (
    id_matricula bigint NOT NULL,
    id_matricula_etnia bigint,
    id_nivel bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');
     DROP TABLE came.came_matricula;
       came         heap    postgres    false    5            �            1259    16500    came_matricula_etnia    TABLE     t   CREATE TABLE came.came_matricula_etnia (
    id_matricula_etnia bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 &   DROP TABLE came.came_matricula_etnia;
       came         heap    postgres    false    5            �            1259    16505    came_modulo    TABLE     �   CREATE TABLE came.came_modulo (
    id_modulo bigint NOT NULL,
    id_estado bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_modulo;
       came         heap    postgres    false    5            �            1259    16514    came_movimientos_claves    TABLE     �   CREATE TABLE came.came_movimientos_claves (
    id_movimientos_claves bigint NOT NULL,
    id_acciones_mejoras bigint
)
WITH (autovacuum_enabled='true');
 )   DROP TABLE came.came_movimientos_claves;
       came         heap    postgres    false    5            �            1259    16520 
   came_nivel    TABLE     `   CREATE TABLE came.came_nivel (
    id_nivel bigint NOT NULL
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_nivel;
       came         heap    postgres    false    5            �            1259    16525    came_objetivo_mejora    TABLE     �   CREATE TABLE came.came_objetivo_mejora (
    id_objetivo_mejora bigint NOT NULL,
    id_foco bigint,
    id_sesion_foco bigint
)
WITH (autovacuum_enabled='true');
 &   DROP TABLE came.came_objetivo_mejora;
       came         heap    postgres    false    5            �            1259    16532    came_perfil    TABLE     a  CREATE TABLE came.came_perfil (
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
       came         heap    postgres    false    5            �            1259    16542    came_perfil_menu    TABLE     (  CREATE TABLE came.came_perfil_menu (
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
       came         heap    postgres    false    5            �            1259    16550    came_perfil_menu_acceso    TABLE     �   CREATE TABLE came.came_perfil_menu_acceso (
    id_perfil_menu_acceso bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');
 )   DROP TABLE came.came_perfil_menu_acceso;
       came         heap    postgres    false    5            �            1259    16558    came_perfil_nivel    TABLE     �   CREATE TABLE came.came_perfil_nivel (
    id_perfil_nivel bigint NOT NULL,
    nombre character varying NOT NULL
)
WITH (autovacuum_enabled='true');
 #   DROP TABLE came.came_perfil_nivel;
       came         heap    postgres    false    5            �            1259    16566    came_periodo    TABLE     �   CREATE TABLE came.came_periodo (
    id_periodo bigint NOT NULL,
    anio bigint,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_periodo;
       came         heap    postgres    false    5            �            1259    17024 $   came_periodo_asignacion_supervisores    TABLE     T  CREATE TABLE came.came_periodo_asignacion_supervisores (
    id_periodo_asignacion_supervisores bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
 6   DROP TABLE came.came_periodo_asignacion_supervisores;
       came         heap    postgres    false    5            �            1259    17018    came_periodo_conformacion_redes    TABLE     J  CREATE TABLE came.came_periodo_conformacion_redes (
    id_periodo_conformacion_redes bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
 1   DROP TABLE came.came_periodo_conformacion_redes;
       came         heap    postgres    false    5            �            1259    17006 $   came_periodo_documentos_provinciales    TABLE       CREATE TABLE came.came_periodo_documentos_provinciales (
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
 6   DROP TABLE came.came_periodo_documentos_provinciales;
       came         heap    postgres    false    5            �            1259    16577 "   came_periodo_documentos_regionales    TABLE     �  CREATE TABLE came.came_periodo_documentos_regionales (
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
 4   DROP TABLE came.came_periodo_documentos_regionales;
       came         heap    postgres    false    5            �            1259    16583 2   came_periodo_organizacion_planificacion_provincial    TABLE     �   CREATE TABLE came.came_periodo_organizacion_planificacion_provincial (
    id_periodo_organizacion_planificacion_provincial bigint NOT NULL,
    id_periodo bigint,
    id_deprov bigint
)
WITH (autovacuum_enabled='true');
 D   DROP TABLE came.came_periodo_organizacion_planificacion_provincial;
       came         heap    postgres    false    5            �            1259    16589 /   came_periodo_planificacion_implementacion_apoyo    TABLE     j  CREATE TABLE came.came_periodo_planificacion_implementacion_apoyo (
    id_periodo_planificacion_implementacion_apoyo bigint NOT NULL,
    id_periodo bigint,
    fecha_inicio timestamp without time zone,
    fecha_fin timestamp without time zone,
    id_usuario_registro bigint,
    fecha_registro timestamp without time zone
)
WITH (autovacuum_enabled='true');
 A   DROP TABLE came.came_periodo_planificacion_implementacion_apoyo;
       came         heap    postgres    false    5            �            1259    16595    came_red    TABLE     �   CREATE TABLE came.came_red (
    id_red bigint NOT NULL,
    id_red_tipo bigint,
    id_periodo bigint,
    id_usuario bigint,
    id_deprov bigint
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_red;
       came         heap    postgres    false    5            �            1259    16603    came_red_establecimiento    TABLE     �   CREATE TABLE came.came_red_establecimiento (
    id_red_establecimiento bigint NOT NULL,
    id_red bigint,
    id_establecimiento bigint,
    id_periodo bigint
)
WITH (autovacuum_enabled='true');
 *   DROP TABLE came.came_red_establecimiento;
       came         heap    postgres    false    5            �            1259    16611    came_red_sostenedor    TABLE     �   CREATE TABLE came.came_red_sostenedor (
    id_red_sostenedor bigint NOT NULL,
    id_red bigint,
    id_sostenedor bigint
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_red_sostenedor;
       came         heap    postgres    false    5            �            1259    16618    came_red_tipo    TABLE     �   CREATE TABLE came.came_red_tipo (
    id_red_tipo bigint NOT NULL,
    nombre character varying
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_red_tipo;
       came         heap    postgres    false    5            �            1259    16626    came_sesion    TABLE     �   CREATE TABLE came.came_sesion (
    id_sesion bigint NOT NULL,
    id_asesoria bigint,
    id_session_estado bigint
)
WITH (autovacuum_enabled='true');
    DROP TABLE came.came_sesion;
       came         heap    postgres    false    5            �            1259    16633    came_sesion_estado    TABLE     p   CREATE TABLE came.came_sesion_estado (
    id_sesion_estado bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 $   DROP TABLE came.came_sesion_estado;
       came         heap    postgres    false    5            �            1259    16638    came_sesion_foco    TABLE     �   CREATE TABLE came.came_sesion_foco (
    id_sesion_foco bigint NOT NULL,
    id_sesion bigint,
    id_foco bigint
)
WITH (autovacuum_enabled='true');
 "   DROP TABLE came.came_sesion_foco;
       came         heap    postgres    false    5            �            1259    16645    came_sesion_movimientos_claves    TABLE     �   CREATE TABLE came.came_sesion_movimientos_claves (
    id_sesion_movimientos_claves bigint NOT NULL,
    id_sesion_foco bigint,
    id_foco bigint,
    id_movimientos_claves bigint
)
WITH (autovacuum_enabled='true');
 0   DROP TABLE came.came_sesion_movimientos_claves;
       came         heap    postgres    false    5            �            1259    16653    came_sostenedor    TABLE     j   CREATE TABLE came.came_sostenedor (
    id_sostenedor bigint NOT NULL
)
WITH (autovacuum_enabled='true');
 !   DROP TABLE came.came_sostenedor;
       came         heap    postgres    false    5            �            1259    16658    came_sub_modulo    TABLE     �   CREATE TABLE came.came_sub_modulo (
    id_sub_modulo bigint NOT NULL,
    id_estado bigint,
    id_modulo bigint,
    nombre character varying,
    status boolean
)
WITH (autovacuum_enabled='true');
 !   DROP TABLE came.came_sub_modulo;
       came         heap    postgres    false    5            �            1259    16668    came_supervisor    TABLE     �   CREATE TABLE came.came_supervisor (
    id_supervisor bigint NOT NULL,
    id_usuario bigint,
    id_deprov bigint,
    start_date date,
    end_date date,
    status boolean
)
WITH (autovacuum_enabled='true');
 !   DROP TABLE came.came_supervisor;
       came         heap    postgres    false    5            �            1259    16674    came_supervisor_establecimiento    TABLE     �   CREATE TABLE came.came_supervisor_establecimiento (
    id_supervisor_establecimiento bigint NOT NULL,
    id_asignacion_tipo bigint,
    id_supervisor bigint,
    id_red bigint,
    id_establecimiento bigint
)
WITH (autovacuum_enabled='true');
 1   DROP TABLE came.came_supervisor_establecimiento;
       came         heap    postgres    false    5            �            1259    17060    came_supervisor_suplencia    TABLE     i  CREATE TABLE came.came_supervisor_suplencia (
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
       came         heap    postgres    false    5            �            1259    16683    came_usuario    TABLE     v  CREATE TABLE came.came_usuario (
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
       came         heap    postgres    false    5            �            1259    16692    came_usuario_perfil    TABLE     	  CREATE TABLE came.came_usuario_perfil (
    id_usuario_perfil bigint NOT NULL,
    id_usuario bigint,
    id_perfil bigint,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    status boolean
)
WITH (autovacuum_enabled='true');
 %   DROP TABLE came.came_usuario_perfil;
       came         heap    postgres    false    5            �            1259    16396    secuencia_id    SEQUENCE     s   CREATE SEQUENCE came.secuencia_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE came.secuencia_id;
       came          postgres    false    5            A          0    16398    came_acciones_mejoras 
   TABLE DATA           K   COPY came.came_acciones_mejoras (id_acciones_mejoras, id_foco) FROM stdin;
    came          postgres    false    203   �q      B          0    16404    came_asesoria 
   TABLE DATA           Q   COPY came.came_asesoria (id_asesoria, id_supervisor_establecimiento) FROM stdin;
    came          postgres    false    204   �q      o          0    17051    came_asignacion_maxima 
   TABLE DATA           }   COPY came.came_asignacion_maxima (id_asignacion_maxima, supervisores, foco, id_usuario_registro, fecha_registro) FROM stdin;
    came          postgres    false    249   �q      C          0    16410    came_asignacion_tipo 
   TABLE DATA           @   COPY came.came_asignacion_tipo (id_asignacion_tipo) FROM stdin;
    came          postgres    false    205   -r      D          0    16415 +   came_came_estandares_indicativos_desempenio 
   TABLE DATA           r   COPY came.came_came_estandares_indicativos_desempenio (id_estandares_indicativos_desempenio, id_foco) FROM stdin;
    came          postgres    false    206   Jr      E          0    16421    came_categorizacion 
   TABLE DATA           >   COPY came.came_categorizacion (id_categorizacion) FROM stdin;
    came          postgres    false    207   gr      F          0    16426    came_clasificacion_sep 
   TABLE DATA           D   COPY came.came_clasificacion_sep (id_clasificacion_sep) FROM stdin;
    came          postgres    false    208   �r      G          0    16431    came_director 
   TABLE DATA           2   COPY came.came_director (id_director) FROM stdin;
    came          postgres    false    209   �r      H          0    16436    came_elemento_lista 
   TABLE DATA           X   COPY came.came_elemento_lista (id_elemento_lista, id_lista, nombre, status) FROM stdin;
    came          postgres    false    210   �r      J          0    16447    came_establecimiento 
   TABLE DATA           �   COPY came.came_establecimiento (id_establecimiento, id_categorizacion1, id_establecimiento_estado, id_director, id_categorizacion2, id_clasificacion_sep, id_sostenedor, id_deprov, id_comuna) FROM stdin;
    came          postgres    false    212   5s      K          0    16458 -   came_establecimiento_categorizacion_historica 
   TABLE DATA           �   COPY came.came_establecimiento_categorizacion_historica (id_establecimiento_categorizacion_historica, id_categorizacion, id_establecimiento) FROM stdin;
    came          postgres    false    213   Rs      L          0    16466    came_establecimiento_estado 
   TABLE DATA           N   COPY came.came_establecimiento_estado (id_establecimiento_estado) FROM stdin;
    came          postgres    false    214   os      M          0    16471    came_estado 
   TABLE DATA           6   COPY came.came_estado (id_estado, nombre) FROM stdin;
    came          postgres    false    215   �s      N          0    16479 	   came_foco 
   TABLE DATA           *   COPY came.came_foco (id_foco) FROM stdin;
    came          postgres    false    216   �s      O          0    16484 
   came_lista 
   TABLE DATA           4   COPY came.came_lista (id_lista, nombre) FROM stdin;
    came          postgres    false    217   �s      P          0    16492    came_matricula 
   TABLE DATA           f   COPY came.came_matricula (id_matricula, id_matricula_etnia, id_nivel, id_establecimiento) FROM stdin;
    came          postgres    false    218   it      Q          0    16500    came_matricula_etnia 
   TABLE DATA           @   COPY came.came_matricula_etnia (id_matricula_etnia) FROM stdin;
    came          postgres    false    219   �t      R          0    16505    came_modulo 
   TABLE DATA           I   COPY came.came_modulo (id_modulo, id_estado, nombre, status) FROM stdin;
    came          postgres    false    220   �t      S          0    16514    came_movimientos_claves 
   TABLE DATA           [   COPY came.came_movimientos_claves (id_movimientos_claves, id_acciones_mejoras) FROM stdin;
    came          postgres    false    221   Vu      T          0    16520 
   came_nivel 
   TABLE DATA           ,   COPY came.came_nivel (id_nivel) FROM stdin;
    came          postgres    false    222   su      U          0    16525    came_objetivo_mejora 
   TABLE DATA           Y   COPY came.came_objetivo_mejora (id_objetivo_mejora, id_foco, id_sesion_foco) FROM stdin;
    came          postgres    false    223   �u      V          0    16532    came_perfil 
   TABLE DATA           �   COPY came.came_perfil (id_perfil, id_estado, id_perfil_nivel, nombre, descripcion, habilitado, ultima_modificacion, id_usuario_modificacion) FROM stdin;
    came          postgres    false    224   �u      W          0    16542    came_perfil_menu 
   TABLE DATA           �   COPY came.came_perfil_menu (id_perfil_menu, id_perfil, id_sub_modulo, id_perfil_menu_acceso, start_date, end_date, status) FROM stdin;
    came          postgres    false    225   �v      X          0    16550    came_perfil_menu_acceso 
   TABLE DATA           N   COPY came.came_perfil_menu_acceso (id_perfil_menu_acceso, nombre) FROM stdin;
    came          postgres    false    226   {      Y          0    16558    came_perfil_nivel 
   TABLE DATA           B   COPY came.came_perfil_nivel (id_perfil_nivel, nombre) FROM stdin;
    came          postgres    false    227   T{      Z          0    16566    came_periodo 
   TABLE DATA           [   COPY came.came_periodo (id_periodo, anio, id_usuario_registro, fecha_registro) FROM stdin;
    came          postgres    false    228   �{      n          0    17024 $   came_periodo_asignacion_supervisores 
   TABLE DATA           �   COPY came.came_periodo_asignacion_supervisores (id_periodo_asignacion_supervisores, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
    came          postgres    false    248   �{      m          0    17018    came_periodo_conformacion_redes 
   TABLE DATA           �   COPY came.came_periodo_conformacion_redes (id_periodo_conformacion_redes, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
    came          postgres    false    247   �{      l          0    17006 $   came_periodo_documentos_provinciales 
   TABLE DATA           �   COPY came.came_periodo_documentos_provinciales (periodo_documentos_provinciales, id_periodo, id_region, id_deprov, visible, es_caso_especial, template_path, fecha_registro, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro1) FROM stdin;
    came          postgres    false    246   �{      [          0    16577 "   came_periodo_documentos_regionales 
   TABLE DATA           �   COPY came.came_periodo_documentos_regionales (id_periodo_documentos_regionales, id_periodo, id_region, fecha_inicio, fecha_fin, visible, es_caso_especial, template_path, id_usuario_registro, fecha_registro) FROM stdin;
    came          postgres    false    229   |      \          0    16583 2   came_periodo_organizacion_planificacion_provincial 
   TABLE DATA           �   COPY came.came_periodo_organizacion_planificacion_provincial (id_periodo_organizacion_planificacion_provincial, id_periodo, id_deprov) FROM stdin;
    came          postgres    false    230   5|      ]          0    16589 /   came_periodo_planificacion_implementacion_apoyo 
   TABLE DATA           �   COPY came.came_periodo_planificacion_implementacion_apoyo (id_periodo_planificacion_implementacion_apoyo, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
    came          postgres    false    231   R|      ^          0    16595    came_red 
   TABLE DATA           X   COPY came.came_red (id_red, id_red_tipo, id_periodo, id_usuario, id_deprov) FROM stdin;
    came          postgres    false    232   o|      _          0    16603    came_red_establecimiento 
   TABLE DATA           p   COPY came.came_red_establecimiento (id_red_establecimiento, id_red, id_establecimiento, id_periodo) FROM stdin;
    came          postgres    false    233   �|      `          0    16611    came_red_sostenedor 
   TABLE DATA           U   COPY came.came_red_sostenedor (id_red_sostenedor, id_red, id_sostenedor) FROM stdin;
    came          postgres    false    234   �|      a          0    16618    came_red_tipo 
   TABLE DATA           :   COPY came.came_red_tipo (id_red_tipo, nombre) FROM stdin;
    came          postgres    false    235   �|      b          0    16626    came_sesion 
   TABLE DATA           N   COPY came.came_sesion (id_sesion, id_asesoria, id_session_estado) FROM stdin;
    came          postgres    false    236   �|      c          0    16633    came_sesion_estado 
   TABLE DATA           <   COPY came.came_sesion_estado (id_sesion_estado) FROM stdin;
    came          postgres    false    237    }      d          0    16638    came_sesion_foco 
   TABLE DATA           L   COPY came.came_sesion_foco (id_sesion_foco, id_sesion, id_foco) FROM stdin;
    came          postgres    false    238   }      e          0    16645    came_sesion_movimientos_claves 
   TABLE DATA           �   COPY came.came_sesion_movimientos_claves (id_sesion_movimientos_claves, id_sesion_foco, id_foco, id_movimientos_claves) FROM stdin;
    came          postgres    false    239   :}      f          0    16653    came_sostenedor 
   TABLE DATA           6   COPY came.came_sostenedor (id_sostenedor) FROM stdin;
    came          postgres    false    240   W}      g          0    16658    came_sub_modulo 
   TABLE DATA           \   COPY came.came_sub_modulo (id_sub_modulo, id_estado, id_modulo, nombre, status) FROM stdin;
    came          postgres    false    241   t}      h          0    16668    came_supervisor 
   TABLE DATA           k   COPY came.came_supervisor (id_supervisor, id_usuario, id_deprov, start_date, end_date, status) FROM stdin;
    came          postgres    false    242   �~      i          0    16674    came_supervisor_establecimiento 
   TABLE DATA           �   COPY came.came_supervisor_establecimiento (id_supervisor_establecimiento, id_asignacion_tipo, id_supervisor, id_red, id_establecimiento) FROM stdin;
    came          postgres    false    243   4      p          0    17060    came_supervisor_suplencia 
   TABLE DATA           �   COPY came.came_supervisor_suplencia (id_supervisor_suplencia, id_supervisor_ausente, id_supervisor_suplente, start_date, end_date, fecha_registro, id_usuario_registro) FROM stdin;
    came          postgres    false    250   Q      j          0    16683    came_usuario 
   TABLE DATA           	  COPY came.came_usuario (id_usuario, id_estado, id_region, id_deprov, usuario_dominio, run, nombre, apellido_paterno, apellido_materno, email, habilitado, reintentos, ultima_modificacion, id_usuario_modificacion, dv, fecha_registro, id_usuario_registro) FROM stdin;
    came          postgres    false    244   n      k          0    16692    came_usuario_perfil 
   TABLE DATA           s   COPY came.came_usuario_perfil (id_usuario_perfil, id_usuario, id_perfil, start_date, end_date, status) FROM stdin;
    came          postgres    false    245   �      y           0    0 +   came_establecimiento_id_categorizacion2_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('came.came_establecimiento_id_categorizacion2_seq', 1, false);
          came          postgres    false    211            z           0    0    secuencia_id    SEQUENCE SET     9   SELECT pg_catalog.setval('came.secuencia_id', 71, true);
          came          postgres    false    202            �           2606    16403 .   came_acciones_mejoras PK_came_acciones_mejoras 
   CONSTRAINT     }   ALTER TABLE ONLY came.came_acciones_mejoras
    ADD CONSTRAINT "PK_came_acciones_mejoras" PRIMARY KEY (id_acciones_mejoras);
 X   ALTER TABLE ONLY came.came_acciones_mejoras DROP CONSTRAINT "PK_came_acciones_mejoras";
       came            postgres    false    203            �           2606    16409    came_asesoria PK_came_asesoria 
   CONSTRAINT     e   ALTER TABLE ONLY came.came_asesoria
    ADD CONSTRAINT "PK_came_asesoria" PRIMARY KEY (id_asesoria);
 H   ALTER TABLE ONLY came.came_asesoria DROP CONSTRAINT "PK_came_asesoria";
       came            postgres    false    204            ~           2606    17055 0   came_asignacion_maxima PK_came_asignacion_maxima 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_asignacion_maxima
    ADD CONSTRAINT "PK_came_asignacion_maxima" PRIMARY KEY (id_asignacion_maxima);
 Z   ALTER TABLE ONLY came.came_asignacion_maxima DROP CONSTRAINT "PK_came_asignacion_maxima";
       came            postgres    false    249            �           2606    16414 ,   came_asignacion_tipo PK_came_asignacion_tipo 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_asignacion_tipo
    ADD CONSTRAINT "PK_came_asignacion_tipo" PRIMARY KEY (id_asignacion_tipo);
 V   ALTER TABLE ONLY came.came_asignacion_tipo DROP CONSTRAINT "PK_came_asignacion_tipo";
       came            postgres    false    205            �           2606    16420 Z   came_came_estandares_indicativos_desempenio PK_came_came_estandares_indicativos_desempenio 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio
    ADD CONSTRAINT "PK_came_came_estandares_indicativos_desempenio" PRIMARY KEY (id_estandares_indicativos_desempenio);
 �   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio DROP CONSTRAINT "PK_came_came_estandares_indicativos_desempenio";
       came            postgres    false    206            �           2606    16425 *   came_categorizacion PK_came_categorizacion 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_categorizacion
    ADD CONSTRAINT "PK_came_categorizacion" PRIMARY KEY (id_categorizacion);
 T   ALTER TABLE ONLY came.came_categorizacion DROP CONSTRAINT "PK_came_categorizacion";
       came            postgres    false    207            �           2606    16430 0   came_clasificacion_sep PK_came_clasificacion_sep 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_clasificacion_sep
    ADD CONSTRAINT "PK_came_clasificacion_sep" PRIMARY KEY (id_clasificacion_sep);
 Z   ALTER TABLE ONLY came.came_clasificacion_sep DROP CONSTRAINT "PK_came_clasificacion_sep";
       came            postgres    false    208            �           2606    16435    came_director PK_came_director 
   CONSTRAINT     e   ALTER TABLE ONLY came.came_director
    ADD CONSTRAINT "PK_came_director" PRIMARY KEY (id_director);
 H   ALTER TABLE ONLY came.came_director DROP CONSTRAINT "PK_came_director";
       came            postgres    false    209            �           2606    16444 *   came_elemento_lista PK_came_elemento_lista 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_elemento_lista
    ADD CONSTRAINT "PK_came_elemento_lista" PRIMARY KEY (id_elemento_lista);
 T   ALTER TABLE ONLY came.came_elemento_lista DROP CONSTRAINT "PK_came_elemento_lista";
       came            postgres    false    210                       2606    16457 ,   came_establecimiento PK_came_establecimiento 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "PK_came_establecimiento" PRIMARY KEY (id_establecimiento);
 V   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "PK_came_establecimiento";
       came            postgres    false    212                       2606    16465 ^   came_establecimiento_categorizacion_historica PK_came_establecimiento_categorizacion_historica 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "PK_came_establecimiento_categorizacion_historica" PRIMARY KEY (id_establecimiento_categorizacion_historica);
 �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "PK_came_establecimiento_categorizacion_historica";
       came            postgres    false    213                       2606    16470 :   came_establecimiento_estado PK_came_establecimiento_estado 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_estado
    ADD CONSTRAINT "PK_came_establecimiento_estado" PRIMARY KEY (id_establecimiento_estado);
 d   ALTER TABLE ONLY came.came_establecimiento_estado DROP CONSTRAINT "PK_came_establecimiento_estado";
       came            postgres    false    214                       2606    16478    came_estado PK_came_estado 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_estado
    ADD CONSTRAINT "PK_came_estado" PRIMARY KEY (id_estado);
 D   ALTER TABLE ONLY came.came_estado DROP CONSTRAINT "PK_came_estado";
       came            postgres    false    215                       2606    16483    came_foco PK_came_foco 
   CONSTRAINT     Y   ALTER TABLE ONLY came.came_foco
    ADD CONSTRAINT "PK_came_foco" PRIMARY KEY (id_foco);
 @   ALTER TABLE ONLY came.came_foco DROP CONSTRAINT "PK_came_foco";
       came            postgres    false    216                       2606    16491    came_lista PK_came_lista 
   CONSTRAINT     \   ALTER TABLE ONLY came.came_lista
    ADD CONSTRAINT "PK_came_lista" PRIMARY KEY (id_lista);
 B   ALTER TABLE ONLY came.came_lista DROP CONSTRAINT "PK_came_lista";
       came            postgres    false    217                       2606    16499     came_matricula PK_came_matricula 
   CONSTRAINT     h   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "PK_came_matricula" PRIMARY KEY (id_matricula);
 J   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "PK_came_matricula";
       came            postgres    false    218                       2606    16504 ,   came_matricula_etnia PK_came_matricula_etnia 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_matricula_etnia
    ADD CONSTRAINT "PK_came_matricula_etnia" PRIMARY KEY (id_matricula_etnia);
 V   ALTER TABLE ONLY came.came_matricula_etnia DROP CONSTRAINT "PK_came_matricula_etnia";
       came            postgres    false    219                       2606    16513    came_modulo PK_came_modulo 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_modulo
    ADD CONSTRAINT "PK_came_modulo" PRIMARY KEY (id_modulo);
 D   ALTER TABLE ONLY came.came_modulo DROP CONSTRAINT "PK_came_modulo";
       came            postgres    false    220                        2606    16519 2   came_movimientos_claves PK_came_movimientos_claves 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_movimientos_claves
    ADD CONSTRAINT "PK_came_movimientos_claves" PRIMARY KEY (id_movimientos_claves);
 \   ALTER TABLE ONLY came.came_movimientos_claves DROP CONSTRAINT "PK_came_movimientos_claves";
       came            postgres    false    221            "           2606    16524    came_nivel PK_came_nivel 
   CONSTRAINT     \   ALTER TABLE ONLY came.came_nivel
    ADD CONSTRAINT "PK_came_nivel" PRIMARY KEY (id_nivel);
 B   ALTER TABLE ONLY came.came_nivel DROP CONSTRAINT "PK_came_nivel";
       came            postgres    false    222            &           2606    16531 ,   came_objetivo_mejora PK_came_objetivo_mejora 
   CONSTRAINT     z   ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "PK_came_objetivo_mejora" PRIMARY KEY (id_objetivo_mejora);
 V   ALTER TABLE ONLY came.came_objetivo_mejora DROP CONSTRAINT "PK_came_objetivo_mejora";
       came            postgres    false    223            *           2606    16541    came_perfil PK_came_perfil 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "PK_came_perfil" PRIMARY KEY (id_perfil);
 D   ALTER TABLE ONLY came.came_perfil DROP CONSTRAINT "PK_came_perfil";
       came            postgres    false    224            /           2606    16549 $   came_perfil_menu PK_came_perfil_menu 
   CONSTRAINT     n   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "PK_came_perfil_menu" PRIMARY KEY (id_perfil_menu);
 N   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "PK_came_perfil_menu";
       came            postgres    false    225            1           2606    16557 2   came_perfil_menu_acceso PK_came_perfil_menu_acceso 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu_acceso
    ADD CONSTRAINT "PK_came_perfil_menu_acceso" PRIMARY KEY (id_perfil_menu_acceso);
 \   ALTER TABLE ONLY came.came_perfil_menu_acceso DROP CONSTRAINT "PK_came_perfil_menu_acceso";
       came            postgres    false    226            3           2606    16565 &   came_perfil_nivel PK_came_perfil_nivel 
   CONSTRAINT     q   ALTER TABLE ONLY came.came_perfil_nivel
    ADD CONSTRAINT "PK_came_perfil_nivel" PRIMARY KEY (id_perfil_nivel);
 P   ALTER TABLE ONLY came.came_perfil_nivel DROP CONSTRAINT "PK_came_perfil_nivel";
       came            postgres    false    227            5           2606    16570    came_periodo PK_came_periodo 
   CONSTRAINT     b   ALTER TABLE ONLY came.came_periodo
    ADD CONSTRAINT "PK_came_periodo" PRIMARY KEY (id_periodo);
 F   ALTER TABLE ONLY came.came_periodo DROP CONSTRAINT "PK_came_periodo";
       came            postgres    false    228            |           2606    17029 L   came_periodo_asignacion_supervisores PK_came_periodo_asignacion_supervisores 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores
    ADD CONSTRAINT "PK_came_periodo_asignacion_supervisores" PRIMARY KEY (id_periodo_asignacion_supervisores);
 v   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores DROP CONSTRAINT "PK_came_periodo_asignacion_supervisores";
       came            postgres    false    248            v           2606    17014 L   came_periodo_documentos_provinciales PK_came_periodo_documentos_provinciales 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_provinciales
    ADD CONSTRAINT "PK_came_periodo_documentos_provinciales" PRIMARY KEY (periodo_documentos_provinciales);
 v   ALTER TABLE ONLY came.came_periodo_documentos_provinciales DROP CONSTRAINT "PK_came_periodo_documentos_provinciales";
       came            postgres    false    246            8           2606    16582 H   came_periodo_documentos_regionales PK_came_periodo_documentos_regionales 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_regionales
    ADD CONSTRAINT "PK_came_periodo_documentos_regionales" PRIMARY KEY (id_periodo_documentos_regionales);
 r   ALTER TABLE ONLY came.came_periodo_documentos_regionales DROP CONSTRAINT "PK_came_periodo_documentos_regionales";
       came            postgres    false    229            ;           2606    16588 h   came_periodo_organizacion_planificacion_provincial PK_came_periodo_organizacion_planificacion_provincial 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_organizacion_planificacion_provincial
    ADD CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial" PRIMARY KEY (id_periodo_organizacion_planificacion_provincial);
 �   ALTER TABLE ONLY came.came_periodo_organizacion_planificacion_provincial DROP CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial";
       came            postgres    false    230            y           2606    17023 Z   came_periodo_conformacion_redes PK_came_periodo_organizacion_planificacion_provincial_test 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_conformacion_redes
    ADD CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial_test" PRIMARY KEY (id_periodo_conformacion_redes);
 �   ALTER TABLE ONLY came.came_periodo_conformacion_redes DROP CONSTRAINT "PK_came_periodo_organizacion_planificacion_provincial_test";
       came            postgres    false    247            >           2606    16594 b   came_periodo_planificacion_implementacion_apoyo PK_came_periodo_planificacion_implementacion_apoyo 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo
    ADD CONSTRAINT "PK_came_periodo_planificacion_implementacion_apoyo" PRIMARY KEY (id_periodo_planificacion_implementacion_apoyo);
 �   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo DROP CONSTRAINT "PK_came_periodo_planificacion_implementacion_apoyo";
       came            postgres    false    231            C           2606    16602    came_red PK_came_red 
   CONSTRAINT     V   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "PK_came_red" PRIMARY KEY (id_red);
 >   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "PK_came_red";
       came            postgres    false    232            H           2606    16610 4   came_red_establecimiento PK_came_red_establecimiento 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "PK_came_red_establecimiento" PRIMARY KEY (id_red_establecimiento);
 ^   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "PK_came_red_establecimiento";
       came            postgres    false    233            L           2606    16617 *   came_red_sostenedor PK_came_red_sostenedor 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "PK_came_red_sostenedor" PRIMARY KEY (id_red_sostenedor);
 T   ALTER TABLE ONLY came.came_red_sostenedor DROP CONSTRAINT "PK_came_red_sostenedor";
       came            postgres    false    234            N           2606    16625    came_red_tipo PK_came_red_tipo 
   CONSTRAINT     e   ALTER TABLE ONLY came.came_red_tipo
    ADD CONSTRAINT "PK_came_red_tipo" PRIMARY KEY (id_red_tipo);
 H   ALTER TABLE ONLY came.came_red_tipo DROP CONSTRAINT "PK_came_red_tipo";
       came            postgres    false    235            R           2606    16632    came_sesion PK_came_sesion 
   CONSTRAINT     _   ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "PK_came_sesion" PRIMARY KEY (id_sesion);
 D   ALTER TABLE ONLY came.came_sesion DROP CONSTRAINT "PK_came_sesion";
       came            postgres    false    236            T           2606    16637 (   came_sesion_estado PK_came_sesion_estado 
   CONSTRAINT     t   ALTER TABLE ONLY came.came_sesion_estado
    ADD CONSTRAINT "PK_came_sesion_estado" PRIMARY KEY (id_sesion_estado);
 R   ALTER TABLE ONLY came.came_sesion_estado DROP CONSTRAINT "PK_came_sesion_estado";
       came            postgres    false    237            X           2606    16644 $   came_sesion_foco PK_came_sesion_foco 
   CONSTRAINT     n   ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "PK_came_sesion_foco" PRIMARY KEY (id_sesion_foco);
 N   ALTER TABLE ONLY came.came_sesion_foco DROP CONSTRAINT "PK_came_sesion_foco";
       came            postgres    false    238            ]           2606    16652 @   came_sesion_movimientos_claves PK_came_sesion_movimientos_claves 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "PK_came_sesion_movimientos_claves" PRIMARY KEY (id_sesion_movimientos_claves);
 j   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "PK_came_sesion_movimientos_claves";
       came            postgres    false    239            _           2606    16657 "   came_sostenedor PK_came_sostenedor 
   CONSTRAINT     k   ALTER TABLE ONLY came.came_sostenedor
    ADD CONSTRAINT "PK_came_sostenedor" PRIMARY KEY (id_sostenedor);
 L   ALTER TABLE ONLY came.came_sostenedor DROP CONSTRAINT "PK_came_sostenedor";
       came            postgres    false    240            c           2606    16667 "   came_sub_modulo PK_came_sub_modulo 
   CONSTRAINT     k   ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "PK_came_sub_modulo" PRIMARY KEY (id_sub_modulo);
 L   ALTER TABLE ONLY came.came_sub_modulo DROP CONSTRAINT "PK_came_sub_modulo";
       came            postgres    false    241            f           2606    16673 "   came_supervisor PK_came_supervisor 
   CONSTRAINT     k   ALTER TABLE ONLY came.came_supervisor
    ADD CONSTRAINT "PK_came_supervisor" PRIMARY KEY (id_supervisor);
 L   ALTER TABLE ONLY came.came_supervisor DROP CONSTRAINT "PK_came_supervisor";
       came            postgres    false    242            l           2606    16682 B   came_supervisor_establecimiento PK_came_supervisor_establecimiento 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "PK_came_supervisor_establecimiento" PRIMARY KEY (id_supervisor_establecimiento);
 l   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "PK_came_supervisor_establecimiento";
       came            postgres    false    243            �           2606    17066 6   came_supervisor_suplencia PK_came_supervisor_suplencia 
   CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_suplencia
    ADD CONSTRAINT "PK_came_supervisor_suplencia" PRIMARY KEY (id_supervisor_suplencia);
 `   ALTER TABLE ONLY came.came_supervisor_suplencia DROP CONSTRAINT "PK_came_supervisor_suplencia";
       came            postgres    false    250            o           2606    16691    came_usuario PK_came_usuario 
   CONSTRAINT     b   ALTER TABLE ONLY came.came_usuario
    ADD CONSTRAINT "PK_came_usuario" PRIMARY KEY (id_usuario);
 F   ALTER TABLE ONLY came.came_usuario DROP CONSTRAINT "PK_came_usuario";
       came            postgres    false    244            s           2606    16698 *   came_usuario_perfil PK_came_usuario_perfil 
   CONSTRAINT     w   ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "PK_came_usuario_perfil" PRIMARY KEY (id_usuario_perfil);
 T   ALTER TABLE ONLY came.came_usuario_perfil DROP CONSTRAINT "PK_came_usuario_perfil";
       came            postgres    false    245            d           1259    16671    IX_Relationship100    INDEX     T   CREATE INDEX "IX_Relationship100" ON came.came_supervisor USING btree (id_usuario);
 &   DROP INDEX came."IX_Relationship100";
       came            postgres    false    242            g           1259    16679    IX_Relationship102    INDEX     `   CREATE INDEX "IX_Relationship102" ON came.came_supervisor_establecimiento USING btree (id_red);
 &   DROP INDEX came."IX_Relationship102";
       came            postgres    false    243            I           1259    16614    IX_Relationship105    INDEX     T   CREATE INDEX "IX_Relationship105" ON came.came_red_sostenedor USING btree (id_red);
 &   DROP INDEX came."IX_Relationship105";
       came            postgres    false    234            ?           1259    16598    IX_Relationship106    INDEX     N   CREATE INDEX "IX_Relationship106" ON came.came_red USING btree (id_red_tipo);
 &   DROP INDEX came."IX_Relationship106";
       came            postgres    false    232            D           1259    16606    IX_Relationship107    INDEX     Y   CREATE INDEX "IX_Relationship107" ON came.came_red_establecimiento USING btree (id_red);
 &   DROP INDEX came."IX_Relationship107";
       came            postgres    false    233            +           1259    16545    IX_Relationship109    INDEX     T   CREATE INDEX "IX_Relationship109" ON came.came_perfil_menu USING btree (id_perfil);
 &   DROP INDEX came."IX_Relationship109";
       came            postgres    false    225            J           1259    16615    IX_Relationship110    INDEX     [   CREATE INDEX "IX_Relationship110" ON came.came_red_sostenedor USING btree (id_sostenedor);
 &   DROP INDEX came."IX_Relationship110";
       came            postgres    false    234            <           1259    16592    IX_Relationship113    INDEX     t   CREATE INDEX "IX_Relationship113" ON came.came_periodo_planificacion_implementacion_apoyo USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship113";
       came            postgres    false    231            @           1259    16599    IX_Relationship114    INDEX     M   CREATE INDEX "IX_Relationship114" ON came.came_red USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship114";
       came            postgres    false    232            E           1259    16607    IX_Relationship115    INDEX     e   CREATE INDEX "IX_Relationship115" ON came.came_red_establecimiento USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship115";
       came            postgres    false    233            �           1259    16450    IX_Relationship116    INDEX     h   CREATE INDEX "IX_Relationship116" ON came.came_establecimiento USING btree (id_establecimiento_estado);
 &   DROP INDEX came."IX_Relationship116";
       came            postgres    false    212                       1259    16497    IX_Relationship117    INDEX     [   CREATE INDEX "IX_Relationship117" ON came.came_matricula USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship117";
       came            postgres    false    218                        1259    16451    IX_Relationship118    INDEX     Z   CREATE INDEX "IX_Relationship118" ON came.came_establecimiento USING btree (id_director);
 &   DROP INDEX came."IX_Relationship118";
       came            postgres    false    212            `           1259    16665    IX_Relationship122    INDEX     S   CREATE INDEX "IX_Relationship122" ON came.came_sub_modulo USING btree (id_modulo);
 &   DROP INDEX came."IX_Relationship122";
       came            postgres    false    241                       1259    16511    IX_Relationship123    INDEX     O   CREATE INDEX "IX_Relationship123" ON came.came_modulo USING btree (id_estado);
 &   DROP INDEX came."IX_Relationship123";
       came            postgres    false    220            ,           1259    16546    IX_Relationship124    INDEX     X   CREATE INDEX "IX_Relationship124" ON came.came_perfil_menu USING btree (id_sub_modulo);
 &   DROP INDEX came."IX_Relationship124";
       came            postgres    false    225            �           1259    16442    IX_Relationship126    INDEX     V   CREATE INDEX "IX_Relationship126" ON came.came_elemento_lista USING btree (id_lista);
 &   DROP INDEX came."IX_Relationship126";
       came            postgres    false    210            6           1259    16580    IX_Relationship127    INDEX     g   CREATE INDEX "IX_Relationship127" ON came.came_periodo_documentos_regionales USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship127";
       came            postgres    false    229                       1259    16462    IX_Relationship128    INDEX     z   CREATE INDEX "IX_Relationship128" ON came.came_establecimiento_categorizacion_historica USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship128";
       came            postgres    false    213                       1259    16452    IX_Relationship129    INDEX     a   CREATE INDEX "IX_Relationship129" ON came.came_establecimiento USING btree (id_categorizacion2);
 &   DROP INDEX came."IX_Relationship129";
       came            postgres    false    212                       1259    16453    IX_Relationship130    INDEX     c   CREATE INDEX "IX_Relationship130" ON came.came_establecimiento USING btree (id_clasificacion_sep);
 &   DROP INDEX came."IX_Relationship130";
       came            postgres    false    212                       1259    16454    IX_Relationship131    INDEX     a   CREATE INDEX "IX_Relationship131" ON came.came_establecimiento USING btree (id_categorizacion2);
 &   DROP INDEX came."IX_Relationship131";
       came            postgres    false    212            9           1259    16586    IX_Relationship132    INDEX     w   CREATE INDEX "IX_Relationship132" ON came.came_periodo_organizacion_planificacion_provincial USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship132";
       came            postgres    false    230            t           1259    17012    IX_Relationship133    INDEX     i   CREATE INDEX "IX_Relationship133" ON came.came_periodo_documentos_provinciales USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship133";
       came            postgres    false    246            h           1259    16680    IX_Relationship134    INDEX     l   CREATE INDEX "IX_Relationship134" ON came.came_supervisor_establecimiento USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship134";
       came            postgres    false    243                       1259    16455    IX_Relationship135    INDEX     \   CREATE INDEX "IX_Relationship135" ON came.came_establecimiento USING btree (id_sostenedor);
 &   DROP INDEX came."IX_Relationship135";
       came            postgres    false    212            A           1259    16600    IX_Relationship137    INDEX     M   CREATE INDEX "IX_Relationship137" ON came.came_red USING btree (id_usuario);
 &   DROP INDEX came."IX_Relationship137";
       came            postgres    false    232            �           1259    16401    IX_Relationship138    INDEX     W   CREATE INDEX "IX_Relationship138" ON came.came_acciones_mejoras USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship138";
       came            postgres    false    203            �           1259    16418    IX_Relationship139    INDEX     m   CREATE INDEX "IX_Relationship139" ON came.came_came_estandares_indicativos_desempenio USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship139";
       came            postgres    false    206                       1259    16517    IX_Relationship140    INDEX     e   CREATE INDEX "IX_Relationship140" ON came.came_movimientos_claves USING btree (id_acciones_mejoras);
 &   DROP INDEX came."IX_Relationship140";
       came            postgres    false    221            U           1259    16641    IX_Relationship141    INDEX     T   CREATE INDEX "IX_Relationship141" ON came.came_sesion_foco USING btree (id_sesion);
 &   DROP INDEX came."IX_Relationship141";
       came            postgres    false    238            V           1259    16642    IX_Relationship142    INDEX     R   CREATE INDEX "IX_Relationship142" ON came.came_sesion_foco USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship142";
       came            postgres    false    238            Y           1259    16648    IX_Relationship143    INDEX     g   CREATE INDEX "IX_Relationship143" ON came.came_sesion_movimientos_claves USING btree (id_sesion_foco);
 &   DROP INDEX came."IX_Relationship143";
       came            postgres    false    239            Z           1259    16649    IX_Relationship144    INDEX     `   CREATE INDEX "IX_Relationship144" ON came.came_sesion_movimientos_claves USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship144";
       came            postgres    false    239            [           1259    16650    IX_Relationship145    INDEX     n   CREATE INDEX "IX_Relationship145" ON came.came_sesion_movimientos_claves USING btree (id_movimientos_claves);
 &   DROP INDEX came."IX_Relationship145";
       came            postgres    false    239            #           1259    16528    IX_Relationship146    INDEX     V   CREATE INDEX "IX_Relationship146" ON came.came_objetivo_mejora USING btree (id_foco);
 &   DROP INDEX came."IX_Relationship146";
       came            postgres    false    223            $           1259    16529    IX_Relationship147    INDEX     ]   CREATE INDEX "IX_Relationship147" ON came.came_objetivo_mejora USING btree (id_sesion_foco);
 &   DROP INDEX came."IX_Relationship147";
       came            postgres    false    223            p           1259    16695    IX_Relationship148    INDEX     X   CREATE INDEX "IX_Relationship148" ON came.came_usuario_perfil USING btree (id_usuario);
 &   DROP INDEX came."IX_Relationship148";
       came            postgres    false    245            q           1259    16696    IX_Relationship149    INDEX     W   CREATE INDEX "IX_Relationship149" ON came.came_usuario_perfil USING btree (id_perfil);
 &   DROP INDEX came."IX_Relationship149";
       came            postgres    false    245                       1259    16463    IX_Relationship151    INDEX     z   CREATE INDEX "IX_Relationship151" ON came.came_establecimiento_categorizacion_historica USING btree (id_establecimiento);
 &   DROP INDEX came."IX_Relationship151";
       came            postgres    false    213            -           1259    16547    IX_Relationship152    INDEX     `   CREATE INDEX "IX_Relationship152" ON came.came_perfil_menu USING btree (id_perfil_menu_acceso);
 &   DROP INDEX came."IX_Relationship152";
       came            postgres    false    225            F           1259    16608    IX_Relationship154    INDEX     ]   CREATE INDEX "IX_Relationship154" ON came.came_red_establecimiento USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship154";
       came            postgres    false    233            z           1259    17027    IX_Relationship155    INDEX     i   CREATE INDEX "IX_Relationship155" ON came.came_periodo_asignacion_supervisores USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship155";
       came            postgres    false    248                       1259    17063    IX_Relationship158    INDEX     i   CREATE INDEX "IX_Relationship158" ON came.came_supervisor_suplencia USING btree (id_supervisor_ausente);
 &   DROP INDEX came."IX_Relationship158";
       came            postgres    false    250            �           1259    17064    IX_Relationship159    INDEX     j   CREATE INDEX "IX_Relationship159" ON came.came_supervisor_suplencia USING btree (id_supervisor_suplente);
 &   DROP INDEX came."IX_Relationship159";
       came            postgres    false    250            w           1259    17021    IX_Relationship232    INDEX     d   CREATE INDEX "IX_Relationship232" ON came.came_periodo_conformacion_redes USING btree (id_periodo);
 &   DROP INDEX came."IX_Relationship232";
       came            postgres    false    247            '           1259    16539    IX_Relationship67    INDEX     T   CREATE INDEX "IX_Relationship67" ON came.came_perfil USING btree (id_perfil_nivel);
 %   DROP INDEX came."IX_Relationship67";
       came            postgres    false    224            i           1259    16677    IX_Relationship81    INDEX     k   CREATE INDEX "IX_Relationship81" ON came.came_supervisor_establecimiento USING btree (id_asignacion_tipo);
 %   DROP INDEX came."IX_Relationship81";
       came            postgres    false    243            �           1259    16407    IX_Relationship82    INDEX     d   CREATE INDEX "IX_Relationship82" ON came.came_asesoria USING btree (id_supervisor_establecimiento);
 %   DROP INDEX came."IX_Relationship82";
       came            postgres    false    204            O           1259    16629    IX_Relationship83    INDEX     P   CREATE INDEX "IX_Relationship83" ON came.came_sesion USING btree (id_asesoria);
 %   DROP INDEX came."IX_Relationship83";
       came            postgres    false    236            j           1259    16678    IX_Relationship84    INDEX     f   CREATE INDEX "IX_Relationship84" ON came.came_supervisor_establecimiento USING btree (id_supervisor);
 %   DROP INDEX came."IX_Relationship84";
       came            postgres    false    243            P           1259    16630    IX_Relationship85    INDEX     V   CREATE INDEX "IX_Relationship85" ON came.came_sesion USING btree (id_session_estado);
 %   DROP INDEX came."IX_Relationship85";
       came            postgres    false    236                       1259    16495    IX_Relationship92    INDEX     Z   CREATE INDEX "IX_Relationship92" ON came.came_matricula USING btree (id_matricula_etnia);
 %   DROP INDEX came."IX_Relationship92";
       came            postgres    false    218                       1259    16496    IX_Relationship93    INDEX     P   CREATE INDEX "IX_Relationship93" ON came.came_matricula USING btree (id_nivel);
 %   DROP INDEX came."IX_Relationship93";
       came            postgres    false    218            	           1259    16461    IX_Relationship95    INDEX     x   CREATE INDEX "IX_Relationship95" ON came.came_establecimiento_categorizacion_historica USING btree (id_categorizacion);
 %   DROP INDEX came."IX_Relationship95";
       came            postgres    false    213            a           1259    16664    IX_Relationship97    INDEX     R   CREATE INDEX "IX_Relationship97" ON came.came_sub_modulo USING btree (id_estado);
 %   DROP INDEX came."IX_Relationship97";
       came            postgres    false    241            (           1259    16538    IX_Relationship98    INDEX     N   CREATE INDEX "IX_Relationship98" ON came.came_perfil USING btree (id_estado);
 %   DROP INDEX came."IX_Relationship98";
       came            postgres    false    224            m           1259    16689    IX_Relationship99    INDEX     O   CREATE INDEX "IX_Relationship99" ON came.came_usuario USING btree (id_estado);
 %   DROP INDEX came."IX_Relationship99";
       came            postgres    false    244            �           2606    16934    came_supervisor Relationship100    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor
    ADD CONSTRAINT "Relationship100" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);
 I   ALTER TABLE ONLY came.came_supervisor DROP CONSTRAINT "Relationship100";
       came          postgres    false    3183    242    244            �           2606    16949 /   came_supervisor_establecimiento Relationship102    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship102" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);
 Y   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship102";
       came          postgres    false    3139    232    243            �           2606    16879 #   came_red_sostenedor Relationship105    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "Relationship105" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);
 M   ALTER TABLE ONLY came.came_red_sostenedor DROP CONSTRAINT "Relationship105";
       came          postgres    false    234    232    3139            �           2606    16854    came_red Relationship106    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship106" FOREIGN KEY (id_red_tipo) REFERENCES came.came_red_tipo(id_red_tipo);
 B   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "Relationship106";
       came          postgres    false    3150    232    235            �           2606    16874 (   came_red_establecimiento Relationship107    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship107" FOREIGN KEY (id_red) REFERENCES came.came_red(id_red);
 R   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "Relationship107";
       came          postgres    false    232    233    3139            �           2606    16814     came_perfil_menu Relationship109    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship109" FOREIGN KEY (id_perfil) REFERENCES came.came_perfil(id_perfil);
 J   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "Relationship109";
       came          postgres    false    224    225    3114            �           2606    16884 #   came_red_sostenedor Relationship110    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_sostenedor
    ADD CONSTRAINT "Relationship110" FOREIGN KEY (id_sostenedor) REFERENCES came.came_sostenedor(id_sostenedor);
 M   ALTER TABLE ONLY came.came_red_sostenedor DROP CONSTRAINT "Relationship110";
       came          postgres    false    240    3167    234            �           2606    16844 ?   came_periodo_planificacion_implementacion_apoyo Relationship113    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo
    ADD CONSTRAINT "Relationship113" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 i   ALTER TABLE ONLY came.came_periodo_planificacion_implementacion_apoyo DROP CONSTRAINT "Relationship113";
       came          postgres    false    3125    228    231            �           2606    16849    came_red Relationship114    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship114" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 B   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "Relationship114";
       came          postgres    false    232    228    3125            �           2606    16864 (   came_red_establecimiento Relationship115    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship115" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 R   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "Relationship115";
       came          postgres    false    212    3078    233            �           2606    16744 $   came_establecimiento Relationship116    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship116" FOREIGN KEY (id_establecimiento_estado) REFERENCES came.came_establecimiento_estado(id_establecimiento_estado);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship116";
       came          postgres    false    3085    212    214            �           2606    16769    came_matricula Relationship117    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship117" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 H   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "Relationship117";
       came          postgres    false    3078    218    212            �           2606    16739 $   came_establecimiento Relationship118    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship118" FOREIGN KEY (id_director) REFERENCES came.came_director(id_director);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship118";
       came          postgres    false    212    3067    209            �           2606    16929    came_sub_modulo Relationship122    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "Relationship122" FOREIGN KEY (id_modulo) REFERENCES came.came_modulo(id_modulo);
 I   ALTER TABLE ONLY came.came_sub_modulo DROP CONSTRAINT "Relationship122";
       came          postgres    false    220    241    3101            �           2606    16784    came_modulo Relationship123    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_modulo
    ADD CONSTRAINT "Relationship123" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 E   ALTER TABLE ONLY came.came_modulo DROP CONSTRAINT "Relationship123";
       came          postgres    false    215    220    3087            �           2606    16824     came_perfil_menu Relationship124    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship124" FOREIGN KEY (id_sub_modulo) REFERENCES came.came_sub_modulo(id_sub_modulo);
 J   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "Relationship124";
       came          postgres    false    225    241    3171            �           2606    16714 #   came_elemento_lista Relationship126    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_elemento_lista
    ADD CONSTRAINT "Relationship126" FOREIGN KEY (id_lista) REFERENCES came.came_lista(id_lista);
 M   ALTER TABLE ONLY came.came_elemento_lista DROP CONSTRAINT "Relationship126";
       came          postgres    false    210    3091    217            �           2606    16834 2   came_periodo_documentos_regionales Relationship127    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_regionales
    ADD CONSTRAINT "Relationship127" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 \   ALTER TABLE ONLY came.came_periodo_documentos_regionales DROP CONSTRAINT "Relationship127";
       came          postgres    false    229    228    3125            �           2606    16759 =   came_establecimiento_categorizacion_historica Relationship128    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship128" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 g   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "Relationship128";
       came          postgres    false    3078    212    213            �           2606    16719 $   came_establecimiento Relationship129    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship129" FOREIGN KEY (id_categorizacion2) REFERENCES came.came_categorizacion(id_categorizacion);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship129";
       came          postgres    false    3063    207    212            �           2606    16734 $   came_establecimiento Relationship130    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship130" FOREIGN KEY (id_clasificacion_sep) REFERENCES came.came_clasificacion_sep(id_clasificacion_sep);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship130";
       came          postgres    false    212    3065    208            �           2606    16724 $   came_establecimiento Relationship131    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship131" FOREIGN KEY (id_categorizacion2) REFERENCES came.came_categorizacion(id_categorizacion);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship131";
       came          postgres    false    3063    212    207            �           2606    16839 B   came_periodo_organizacion_planificacion_provincial Relationship132    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_organizacion_planificacion_provincial
    ADD CONSTRAINT "Relationship132" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 l   ALTER TABLE ONLY came.came_periodo_organizacion_planificacion_provincial DROP CONSTRAINT "Relationship132";
       came          postgres    false    230    228    3125            �           2606    17035 /   came_periodo_conformacion_redes Relationship132    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_conformacion_redes
    ADD CONSTRAINT "Relationship132" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 Y   ALTER TABLE ONLY came.came_periodo_conformacion_redes DROP CONSTRAINT "Relationship132";
       came          postgres    false    228    247    3125            �           2606    17030 4   came_periodo_documentos_provinciales Relationship133    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_documentos_provinciales
    ADD CONSTRAINT "Relationship133" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 ^   ALTER TABLE ONLY came.came_periodo_documentos_provinciales DROP CONSTRAINT "Relationship133";
       came          postgres    false    3125    228    246            �           2606    16944 /   came_supervisor_establecimiento Relationship134    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship134" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 Y   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship134";
       came          postgres    false    243    3078    212            �           2606    16749 $   came_establecimiento Relationship135    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship135" FOREIGN KEY (id_sostenedor) REFERENCES came.came_sostenedor(id_sostenedor);
 N   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship135";
       came          postgres    false    3167    212    240            �           2606    16859    came_red Relationship137    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red
    ADD CONSTRAINT "Relationship137" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);
 B   ALTER TABLE ONLY came.came_red DROP CONSTRAINT "Relationship137";
       came          postgres    false    3183    244    232            �           2606    16699 %   came_acciones_mejoras Relationship138    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_acciones_mejoras
    ADD CONSTRAINT "Relationship138" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 O   ALTER TABLE ONLY came.came_acciones_mejoras DROP CONSTRAINT "Relationship138";
       came          postgres    false    216    203    3089            �           2606    16709 ;   came_came_estandares_indicativos_desempenio Relationship139    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio
    ADD CONSTRAINT "Relationship139" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 e   ALTER TABLE ONLY came.came_came_estandares_indicativos_desempenio DROP CONSTRAINT "Relationship139";
       came          postgres    false    216    3089    206            �           2606    16789 '   came_movimientos_claves Relationship140    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_movimientos_claves
    ADD CONSTRAINT "Relationship140" FOREIGN KEY (id_acciones_mejoras) REFERENCES came.came_acciones_mejoras(id_acciones_mejoras);
 Q   ALTER TABLE ONLY came.came_movimientos_claves DROP CONSTRAINT "Relationship140";
       came          postgres    false    221    3053    203            �           2606    16904     came_sesion_foco Relationship141    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "Relationship141" FOREIGN KEY (id_sesion) REFERENCES came.came_sesion(id_sesion);
 J   ALTER TABLE ONLY came.came_sesion_foco DROP CONSTRAINT "Relationship141";
       came          postgres    false    3154    236    238            �           2606    16899     came_sesion_foco Relationship142    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_foco
    ADD CONSTRAINT "Relationship142" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 J   ALTER TABLE ONLY came.came_sesion_foco DROP CONSTRAINT "Relationship142";
       came          postgres    false    238    216    3089            �           2606    16919 .   came_sesion_movimientos_claves Relationship143    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship143" FOREIGN KEY (id_sesion_foco) REFERENCES came.came_sesion_foco(id_sesion_foco);
 X   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "Relationship143";
       came          postgres    false    239    238    3160            �           2606    16909 .   came_sesion_movimientos_claves Relationship144    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship144" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 X   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "Relationship144";
       came          postgres    false    239    3089    216            �           2606    16914 .   came_sesion_movimientos_claves Relationship145    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion_movimientos_claves
    ADD CONSTRAINT "Relationship145" FOREIGN KEY (id_movimientos_claves) REFERENCES came.came_movimientos_claves(id_movimientos_claves);
 X   ALTER TABLE ONLY came.came_sesion_movimientos_claves DROP CONSTRAINT "Relationship145";
       came          postgres    false    3104    221    239            �           2606    16794 $   came_objetivo_mejora Relationship146    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "Relationship146" FOREIGN KEY (id_foco) REFERENCES came.came_foco(id_foco);
 N   ALTER TABLE ONLY came.came_objetivo_mejora DROP CONSTRAINT "Relationship146";
       came          postgres    false    3089    216    223            �           2606    16799 $   came_objetivo_mejora Relationship147    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_objetivo_mejora
    ADD CONSTRAINT "Relationship147" FOREIGN KEY (id_sesion_foco) REFERENCES came.came_sesion_foco(id_sesion_foco);
 N   ALTER TABLE ONLY came.came_objetivo_mejora DROP CONSTRAINT "Relationship147";
       came          postgres    false    223    3160    238            �           2606    16969 #   came_usuario_perfil Relationship148    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "Relationship148" FOREIGN KEY (id_usuario) REFERENCES came.came_usuario(id_usuario);
 M   ALTER TABLE ONLY came.came_usuario_perfil DROP CONSTRAINT "Relationship148";
       came          postgres    false    244    245    3183            �           2606    16964 #   came_usuario_perfil Relationship149    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_usuario_perfil
    ADD CONSTRAINT "Relationship149" FOREIGN KEY (id_perfil) REFERENCES came.came_perfil(id_perfil);
 M   ALTER TABLE ONLY came.came_usuario_perfil DROP CONSTRAINT "Relationship149";
       came          postgres    false    224    3114    245            �           2606    16764 =   came_establecimiento_categorizacion_historica Relationship151    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship151" FOREIGN KEY (id_establecimiento) REFERENCES came.came_establecimiento(id_establecimiento);
 g   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "Relationship151";
       came          postgres    false    212    3078    213            �           2606    16819     came_perfil_menu Relationship152    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil_menu
    ADD CONSTRAINT "Relationship152" FOREIGN KEY (id_perfil_menu_acceso) REFERENCES came.came_perfil_menu_acceso(id_perfil_menu_acceso);
 J   ALTER TABLE ONLY came.came_perfil_menu DROP CONSTRAINT "Relationship152";
       came          postgres    false    3121    226    225            �           2606    16869 (   came_red_establecimiento Relationship154    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_red_establecimiento
    ADD CONSTRAINT "Relationship154" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 R   ALTER TABLE ONLY came.came_red_establecimiento DROP CONSTRAINT "Relationship154";
       came          postgres    false    233    228    3125            �           2606    17040 4   came_periodo_asignacion_supervisores Relationship155    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores
    ADD CONSTRAINT "Relationship155" FOREIGN KEY (id_periodo) REFERENCES came.came_periodo(id_periodo);
 ^   ALTER TABLE ONLY came.came_periodo_asignacion_supervisores DROP CONSTRAINT "Relationship155";
       came          postgres    false    3125    248    228            �           2606    17067 )   came_supervisor_suplencia Relationship158    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_suplencia
    ADD CONSTRAINT "Relationship158" FOREIGN KEY (id_supervisor_ausente) REFERENCES came.came_supervisor(id_supervisor);
 S   ALTER TABLE ONLY came.came_supervisor_suplencia DROP CONSTRAINT "Relationship158";
       came          postgres    false    250    242    3174            �           2606    17072 )   came_supervisor_suplencia Relationship159    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_suplencia
    ADD CONSTRAINT "Relationship159" FOREIGN KEY (id_supervisor_suplente) REFERENCES came.came_supervisor(id_supervisor);
 S   ALTER TABLE ONLY came.came_supervisor_suplencia DROP CONSTRAINT "Relationship159";
       came          postgres    false    3174    250    242            �           2606    16729 #   came_establecimiento Relationship67    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento
    ADD CONSTRAINT "Relationship67" FOREIGN KEY (id_categorizacion1) REFERENCES came.came_categorizacion(id_categorizacion);
 M   ALTER TABLE ONLY came.came_establecimiento DROP CONSTRAINT "Relationship67";
       came          postgres    false    207    212    3063            �           2606    16809    came_perfil Relationship67    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "Relationship67" FOREIGN KEY (id_perfil_nivel) REFERENCES came.came_perfil_nivel(id_perfil_nivel);
 D   ALTER TABLE ONLY came.came_perfil DROP CONSTRAINT "Relationship67";
       came          postgres    false    3123    224    227            �           2606    16939 .   came_supervisor_establecimiento Relationship81    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship81" FOREIGN KEY (id_asignacion_tipo) REFERENCES came.came_asignacion_tipo(id_asignacion_tipo);
 X   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship81";
       came          postgres    false    243    3058    205            �           2606    16704    came_asesoria Relationship82    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_asesoria
    ADD CONSTRAINT "Relationship82" FOREIGN KEY (id_supervisor_establecimiento) REFERENCES came.came_supervisor_establecimiento(id_supervisor_establecimiento);
 F   ALTER TABLE ONLY came.came_asesoria DROP CONSTRAINT "Relationship82";
       came          postgres    false    204    3180    243            �           2606    16889    came_sesion Relationship83    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "Relationship83" FOREIGN KEY (id_asesoria) REFERENCES came.came_asesoria(id_asesoria);
 D   ALTER TABLE ONLY came.came_sesion DROP CONSTRAINT "Relationship83";
       came          postgres    false    3056    236    204            �           2606    16954 .   came_supervisor_establecimiento Relationship84    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_supervisor_establecimiento
    ADD CONSTRAINT "Relationship84" FOREIGN KEY (id_supervisor) REFERENCES came.came_supervisor(id_supervisor);
 X   ALTER TABLE ONLY came.came_supervisor_establecimiento DROP CONSTRAINT "Relationship84";
       came          postgres    false    243    3174    242            �           2606    16894    came_sesion Relationship85    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sesion
    ADD CONSTRAINT "Relationship85" FOREIGN KEY (id_session_estado) REFERENCES came.came_sesion_estado(id_sesion_estado);
 D   ALTER TABLE ONLY came.came_sesion DROP CONSTRAINT "Relationship85";
       came          postgres    false    237    236    3156            �           2606    16774    came_matricula Relationship92    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship92" FOREIGN KEY (id_matricula_etnia) REFERENCES came.came_matricula_etnia(id_matricula_etnia);
 G   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "Relationship92";
       came          postgres    false    218    3098    219            �           2606    16779    came_matricula Relationship93    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_matricula
    ADD CONSTRAINT "Relationship93" FOREIGN KEY (id_nivel) REFERENCES came.came_nivel(id_nivel);
 G   ALTER TABLE ONLY came.came_matricula DROP CONSTRAINT "Relationship93";
       came          postgres    false    222    218    3106            �           2606    16754 <   came_establecimiento_categorizacion_historica Relationship95    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica
    ADD CONSTRAINT "Relationship95" FOREIGN KEY (id_categorizacion) REFERENCES came.came_categorizacion(id_categorizacion);
 f   ALTER TABLE ONLY came.came_establecimiento_categorizacion_historica DROP CONSTRAINT "Relationship95";
       came          postgres    false    207    3063    213            �           2606    16924    came_sub_modulo Relationship97    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_sub_modulo
    ADD CONSTRAINT "Relationship97" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 H   ALTER TABLE ONLY came.came_sub_modulo DROP CONSTRAINT "Relationship97";
       came          postgres    false    215    3087    241            �           2606    16804    came_perfil Relationship98    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_perfil
    ADD CONSTRAINT "Relationship98" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 D   ALTER TABLE ONLY came.came_perfil DROP CONSTRAINT "Relationship98";
       came          postgres    false    215    3087    224            �           2606    16959    came_usuario Relationship99    FK CONSTRAINT     �   ALTER TABLE ONLY came.came_usuario
    ADD CONSTRAINT "Relationship99" FOREIGN KEY (id_estado) REFERENCES came.came_estado(id_estado);
 E   ALTER TABLE ONLY came.came_usuario DROP CONSTRAINT "Relationship99";
       came          postgres    false    215    3087    244            A      x������ � �      B      x������ � �      o   A   x��Q�0�oP1�KR`P-�cw�3�:1U$կ�)�`%�-��a��x�sO��o����g      C      x������ � �      D      x������ � �      E      x������ � �      F      x������ � �      G      x������ � �      H   g   x�m�1�@D�z�� ��} nA�-�R��e���GSGA�eF5*C4=2P^�����sk{�)�CER�� ~)���O�Th��%��d��c� �P#/      J      x������ � �      K      x������ � �      L      x������ � �      M   5   x�32143�4��0��4661766��HL���,IL��26���CR���� T^�      N      x������ � �      O   k   x�M���0 ��LP��N�]����@��$���S�;#�q6M�(kK��m+��3K64�pն�9��O,��P(��}Z}j�>E��Q�;��ҽ�6�~�u�#�      P      x������ � �      Q      x������ � �      R   �   x�u�1�0й9J��IF$Y�YB�`D���b���������Л�A3:�=|n�{냵��v趗�25�1Q�+��ֈ����+��E���4cc�����x�	3�%�����wT1qE�EI�;�4�-���TF��(/��p�a���i��R��Yj�      S      x������ � �      T      x������ � �      U      x������ � �      V   8  x���Mj�0���)t���o�])�
��c)A�ʍ��g�G��*'B~@��g��zσ4jIX#IT�a���D�J!�B=��$N���k��m�W��g��Mo�!G>�qɀW
*(��!L����1 H�x%��桩�em۞�����w�	�d"��۶�>D���pۜ K%c=��cֱy�Su��?�5�ND����H;��������ￛ���B�Dk�I���~��>�~Ӎ�r��@4��� �)�J��m�庝�c�zp�3�Y6N?g�X����i\�Ws� C^q�@������7H�      W   �  x���ۑ#+��g��֥���8l���m�tc��qU��O��������Z�/42	j��q}��aH���?SB�� W�" �����~F;	������|�A�9JaZ�)� CЈ�'W�Y��5�P�s�����0�,�ɀZ����.)R��O`�n�߫��0�Ń3��0�����0�ü�`��NƟ��]��Y]A`��R���p� A��L�3�+�3�C�#��d#WY9�u�K�;e������L�r�L�AW+��6�#���,F��:#.�jsa�s`�"�H�&��-#���,��
�#����0!ct��;�1��di��E�Q��X�UԾa.^ܲ�F=��nfj�W�V��l�y��NEX=�w�o9�*�a��ϼa.���ӊeV��c��Ԑ"�.���2�)�$�rԨg����!`#�J�Q���LGX��>1Vg����K?Κڸng/�66��z�j7{C@"���[d~Y�n�eG��֧�F���ߞ#����qJ��Dв��|���VήsAjs�@!yi°q,�0~]##��wX��f&-�{]#�S=�e&!jˬ�����̤\W:eߣFi<�Xa����=n��ݚg�^�eM��qV�8Ny"�XN�h�پ�om��M yn�4�^T�E�n͚O"�m�ې�on�D�&��H8'#0��T�;�����n"HZw)T�����F�ȓ�_�eV���U��I���N�v�m"ȑ�1�VM�:�l��cK��<��2k-�J�3��`G����Z`����_���h�|��� ����'k0#=����&$մ_k�&��~��\Wd{�S�� ��!�`�0���Ջ��%���:f��������)��܆ Eױ�!Y�X��g��!Y���R�7����w	�a-����d����_�%��{�7����;�-����xY{����k
�q�Áq�:�,��_T�d����������_VG n      X   A   x�3214571241 �F�Ɯřy
��ɩ��\F�&�e�EX�MA�
�
�)�%�E\1z\\\ ���      Y   @   x�32143�0112��0�06277��KL���K��24�,JM�%i�YP�_����	������ 7�O      Z      x������ � �      n      x������ � �      m      x������ � �      l      x������ � �      [      x������ � �      \      x������ � �      ]      x������ � �      ^      x������ � �      _      x������ � �      `      x������ � �      a      x������ � �      b      x������ � �      c      x������ � �      d      x������ � �      e      x������ � �      f      x������ � �      g   c  x���OJ�0�uz�9���ӤK2���6S�IIZo�<�\̌V��T���"�_��EI�,
�%�� 4�U�Sc\2&�;E��*J)sE�1~�	d��	C��]��w��2��)��%l��\�֛�VC�m�bhj�;h�_�8�����B��a�jW�Yy�O~f�];��v��nL�4��D���!8��X��_)��T2�	��\��i��$��y��(Ώ�`Y��-Īu����WO��c�}ٛH��o5BD�'��:�P�$�,ND��,Ȗį� �ZDd9v��G���δ����El9��y����:)bw_
�[C�؍����t�e�Y���      h   =   x����0E�3���#�������Gw6���!�M�R�����U��q���k�������      i      x������ � �      p      x������ � �      j   x   x�U�;�0C��)�Q<J��-��D0���g��,����#Dh��u+Z7�ʒvB�V8:����h��)�Cs��o��.Y�c��J���z�����NX�i��1�ܝ&%      k   s   x��ͻ�0ј�B��]�}�"\��p�������� x��h�.e�	���w��+#��F;��t��cc���v����4"f{vJ6S��7�׿[�<��[�h�ㆯ�{�K'Z     