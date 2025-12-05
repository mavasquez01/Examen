#Creación de base de datos
CREATE DATABASE sistema_reserva_bicicletas;

#
USE sistema_reserva_bicicletas;

#Creación de tablas
CREATE TABLE JMMV_usuarios(
    JMMV_usuarios_id_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_usuarios_nom_usuario VARCHAR(75) NOT NULL UNIQUE,
    JMMV_usuarios_contrasena VARCHAR(20) NOT NULL,
    JMMV_usuarios_correo VARCHAR(50) NOT NULL,
    JMMV_usuarios_id_rol INT NOT NULL,
    JMMV_usuarios_esta_activo BOOLEAN NOT NULL
    );

CREATE TABLE JMMV_roles(
    JMMV_roles_id_rol INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_roles_nombre VARCHAR(75) NOT NULL UNIQUE,
    JMMV_roles_esta_activo BOOLEAN NOT NULL
    );
    
CREATE TABLE JMMV_clientes(
    JMMV_clientes_id_cliente INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_clientes_id_usuario INT NOT NULL,
    JMMV_clientes_run INT NOT NULL UNIQUE,
    JMMV_clientes_nombres VARCHAR(150) NOT NULL,
    JMMV_clientes_apellido_paterno VARCHAR(75) NOT NULL,
    JMMV_clientes_apellido_materno VARCHAR(75) DEFAULT 'No aplica',    
    JMMV_clientes_id_comuna INT NOT NULL,
    JMMV_clientes_calle VARCHAR(75) NOT NULL,
    JMMV_clientes_num_calle INT NOT NULL,
    JMMV_clientes_telefono INT,
    JMMV_clientes_esta_activo BOOLEAN NOT NULL
    );
    
CREATE TABLE JMMV_comunas(
    JMMV_comunas_id_comuna INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_comunas_nombre VARCHAR(75) NOT NULL UNIQUE,
    JMMV_comunas_esta_activo BOOLEAN NOT NULL
    );
    
CREATE TABLE JMMV_reservas(
    JMMV_reservas_id_reserva INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_reservas_id_cliente INT NOT NULL,
    JMMV_reservas_id_bicicleta INT NOT NULL,
    JMMV_reservas_fecha_inicio DATE NOT NULL,
    JMMV_reservas_fecha_fin DATE NOT NULL,
    JMMV_reservas_esta_activo BOOLEAN NOT NULL
    );

CREATE TABLE JMMV_bicicletas(
    JMMV_bicicletas_id_bicicleta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_bicicletas_nombre VARCHAR(75) NOT NULL UNIQUE,
    JMMV_bicicletas_id_tipo_bicicleta INT NOT NULL,
    JMMV_bicicletas_esta_disponible BOOLEAN NOT NULL,
    JMMV_bicicletas_esta_activo BOOLEAN NOT NULL
    );
    
CREATE TABLE JMMV_tipos_bicicletas(
    JMMV_tipos_bicicletas_id_tipo_bicicleta INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_tipos_bicicletas_nombre VARCHAR(75) NOT NULL UNIQUE,
    JMMV_tipos_bicicletas_descripcion TEXT,
    JMMV_tipos_bicicletas_esta_activo BOOLEAN NOT NULL
    );

#Creación de FKs

ALTER TABLE JMMV_usuarios
ADD CONSTRAINT fk1_JMMV_usuarios
FOREIGN KEY (JMMV_usuarios_id_rol)
REFERENCES JMMV_roles(JMMV_roles_id_rol);

ALTER TABLE JMMV_clientes
ADD CONSTRAINT fk1_JMMV_clientes
FOREIGN KEY (JMMV_clientes_id_usuario)
REFERENCES JMMV_usuarios(JMMV_usuarios_id_usuario);

ALTER TABLE JMMV_clientes
ADD CONSTRAINT fk2_JMMV_clientes
FOREIGN KEY (JMMV_clientes_id_comuna)
REFERENCES JMMV_comunas(JMMV_comunas_id_comuna);

