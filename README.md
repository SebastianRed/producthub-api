<div align="center">

# 📦 ProductHub API

<p>
  <img src="https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk" alt="Java" />
  <img src="https://img.shields.io/badge/Spring%20Boot-4.x-6DB33F?style=for-the-badge&logo=spring-boot" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/PostgreSQL-17+-4169E1?style=for-the-badge&logo=postgresql" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Maven-3-C71A36?style=for-the-badge&logo=apachemaven" alt="Maven" />
  <img src="https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=swagger" alt="OpenAPI" />
</p>

**REST API para la gestión de productos e inventario, desarrollada con Spring Boot. Incluye operaciones CRUD, filtros dinámicos, paginación, validaciones, manejo global de excepciones y documentación interactiva con OpenAPI (Swagger).**

</div>

---

## ✨ Características

- ✅ CRUD completo de productos
- ✅ CRUD básico de categorías
- ✅ Soft Delete de productos
- ✅ Paginación
- ✅ Ordenamiento
- ✅ Filtros dinámicos
- ✅ Consulta de productos con bajo stock
- ✅ Validaciones con Bean Validation
- ✅ Manejo global de excepciones
- ✅ Documentación OpenAPI / Swagger
- ✅ PostgreSQL + Spring Data JPA

---

# 🛠 Tecnologías

| Tecnología | Versión |
|------------|----------|
| Java | 25 |
| Spring Boot | 4.x |
| Spring Web | ✓ |
| Spring Data JPA | ✓ |
| PostgreSQL | ✓ |
| Hibernate Validator | ✓ |
| SpringDoc OpenAPI | ✓ |
| Maven | ✓ |

---

# 📁 Estructura del proyecto

```text
src
└── main
    ├── java
    │   └── cl.sebastianrojo.producthub
    │       ├── config
    │       ├── controller
    │       ├── dto
    │       │   ├── request
    │       │   └── response
    │       ├── entity
    │       ├── exception
    │       ├── mapper
    │       ├── repository
    │       ├── service
    │       ├── specification
    │       └── ProductHubApplication
    │
    └── resources
        ├── application.properties
        └── data.sql
```

---

# 🗄 Modelo de datos

## Product

| Campo | Tipo |
|--------|------|
| id | Long |
| name | String |
| description | String |
| price | BigDecimal |
| stock | Integer |
| active | Boolean |
| category | Category |

---

## Category

| Campo | Tipo |
|--------|------|
| id | Long |
| name | String |

---

# 🚀 Endpoints

## Productos

| Método | Endpoint | Descripción |
|---------|----------|-------------|
| GET | `/api/products` | Lista productos |
| GET | `/api/products/{id}` | Obtiene un producto |
| POST | `/api/products` | Crea un producto |
| PUT | `/api/products/{id}` | Actualiza un producto |
| DELETE | `/api/products/{id}` | Soft Delete |

---

## Categorías

| Método | Endpoint |
|---------|----------|
| GET | `/api/categories` |
| POST | `/api/categories` |

---

## Endpoint especial

| Método | Endpoint |
|---------|----------|
| GET | `/api/products/low-stock` |

Obtiene todos los productos cuyo stock sea menor o igual al valor indicado.

Ejemplo:

```
GET /api/products/low-stock?maxStock=5
```

---

# 🔍 Paginación

Todos los listados soportan paginación.

Ejemplo:

```
GET /api/products?page=0&size=10
```

También puede utilizarse:

```
sort=name,asc
sort=price,desc
```

---

# 🎯 Filtros

La búsqueda de productos permite combinar múltiples filtros.

Ejemplo:

```http
GET /api/products?
categoryId=2&
minPrice=1000&
maxPrice=5000&
stockMin=5&
active=true&
page=0&
size=10
```

Filtros disponibles:

- categoryId
- minPrice
- maxPrice
- stockMin
- active

Todos pueden combinarse entre sí.

---

# ✔ Validaciones

La API valida automáticamente las solicitudes.

Algunas reglas implementadas:

- Nombre obligatorio
- Precio mayor que cero
- Stock mayor o igual a cero
- Categoría existente
- Campos obligatorios

---

# ⚠ Manejo de errores

Las excepciones son gestionadas mediante un `@RestControllerAdvice`.

Respuestas consistentes para:

| Código | Descripción |
|----------|-------------|
| 400 | Solicitud inválida |
| 404 | Recurso no encontrado |
| 500 | Error interno |

Ejemplo:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Product not found with id 15",
  "timestamp": "2026-07-10T15:30:42"
}
```

---

# 📖 Documentación

Una vez iniciada la aplicación, la documentación está disponible en:

```
http://localhost:8080/swagger-ui.html
```

o

```
http://localhost:8080/swagger-ui/index.html
```

La especificación OpenAPI puede consultarse en:

```
/v3/api-docs
```

---

# ⚙ Configuración

Crear una base de datos PostgreSQL.

Modificar el archivo:

```properties
application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/producthub
spring.datasource.username=postgres
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
```

---

# ▶ Ejecución

Clonar el proyecto

```bash
git clone https://github.com/tu-usuario/producthub-api.git
```

Entrar al directorio

```bash
cd producthub-api
```

Compilar

```bash
mvn clean install
```

Ejecutar

```bash
mvn spring-boot:run
```

---

# 📌 Ejemplo de creación de producto

```json
POST /api/products

{
  "name": "Mouse Logitech G203",
  "description": "Mouse Gamer RGB",
  "price": 24990,
  "stock": 18,
  "categoryId": 2
}
```

---

# 💡 Buenas prácticas implementadas

- Arquitectura por capas
- DTOs para Request y Response
- Separación entre entidades y API
- Uso de Specifications para filtros
- Validaciones centralizadas
- Manejo global de errores
- Soft Delete
- Código documentado con OpenAPI
- Convenciones REST