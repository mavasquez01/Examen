package datosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.JMMV_Cliente;



public class JMMV_ClienteDAO {

    //atributo para la conexión a BD
    private JMMV_Conexion conexion;


    public JMMV_ClienteDAO() {
    
        conexion = new JMMV_Conexion();
    }

    public List<JMMV_Cliente> JMMV_ObtenerTodosLosClientes() {

        List<JMMV_Cliente> listaClientes = new ArrayList<>();

        //preparar consulta        
        String sql = "SELECT\n"
                + "u.JMMV_usuarios_nom_usuario AS nom_usuario,\n"
                + "u.JMMV_usuarios_contrasena AS contrasena,\n"
                + "d.JMMV_datos_personales_correo AS correo,\n"
                + "d.JMMV_datos_personales_run AS run,\n"
                + "d.JMMV_datos_personales_nombres AS nombres,\n"
                + "d.JMMV_datos_personales_apellido_paterno AS apellido_pat,\n"
                + "d.JMMV_datos_personales_apellido_materno AS apellido_mat,\n"
                + "c.JMMV_comunas_nombre AS comuna,\n"
                + "d.JMMV_datos_personales_calle AS calle,\n"
                + "d.JMMV_datos_personales_num_calle AS numero,\n"
                + "d.JMMV_datos_personales_telefono AS telefono,\n"
                + "u.JMMV_usuarios_esta_activo AS activo\n"
                + "FROM JMMV_usuarios AS u\n"
                + "JOIN JMMV_datos_personales AS d ON u.JMMV_usuarios_id_dato_personal = d.JMMV_datos_personales_id_dato_personal\n"
                + "JOIN JMMV_comunas AS c ON d.JMMV_datos_personales_id_comuna = c.JMMV_comunas_id_comuna\n"
                + "WHERE u.JMMV_usuarios_id_rol = 2\n"
                + "ORDER BY d.JMMV_datos_personales_run ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                //obtener datos
                String JMMV_nomUsuario = rs.getString("nom_usuario");
                String JMMV_contrasena = rs.getString("contrasena");
                String JMMV_correo = rs.getString("correo");
                int JMMV_run = rs.getInt("run");
                String JMMV_nombres = rs.getString("nombres");
                String JMMV_apellidoPaterno = rs.getString("apellido P");
                String JMMV_apellidoMaterno = rs.getString("apellido M");
                int JMMV_comuna = rs.getInt("comuna");
                String JMMV_calle = rs.getString("calle");
                int JMMV_numCalle = rs.getInt("numero");
                int JMMV_telefono = rs.getInt("telefono");
                boolean JMMV_estaActivo = rs.getBoolean("activo");                
                
                //obtener nombre de comuna
                String nombreComuna = JMMV_ObtenerNombreComunaPorId(JMMV_comuna);                      

                //crear objeto de la clase
                JMMV_Cliente cliente = new JMMV_Cliente(JMMV_nomUsuario, JMMV_contrasena, JMMV_correo, JMMV_run, JMMV_nombres, JMMV_apellidoPaterno, JMMV_apellidoMaterno, nombreComuna, JMMV_calle, JMMV_numCalle, JMMV_telefono, JMMV_estaActivo);

                //agregar cliente a lista
                listaClientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientes;

    }

