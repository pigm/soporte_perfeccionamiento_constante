--
-- PostgreSQL database dump
--

-- Dumped from database version 13.0
-- Dumped by pg_dump version 13.0

-- Started on 2020-11-09 17:57:52

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
-- TOC entry 3406 (class 0 OID 16479)
-- Dependencies: 216
-- Data for Name: came_foco; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_foco (id_foco) FROM stdin;
\.


--
-- TOC entry 3393 (class 0 OID 16398)
-- Dependencies: 203
-- Data for Name: came_acciones_mejoras; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_acciones_mejoras (id_acciones_mejoras, id_foco) FROM stdin;
\.


--
-- TOC entry 3395 (class 0 OID 16410)
-- Dependencies: 205
-- Data for Name: came_asignacion_tipo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_asignacion_tipo (id_asignacion_tipo) FROM stdin;
\.


--
-- TOC entry 3397 (class 0 OID 16421)
-- Dependencies: 207
-- Data for Name: came_categorizacion; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_categorizacion (id_categorizacion) FROM stdin;
\.


--
-- TOC entry 3398 (class 0 OID 16426)
-- Dependencies: 208
-- Data for Name: came_clasificacion_sep; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_clasificacion_sep (id_clasificacion_sep) FROM stdin;
\.


--
-- TOC entry 3399 (class 0 OID 16431)
-- Dependencies: 209
-- Data for Name: came_director; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_director (id_director) FROM stdin;
\.


--
-- TOC entry 3404 (class 0 OID 16466)
-- Dependencies: 214
-- Data for Name: came_establecimiento_estado; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_establecimiento_estado (id_establecimiento_estado) FROM stdin;
\.


--
-- TOC entry 3430 (class 0 OID 16653)
-- Dependencies: 240
-- Data for Name: came_sostenedor; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sostenedor (id_sostenedor) FROM stdin;
\.


--
-- TOC entry 3402 (class 0 OID 16447)
-- Dependencies: 212
-- Data for Name: came_establecimiento; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_establecimiento (id_establecimiento, id_categorizacion1, id_establecimiento_estado, id_director, id_categorizacion2, id_clasificacion_sep, id_sostenedor, id_deprov, id_comuna) FROM stdin;
\.


--
-- TOC entry 3405 (class 0 OID 16471)
-- Dependencies: 215
-- Data for Name: came_estado; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_estado (id_estado, nombre) FROM stdin;
2416190883893347335	habilitado
2416190883893347336	inhabilitado
\.


