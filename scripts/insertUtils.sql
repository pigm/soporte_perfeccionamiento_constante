INSERT INTO came.came_estado
                (id_estado, nombre)
VALUES(2416190883893347335, 'habilitado'),
      (2416190883893347336, 'inhabilitado');

-- INSERT INTO came.came_region
--                 (id_region, nombre)
-- VALUES(2414154314781033475, 'Region');

-- INSERT INTO came.came_provincia
-- (id_provincia, id_region, nombre)
-- VALUES(2414154375564887044, 2414154314781033475, 'Provincia');

INSERT INTO came.came_usuario
(id_usuario, id_estado, id_region, id_deprov, usuario_dominio, run, 
nombre, apellido_paterno, apellido_materno, email, reintentos, 
ultima_modificacion, id_usuario_modificacion, dv, fecha_registro, id_usuario_registro, habilitado)
VALUES(2414154165161821185, 2416190883893347335, 1, 11, 'loreto.arias', 15898260, 'Loreto Andrea', 'Arias', 'Fuentes', 
'loreto.arias@mineduc.cl', 0, '2020-09-28', 2414154453067236357, '9', '2020-09-28', 2414154516208288774, false);

INSERT INTO came.came_perfil_nivel
               (id_perfil_nivel, nombre)
        VALUES (2416184421980832772, 'nacional'),
               (2416184421980832773, 'regional'),
               (2416184421980832774, 'provincial');


INSERT INTO came.came_perfil_menu_acceso(
	id_perfil_menu_acceso, nombre)
	VALUES (2415742140157002753, 'sin acceso'),
		   (2415742140157002754, 'ver'),
		   (2415742140157002755, 'ver y editar');

INSERT INTO came.came_modulo(
	id_modulo, id_estado, nombre, status)
	VALUES  (2416198290916770825, 2416190883893347335, 'Administracion', true),	
	        (2445312600943101230, 2416190883893347335, 'establecimientos', true),
			(2416198290916770826, 2416190883893347335, 'Plan Regional y Provincial', true),
			(2416198290916770827, 2416190883893347335, 'Organización y Planififación Operacional Provincial', true),
			(2416198290916770828, 2416190883893347335, 'Planififación del apoyo', true),
			(2416198290916770829, 2416190883893347335, 'Reportes', true),
			(2416198290916770830, 2416190883893347335, 'Documentos', true),
			(2416198290916770831, 2416190883893347335, 'Mensajería', true),
			(2416198290916770832, 2416190883893347335, 'Encuesta', true);

INSERT INTO came.came_sub_modulo(
	id_sub_modulo, id_estado, id_modulo, nombre, status)
	VALUES (2416254586118472721, 2416190883893347335, 2416198290916770825, 'Perfiles', true),
		   (2416254586118472722, 2416190883893347335, 2416198290916770825, 'Usuarios', true),
		   (2416254586126861331, 2416190883893347335, 2416198290916770825, 'Periodo', true),
		   (2416254586126861347, 2416190883893347335, 2416198290916770825, 'Listas desplegables', true),
		   (2416254586126861348, 2416190883893347335, 2416198290916770825, 'Asignación máxima', true),
		   (2416254586126861349, 2416190883893347335, 2416198290916770825, 'Suplencia', true),
		   (2416254586126861350, 2416190883893347335, 2416198290916770825, 'Focos', true),
		   
			(2445315877667603760, 2416190883893347335, 2445312600943101230, 'establecimientos', true),

		   (2416254586126861332, 2416190883893347335, 2416198290916770826, 'Ingreso plan', true),
		   (2416254586126861333, 2416190883893347335, 2416198290916770826, 'Validar plan', true),
		   
		   (2416254586126861334, 2416190883893347335, 2416198290916770827, 'Administración de redes', true),
		   (2416254586126861335, 2416190883893347335, 2416198290916770827, 'Configuración de Red', true),
		   (2416254586126861336, 2416190883893347335, 2416198290916770827, 'Administración de Supervisores', true),
		   (2416254586126861337, 2416190883893347335, 2416198290916770827, 'Supervisores', true),
		   
		   (2416254586126861338, 2416190883893347335, 2416198290916770828, 'Administración de Sesiones', true),
		   (2416254586126861339, 2416190883893347335, 2416198290916770828, 'Registro de Sesión', true),
		   
		   (2416254586126861340, 2416190883893347335, 2416198290916770829, 'Reportes', true),
		   
		   (2416254586126861341, 2416190883893347335, 2416198290916770830, 'Visualización de Documentos', true),
		   (2416254586126861342, 2416190883893347335, 2416198290916770830, 'Carga de Documentos', true),
		   
		   (2416254586126861343, 2416190883893347335, 2416198290916770831, 'Listar', true),
		   (2416254586126861344, 2416190883893347335, 2416198290916770831, 'Crear', true),
		   
		   (2416254586126861345, 2416190883893347335, 2416198290916770832, 'Listar', true),
		   (2416254586126861346, 2416190883893347335, 2416198290916770832, 'Crear', true);


