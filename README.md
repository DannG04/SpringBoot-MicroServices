# Spring Boot Microservices - Test Project

> ⚠️ **Nota / Note**: Este es un proyecto de prueba para demostración y aprendizaje de arquitectura de microservicios. / This is a test project for demonstration and learning purposes of microservices architecture.

## 📋 Descripción / Description

Este proyecto es una implementación de prueba de una arquitectura de microservicios utilizando Spring Boot y Spring Cloud. El sistema simula una aplicación educativa que gestiona cursos y estudiantes.

*This project is a test implementation of a microservices architecture using Spring Boot and Spring Cloud. The system simulates an educational application that manages courses and students.*

## 🏗️ Arquitectura del Sistema / System Architecture

El sistema está compuesto por 5 microservicios principales que trabajan en conjunto:

*The system is composed of 5 main microservices working together:*

```
┌─────────────────────────────────────────────────────────────┐
│                        Client / Usuario                      │
└───────────────────────────┬─────────────────────────────────┘
                            │
                            ▼
┌───────────────────────────────────────────────────────────────┐
│                    API Gateway (Spring Cloud Gateway)         │
│                       Entry Point / Punto de Entrada          │
└───────────────────────────┬───────────────────────────────────┘
                            │
                            ▼
┌───────────────────────────────────────────────────────────────┐
│              Eureka Server (Service Discovery)                │
│                    Registry / Registro de Servicios           │
└─────────────┬─────────────────────────────┬───────────────────┘
              │                             │
    ┌─────────▼──────────┐       ┌─────────▼──────────┐
    │  Student Service   │◄──────┤  Course Service    │
    │   (MySQL - 8090)   │       │ (PostgreSQL - 9090)│
    └────────────────────┘       └────────────────────┘
              ▲                             ▲
              │                             │
              └─────────────┬───────────────┘
                            │
                    ┌───────▼────────┐
                    │ Config Server  │
                    │   (Port 8888)  │
                    └────────────────┘
```

## 🔧 Componentes / Components

### 1. **Eureka Server** (microservice-eureka)
- **Puerto / Port**: 8761
- **Función / Function**: Servidor de descubrimiento de servicios. Todos los microservicios se registran aquí para que puedan descubrirse entre sí.
- *Service discovery server. All microservices register here so they can discover each other.*

### 2. **API Gateway** (microservice-gateway)
- **Función / Function**: Punto de entrada único para todas las peticiones del cliente. Enruta las solicitudes a los microservicios apropiados.
- *Single entry point for all client requests. Routes requests to appropriate microservices.*

### 3. **Config Server** (microservice-config)
- **Puerto / Port**: 8888
- **Función / Function**: Servidor de configuración centralizada. Gestiona la configuración de todos los microservicios.
- *Centralized configuration server. Manages configuration for all microservices.*

### 4. **Student Service** (microservice-student)
- **Puerto / Port**: 8090
- **Base de Datos / Database**: MySQL
- **Función / Function**: Gestiona toda la información relacionada con estudiantes.
- *Manages all student-related information.*
- **Entidad Principal / Main Entity**: Student (id, name, lastName, email, courseId)

### 5. **Course Service** (microservice-course)
- **Puerto / Port**: 9090
- **Base de Datos / Database**: PostgreSQL
- **Función / Function**: Gestiona la información de cursos y se comunica con Student Service para obtener estudiantes por curso.
- *Manages course information and communicates with Student Service to get students by course.*
- **Entidad Principal / Main Entity**: Course (id, name, teacher)

## 💻 Stack Tecnológico / Technology Stack

- **Framework**: Spring Boot 3.5.4
- **Lenguaje / Language**: Java 17
- **Build Tool**: Maven
- **Service Discovery**: Spring Cloud Netflix Eureka
- **API Gateway**: Spring Cloud Gateway
- **Configuration**: Spring Cloud Config Server
- **Databases**: 
  - MySQL (Student Service)
  - PostgreSQL (Course Service)
- **ORM**: Spring Data JPA / Hibernate
- **Communication**: OpenFeign (Inter-service communication)
- **Utilities**: Lombok

