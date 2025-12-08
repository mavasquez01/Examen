package DAO;

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
    //acceso: 
    //su rol es Administrador y (su username y contraseña son correctas)y está activo.
    public String JMMV_VerificarLogin(String user, String pass) throws SQLException {

        //variable para retornar el nombre de usuario
        String usuario = null;

        String sql = "SELECT "
                + "r.JMMV_roles_nombre AS rol_usuario, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario "
                + "FROM JMMV_usuarios u "
                + "JOIN JMMV_roles r ON u.JMMV_usuarios_id_rol = r.JMMV_roles_id_rol "
                + "WHERE r.JMMV_roles_id_rol = ? "
                + "&& u.JMMV_usuarios_nom_usuario = ? "
                + "&& u.JMMV_usuarios_contrasena = ? "
                + "&& u.JMMV_usuarios_esta_activo = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 1); //rol 1, administrador
            pstmt.setString(2, user);
            pstmt.setString(3, pass);
            pstmt.setBoolean(4, true); //activo

            try (ResultSet rs = pstmt.executeQuery();) {

                if (rs.next()) {}
                    usuario = rs.getString("nombre_usuario");                
                    
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
