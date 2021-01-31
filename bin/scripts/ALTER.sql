ALTER TABLE came.came_acciones_mejoras RENAME COLUMN cracion TO fecha_registro;
ALTER TABLE came.came_acciones_mejoras ALTER COLUMN fecha_registro TYPE timestamp USING fecha_registro::timestamp;

ALTER TABLE came.came_foco RENAME COLUMN cracion TO fecha_registro;
ALTER TABLE came.came_foco ALTER COLUMN fecha_registro TYPE timestamp USING fecha_registro::timestamp;

ALTER TABLE came.came_movimientos_claves RENAME COLUMN cracion TO fecha_registro;
ALTER TABLE came.came_movimientos_claves ALTER COLUMN fecha_registro TYPE timestamp USING fecha_registro::timestamp;

ALTER TABLE came.came_periodo_documentos_provinciales RENAME COLUMN periodo_documentos_provinciales TO id_periodo_documentos_provinciales;

ALTER TABLE came.came_periodo_documentos_regionales ADD descripcion varchar NULL;
ALTER TABLE came.came_periodo_documentos_provinciales ADD descripcion varchar NULL;
ALTER TABLE came.came_periodo_conformacion_redes ADD descripcion varchar NULL;
ALTER TABLE came.came_periodo_asignacion_supervisores ADD descripcion varchar NULL;
ALTER TABLE came.came_periodo_planificacion_implementacion_apoyo ADD descripcion varchar NULL;

ALTER TABLE came.came_periodo_documentos_provinciales DROP COLUMN fecha_registro1;