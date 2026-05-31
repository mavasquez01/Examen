# Gestión de Bicicletas

Sistema de gestión de bicicletas desarrollado con Java Swing y base de datos SQL.

## Descripción

Aplicación de escritorio para administrar un inventario de bicicletas, permitiendo:
- Registrar y gestionar bicicletas
- Consultar disponibilidad
- Realizar reservas
- Administrar el estado y mantenimiento de las bicicletas
- Interfaz gráfica intuitiva con Java Swing

## Características

- Interfaz gráfica de usuario con Java Swing
- Gestión de inventario de bicicletas
- Control de disponibilidad
- Sistema de reservas
- Base de datos SQL integrada
- Registro de mantenimiento

## Requisitos

- JDK 8 o superior
- Maven 3.6+
- Base de datos SQL (según configuración en `BBDD_Mardonez_Vasquez.sql`)

## Instalación

```bash
git clone https://github.com/mavasquez01/Gestion_Bicicleta.git
cd Gestion_Bicicleta
mvn clean install
```
El proyecto fue creado en Netbeans pero puede ser ejecutado directamente desde tu IDE (NetBeans, Eclipse, IntelliJ):

1. Abrir el proyecto
2. Click derecho en el proyecto → Run

## Estructura del Proyecto

```
├── src/                              # Código fuente Java
├── pom.xml                          # Configuración Maven
├── nbactions.xml                    # Configuración NetBeans
├── BBDD_Mardonez_Vasquez.sql       # Script de base de datos
├── MR_Mardones_Vasquez.drawio      # Diagrama del modelo relacional
└── README.md
```

## Base de Datos

Para inicializar la base de datos, ejecutar el script:
```bash
BBDD_Mardonez_Vasquez.sql
```
## Demo

[![Watch the video](https://youtube.com)](https://youtu.be/IxUAuAt7R_g)

## Imagenes
1. Login
   
<img width="412" height="309" alt="Captura de pantalla 2026-05-31 131535" src="https://github.com/user-attachments/assets/3a381bd6-857c-4f1a-a0ee-8320f72ff034" />

2. Menu Principal

<img width="712" height="399" alt="Captura de pantalla 2026-05-31 131543" src="https://github.com/user-attachments/assets/f0837f17-14d9-4899-b43a-5fc98f7301eb" />

3.Gestión Usuarios

<img width="754" height="553" alt="Captura de pantalla 2026-05-31 131547" src="https://github.com/user-attachments/assets/830761ff-4e27-4f2d-87f1-8f129bb54370" />

4.Listado Usuarios

<img width="1114" height="725" alt="Captura de pantalla 2026-05-31 131553" src="https://github.com/user-attachments/assets/693b146a-be42-43b2-af4d-4159118100e7" />

5.Edición Usuario

<img width="750" height="560" alt="Captura de pantalla 2026-05-31 131559" src="https://github.com/user-attachments/assets/b36eb3c8-d107-4065-8bdf-35596a40887e" />

6.Eliminación Usuario

<img width="749" height="553" alt="Captura de pantalla 2026-05-31 131606" src="https://github.com/user-attachments/assets/00521190-7ff2-4362-a752-e9a98d5e14ea" />

7.Gestión Bicicleta

<img width="589" height="429" alt="Captura de pantalla 2026-05-31 131614" src="https://github.com/user-attachments/assets/3530e356-d2ef-4ef6-a515-9b47badc950f" />

8.Listado Bicicleta

<img width="842" height="658" alt="Captura de pantalla 2026-05-31 131626" src="https://github.com/user-attachments/assets/714c97e2-c0b3-4e05-bc39-62e64358245d" />

9.Edición Bicicleta

<img width="584" height="429" alt="Captura de pantalla 2026-05-31 131623" src="https://github.com/user-attachments/assets/991e31b0-2bb7-4d82-abd8-33588d01aba2" />

10.Eliminación Bicicleta

<img width="588" height="430" alt="Captura de pantalla 2026-05-31 133016" src="https://github.com/user-attachments/assets/bd38f075-83e4-462d-98d7-0d71a819c840" />

11.Gestión Reservas

<img width="617" height="489" alt="Captura de pantalla 2026-05-31 131632" src="https://github.com/user-attachments/assets/eaa75997-8a1d-46d7-b9b7-f3235225ed26" />

12.Listado Reservas

<img width="989" height="661" alt="Captura de pantalla 2026-05-31 131845" src="https://github.com/user-attachments/assets/de36f18d-f8fe-43b6-89c1-37d5e05415fe" />

13.Edición Reservas

<img width="623" height="490" alt="Captura de pantalla 2026-05-31 131826" src="https://github.com/user-attachments/assets/d1683d47-57ba-4d1d-8914-8879a0176eb8" />

14.Eliminación Reservas

<img width="624" height="487" alt="Captura de pantalla 2026-05-31 133038" src="https://github.com/user-attachments/assets/69994937-d1a9-4700-bf37-bceeddb33140" />


## Autor

mavasquez01 - jmardonez
