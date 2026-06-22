import servicios.CategoriaServicio;
import servicios.PedidoServicio;
import servicios.ProductoServicio;
import servicios.UsuarioServicio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CategoriaServicio categoriaServicio = new CategoriaServicio();
        ProductoServicio productoServicio = new ProductoServicio(categoriaServicio);
        UsuarioServicio usuarioServicio = new UsuarioServicio();
        PedidoServicio pedidoServicio = new PedidoServicio(usuarioServicio, productoServicio);

        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== SISTEMA DE PEDIDOS (FOOD STORE) ===");
            System.out.println("1. Categorías");
            System.out.println("2. Productos");
            System.out.println("3. Usuarios");
            System.out.println("4. Pedidos");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        subMenuCategorias(scanner, categoriaServicio);
                        break;
                    case 2:
                        subMenuProductos(scanner, productoServicio);
                        break;
                    case 3:
                        subMenuUsuarios(scanner, usuarioServicio);
                        break;
                    case 4:
                        subMenuPedidos(scanner, pedidoServicio, productoServicio);
                        break;
                    case 0:
                        System.out.println("Cerrando el sistema. ¡Chau Chau Adios!");
                        break;
                    default:
                        System.out.println("Opción incorrecta. Ingrese un número entre 0 y 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            } catch (Exception e) {

                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
    private static void subMenuCategorias(Scanner scanner, CategoriaServicio categoriaServicio) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- SUBMENÚ CATEGORÍAS ---");
            System.out.println("1. Listar categorías");
            System.out.println("2. Crear categoría");
            System.out.println("3. Editar categoría");
            System.out.println("4. Eliminar categoría");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- LISTA DE CATEGORÍAS ---");
                        var categorias = categoriaServicio.listarCategoriasActivas();
                        if (categorias.isEmpty()) {
                            System.out.println("No hay categorías cargadas.");
                        } else {
                            for (var cat : categorias) {
                                System.out.println(cat.toString());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n--- CREAR CATEGORÍA ---");
                        System.out.print("Ingrese el nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese la descripción: ");
                        String descripcion = scanner.nextLine();

                        categoriaServicio.crearCategoria(nombre, descripcion);
                        System.out.println("¡Categoría creada con éxito!");
                        break;

                    case 3:
                        System.out.println("\n--- EDITAR CATEGORÍA ---");
                        System.out.print("Ingrese el ID de la categoría a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());

                        System.out.print("Ingrese el nuevo nombre (o presione Enter para no modificar): ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese la nueva descripción (o presione Enter para no modificar): ");
                        String nuevaDescripcion = scanner.nextLine();

                        categoriaServicio.editarCategoria(idEditar, nuevoNombre, nuevaDescripcion);
                        System.out.println("¡Categoría actualizada con éxito!");
                        break;

                    case 4:
                        System.out.println("\n--- ELIMINAR CATEGORÍA ---");
                        System.out.print("Ingrese el ID de la categoría a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());

                        System.out.print("¿Está seguro de eliminar esta categoría? (S/N): ");
                        String confirmacion = scanner.nextLine();

                        if (confirmacion.equalsIgnoreCase("S")) {
                            categoriaServicio.eliminarCategoria(idEliminar);
                            System.out.println("¡Categoría eliminada con éxito!");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción incorrecta. Ingrese un número entre 0 y 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void subMenuProductos(Scanner scanner, ProductoServicio productoServicio) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- SUBMENÚ PRODUCTOS ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Crear producto");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- LISTA DE PRODUCTOS ---");
                        var productos = productoServicio.listarProductosActivos();
                        if (productos.isEmpty()) {
                            System.out.println("No hay productos cargados.");
                        } else {
                            for (var prod : productos) {
                                System.out.println(prod.toString());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n--- CREAR PRODUCTO ---");
                        System.out.print("Ingrese el nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese el precio: ");
                        Double precio = Double.parseDouble(scanner.nextLine());

                        System.out.print("Ingrese la descripción: ");
                        String descripcion = scanner.nextLine();

                        System.out.print("Ingrese el stock: ");
                        int stock = Integer.parseInt(scanner.nextLine());

                        System.out.print("Ingrese la URL o ruta de la imagen: ");
                        String imagen = scanner.nextLine();

                        System.out.print("¿Está disponible? (true/false): ");
                        Boolean disponible = Boolean.parseBoolean(scanner.nextLine());

                        System.out.print("Ingrese el ID de la categoría a la que pertenece: ");
                        Long idCategoria = Long.parseLong(scanner.nextLine());
                        productoServicio.crearProducto(nombre, precio, descripcion, stock, imagen, disponible, idCategoria);
                        System.out.println("¡Producto creado con éxito!");
                        break;

                    case 3:
                        System.out.println("\n--- EDITAR PRODUCTO ---");
                        System.out.print("Ingrese el ID del producto a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());

                        System.out.println("Deje el campo vacío y presione Enter si no desea modificarlo."); // Se permite actualizar uno o más campos [cite: 332]

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();

                        System.out.print("Nuevo precio: ");
                        String precioStr = scanner.nextLine();
                        Double nuevoPrecio = precioStr.isEmpty() ? null : Double.parseDouble(precioStr);

                        System.out.print("Nueva descripción: ");
                        String nuevaDescripcion = scanner.nextLine();

                        System.out.print("Nuevo stock: ");
                        String stockStr = scanner.nextLine();
                        Integer nuevoStock = stockStr.isEmpty() ? null : Integer.parseInt(stockStr);

                        System.out.print("Nuevo ID de categoría: ");
                        String catStr = scanner.nextLine();
                        Long nuevoIdCategoria = catStr.isEmpty() ? null : Long.parseLong(catStr);

                        productoServicio.editarProducto(idEditar, nuevoNombre, nuevoPrecio, nuevaDescripcion, nuevoStock, nuevoIdCategoria);
                        System.out.println("¡Producto actualizado con éxito!");
                        break;

                    case 4:
                        System.out.println("\n--- ELIMINAR PRODUCTO ---");
                        System.out.print("Ingrese el ID del producto a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());

                        System.out.print("¿Está seguro de eliminar este producto? (S/N): ");
                        String confirmacion = scanner.nextLine();

                        if (confirmacion.equalsIgnoreCase("S")) {
                            productoServicio.eliminarProducto(idEliminar);
                            System.out.println("¡Producto eliminado con éxito!");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción incorrecta. Ingrese un número entre 0 y 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Asegúrese de ingresar números válidos para precios, stock e IDs.");
            } catch (RuntimeException e) {
                System.out.println("Error de validación: " + e.getMessage());
            }
        }
    }
    private static void subMenuUsuarios(Scanner scanner, UsuarioServicio usuarioServicio) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- SUBMENÚ USUARIOS ---");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- LISTA DE USUARIOS ---");
                        var usuarios = usuarioServicio.listarUsuariosActivos();
                        if (usuarios.isEmpty()) {
                            System.out.println("No hay usuarios cargados.");
                        } else {
                            for (var usr : usuarios) {
                                System.out.println(usr.toString());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n--- CREAR USUARIO ---");
                        System.out.print("Ingrese el nombre: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Ingrese el apellido: ");
                        String apellido = scanner.nextLine();

                        System.out.print("Ingrese el correo electrónico: ");
                        String mail = scanner.nextLine();

                        System.out.print("Ingrese el celular: ");
                        String celular = scanner.nextLine();

                        System.out.print("Ingrese la contraseña: ");
                        String contrasenia = scanner.nextLine();

                        System.out.println("Seleccione el rol:");
                        System.out.println("1. Administrador");
                        System.out.println("2. Usuario General");
                        System.out.print("Opción de rol: ");
                        int opcionRol = Integer.parseInt(scanner.nextLine());
                        enumeraciones.Rol rolSeleccionado = enumeraciones.Rol.obtenerPorOpcion(opcionRol);

                        usuarioServicio.crearUsuario(nombre, apellido, mail, celular, contrasenia, rolSeleccionado);
                        System.out.println("¡Usuario creado con éxito!");
                        break;

                    case 3:
                        System.out.println("\n--- EDITAR USUARIO ---");
                        System.out.print("Ingrese el ID del usuario a editar: ");
                        Long idEditar = Long.parseLong(scanner.nextLine());

                        System.out.println("Deje el campo vacío y presione Enter si no desea modificarlo.");

                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();

                        System.out.print("Nuevo apellido: ");
                        String nuevoApellido = scanner.nextLine();

                        System.out.print("Nuevo correo electrónico: ");
                        String nuevoMail = scanner.nextLine();

                        System.out.print("Nuevo celular: ");
                        String nuevoCelular = scanner.nextLine();

                        usuarioServicio.editarUsuario(idEditar, nuevoNombre, nuevoApellido, nuevoMail, nuevoCelular);
                        System.out.println("¡Usuario actualizado con éxito!");
                        break;

                    case 4:
                        System.out.println("\n--- ELIMINAR USUARIO ---");
                        System.out.print("Ingrese el ID del usuario a eliminar: ");
                        Long idEliminar = Long.parseLong(scanner.nextLine());

                        System.out.print("¿Está seguro de eliminar este usuario? (S/N): ");
                        String confirmacion = scanner.nextLine();

                        if (confirmacion.equalsIgnoreCase("S")) {
                            usuarioServicio.eliminarUsuario(idEliminar);
                            System.out.println("¡Usuario eliminado con éxito!");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción incorrecta. Ingrese un número entre 0 y 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Asegúrese de ingresar números válidos para los IDs y las opciones.");
            } catch (RuntimeException e) {
                System.out.println("Error de validación: " + e.getMessage());
            }
        }
    }
    private static void subMenuPedidos(Scanner scanner, PedidoServicio pedidoServicio, ProductoServicio productoServicio) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- SUBMENÚ PEDIDOS ---");
            System.out.println("1. Listar pedidos");
            System.out.println("2. Crear pedido con detalles");
            System.out.println("3. Actualizar estado/forma de pago");
            System.out.println("4. Eliminar pedido");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- LISTA DE PEDIDOS ---");
                        var pedidos = pedidoServicio.listarPedidosActivos();
                        if (pedidos.isEmpty()) {
                            System.out.println("No hay pedidos registrados.");
                        } else {
                            for (var ped : pedidos) {
                                System.out.println(ped.toString());
                            }
                        }
                        break;

                    case 2:
                        System.out.println("\n--- CREAR PEDIDO ---");
                        System.out.print("Ingrese el ID del usuario que realiza el pedido: ");
                        Long idUsuario = Long.parseLong(scanner.nextLine());

                        System.out.println("Seleccione la forma de pago:");
                        System.out.println("1. Tarjeta\n2. Transferencia\n3. Efectivo");
                        System.out.print("Opción: ");
                        int opcionPago = Integer.parseInt(scanner.nextLine());
                        enumeraciones.FormaPago formaPago = enumeraciones.FormaPago.obtenerPorOpcion(opcionPago);

                        // Armado del carrito
                        java.util.Map<Long, Integer> carrito = new java.util.HashMap<>();
                        boolean agregandoProductos = true;

                        System.out.println("\n--- PRODUCTOS DISPONIBLES ---");
                        for (var prod : productoServicio.listarProductosActivos()) {
                            System.out.println("ID: " + prod.obtenerId() + " | " + prod.obtenerNombre() + " | Precio: $" + prod.obtenerPrecio() + " | Stock: " + prod.obtenerStock());
                        }

                        while (agregandoProductos) {
                            System.out.print("\nIngrese el ID del producto a comprar: ");
                            Long idProducto = Long.parseLong(scanner.nextLine());

                            System.out.print("Ingrese la cantidad: ");
                            int cantidad = Integer.parseInt(scanner.nextLine());
                            carrito.put(idProducto, carrito.getOrDefault(idProducto, 0) + cantidad);

                            System.out.print("¿Desea agregar otro producto al pedido? (S/N): ");
                            String respuesta = scanner.nextLine();
                            if (respuesta.equalsIgnoreCase("N")) {
                                agregandoProductos = false;
                            }
                        }
                        pedidoServicio.crearPedido(idUsuario, formaPago, carrito);
                        System.out.println("¡Pedido creado y calculado con éxito!");
                        break;

                    case 3:
                        System.out.println("\n--- ACTUALIZAR PEDIDO ---");
                        System.out.print("Ingrese el ID del pedido a modificar: ");
                        Long idPedidoEditar = Long.parseLong(scanner.nextLine());

                        System.out.println("Nuevos Estados (Ingrese 0 si no desea modificar):");
                        System.out.println("1. Pendiente\n2. Confirmado\n3. Terminado\n4. Cancelado");
                        System.out.print("Opción de estado: ");
                        int opcEstado = Integer.parseInt(scanner.nextLine());
                        enumeraciones.Estado nuevoEstado = null;
                        if (opcEstado != 0) {
                            nuevoEstado = enumeraciones.Estado.obtenerPorOpcion(opcEstado);
                        }

                        System.out.println("Nuevas Formas de Pago (Ingrese 0 si no desea modificar):");
                        System.out.println("1. Tarjeta\n2. Transferencia\n3. Efectivo");
                        System.out.print("Opción de pago: ");
                        int opcPagoEdicion = Integer.parseInt(scanner.nextLine());
                        enumeraciones.FormaPago nuevaFormaPago = null;
                        if (opcPagoEdicion != 0) {
                            nuevaFormaPago = enumeraciones.FormaPago.obtenerPorOpcion(opcPagoEdicion);
                        }

                        pedidoServicio.actualizarPedido(idPedidoEditar, nuevoEstado, nuevaFormaPago);
                        System.out.println("¡Pedido actualizado con éxito!");
                        break;

                    case 4:
                        System.out.println("\n--- ELIMINAR PEDIDO ---");
                        System.out.print("Ingrese el ID del pedido a eliminar: ");
                        Long idPedidoEliminar = Long.parseLong(scanner.nextLine());

                        System.out.print("¿Está seguro de eliminar este pedido? (S/N): ");
                        String confirmacion = scanner.nextLine();

                        if (confirmacion.equalsIgnoreCase("S")) {
                            pedidoServicio.eliminarPedido(idPedidoEliminar);
                            System.out.println("¡Pedido eliminado con éxito!");
                        } else {
                            System.out.println("Operación cancelada.");
                        }
                        break;

                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción incorrecta. Ingrese un número entre 0 y 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Asegúrese de ingresar números válidos.");
            } catch (RuntimeException e) {
                System.out.println("Error de validación: " + e.getMessage());
            }
        }
    }
}