# 📚 Literatura API - Proyecto Spring Boot

Aplicación de consola desarrollada con **Spring Boot** que consume la API pública de libros Gutendex, permite buscar libros por título y almacenarlos en una base de datos utilizando JPA.

---

## 🚀 Funcionalidades

* 🔎 Buscar libro por título (consumiendo API externa)
* 💾 Guardar libro en base de datos
* 📚 Listar libros guardados
* 👤 Listar autores registrados
* 🌎 Filtrar libros por idioma
* 📆 Listar autores vivos en un año determinado
* 🛡 Validación para evitar duplicados
* ⚠ Manejo básico de errores y validaciones

---

## 🛠 Tecnologías utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* Base de datos (H2 / MySQL según configuración)
* Jackson (para conversión JSON)
* API externa: Gutendex

---

## 🌐 API utilizada

La aplicación consume la API pública:

https://gutendex.com/

Ejemplo de búsqueda:

https://gutendex.com/books/?search=pride

---

## 🧠 Estructura del Proyecto

```
com.practicas
│
├── model          → Entidades (Libro, Autor, etc.)
├── repository     → Interfaces JPA
├── service        → Consumo API y conversión de datos
├── Principal      → Lógica del menú y funcionalidades
└── LiteraturaApplication → Clase principal
```

---

## ▶️ Cómo ejecutar el proyecto

1. Clonar el repositorio:

```
git clone https://github.com/TU-USUARIO/TU-REPOSITORIO.git
```

2. Abrir en IntelliJ o IDE de preferencia.

3. Configurar base de datos en `application.properties`.

4. Ejecutar la clase:

```
LiteraturaApplication.java
```

---

## 📌 Ejemplo de uso

```
1 - Buscar libro por título
2 - Listar libros guardados
3 - Listar autores
4 - Listar libros por idioma
5 - Listar autores vivos en determinado año
0 - Salir
```

---

## 🎯 Objetivo del Proyecto

Este proyecto fue desarrollado como práctica de:

* Consumo de APIs REST
* Manejo de JSON
* Persistencia con JPA
* Relaciones entre entidades
* Buenas prácticas básicas en backend

---

## 📈 Mejoras futuras

* Crear API REST en lugar de aplicación de consola
* Agregar paginación
* Agregar estadísticas de libros
* Implementar DTOs
* Mejorar manejo global de excepciones

---

## 👨‍💻 Autor

Cristian Molina
Proyecto desarrollado como práctica de backend con Spring Boot.

---

⭐ Si te gustó el proyecto, dale una estrella al repositorio.
