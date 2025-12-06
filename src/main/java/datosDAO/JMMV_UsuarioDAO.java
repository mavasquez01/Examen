package datosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;



public class JMMV_UsuarioDAO {

    private JMMV_Conexion conexion;

    public JMMV_UsuarioDAO() {

        conexion = new JMMV_Conexion();

    }

    //método para verificar si el usuario tiene acceso al módulo de Gestión
    //acceso: su rol es Administrador y (su username y contraseña son correctas)
    public List<String> JMMV_VerificarLogin(String user, String pass) throws SQLException {

        //variable para retornar el nombre de usuario
        List<String> usuario = null;

        String sql = "SELECT r.JMMV_roles_nombre AS rol_usuario, u.JMMV_usuarios_nom_usuario AS nombre_usuario\n"
                + "FROM JMMV_usuarios u\n"
                + "JOIN JMMV_roles r ON u.JMMV_usuarios_id_rol = r.JMMV_roles_id_rol\n"
                + "WHERE r.JMMV_roles_id_rol = 1 AND u.JMMV_usuarios_nom_usuario = 'admin' AND u.JMMV_usuarios_contrasena = '123' AND u.JMMV_usuarios_esta_activo = TRUE";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user);
            stmt.setString(2, pass);

            try (ResultSet rs = stmt.executeQuery();) {

                if (rs.next()) {}
                    usuario.add(rs.getString("rolUsuario"));
                    usuario.add(rs.getString("nombre_usuario"));
                    
                    
                }
            System.out.println("Conexión exitosa");
            return usuario;
            
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());

        }

        System.out.println("Conexión erronea");
        return null;
    }

}
