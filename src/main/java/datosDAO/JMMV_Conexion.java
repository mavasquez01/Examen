package datosDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class JMMV_Conexion {

    //parámetros para la conexión a la BD
    private final String url = "jdbc:mysql://localhost:3306/sistema_reserva_bicicletas";
    private final String user = "root";
    private final String pass = "";

    //método para conectar a la BD
    public Connection JMMV_Conectar() {

        Connection conexion = null;

        //intentar conexión
        try {

            conexion = DriverManager.getConnection(url, user, pass);

            //manejo de error en la conexión
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error en la conexión: " + e.getMessage());
        }

        System.out.println("Test JM | JMMV_Conexion | : Conexión exitosa");
        return conexion;

    }
}
