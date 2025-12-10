#Creación de base de datos
CREATE DATABASE sistema_reserva_bicicletas;

#use
USE sistema_reserva_bicicletas;

#Creación de tablas
CREATE TABLE JMMV_usuarios(
    JMMV_usuarios_id_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    JMMV_usuarios_nom_usuario VARCHAR(75) NOT NULL UNIQUE,
    JMMV_usuarios_contrasena VARCHAR(20) NOT NULL,
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
    JMMV_clientes_correo VARCHAR(50) NOT NULL UNIQUE,
    JMMV_clientes_run INT NOT NULL UNIQUE,
    JMMV_clientes_nombres VARCHAR(150) NOT NULL,
    JMMV_clientes_apellido_paterno VARCHAR(75) NOT NULL,
    JMMV_clientes_apellido_materno VARCHAR(75) DEFAULT 'NA',    
    JMMV_clientes_id_comuna INT NOT NULL,
    JMMV_clientes_calle VARCHAR(75) NOT NULL,
    JMMV_clientes_num_calle INT NOT NULL,
    JMMV_clientes_telefono INT NOT NULL,
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
(NULL,'Urbana',TRUE),
(NULL,'Ruta',TRUE),
(NULL,'Montaña',TRUE),
(NULL,'Triatlon',TRUE),
(NULL,'Plegable',TRUE),
(NULL,'BMXX',TRUE),
(NULL,'Gravel',TRUE),
(NULL,'EBikes',TRUE)
;

INSERT INTO JMMV_usuarios
VALUES
(NULL,'admin','123',1,TRUE),
(NULL,'admin2','1234Abcd',1,TRUE),
(NULL,'usuarioC1','Pass1111',2,TRUE),
(NULL,'usuarioC2','Pass2222',2,TRUE),
(NULL,'usuarioC3','Pass3333',2,TRUE),
(NULL,'usuarioC4','Pass4444',2,TRUE),
(NULL,'usuarioC5','Pass5555',2,TRUE),
(NULL,'usuarioC6','Pass6666',2,TRUE),
(NULL,'usuarioC7','Pass7777',2,TRUE),
(NULL,'usuarioC8','Pass8888',2,TRUE),
(NULL,'usuarioC9','Pass9999',2,TRUE),
(NULL,'usuarioC10','Pass1010',2,TRUE),
(NULL,'usuarioC11','Pass1111',2,TRUE),
(NULL,'usuarioC12','Pass1212',2,TRUE),
(NULL,'usuarioC13','Pass1313',2,TRUE),
(NULL,'usuarioC14','Pass1414',2,TRUE),
(NULL,'usuarioC15','Pass1515',2,TRUE),
(NULL,'usuarioC16','Pass1616',2,TRUE),
(NULL,'usuarioC17','Pass1717',2,TRUE),
(NULL,'usuarioC18','Pass1818',2,TRUE),
(NULL,'usuarioC19','Pass1919',2,TRUE),
(NULL,'usuarioC20','Pass2020',2,TRUE)
;

INSERT INTO JMMV_clientes
VALUES
(NULL,3,'correo1@dominio.com',197997214,'Juan Carlos','Mora','Isla',9,'Lautaro',111,963258741,TRUE),
(NULL,4,'correo2@dominio.com',85792520,'Rodrigo Marcos','Carrasco','Pedrero',1,'Arturo Prat',222,987654321,TRUE),
(NULL,5,'correo3@dominio.com',185455793,'Natalia Andrea','Silva','Rojas',1,'Los Alerces',333,985263741,FALSE),
(NULL,6,'correo4@dominio.com',117678148,'Carla Alicia','Lastra','Montes',1,'Santo Domingo',444,965487321,TRUE),
(NULL,7,'correo5@dominio.com',144361857,'Pedro Pablo','Peirano','Olate',1,'Obispo',555,974185263,TRUE),
(NULL,8,'correo6@dominio.com',140257974,'Carla Alicia','Lastra','Montes',1,'Alameda',666,978456123,TRUE),
(NULL,9,'correo7@dominio.com',125794246,'Alonso Felipe','Oliva','Olivos',1,'Aromos',777,952147863,FALSE),
(NULL,10,'correo8@dominio.com',68364477,'Sandra Camila','Parra','Salgado',1,'Las Rosas',888,957846321,TRUE),
(NULL,11,'correo9@dominio.com',555555555,'Alex David','Smith',DEFAULT,1,'Arauco',999,956123478,TRUE),
(NULL,12,'correo10@dominio.com',136270508,'Roberto Javier','Escobar','Vidal',8,'Los Copihues',1010,951847623,TRUE),
(NULL,13,'correo11@dominio.com',121839857,'Amelia Fernanda','Molina','Soto',1,'Agustinas',1111,954613287,TRUE),
(NULL,14,'correo12@dominio.com',202554342,'Patricio','Bravo','Araya',10,'Independencia',1212,963147852,TRUE),
(NULL,15,'correo13@dominio.com',259885167,'Ausguste','Smith',DEFAULT,3,'Violetas',1350,956177478,TRUE),
(NULL,16,'correo14@dominio.com',147339755,'Gladys Alejandra','Contreras','Labra',1,'Los Peumos',2314,976123478,TRUE),
(NULL,17,'correo15@dominio.com',92212394,'Felipe Alberto','Vargas','Vidal',8,'Los Mares',85,951847223,TRUE),
(NULL,18,'correo16@dominio.com',272180458,'Paulina Alicia','Molina','Contreras',1,'Alameda',65,954611287,TRUE),
(NULL,19,'correo17@dominio.com',203823061,'Jorge Domingo','Rosales','Araya',10,'Conquistadores',142,963197852,TRUE),
(NULL,20,'correo18@dominio.com',113716487,'Pablo','Jorquera','Rojas',3,'Libertad',35,956177778,TRUE),
(NULL,21,'correo19@dominio.com',209783886,'Isabel Margarita','Leiva','Sutil',1,'Patricio Lynch',41,976823478,TRUE),
(NULL,22,'correo20@dominio.com',155453737,'Carolina Andrea','Jaque','Lillo',1,'Baquedano',74,977123478,TRUE);

INSERT INTO JMMV_bicicletas
VALUES
(NULL,'Range',6,FALSE,FALSE),
(NULL,'Sight',5,FALSE,FALSE),
(NULL,'Charger',4,TRUE,TRUE),
(NULL,'Shore',3,TRUE,TRUE),
(NULL,'Fluid',2,TRUE,TRUE),
(NULL,'Search',1,TRUE,TRUE),
(NULL,'Storm',6,TRUE,TRUE),
(NULL,'Rocket',5,TRUE,TRUE),
(NULL,'Scale',4,TRUE,TRUE),
(NULL,'Sport',3,TRUE,TRUE),
(NULL,'Strider',2,TRUE,TRUE),
(NULL,'Phoenix',1,TRUE,TRUE),
(NULL,'Nomad',6,FALSE,TRUE),
(NULL,'Spire',5,TRUE,TRUE),
(NULL,'Bronson',4,TRUE,TRUE),
(NULL,'Access',3,TRUE,TRUE),
(NULL,'Collosus',2,TRUE,TRUE),
(NULL,'Rival',1,TRUE,TRUE),
(NULL,'New Pro',6,TRUE,TRUE),
(NULL,'Stereo',5,TRUE,TRUE),
(NULL,'Laser',4,TRUE,TRUE),
(NULL,'Limbo',3,TRUE,TRUE),
(NULL,'Master',2,TRUE,TRUE),
(NULL,'Fallout',1,TRUE,TRUE),
(NULL,'Goat',6,TRUE,TRUE),
(NULL,'Sparks',5,TRUE,TRUE)
;

INSERT INTO JMMV_reservas
VALUES
(NULL,2,3,'2025-11-01','2025-11-08',TRUE),
(NULL,3,6,'2025-11-02','2025-11-09',TRUE),
(NULL,4,4,'2025-12-02','2025-12-09',TRUE),
(NULL,6,7,'2025-12-03','2025-12-10',TRUE),
(NULL,7,8,'2025-12-04','2025-12-11',TRUE),
(NULL,8,9,'2025-12-04','2025-12-11',TRUE),
(NULL,10,10,'2025-12-05','2025-12-12',TRUE),
(NULL,11,11,'2025-12-04','2025-12-11',TRUE),
(NULL,12,12,'2025-12-04','2025-12-11',TRUE),
(NULL,13,13,'2025-12-05','2025-12-12',TRUE),
(NULL,14,3,'2025-12-06','2025-12-13',TRUE),
(NULL,15,4,'2025-12-06','2025-12-13',TRUE),
(NULL,16,5,'2025-12-07','2025-12-14',TRUE),
(NULL,17,6,'2025-12-07','2025-12-14',TRUE),
(NULL,18,7,'2025-12-07','2025-12-14',TRUE),
(NULL,19,8,'2025-12-07','2025-12-14',TRUE),
(NULL,20,9,'2025-12-08','2025-12-15',TRUE),
(NULL,1,10,'2025-12-08','2025-12-15',TRUE),
(NULL,2,11,'2025-12-08','2025-12-15',TRUE),
(NULL,3,12,'2025-12-08','2025-12-15',TRUE),
(NULL,4,13,'2026-12-08','2025-12-15',TRUE),
(NULL,17,17,'2025-12-09','2025-12-16',TRUE),
(NULL,7,18,'2025-12-10','2025-12-17',TRUE),
(NULL,8,19,'2025-12-10','2025-12-17',TRUE),
(NULL,9,21,'2025-12-10','2025-12-17',TRUE),
(NULL,20,20,'2025-12-10','2025-12-17',TRUE),
(NULL,11,22,'2026-01-10','2026-01-17',TRUE)
;

##########################################
## 3 Modificaciones de datos de usuarios: CLIENTES
UPDATE jmmv_clientes
SET JMMV_clientes_calle = 'Lautaro'
WHERE JMMV_clientes_id_cliente = 5;

UPDATE jmmv_clientes
SET JMMV_clientes_num_calle = 525
WHERE JMMV_clientes_id_cliente = 2;

UPDATE jmmv_clientes
SET JMMV_clientes_telefono = 987654521
WHERE JMMV_clientes_id_cliente = 1;


## 2 Modificaciones de datos de categorías
UPDATE JMMV_tipos_bicicletas
SET JMMV_tipos_bicicletas_nombre = 'BMX'
WHERE JMMV_tipos_bicicletas_id_tipo_bicicleta = 6;

UPDATE JMMV_tipos_bicicletas
SET JMMV_tipos_bicicletas_nombre = 'EBike'
WHERE JMMV_tipos_bicicletas_id_tipo_bicicleta = 8;


## 3 Modificaciones de datos de reservas
UPDATE JMMV_reservas
SET JMMV_reservas_id_cliente = 12
WHERE JMMV_reservas_id_reserva = 16;

UPDATE JMMV_reservas
SET JMMV_reservas_id_bicicleta = 3
WHERE JMMV_reservas_id_reserva = 14;

UPDATE JMMV_reservas
SET JMMV_reservas_id_cliente = 4
WHERE JMMV_reservas_id_reserva = 10;


## 3 Eliminaciones de datos de reservas
#borrado físico no aplicado
#DELETE FROM jmmv_reservas WHERE jmmv_reservas_id_cliente = 6;
#DELETE FROM jmmv_reservas WHERE jmmv_reservas_id_cliente = 11;
#DELETE FROM jmmv_reservas WHERE jmmv_reservas_id_cliente = 8;


## 3 Eliminaciones de datos de usuarios: CLIENTES
#borrado físico no aplicado
#DELETE FROM jmmv_clientes WHERE jmmv_clientes_id_cliente = 6;
#DELETE FROM jmmv_clientes WHERE jmmv_clientes_id_cliente = 11;
#DELETE FROM jmmv_clientes WHERE jmmv_clientes_id_cliente = 8;


## 6 Consultas utilizadas en la aplicación
SELECT
b.JMMV_bicicletas_id_bicicleta AS id,
b.JMMV_bicicletas_nombre AS nombre,
b.JMMV_bicicletas_id_tipo_bicicleta AS tipo,
b.JMMV_bicicletas_esta_disponible AS disponibilidad
FROM JMMV_bicicletas AS b
JOIN JMMV_tipos_bicicletas AS t ON b.JMMV_bicicletas_id_tipo_bicicleta = t.JMMV_tipos_bicicletas_id_tipo_bicicleta
WHERE b.JMMV_bicicletas_esta_activo = TRUE
ORDER BY b.JMMV_bicicletas_id_bicicleta ASC;

SELECT b.JMMV_bicicletas_nombre AS nombre
FROM JMMV_bicicletas AS b
WHERE b.JMMV_bicicletas_esta_activo = TRUE
ORDER BY b.JMMV_bicicletas_nombre ASC;

SELECT t.JMMV_tipos_bicicletas_nombre AS tipo_bicicleta
FROM JMMV_tipos_bicicletas t
WHERE t.JMMV_tipos_bicicletas_esta_activo = TRUE
ORDER BY t.JMMV_tipos_bicicletas_nombre ASC;

SELECT
c.JMMV_clientes_id_cliente AS id_cliente,
c.JMMV_clientes_id_usuario AS id_usuario,
c.JMMV_clientes_run AS run,
c.JMMV_clientes_nombres AS nombres,
c.JMMV_clientes_apellido_paterno AS ap_pat,
c.JMMV_clientes_apellido_materno AS ap_mat,
c.JMMV_clientes_id_comuna AS comuna,
c.JMMV_clientes_calle AS calle,
c.JMMV_clientes_num_calle AS numero,
c.JMMV_clientes_telefono AS telefono,
u.JMMV_usuarios_nom_usuario AS nombre_usuario,
u.JMMV_usuarios_contrasena AS contrasena,
c.JMMV_clientes_correo AS correo
FROM JMMV_clientes c
JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario
WHERE c.JMMV_clientes_esta_activo = TRUE
ORDER BY c.JMMV_clientes_id_cliente ASC;

SELECT
c.JMMV_comunas_nombre AS comuna
FROM JMMV_comunas c
WHERE c.JMMV_comunas_esta_activo = TRUE
ORDER BY JMMV_comunas_nombre ASC;

SELECT CONCAT(
JMMV_clientes_nombres,' ',JMMV_clientes_apellido_paterno, 
IF(JMMV_clientes_apellido_materno = 'NA','',CONCAT(' ',JMMV_clientes_apellido_materno))) AS nombre_completo
FROM JMMV_clientes c
WHERE c.JMMV_clientes_esta_activo = TRUE
ORDER BY c.JMMV_clientes_nombres ASC;