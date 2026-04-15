# API Red Neuronal — Sistema de Análisis de Marcha

![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.6-6DB33F?logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.4-6DB33F?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-336791?logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)
![CI](https://img.shields.io/badge/CI-GitHub%20Actions-2088FF?logo=githubactions&logoColor=white)
![Licencia](https://img.shields.io/badge/Licencia-MIT-green)

---

## Descripción del Proyecto

**API Red Neuronal** es un backend basado en microservicios diseñado para la **gestión, monitoreo y análisis de datos inerciales** provenientes de sensores corporales (acelerómetro, giroscopio y magnetómetro) capturados desde dispositivos móviles Android.

El sistema procesa las señales recogidas, extrae características del patrón de marcha y aplica un modelo de **red neuronal (Neuroph 2.98)** para detectar posibles patologías relacionadas con caídas en adultos mayores. Los resultados quedan disponibles para profesionales médicos a través de una API REST documentada con Swagger.

### Flujo principal

```
App Móvil → API Gateway → Microservicios de Gestión → Red Neuronal → Predicción
```

---

## Tabla de Contenidos

- [Características Principales](#características-principales)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Requisitos Previos](#requisitos-previos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso y Ejemplos](#uso-y-ejemplos)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Endpoints de la API](#endpoints-de-la-api)
- [Contribución](#contribución)
- [Licencia](#licencia)
- [Contacto](#contacto)

---

## Características Principales

- **Arquitectura de microservicios** con descubrimiento de servicios (Netflix Eureka) y enrutamiento centralizado (Spring Cloud Gateway).
- **Autenticación y autorización** basada en tokens JWT con roles (`ADMIN`, `SUPER_ADMIN`, usuario de app).
- **Pipeline de análisis de señales**: recepción de datos crudos → procesamiento de señales → extracción de características → inferencia con red neuronal.
- **CRUD completo** para usuarios, participantes/pacientes, registros de datos, señales y patologías.
- **Entrenamiento y predicción** del modelo neuronal a través de endpoints REST dedicados.
- **Documentación Swagger** disponible en cada microservicio (`/swagger-ui.html`).
- **Logging transversal** mediante AOP que persiste eventos en base de datos.
- **CI/CD** con GitHub Actions para compilación automática en cada push a `master`.

---

## Tecnologías Utilizadas

| Categoría | Tecnología |
|---|---|
| Lenguaje | Java 17 |
| Framework principal | Spring Boot 3.3.6 |
| Microservicios | Spring Cloud 2023.0.4 (Gateway, Eureka, Config, OpenFeign) |
| Seguridad | Spring Security + JWT (`java-jwt 4.4.0`) |
| Persistencia | Spring Data JPA + Hibernate + PostgreSQL |
| Red Neuronal | Neuroph 2.98 |
| Serialización | Gson 2.11.0 |
| Documentación API | SpringDoc OpenAPI 2.5.0 (Swagger UI) |
| Tests | JUnit + H2 (base de datos en memoria) |
| Build | Apache Maven 3.x |
| CI/CD | GitHub Actions |

---

## Requisitos Previos

- **JDK 17** o superior
- **Apache Maven 3.8+** (o usar el wrapper `./mvnw` incluido)
- **PostgreSQL 14+** corriendo en `localhost:5432`
- (Opcional) Cliente REST como Postman o curl para probar los endpoints

---

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/<usuario>/API-RedNeuronal-master.git
cd API-RedNeuronal-master
```

### 2. Crear y cargar la base de datos

```bash
# Crear la base de datos
psql -U postgres -c "CREATE DATABASE sensores_inteligentes WITH ENCODING = 'UTF8';"

# Cargar el esquema, procedimientos almacenados y triggers
psql -U postgres -d sensores_inteligentes -f sensor_inteligente.sql
```

### 3. Compilar todos los módulos

```bash
mvn clean install -DskipTests
```

### 4. Iniciar los servicios en orden

Cada microservicio debe ejecutarse en una terminal separada respetando el siguiente orden:

```bash
# 1. Registro de servicios (Eureka)
mvn -pl microservicio-eureka spring-boot:run

# 2. Servidor de configuración
mvn -pl microservicio-config spring-boot:run

# 3. API Gateway
mvn -pl microservicio-gateway spring-boot:run

# 4. Microservicios de negocio (en cualquier orden)
mvn -pl microservicio-gestion/microservicio-usuario spring-boot:run
mvn -pl microservicio-gestion/microservicio-participante spring-boot:run
mvn -pl microservicio-gestion/microservicio-dato spring-boot:run
mvn -pl microservicio-gestion/microservicio-patologia spring-boot:run
mvn -pl microservicio-gestion/microservicio-sennal spring-boot:run
mvn -pl microservicio-gestion/microservicio-redneuronal spring-boot:run
```

Una vez en marcha, el panel de Eureka está disponible en `http://localhost:8761`.

---

## Configuración

### Variables de entorno

Las credenciales de base de datos se inyectan mediante variables de entorno en producción. En desarrollo local se usan los valores por defecto del `application.properties` de cada servicio.

| Variable | Descripción | Valor por defecto (dev) |
|---|---|---|
| `SensoresBDURL` | URL JDBC de la BD principal | `jdbc:postgresql://localhost:5432/sensores_inteligentes` |
| `RedNeuronalBDURL` | URL JDBC para el servicio de red neuronal | `jdbc:postgresql://localhost:5432/sensores_inteligentes` |
| `DB_USER` | Usuario de PostgreSQL | `postgres` |
| `BD_PASS` | Contraseña de PostgreSQL | `0000` |

> **Importante:** Modifica las credenciales antes de desplegar en cualquier entorno no local.

### Puertos de los servicios

| Servicio | Puerto |
|---|---|
| API Gateway | 8081 |
| Eureka (panel web) | 8761 |
| microservicio-usuario | 8085 |
| microservicio-participante | 8086 |
| microservicio-dato | 8087 |
| microservicio-patologia | 8088 |
| microservicio-sennal | 8089 |
| microservicio-redneuronal | 8092 |

---

## Uso y Ejemplos

Todas las peticiones se realizan a través del Gateway (`http://localhost:8081`). La mayoría de los endpoints requieren un token JWT en la cabecera `Authorization: Bearer <token>`.

### Autenticarse y obtener un token

```bash
curl -X POST http://localhost:8081/api/login/ \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "tu_password"}'
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Crear un participante

```bash
curl -X POST http://localhost:8081/api/v1/participante/create \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Juan",
    "apellido": "Pérez",
    "ci": "1234567890",
    "edad": 72
  }'
```

### Enviar datos de señal y obtener predicción

```bash
# 1. Crear registro de datos
curl -X POST http://localhost:8081/api/v1/datoparticipante/create \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"idParticipante": 1, "fecha": "2025-04-15"}'

# 2. Insertar señal (acelerómetro, giroscopio, magnetómetro)
curl -X POST http://localhost:8081/api/v1/sennal/create \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"idDato": 1, "acelerometroX": 0.12, "acelerometroY": 9.81, "acelerometroZ": 0.05, ...}'

# 3. Solicitar predicción
curl -X POST http://localhost:8081/api/v1/modelo/predecir \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"idDato": 1}'
```

### Ejecutar tests

```bash
# Todos los módulos
mvn test

# Un módulo específico
mvn -pl microservicio-gestion/microservicio-usuario test
```

---

## Estructura del Proyecto

```
API-RedNeuronal-master/
│
├── pom.xml                            # POM raíz (agregador)
├── sensor_inteligente.sql             # Esquema PostgreSQL + procedimientos + triggers
├── documentacion.md                   # Documentación técnica del proyecto (ES)
│
├── .github/workflows/
│   └── github-actions-demo.yml        # Pipeline CI/CD
│
├── microservicio-eureka/              # Registro de servicios :8761
├── microservicio-config/              # Servidor de configuración centralizada
├── microservicio-gateway/             # API Gateway (enrutamiento) :8081
│
└── microservicio-gestion/             # POM padre de los servicios de negocio
    ├── microservicio-usuario/         # Auth + JWT + gestión de usuarios :8085
    ├── microservicio-participante/    # Gestión de pacientes/participantes :8086
    ├── microservicio-dato/            # Registros de datos del sensor :8087
    ├── microservicio-patologia/       # Clasificación de patologías :8088
    ├── microservicio-sennal/          # Procesamiento de señales :8089
    └── microservicio-redneuronal/     # Inferencia con red neuronal :8092
        └── libs/neuroph-2.98/         # JARs de Neuroph (scope system)
```

Cada microservicio de gestión sigue la misma organización interna de paquetes:

```
com.example.<servicio>/
├── config/          # Seguridad, CORS, Swagger, TokenService
├── core/
│   ├── controller/  # Endpoints REST
│   ├── dto/         # Objetos de transferencia de datos
│   ├── entities/    # Entidades JPA
│   ├── repository/  # Repositorios Spring Data
│   ├── service/     # Interfaces de negocio
│   ├── serviceimpl/ # Implementaciones
│   ├── feingclient/ # Clientes Feign para comunicación interna
│   └── exception/   # Excepciones y manejadores globales
└── aspect/          # AOP: logging transversal
```

---

## Endpoints de la API

Todos los endpoints se acceden a través del Gateway en `http://localhost:8081`.

### Autenticación y Usuarios (`/api/**` → :8085)

| Método | Ruta | Auth | Descripción |
|---|---|---|---|
| `POST` | `/api/login/` | No | Iniciar sesión y obtener token JWT |
| `GET` | `/api/v1/token/` | Sí | Validar token de microservicio |
| `POST` | `/api/v1/usuarioApp/create` | No | Registrar usuario desde la app móvil |
| `POST` | `/api/v1/usuario/create` | Admin | Crear usuario (admin/super-admin) |
| `GET` | `/api/v1/usuario/all` | Admin | Listar todos los usuarios |
| `PATCH` | `/api/v1/usuario/obtenerUsuario/{id}` | Admin | Obtener usuario por ID |
| `PUT` | `/api/v1/usuario/update/{id}` | Admin | Actualizar usuario |
| `DELETE` | `/api/v1/usuario/delete/{id}` | Admin | Eliminar usuario |

### Participantes (`/api/v1/participante/**` → :8086)

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/participante/create` | Crear participante/paciente |
| `GET` | `/api/v1/participante/all` | Listar todos los participantes |
| `GET` | `/api/v1/participante/getParticipante/{id}` | Obtener participante por ID |
| `GET` | `/api/v1/participante/obtenerParticipanteXCi/{ci}` | Buscar por cédula de identidad |
| `GET` | `/api/v1/participante/obtenerParticipanteXUsuario/{id}` | Participantes de un usuario |
| `GET` | `/api/v1/participante/obtenerDatoXParticipante/{id}` | Registros de datos de un participante |
| `GET` | `/api/v1/participante/getIdbyParticipantebyCi/{ci}` | Obtener ID por cédula |
| `PUT` | `/api/v1/participante/update/{id}` | Actualizar participante |
| `DELETE` | `/api/v1/participante/delete/{id}` | Eliminar participante |

### Datos del Sensor (`/api/v1/datoparticipante/**` → :8087)

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/datoparticipante/create` | Crear registro de sesión de captura |
| `GET` | `/api/v1/datoparticipante/allbyParticipante/{id}` | Todos los datos de un participante |
| `PATCH` | `/api/v1/datoparticipante/obtenerDato/{id}` | Obtener dato por ID |
| `GET` | `/api/v1/datoparticipante/findbyid/{id}` | Verificar existencia de registro |
| `GET` | `/api/v1/datoparticipante/obtenerIdbyparticipanteAndFecha/idParticipante={id}&fecha={fecha}` | Buscar por participante y fecha |
| `PUT` | `/api/v1/datoparticipante/update/{id}` | Actualizar registro |
| `DELETE` | `/api/v1/datoparticipante/delete/{id}` | Eliminar registro |

### Patologías (`/api/v1/patologia/**` → :8088)

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/patologia/create` | Crear patología |
| `POST` | `/api/v1/patologia/createrelacion` | Asociar dato ↔ patología |
| `GET` | `/api/v1/patologia/all` | Listar todas las patologías |
| `GET` | `/api/v1/patologia/obtenerPatologiaXDato/{id_dato}` | Patologías de un registro |
| `GET` | `/api/v1/patologia/obtenerIdbyNombre/{nombre}` | Obtener ID por nombre |
| `PUT` | `/api/v1/patologia/update/{idpatologia}` | Actualizar patología |
| `DELETE` | `/api/v1/patologia/delete/{idpatologia}` | Eliminar patología |
| `DELETE` | `/api/v1/patologia/eliminarrelacion/iddato={iddato}&fecha={fecha}` | Eliminar relación dato-patología |

### Señales (`/api/v1/sennal/**` → :8089)

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/sennal/create` | Insertar muestra de señal IMU |
| `GET` | `/api/v1/sennal/findAll/{iddato}` | Obtener todas las señales de un dato |
| `DELETE` | `/api/v1/sennal/delete/{idSennal}` | Eliminar señal |

### Red Neuronal (`/api/v1/modelo/**` → :8092)

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/api/v1/modelo/crear-inicial` | Crear modelo inicial con datos existentes |
| `POST` | `/api/v1/modelo/entrenar` | Reentrenar el modelo con nuevos datos |
| `POST` | `/api/v1/modelo/predecir` | Obtener predicción de patología de marcha |

> La documentación interactiva Swagger de cada servicio está disponible en `http://localhost:<puerto>/swagger-ui.html`.

---

## Contribución

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad o corrección: `git checkout -b feature/mi-funcionalidad`.
3. Realiza tus cambios y asegúrate de que los tests pasen: `mvn test`.
4. Sube tu rama: `git push origin feature/mi-funcionalidad`.
5. Abre un Pull Request describiendo los cambios realizados.

---

## Licencia

Distribuido bajo la licencia **MIT**. Consulta el archivo `LICENSE` para más detalles.

---

## Contacto

**Rayner Alejandro Soto Martínez** — [raynersoto01@gmail.com](mailto:raynersoto01@gmail.com)

Repositorio: [https://github.com/raynersoto/API-RedNeuronal-master](https://github.com/raynersoto/API-RedNeuronal-master)