    public boolean JMMV_AgregarCliente(JMMV_Cliente cliente) {

        //variable para guardar la PK
        int pkInsertada = -1;

        //string para primer INSERT
        String sqlUno = "INSERT INTO JMMV_usuarios\n"
                + "(JMMV_usuarios_nom_usuario,JMMV_usuarios_contrasena,JMMV_usuarios_id_rol,JMMV_usuarios_correo,JMMV_usuarios_esta_activo)\n"
                + "VALUES(?,?,?,?,?)";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmtUno = conn.prepareStatement(sqlUno, Statement.RETURN_GENERATED_KEYS)) {

            //desactivar auto commit
            conn.setAutoCommit(false);

            //preparar valores a enviar
            pstmtUno.setString(1, cliente.getJMMV_Cliente_nomUsuario());
            pstmtUno.setString(2, cliente.getJMMV_Cliente_contrasena());
            pstmtUno.setString(3, cliente.getJMMV_Cliente_correo());
            pstmtUno.setBoolean(4, true);

            //ejecutar primer INSERT
            pstmtUno.executeUpdate();

            //recuperar PK
            try (ResultSet rs = pstmtUno.getGeneratedKeys()) {
                if (rs.next()) {
                    pkInsertada = rs.getInt(1);
                }
            }

            //string segundo INSERT            
            String sqlDos = "INSERT INTO JMMV_datos_personales\n"
                    + "(JMMV_datos_personales_id_dato_personal,JMMV_datos_personales_run,JMMV_datos_personales_nombres,JMMV_datos_personales_apellido_paterno,JMMV_datos_personales_apellido_materno,JMMV_datos_personales_id_comuna,JMMV_datos_personales_calle,JMMV_datos_personales_num_calle)\n"
                    + "VALUES(?,?,?,?,?,?,?,?)";

            int idComuna = JMMV_ObtenerIdComunaPorNombre(cliente.getJMMV_Cliente_comuna());
            
            //preparar valores a enviar
            try (PreparedStatement pstmtDos = conn.prepareStatement(sqlDos)) {
                pstmtDos.setInt(1, pkInsertada);
                pstmtDos.setInt(2, cliente.getJMMV_Cliente_run());
                pstmtDos.setString(3, cliente.getJMMV_Cliente_nombres());
                pstmtDos.setString(4, cliente.getJMMV_Cliente_apellidoPaterno());
                pstmtDos.setString(5, cliente.getJMMV_Cliente_apellidoMaterno());
                pstmtDos.setInt(6, idComuna);
                pstmtDos.setString(7, cliente.getJMMV_Cliente_calle());
                pstmtDos.setInt(8, cliente.getJMMV_Cliente_numCalle());

                //ejecutar segundo INSERT
                pstmtDos.executeUpdate();
            }

            //commit
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean JMMV_ActualizarCliente(JMMV_Cliente cliente) {

        //string para primer UPDATE
        String sqlUno = "UPDATE INTO JMMV_usuarios\n"
                + "(JMMV_usuarios_nom_usuario,JMMV_usuarios_contrasena,JMMV_usuarios_correo,JMMV_usuarios_esta_activo)\n"
                + "VALUES(?,?,?,?)";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmtUno = conn.prepareStatement(sqlUno)) {

            //desactivar auto commit
            conn.setAutoCommit(false);

            //preparar valores a enviar
            pstmtUno.setString(1, cliente.getJMMV_Cliente_nomUsuario());
            pstmtUno.setString(2, cliente.getJMMV_Cliente_contrasena());
            pstmtUno.setString(3, cliente.getJMMV_Cliente_correo());
            pstmtUno.setBoolean(4, cliente.isJMMV_Cliente_estaActivo());

            //ejecutar primer UPDATE
            pstmtUno.executeUpdate();

            //string segundo UPDATE            
            String sqlDos = "UPDATE JMMV_datos_personales\n"
                    + "(JMMV_datos_personales_run,JMMV_datos_personales_nombres,JMMV_datos_personales_apellido_paterno,JMMV_datos_personales_apellido_materno,JMMV_datos_personales_id_comuna,JMMV_datos_personales_calle,JMMV_datos_personales_num_calle)\n"
                    + "VALUES(?,?,?,?,?,?,?,?)";

            //preparar valores a enviar
            try (PreparedStatement pstmtDos = conn.prepareStatement(sqlDos)) {
                pstmtDos.setInt(2, cliente.getJMMV_Cliente_run());
                pstmtDos.setString(3, cliente.getJMMV_Cliente_nombres());
                pstmtDos.setString(4, cliente.getJMMV_Cliente_apellidoPaterno());
                pstmtDos.setString(5, cliente.getJMMV_Cliente_apellidoMaterno());
                pstmtDos.setInt(6, JMMV_ObtenerIdComunaPorNombre(cliente.getJMMV_Cliente_comuna()));
                pstmtDos.setString(7, cliente.getJMMV_Cliente_calle());
                pstmtDos.setInt(8, cliente.getJMMV_Cliente_numCalle());

                //ejecutar segundo UPDATE
                pstmtDos.executeUpdate();
            }

            //commit
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean JMMV_EliminarCliente(int idCliente) {

        String sql = "DELETE FROM JMMV_usuarios where JMMV_usuarios_id_usuario = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }

    //métodos auxiliares
    //obtener id de comuna por su nombre
    public int JMMV_ObtenerIdComunaPorNombre(String nombreIDComuna) {

        String sql = "SELECT JMMV_comunas_id_comuna FROM JMMV_comunas WHERE JMMV_comunas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "JMMV_comunas_id_comuna");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }

    //obtener nombre completo de todos los clientes
    public List<String> JMMV_ObtenerNombresCompletosClientes() {

        List<String> listaNombresCompletos = new ArrayList<>();

        //consulta
        String sql = "SELECT CONCAT(JMMV_datos_personales_nombres,' ',JMMV_datos_personales_apellido_paterno,' ',JMMV_datos_personales_apellido_materno)\n"
                + "AS nombre_completo\n"
                + "FROM JMMV_datos_personales";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                //agregar nombre a la lista
                listaNombresCompletos.add("nombre_completo");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNombresCompletos;

    }
    
    public String JMMV_ObtenerNombreComunaPorId(int idComuna){
        String sql = "SELECT JMMV_comunas_nombre FROM JMMV_comunas WHERE JMMV_comunas_id_comuna = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "JMMV_comunas_nombre");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("JMMV_comunas_nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
