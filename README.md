# 🍔 Food Store - Sistema de Gestión de Pedidos

Este proyecto es el Trabajo Práctico Integrador para la materia **Programación 2** de la Tecnicatura Universitaria en Programación de la Universidad Tecnológica Nacional (UTN).

Se trata de un sistema de gestión de pedidos de comida interactivo por consola. Implementa persistencia de datos real mediante una base de datos relacional (JDBC) y una arquitectura limpia en capas orientada a objetos (POO), garantizando un diseño robusto, cohesivo y escalable.

Link PDF: https://drive.google.com/file/d/1iO5c9D7hbLpT9IZqFIvQ44FXRAxfqgLV/view?usp=sharing

**Autores:** Santino Barone, Máximo Quiroga, Franco Rios y Fabrizio Simon.

---

## 🚀 Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 21)
* **Base de Datos:** MySQL
* **Conectividad:** JDBC (Java Database Connectivity)
* **Gestión de Versiones:** Git y GitHub
* **Librerías externas:** `mysql-connector-j-8.x.x` (Driver de MySQL)

---

## 🏗️ Arquitectura y Patrones

El sistema fue diseñado priorizando la separación de responsabilidades y el bajo acoplamiento, estructurando el código en los siguientes paquetes:

* **`entidades`**: Modelo de dominio (Usuario, Producto, Categoria, Pedido, DetallePedido) aplicando herencia (clase `Base`) y polimorfismo (interfaz `Calculable`).
* **`servicios`**: Capa lógica encargada de coordinar operaciones, aplicar reglas de negocio y manejar transacciones seguras (ACID) con la base de datos.
* **`excepciones`**: Manejo de errores personalizados (`ReglaNegocioExcepcion`, `EntidadNoEncontradaExcepcion`) para proteger la integridad del dominio.
* **`configuracion`**: Gestión de la conexión a la base de datos MySQL.
* **`enumeraciones`**: Constantes del sistema (`Rol`, `Estado`, `FormaPago`).
* **`Main.java`**: Interfaz de usuario por consola que captura inputs e interactúa exclusivamente con los servicios.

### ✨ Funcionalidades Destacadas
* **Baja Lógica (Soft Delete):** Ningún registro se elimina físicamente de la base de datos. Se utiliza un flag `eliminado = true` para preservar el historial de auditoría.
* **Validaciones Transaccionales:** La creación de pedidos utiliza `conexion.setAutoCommit(false)` para garantizar que el descuento de stock y la generación del detalle ocurran sin errores, ejecutando un `rollback()` automático en caso de fallo.
* **Colecciones en Memoria:** Uso intensivo de `List` y `Map` para el manejo dinámico de carritos de compras antes de su persistencia.

---

## ⚙️ Requisitos Previos

Para ejecutar este proyecto en tu entorno local, vas a necesitar:
1.  **Java Development Kit (JDK) 21** instalado y configurado en tus variables de entorno.
2.  **Motor de base de datos MySQL** (versión 8.0 o superior) corriendo localmente en el puerto `3306`.
3.  **Driver MySQL Connector/J** (`.jar`) para enlazar el proyecto con la base de datos.

---

## 🛠️ Instrucciones de Ejecución

Sigue estos pasos para levantar el proyecto en tu entorno local:

### 1. Descargar el código
* Clona este repositorio usando Git (`git clone https://github.com/TuUsuario/food-store-tpi.git`) o descarga el archivo `.zip` y descomprímelo.
* Abre la carpeta del proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse o NetBeans).

### 2. Levantar la Base de Datos
* Asegúrate de tener encendido tu servidor MySQL (por ejemplo, desde el panel de XAMPP).
* Abre tu gestor de base de datos (phpMyAdmin, Workbench, etc.).
* Importa y ejecuta el archivo **`food_store_db.sql`** incluido en la raíz de este proyecto. Esto creará la base de datos `food_store`, todas las tablas necesarias y cargará datos de prueba.

### 3. Configurar Credenciales de Conexión
* En tu IDE, abre el archivo `src/configuracion/ConexionDB.java`.
* Verifica que las variables `USUARIO` y `CONTRASENIA` coincidan con las de tu servidor MySQL local (por defecto suele ser `root` y clave vacía `""`).

### 4. Vincular el Driver JDBC
* Descarga el driver MySQL Connector/J (`mysql-connector-j-8.0.33.jar`) si no lo tienes.
* Agrégalo a las librerías de tu proyecto en el IDE:
  * **IntelliJ:** *File > Project Structure > Modules > Dependencies > + > JARs or directories*.
  * **Eclipse:** *Click derecho en el proyecto > Build Path > Configure Build Path > Libraries > Add External JARs*.
  * **NetBeans:** *Click derecho en Libraries > Add JAR/Folder*.

### 5. Compilar y Ejecutar
* Abre la clase principal ubicada en `src/Main.java`.
* Ejecuta el método `main`.
