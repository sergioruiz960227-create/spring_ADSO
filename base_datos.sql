-- ============================================================
-- Script de la base de datos del módulo de usuarios (OPCIONAL)
-- ============================================================
-- La aplicación crea la base de datos y la tabla sola al arrancar
-- (createDatabaseIfNotExist y ddl-auto=update en application.properties),
-- y CargadorDatos inserta los usuarios iniciales.
-- Este script solo sirve para crear la misma estructura a mano en phpMyAdmin.

CREATE DATABASE IF NOT EXISTS spring_ADSO CHARACTER SET utf8mb4;
USE spring_ADSO;

-- Tabla de usuarios (equivale a la entidad Usuario)
CREATE TABLE IF NOT EXISTS usuarios (
  id     BIGINT       NOT NULL AUTO_INCREMENT,
  clave  VARCHAR(255) DEFAULT NULL,
  correo VARCHAR(255) DEFAULT NULL,
  nombre VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uk_usuarios_correo (correo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Usuarios iniciales (los mismos que carga CargadorDatos)
INSERT INTO usuarios (nombre, correo, clave) VALUES
  ('Administrador', 'admin@tienda.com',     '1234'),
  ('Vendedor Uno',  'vendedor1@tienda.com', '5678'),
  ('Vendedor Dos',  'vendedor2@tienda.com', '9876');
