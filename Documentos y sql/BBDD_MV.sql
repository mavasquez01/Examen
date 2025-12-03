#Creación de base de datos
CREATE DATABASE sistema_reserva_bicicletas;

#
USE sistema_reserva_bicicletas;

#Creación de tablas
CREATE TABLE JMMV_usuarios(
    JMMV_usuarios_id_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_usuarios_nom_usuario VARCHAR(75) NOT NULL UNIQUE,
    JMMV_usuarios_contrasena VARCHAR(20) NOT NULL,
    JMMV_usuarios_id_rol INT NOT NULL,
    JMMV_usuarios_id_dato_personal INT NOT NULL UNIQUE,
    JMMV_usuarios_esta_activo BOOLEAN
    );

CREATE TABLE JMMV_roles(
    JMMV_roles_id_rol INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_roles_nombre VARCHAR(75) NOT NULL UNIQUE
    );
    
CREATE TABLE JMMV_datos_personales(
    JMMV_datos_personales_id_dato_personal INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_datos_personales_run INT NOT NULL UNIQUE,
    JMMV_datos_personales_nombres VARCHAR(150) NOT NULL,
    JMMV_datos_personales_apellido_paterno VARCHAR(75) NOT NULL,
    JMMV_datos_personales_apellido_materno VARCHAR(75) DEFAULT 'No aplica',
    JMMV_datos_personales_correo VARCHAR(50) NOT NULL,
    JMMV_datos_personales_id_comuna INT NOT NULL,
    JMMV_datos_personales_calle VARCHAR(75) NOT NULL,
    JMMV_datos_personales_num_calle INT NOT NULL,
    JMMV_datos_personales_telefono INT
    );
    
CREATE TABLE JMMV_comunas(
    JMMV_comunas_id_comuna INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_comunas_nombre VARCHAR(75) NOT NULL UNIQUE
    );
    
CREATE TABLE JMMV_reservas(
    JMMV_reservas_id_reserva INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_reservas_id_cliente INT NOT NULL,
    JMMV_reservas_id_bicicleta INT NOT NULL,
    JMMV_reservas_fecha_inicio DATE NOT NULL,
    JMMV_reservas_fecha_fin DATE NOT NULL
    );

CREATE TABLE JMMV_bicicletas(
    JMMV_bicicletas_id_bicicleta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_bicicletas_nombre VARCHAR(75) NOT NULL UNIQUE,
    JMMV_bicicletas_id_tipo_bicicleta INT NOT NULL,
    JMMV_bicicletas_esta_disponible BOOLEAN NOT NULL
    );
    
CREATE TABLE JMMV_tipos_bicicletas(
    JMMV_tipos_bicicletas_id_tipo_bicicleta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_tipos_bicicletas_nombre VARCHAR(75) NOT NULL UNIQUE,
    JMMV_tipos_bicicletas_descripcion TEXT
    );

#Creación de FKs
ALTER TABLE JMMV_datos_personales
ADD CONSTRAINT fk1_JMMV_datos_personales
FOREIGN KEY (JMMV_datos_personales_id_comuna)
REFERENCES JMMV_comunas(JMMV_comunas_id_comuna);

ALTER TABLE JMMV_usuarios
ADD CONSTRAINT fk1_JMMV_usuarios
FOREIGN KEY (JMMV_usuarios_id_rol)
REFERENCES JMMV_roles(JMMV_roles_id_rol);

ALTER TABLE JMMV_usuarios
ADD CONSTRAINT fk2_JMMV_usuarios
FOREIGN KEY (JMMV_usuarios_id_dato_personal)
REFERENCES JMMV_datos_personales(JMMV_datos_personales_id_dato_personal)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE JMMV_bicicletas
ADD CONSTRAINT fk1_JMMV_bicicletas
FOREIGN KEY (JMMV_bicicletas_id_tipo_bicicleta)
REFERENCES JMMV_tipos_bicicletas(JMMV_tipos_bicicletas_id_tipo_bicicleta);

ALTER TABLE JMMV_reservas
ADD CONSTRAINT fk1_JMMV_reservas
FOREIGN KEY (JMMV_reservas_id_cliente)
REFERENCES JMMV_usuarios(JMMV_usuarios_id_usuario);

ALTER TABLE JMMV_reservas
ADD CONSTRAINT fk2_JMMV_reservas
FOREIGN KEY (JMMV_reservas_id_bicicleta)
REFERENCES JMMV_bicicletas(JMMV_bicicletas_id_bicicleta);

#Ingreso de datos a las tablas

INSERT INTO JMMV_roles
VALUES
(NULL,'Administrador'),
(NULL,'Cliente')
;

INSERT INTO JMMV_comunas
VALUES
(NULL,'Cauquenes'),
(NULL,'Chanco'),
(NULL,'Pelluhue'),
(NULL,'Curicó'),
(NULL,'Hualañé'),
(NULL,'Licantén'),
(NULL,'Molina'),
(NULL,'Rauco'),
(NULL,'Romeral'),
(NULL,'Sagrada Familia'),
(NULL,'Teno'),
(NULL,'Vichuquén'),
(NULL,'Colbún'),
(NULL,'Linares'),
(NULL,'Longaví'),
(NULL,'Parral'),
(NULL,'Retiro'),
(NULL,'San Javier'),
(NULL,'Villa Alegre'),
(NULL,'Yerbas Buenas'),
(NULL,'Constitución'),
(NULL,'Curepto'),
(NULL,'Empedrado'),
(NULL,'Maule'),
(NULL,'Pelarco'),
(NULL,'Pencahue'),
(NULL,'Río Claro'),
(NULL,'San Clemente'),
(NULL,'San Rafael'),
(NULL,'Talca')
;