ALTER TABLE JMMV_bicicletas
ADD CONSTRAINT fk1_JMMV_bicicletas
FOREIGN KEY (JMMV_bicicletas_id_tipo_bicicleta)
REFERENCES JMMV_tipos_bicicletas(JMMV_tipos_bicicletas_id_tipo_bicicleta);

ALTER TABLE JMMV_reservas
ADD CONSTRAINT fk1_JMMV_reservas
FOREIGN KEY (JMMV_reservas_id_cliente)
REFERENCES JMMV_clientes(JMMV_clientes_id_cliente);

ALTER TABLE JMMV_reservas
ADD CONSTRAINT fk2_JMMV_reservas
FOREIGN KEY (JMMV_reservas_id_bicicleta)
REFERENCES JMMV_bicicletas(JMMV_bicicletas_id_bicicleta);





# datos

INSERT INTO JMMV_roles
VALUES
(NULL,'Administrador',TRUE),
(NULL,'Cliente',TRUE)
;

INSERT INTO JMMV_comunas
VALUES
(NULL,'Cauquenes',TRUE),
(NULL,'Chanco',TRUE),
(NULL,'Pelluhue',TRUE),
(NULL,'Curicó',TRUE),
(NULL,'Hualañé',TRUE),
(NULL,'Licantén',TRUE),
(NULL,'Molina',TRUE),
(NULL,'Rauco',TRUE),
(NULL,'Romeral',TRUE),
(NULL,'Sagrada Familia',TRUE),
(NULL,'Teno',TRUE),
(NULL,'Vichuquén',TRUE),
(NULL,'Colbún',TRUE),
(NULL,'Linares',TRUE),
(NULL,'Longaví',TRUE),
(NULL,'Parral',TRUE),
(NULL,'Retiro',TRUE),
(NULL,'San Javier',TRUE),
(NULL,'Villa Alegre',TRUE),
(NULL,'Yerbas Buenas',TRUE),
(NULL,'Constitución',TRUE),
(NULL,'Curepto',TRUE),
(NULL,'Empedrado',TRUE),
(NULL,'Maule',TRUE),
(NULL,'Pelarco',TRUE),
(NULL,'Pencahue',TRUE),
(NULL,'Río Claro',TRUE),
(NULL,'San Clemente',TRUE),
(NULL,'San Rafael',TRUE),
(NULL,'Talca',TRUE)
;

INSERT INTO JMMV_tipos_bicicletas
VALUES
(NULL,'Urbana','desc 1',TRUE),
(NULL,'Ruta','desc 2',TRUE),
(NULL,'Montaña','desc 3',TRUE),
(NULL,'Triatlón','desc 4',TRUE),
(NULL,'Plegable','desc 5',TRUE),
(NULL,'BMX',NULL,TRUE)
;

INSERT INTO JMMV_usuarios
VALUES
(NULL,'admin','123','correo1@dominio.com',1,TRUE),
(NULL,'admin2','a222','correo2@dominio.com',1,TRUE),
(NULL,'usuarioC3','a333','correo3@dominio.com',2,TRUE),
(NULL,'usuarioC4','a444','correo4@dominio.com',2,TRUE),
(NULL,'usuarioC5','a555','correo5@dominio.com',2,TRUE),
(NULL,'usuarioC6','a666','correo6@dominio.com',2,TRUE),
(NULL,'usuarioC777','a777','correo7@dominio.com',2,TRUE),
(NULL,'usuarioC8','a888','correo8@dominio.com',2,TRUE),
(NULL,'usuarioC9','a999','correo9@dominio.com',2,TRUE),
(NULL,'usuarioC10','a1010','correo10@dominio.com',2,TRUE),
(NULL,'usuarioC11','a1111','correo11@dominio.com',2,TRUE),
(NULL,'usuarioC12','a1212','correo12@dominio.com',2,TRUE),
(NULL,'usuarioC13','a1111','correo13@dominio.com',2,TRUE),
(NULL,'usuarioC14','a1212','correo14@dominio.com',2,TRUE)
;

