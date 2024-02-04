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

}
