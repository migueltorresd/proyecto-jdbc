import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Intenta conectar a la base de datos
        Connection connection = DatabaseConnector.conectar();

        // Verifica si la conexión fue exitosa
        if (connection != null) {
            System.out.println("¡Conexión exitosa a la base de datos!");

            // Puedes realizar operaciones adicionales aquí si lo deseas

            // Cierra la conexión al finalizar
            DatabaseConnector.cerrarConexion(connection);
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
