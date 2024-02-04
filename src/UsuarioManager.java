import java.sql.*;
import java.util.Scanner;

public class UsuarioManager {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Crea un nuevo usuario en la base de datos.
     *
     * @param connection La conexión activa a la base de datos.
     */
    public static void crearUsuario(Connection connection) {
        // Solicita al usuario que ingrese el nombre del nuevo usuario
        System.out.print("Ingrese el nombre del nuevo usuario: ");
        String nombre = scanner.nextLine();

        // Solicita al usuario que ingrese el correo del nuevo usuario
        System.out.print("Ingrese el correo del nuevo usuario: ");
        String correo = scanner.nextLine();

        try {
            // Prepara una sentencia SQL para insertar el nuevo usuario en la tabla 'usuarios'
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuarios (nombre, correo) VALUES (?, ?)")) {
                // Establece los valores de los parámetros en la sentencia SQL
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, correo);

                // Ejecuta la sentencia SQL para insertar el nuevo usuario
                preparedStatement.executeUpdate();

                // Informa al usuario que el usuario se ha creado exitosamente
                System.out.println("Usuario creado exitosamente.");
            }
        } catch (SQLException e) {
            // En caso de error, imprime la traza de la pila y muestra un mensaje de error
            e.printStackTrace();
            System.out.println("Error al crear el usuario.");
        }
    }

    /**
     * Consulta y muestra un listado de usuarios en la consola.
     *
     * @param connection La conexión activa a la base de datos.
     */
    public static void consultarUsuarios(Connection connection) {
        try
            // Prepara la consulta SQL para obtener datos de la tabla 'usuarios'
            (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_usuario, nombre, correo FROM usuarios");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Listado de usuarios:");
            System.out.println("+----------------+-------------------------+-----------------------------+");
            // Imprime los encabezados de las columnas
            System.out.printf("| %-14s | %-23s | %-27s |\n", "ID", "Nombre", "Correo");
            System.out.println("+----------------+-------------------------+-----------------------------+");

            // Itera sobre los resultados y muestra cada fila de la tabla
            while (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");

                // Muestra los datos de cada usuario
                System.out.printf("| %-14d | %-23s | %-27s |\n", idUsuario, nombre, correo);
            }

            // Línea divisoria al final de la tabla
            System.out.println("+----------------+-------------------------+-----------------------------+");

        } catch (SQLException e) {
            // En caso de error, imprime la traza de la pila y muestra un mensaje de error
            e.printStackTrace();
            System.out.println("Error al consultar usuarios.");
        }
    }

}