INSERT INTO came.came_perfil(
	id_perfil, id_estado, id_perfil_nivel, nombre, descripcion, habilitado, ultima_modificacion, id_usuario_modificacion)
	VALUES (2416264829766468643, 2416190883893347335, 2416184421980832772, 'Administrador', 'Administrador', true, now(), 2414154165161821185),
		   (2416264829774857252, 2416190883893347335, 2416184421980832772, 'Profesional Mineduc', 'Profesional Mineduc', true, now(), 2414154165161821185),
		   (2416264829774857253, 2416190883893347335, 2416184421980832772, 'Encargados Regionales', 'Encargados Regionales', true, now(), 2414154165161821185),
		   (2416264829774857254, 2416190883893347335, 2416184421980832773, 'Coordinador Regional', 'Coordinador Regional', true, now(), 2414154165161821185),
		   (2416264829774857255, 2416190883893347335, 2416184421980832773, 'Jefe de Educación', 'Jefe de Educación', true, now(), 2414154165161821185),
		   (2416264829774857256, 2416190883893347335, 2416184421980832774, 'Jefe Provincial', 'Jefe Provincial', true, now(), 2414154165161821185),
		   (2416264829774857257, 2416190883893347335, 2416184421980832774, 'Jefe Técnico', 'Jefe Técnico', true, now(), 2414154165161821185),
		   (2416264829774857258, 2416190883893347335, 2416184421980832774, 'Supervisor', 'Supervisor', true, now(), 2414154165161821185);

INSERT INTO came.came_usuario_perfil
(id_usuario_perfil, id_usuario, id_perfil, start_date, end_date, status)
VALUES(2414158840820925447, 2414154165161821185, 2416264829766468643, now(), NULL, true);

INSERT INTO came.came_asignacion_maxima
	(id_asignacion_maxima, supervisores, foco, id_usuario_registro, fecha_registro)
VALUES(2439002850966111287, 2, 2, 2414154165161821185, '2020-11-10 18:28:34.934');

INSERT INTO came.came_lista (id_lista, nombre) values (2420130603857871959, 'categorias');
INSERT INTO came.came_lista (id_lista, nombre) values (2420130603924980824, 'porcentaje');
INSERT INTO came.came_lista (id_lista, nombre) values (2420130603941758041, 'tipos de red');
INSERT INTO came.came_lista (id_lista, nombre) values (2420130603950146650, 'momentos asesoria');




INSERT INTO came.came_establecimiento_tipo_dependencia(
	id_establecimiento_dependencia, codigo, descripcion, visible)
	VALUES (2446267361578190149, 1, 'Municipal - Corporacion', true),			
			(2446267361594967366, 2, 'Municipal - DAEM', true),
			(2446267361594967367, 3, 'Particular Subvencionado', true),
			(2446267361594967368, 4, 'Particular no Subvencionado', true),
			(2446267361594967369, 5, 'Corporacion Privada 3166', true),
			(2446267361594967370, 6, 'Servicio Local de Educacion (SLE)', true),
			(2446267361594967371, 7, 'JUNJI', true),
			(2446267361594967372, 8, 'INTEGRA', true),
			(2446267361594967373, 9, 'VTF', true),
			(2446267361594967374, 10, 'CAD', true),
			(2446267361594967375, 11, 'Parvulo - Servicio Local de Educacion (SLE)', true);

INSERT INTO came.came_clasificacion_sep(
	id_clasificacion_sep, descripcion, visible)
	VALUES (2446811709885646160, 'AUTONOMO', true),
		   (2446811709927589201, 'EMERGENTE', true),
		   (2446811709927589202, 'EN RECUPERACION', true);