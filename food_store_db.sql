
CREATE DATABASE IF NOT EXISTS food_store;
USE food_store;

CREATE TABLE categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE productos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    descripcion VARCHAR(255),
    stock INT NOT NULL,
    imagen VARCHAR(255),
    disponible BOOLEAN DEFAULT TRUE,
    id_categoria BIGINT NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id)
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    mail VARCHAR(150) NOT NULL UNIQUE,
    celular VARCHAR(20),
    contrasenia VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL, 
    eliminado BOOLEAN DEFAULT FALSE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) NOT NULL, 
    total DOUBLE NOT NULL,
    forma_pago VARCHAR(20) NOT NULL, 
    id_usuario BIGINT NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE detalles_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,
    id_pedido BIGINT NOT NULL,
    id_producto BIGINT NOT NULL,
    eliminado BOOLEAN DEFAULT FALSE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id),
    FOREIGN KEY (id_producto) REFERENCES productos(id)
);

-- CARGA DE DATOS DE PRUEBA
INSERT INTO categorias (nombre, descripcion, eliminado, fecha_creacion) 
VALUES ('Pizzas', 'Pizzas de super', false, NOW()),
       ('Hamburguesas', 'Hamburguesas smash', false, NOW()),
       ('Bebidas', 'Bebidas sin alcohol', false, NOW());

INSERT INTO productos (nombre, precio, descripcion, stock, imagen, disponible, id_categoria, eliminado, fecha_creacion) 
VALUES ('Muzza', 7500.0, 'tipica muzza', 50, 'muzza.jpg', true, 1, false, NOW()),
       ('Doble bacon', 6800.0, 'Doble medallón con panceta y cheddar', 30, 'bacon.jpg', true, 2, false, NOW()),
       ('Coca Cola', 3500.0, 'La de 1,5l', 100, 'coca.jpg', true, 3, false, NOW());

INSERT INTO usuarios (nombre, apellido, mail, celular, contrasenia, rol, eliminado, fecha_creacion) 
VALUES ('Maxi', 'Admin', 'admin@foodstore.com', '261000000', '1234', 'ADMINISTRADOR', false, NOW());