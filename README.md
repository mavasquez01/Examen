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

## Autor

mavasquez01 - jmardonez