--
-- TOC entry 3418 (class 0 OID 16566)
-- Dependencies: 228
-- Data for Name: came_periodo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo (id_periodo, anio, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3425 (class 0 OID 16618)
-- Dependencies: 235
-- Data for Name: came_red_tipo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red_tipo (id_red_tipo, nombre) FROM stdin;
\.


--
-- TOC entry 3434 (class 0 OID 16683)
-- Dependencies: 244
-- Data for Name: came_usuario; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_usuario (id_usuario, id_estado, id_region, id_deprov, usuario_dominio, run, nombre, apellido_paterno, apellido_materno, email, habilitado, reintentos, ultima_modificacion, id_usuario_modificacion, dv, fecha_registro, id_usuario_registro) FROM stdin;
2414154165161821185	2416190883893347335	1	11	loreto.arias	15898260	Loreto Andrea	Arias	Fuentes	loreto.arias@mineduc.cl	t	0	2020-09-28 00:00:00	2414154453067236357	9	2020-09-28 00:00:00	2414154516208288774
\.


--
-- TOC entry 3422 (class 0 OID 16595)
-- Dependencies: 232
-- Data for Name: came_red; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red (id_red, id_red_tipo, id_periodo, id_usuario, id_deprov) FROM stdin;
\.


--
-- TOC entry 3432 (class 0 OID 16668)
-- Dependencies: 242
-- Data for Name: came_supervisor; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_supervisor (id_supervisor, id_usuario, id_deprov, start_date, end_date, status) FROM stdin;
\.


--
-- TOC entry 3433 (class 0 OID 16674)
-- Dependencies: 243
-- Data for Name: came_supervisor_establecimiento; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_supervisor_establecimiento (id_supervisor_establecimiento, id_asignacion_tipo, id_supervisor, id_red, id_establecimiento) FROM stdin;
\.


--
-- TOC entry 3394 (class 0 OID 16404)
-- Dependencies: 204
-- Data for Name: came_asesoria; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_asesoria (id_asesoria, id_supervisor_establecimiento) FROM stdin;
\.


--
-- TOC entry 3439 (class 0 OID 17051)
-- Dependencies: 249
-- Data for Name: came_asignacion_maxima; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_asignacion_maxima (id_came_asignacion_maxima, supervisores, foco, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3396 (class 0 OID 16415)
-- Dependencies: 206
-- Data for Name: came_came_estandares_indicativos_desempenio; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_came_estandares_indicativos_desempenio (id_estandares_indicativos_desempenio, id_foco) FROM stdin;
\.


--
-- TOC entry 3407 (class 0 OID 16484)
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
-- TOC entry 3400 (class 0 OID 16436)
-- Dependencies: 210
-- Data for Name: came_elemento_lista; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_elemento_lista (id_elemento_lista, id_lista, nombre, status) FROM stdin;
2435191082942727169	2420130603857871959	Estudio	t
2435236300836570115	2420130603857871959	dededede	t
2435843024157148182	2420130603857871959	ewew wewe	t
\.


--
-- TOC entry 3403 (class 0 OID 16458)
-- Dependencies: 213
-- Data for Name: came_establecimiento_categorizacion_historica; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_establecimiento_categorizacion_historica (id_establecimiento_categorizacion_historica, id_categorizacion, id_establecimiento) FROM stdin;
\.


--
-- TOC entry 3409 (class 0 OID 16500)
-- Dependencies: 219
-- Data for Name: came_matricula_etnia; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_matricula_etnia (id_matricula_etnia) FROM stdin;
\.


--
-- TOC entry 3412 (class 0 OID 16520)
-- Dependencies: 222
-- Data for Name: came_nivel; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_nivel (id_nivel) FROM stdin;
\.


--
-- TOC entry 3408 (class 0 OID 16492)
-- Dependencies: 218
-- Data for Name: came_matricula; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_matricula (id_matricula, id_matricula_etnia, id_nivel, id_establecimiento) FROM stdin;
\.


--
-- TOC entry 3410 (class 0 OID 16505)
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
-- TOC entry 3411 (class 0 OID 16514)
-- Dependencies: 221
-- Data for Name: came_movimientos_claves; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_movimientos_claves (id_movimientos_claves, id_acciones_mejoras) FROM stdin;
\.


--
-- TOC entry 3427 (class 0 OID 16633)
-- Dependencies: 237
-- Data for Name: came_sesion_estado; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion_estado (id_sesion_estado) FROM stdin;
\.


--
-- TOC entry 3426 (class 0 OID 16626)
-- Dependencies: 236
-- Data for Name: came_sesion; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion (id_sesion, id_asesoria, id_session_estado) FROM stdin;
\.


--
-- TOC entry 3428 (class 0 OID 16638)
-- Dependencies: 238
-- Data for Name: came_sesion_foco; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion_foco (id_sesion_foco, id_sesion, id_foco) FROM stdin;
\.


--
-- TOC entry 3413 (class 0 OID 16525)
-- Dependencies: 223
-- Data for Name: came_objetivo_mejora; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_objetivo_mejora (id_objetivo_mejora, id_foco, id_sesion_foco) FROM stdin;
\.


--
-- TOC entry 3417 (class 0 OID 16558)
-- Dependencies: 227
-- Data for Name: came_perfil_nivel; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_perfil_nivel (id_perfil_nivel, nombre) FROM stdin;
2416184421980832772	nacional
2416184421980832773	regional
2416184421980832774	provincial
\.


--
-- TOC entry 3414 (class 0 OID 16532)
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
-- TOC entry 3416 (class 0 OID 16550)
-- Dependencies: 226
-- Data for Name: came_perfil_menu_acceso; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_perfil_menu_acceso (id_perfil_menu_acceso, nombre) FROM stdin;
2415742140157002753	sin acceso
2415742140157002754	ver
2415742140157002755	ver y editar
\.


--
-- TOC entry 3431 (class 0 OID 16658)
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
-- TOC entry 3415 (class 0 OID 16542)
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
-- TOC entry 3438 (class 0 OID 17024)
-- Dependencies: 248
-- Data for Name: came_periodo_asignacion_supervisores; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_asignacion_supervisores (id_periodo_asignacion_supervisores, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3437 (class 0 OID 17018)
-- Dependencies: 247
-- Data for Name: came_periodo_conformacion_redes; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_conformacion_redes (id_periodo_conformacion_redes, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3436 (class 0 OID 17006)
-- Dependencies: 246
-- Data for Name: came_periodo_documentos_provinciales; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_documentos_provinciales (periodo_documentos_provinciales, id_periodo, id_region, id_deprov, visible, es_caso_especial, template_path, fecha_registro, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro1) FROM stdin;
\.


--
-- TOC entry 3419 (class 0 OID 16577)
-- Dependencies: 229
-- Data for Name: came_periodo_documentos_regionales; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_documentos_regionales (id_periodo_documentos_regionales, id_periodo, id_region, fecha_inicio, fecha_fin, visible, es_caso_especial, template_path, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3420 (class 0 OID 16583)
-- Dependencies: 230
-- Data for Name: came_periodo_organizacion_planificacion_provincial; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_organizacion_planificacion_provincial (id_periodo_organizacion_planificacion_provincial, id_periodo, id_deprov) FROM stdin;
\.


--
-- TOC entry 3421 (class 0 OID 16589)
-- Dependencies: 231
-- Data for Name: came_periodo_planificacion_implementacion_apoyo; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_periodo_planificacion_implementacion_apoyo (id_periodo_planificacion_implementacion_apoyo, id_periodo, fecha_inicio, fecha_fin, id_usuario_registro, fecha_registro) FROM stdin;
\.


--
-- TOC entry 3423 (class 0 OID 16603)
-- Dependencies: 233
-- Data for Name: came_red_establecimiento; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red_establecimiento (id_red_establecimiento, id_red, id_establecimiento, id_periodo) FROM stdin;
\.


--
-- TOC entry 3424 (class 0 OID 16611)
-- Dependencies: 234
-- Data for Name: came_red_sostenedor; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_red_sostenedor (id_red_sostenedor, id_red, id_sostenedor) FROM stdin;
\.


--
-- TOC entry 3429 (class 0 OID 16645)
-- Dependencies: 239
-- Data for Name: came_sesion_movimientos_claves; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_sesion_movimientos_claves (id_sesion_movimientos_claves, id_sesion_foco, id_foco, id_movimientos_claves) FROM stdin;
\.


--
-- TOC entry 3440 (class 0 OID 17060)
-- Dependencies: 250
-- Data for Name: came_supervisor_suplencia; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_supervisor_suplencia (id_came_supervisor_suplencia, id_supervisor_ausente, id_supervisor_suplente, start_date, end_date, fecha_registro, id_usuario_registro) FROM stdin;
\.


--
-- TOC entry 3435 (class 0 OID 16692)
-- Dependencies: 245
-- Data for Name: came_usuario_perfil; Type: TABLE DATA; Schema: came; Owner: postgres
--

COPY came.came_usuario_perfil (id_usuario_perfil, id_usuario, id_perfil, start_date, end_date, status) FROM stdin;
2414158840820925447	2414154165161821185	2416264829766468643	2020-11-04 10:51:19.372532	\N	t
\.


--
-- TOC entry 3446 (class 0 OID 0)
-- Dependencies: 211
-- Name: came_establecimiento_id_categorizacion2_seq; Type: SEQUENCE SET; Schema: came; Owner: postgres
--

SELECT pg_catalog.setval('came.came_establecimiento_id_categorizacion2_seq', 1, false);


--
-- TOC entry 3447 (class 0 OID 0)
-- Dependencies: 202
-- Name: secuencia_id; Type: SEQUENCE SET; Schema: came; Owner: postgres
--

SELECT pg_catalog.setval('came.secuencia_id', 54, true);


-- Completed on 2020-11-09 17:57:52

--
-- PostgreSQL database dump complete
--