INSERT INTO JMMV_tipos_bicicletas
VALUES
(NULL,'Urbana','desc 1'),
(NULL,'Ruta','desc 2'),
(NULL,'Montaña','desc 3'),
(NULL,'Triatlón','desc 4'),
(NULL,'Plegable','desc 5'),
(NULL,'BMX',NULL)
;

INSERT INTO JMMV_datos_personales
VALUES
(NULL,111111111,'nombre nombre1','apellidoP1','apellidoM1','correo1@dominio.com',9,'calle1',111,9555551),
(NULL,222222222,'nombre nombre2','apellidoP2','apellidoM2','correo2@dominio.com',1,'calle2',222,9555552),
(NULL,333333333,'nombre nombre3','apellidoP3','apellidoM3','correo3@dominio.com',1,'calle3',333,9555553),
(NULL,444444444,'nombre nombre4','apellidoP4','apellidoM4','correo4@dominio.com',1,'calle4',444,9555554),
(NULL,555555555,'nombre nombre5','apellidoP5','apellidoM5','correo5@dominio.com',1,'calle5',555,9555555),
(NULL,666666666,'nombre nombre6','apellidoP6','apellidoM6','correo6@dominio.com',1,'calle6',666,9555556),
(NULL,777777777,'nombre nombre7','apellidoP7','apellidoM7','correo7@dominio.com',1,'calle7',777,9555557),
(NULL,888888888,'nombre nombre8','apellidoP8','apellidoM8','correo8@dominio.com',1,'calle8',888,9555557),
(NULL,999999999,'nombre nombre9','apellidoP9',DEFAULT,'correo9@dominio.com',1,'calle9',999,9555558),
(NULL,111111110,'nombre10','apellidoP10','apellidoM10','correo10@dominio.com',8,'calle10',1010,9555510),
(NULL,222222220,'nombre nombre11','apellidoP11','apellidoM11','correo11@dominio.com',1,'calle11',1111,9555511),
(NULL,333333330,'nombre nombre12','apellidoP12','apellidoM12','correo12@dominio.com',10,'calle11',1212,9555512)
;


INSERT INTO JMMV_usuarios
VALUES
(NULL,'admin','123',1,1,TRUE),
(NULL,'admin2','a222',1,2,TRUE),
(NULL,'usuarioC3','a333',2,3,TRUE),
(NULL,'usuarioC4','a444',2,4,TRUE),
(NULL,'usuarioC5','a555',2,5,TRUE),
(NULL,'usuarioC6','a666',2,6,TRUE),
(NULL,'usuarioC777','a777',2,7,TRUE),
(NULL,'usuarioC8','a888',2,8,TRUE),
(NULL,'usuarioC9','a999',2,9,TRUE),
(NULL,'usuarioC10','a1010',2,10,FALSE),
(NULL,'usuarioC11','a1111',2,11,TRUE),
(NULL,'usuarioC12','a1212',2,12,TRUE)
;

INSERT INTO JMMV_bicicletas
VALUES
(NULL,'bici1',6,TRUE),
(NULL,'bici2',5,TRUE),
(NULL,'bici3',4,TRUE),
(NULL,'bici4',3,TRUE),
(NULL,'bici5',2,TRUE),
(NULL,'bici6',1,TRUE),
(NULL,'bici7',6,TRUE),
(NULL,'bici8',5,TRUE),
(NULL,'bici9',4,TRUE),
(NULL,'bici10',3,TRUE),
(NULL,'bici11',2,TRUE),
(NULL,'bici12',1,TRUE),
(NULL,'bici13',6,FALSE),
(NULL,'bici14',5,TRUE),
(NULL,'bici15',4,TRUE),
(NULL,'bici16',3,TRUE),
(NULL,'bici17',2,TRUE),
(NULL,'bici18',1,TRUE),
(NULL,'bici19',6,TRUE),
(NULL,'bici20',5,TRUE)
;

INSERT INTO JMMV_reservas
VALUES
(NULL,3,2,'2025-10-01','2025-10-08'),
(NULL,4,1,'2025-10-02','2025-10-09'),
(NULL,5,5,'2025-10-02','2025-10-09'),
(NULL,6,1,'2025-10-03','2025-10-10'),
(NULL,3,2,'2025-10-04','2025-10-11'),
(NULL,8,3,'2025-10-04','2025-10-11'),
(NULL,9,4,'2025-10-05','2025-10-12'),
(NULL,10,5,'2025-10-06','2025-10-13'),
(NULL,11,6,'2025-10-06','2025-10-13'),
(NULL,12,7,'2025-10-07','2025-10-14'),
(NULL,3,8,'2025-10-08','2025-10-15'),
(NULL,10,9,'2025-10-08','2025-12-15'),
(NULL,4,10,'2025-10-09','2025-10-16'),
(NULL,5,11,'2025-10-10','2025-10-17'),
(NULL,6,12,'2025-10-10','2025-10-17'),
(NULL,3,13,'2025-10-11','2025-10-18'),
(NULL,8,14,'2025-10-30','2025-11-06'),
(NULL,4,13,'2025-12-08','2025-10-11')
;