INSERT INTO JMMV_clientes
VALUES
(NULL,3,111111111,'nombre nombre1','apellidoP1','apellidoM1',9,'calle1',111,9555551,TRUE),
(NULL,4,222222222,'nombre nombre2','apellidoP2','apellidoM2',1,'calle2',222,9555552,TRUE),
(NULL,5,333333333,'nombre nombre3','apellidoP3','apellidoM3',1,'calle3',333,9555553,FALSE),
(NULL,6,444444444,'nombre nombre4','apellidoP4','apellidoM4',1,'calle4',444,9555554,TRUE),
(NULL,7,555555555,'nombre nombre5','apellidoP5','apellidoM5',1,'calle5',555,9555555,TRUE),
(NULL,8,666666666,'nombre nombre6','apellidoP6','apellidoM6',1,'calle6',666,9555556,TRUE),
(NULL,9,777777777,'nombre nombre7','apellidoP7','apellidoM7',1,'calle7',777,9555557,FALSE),
(NULL,10,888888888,'nombre nombre8','apellidoP8','apellidoM8',1,'calle8',888,9555557,TRUE),
(NULL,11,999999999,'nombre nombre9','apellidoP9',DEFAULT,1,'calle9',999,9555558,TRUE),
(NULL,12,111111110,'nombre10','apellidoP10','apellidoM10',8,'calle10',1010,9555510,TRUE),
(NULL,13,222222220,'nombre nombre11','apellidoP11','apellidoM11',1,'calle11',1111,9555511,TRUE),
(NULL,14,333333330,'nombre nombre12','apellidoP12','apellidoM12',10,'calle11',1212,9555512,TRUE);

INSERT INTO JMMV_bicicletas
VALUES
(NULL,'bici1',6,FALSE,FALSE),
(NULL,'bici2',5,FALSE,FALSE),
(NULL,'bici3',4,TRUE,TRUE),
(NULL,'bici4',3,TRUE,TRUE),
(NULL,'bici5',2,TRUE,TRUE),
(NULL,'bici6',1,TRUE,TRUE),
(NULL,'bici7',6,TRUE,TRUE),
(NULL,'bici8',5,TRUE,TRUE),
(NULL,'bici9',4,TRUE,TRUE),
(NULL,'bici10',3,TRUE,TRUE),
(NULL,'bici11',2,TRUE,TRUE),
(NULL,'bici12',1,TRUE,TRUE),
(NULL,'bici13',6,FALSE,TRUE),
(NULL,'bici14',5,TRUE,TRUE),
(NULL,'bici15',4,TRUE,TRUE),
(NULL,'bici16',3,TRUE,TRUE),
(NULL,'bici17',2,TRUE,TRUE),
(NULL,'bici18',1,TRUE,TRUE),
(NULL,'bici19',6,TRUE,TRUE),
(NULL,'bici20',5,TRUE,TRUE)
;

INSERT INTO JMMV_reservas
VALUES
(NULL,3,2,'2025-10-01','2025-10-08',TRUE),
(NULL,4,1,'2025-10-02','2025-10-09',TRUE),
(NULL,5,5,'2025-10-02','2025-10-09',TRUE),
(NULL,6,1,'2025-10-03','2025-10-10',TRUE),
(NULL,3,2,'2025-10-04','2025-10-11',TRUE),
(NULL,8,3,'2025-10-04','2025-10-11',TRUE),
(NULL,9,4,'2025-10-05','2025-10-12',TRUE),
(NULL,10,5,'2025-10-06','2025-10-13',TRUE),
(NULL,11,6,'2025-10-06','2025-10-13',TRUE),
(NULL,12,7,'2025-10-07','2025-10-14',TRUE),
(NULL,3,8,'2025-10-08','2025-10-15',TRUE),
(NULL,10,9,'2025-10-08','2025-12-15',TRUE),
(NULL,4,10,'2025-10-09','2025-10-16',TRUE),
(NULL,5,11,'2025-10-10','2025-10-17',TRUE),
(NULL,6,12,'2025-10-10','2025-10-17',TRUE),
(NULL,3,13,'2025-10-11','2025-10-18',TRUE),
(NULL,8,14,'2025-10-30','2025-11-06',TRUE),
(NULL,4,13,'2025-12-08','2025-10-11',TRUE)
;