## 🚀 Configuración y Ejecución / Setup and Running

### Prerrequisitos / Prerequisites

1. Java 17 o superior / Java 17 or higher
2. Maven 3.6+
3. MySQL Server
4. PostgreSQL Server

### Base de Datos / Database Setup

**MySQL** (para Student Service / for Student Service):
```sql
CREATE DATABASE studentDb;
```

**PostgreSQL** (para Course Service / for Course Service):
```sql
CREATE DATABASE coursesDb;
```

### Orden de Ejecución / Running Order

Es importante ejecutar los servicios en el siguiente orden:
*It's important to run the services in the following order:*

1. **Config Server** (puerto / port 8888)
   ```bash
   cd microservice-config
   mvn spring-boot:run
   ```

2. **Eureka Server** (puerto / port 8761)
   ```bash
   cd microservice-eureka
   mvn spring-boot:run
   ```

3. **Student Service** (puerto / port 8090)
   ```bash
   cd microservice-student
   mvn spring-boot:run
   ```

4. **Course Service** (puerto / port 9090)
   ```bash
   cd microservice-course
   mvn spring-boot:run
   ```

5. **API Gateway**
   ```bash
   cd microservice-gateway
   mvn spring-boot:run
   ```

### Verificar el Estado / Verify Status

- **Eureka Dashboard**: http://localhost:8761
- Aquí puedes ver todos los servicios registrados / Here you can see all registered services

## 📡 Comunicación entre Servicios / Inter-Service Communication

El **Course Service** se comunica con el **Student Service** a través de **OpenFeign** para obtener la lista de estudiantes asociados a un curso específico. Esta comunicación utiliza el servicio de descubrimiento de Eureka para resolver las direcciones de los servicios.

*The **Course Service** communicates with the **Student Service** through **OpenFeign** to get the list of students associated with a specific course. This communication uses Eureka's discovery service to resolve service addresses.*

## 📝 Características / Features

- ✅ Arquitectura de microservicios / Microservices architecture
- ✅ Service discovery con Eureka / Service discovery with Eureka
- ✅ API Gateway centralizado / Centralized API Gateway
- ✅ Configuración centralizada / Centralized configuration
- ✅ Comunicación entre servicios / Inter-service communication
- ✅ Múltiples bases de datos / Multiple databases
- ✅ Alta cohesión y bajo acoplamiento / High cohesion and low coupling

## ⚠️ Notas Importantes / Important Notes

- Este es un **proyecto de prueba** con fines educativos / This is a **test project** for educational purposes
- Las credenciales de base de datos están en el código (no usar en producción) / Database credentials are hardcoded (do not use in production)
- No incluye seguridad avanzada ni autenticación / Does not include advanced security or authentication
- No está optimizado para producción / Not optimized for production

## 📚 Patrones Implementados / Implemented Patterns

1. **Service Registry Pattern**: Eureka Server
2. **API Gateway Pattern**: Spring Cloud Gateway
3. **Externalized Configuration Pattern**: Config Server
4. **Database per Service Pattern**: Cada microservicio tiene su propia BD / Each microservice has its own DB

## 🔍 Endpoints Principales / Main Endpoints

### Student Service
- `GET /api/student/all` - Obtener todos los estudiantes / Get all students
- `GET /api/student/search/{id}` - Buscar estudiante por ID / Search student by ID
- `POST /api/student/create` - Crear estudiante / Create student
- `GET /api/student/search-by-course/{courseId}` - Buscar estudiantes por curso / Search students by course

### Course Service
- `GET /api/course/all` - Obtener todos los cursos / Get all courses
- `GET /api/course/search/{id}` - Buscar curso por ID / Search course by ID
- `POST /api/course/create` - Crear curso / Create course
- `GET /api/course/search-student/{courseId}` - Buscar estudiantes de un curso / Search students in a course

## 📄 Licencia / License

Este es un proyecto de prueba y aprendizaje. Úsalo libremente para propósitos educativos.

*This is a test and learning project. Use it freely for educational purposes.*

---

**Desarrollado como proyecto de demostración de microservicios con Spring Boot**

*Developed as a Spring Boot microservices demonstration project*
