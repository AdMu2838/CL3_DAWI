drop database if exists CL3_MAMANI_USCAMAYTA;
CREATE DATABASE CL3_MAMANI_USCAMAYTA;
USE CL3_MAMANI_USCAMAYTA;
CREATE TABLE producto (
  id int AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  fecha_registro DATE NOT NULL
);
select * from producto;