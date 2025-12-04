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

    public List<JMMV_Cliente> JMMV_ObtenerTodosLosClientesActivos() {

        List<JMMV_Cliente> listaClientes = new ArrayList<>();

        //preparar consulta        
        String sql = "SELECT c.JMMV_clientes_id_cliente AS id, "
                + "c.JMMV_clientes_run AS run, c.JMMV_clientes_nombres AS nombres, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, c.JMMV_clientes_apellido_materno AS ap_mat, "
                + "co.JMMV_comunas_nombre AS comuna, c.JMMV_clientes_calle AS calle, "
                + "c.JMMV_clientes_num_calle AS num, c.JMMV_clientes_telefono AS telefono, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario, u.JMMV_usuarios_contrasena AS contrasena\n"
                + "FROM JMMV_clientes c\n"
                + "JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario\n"
                + "JOIN JMMV_comunas co ON c.JMMV_clientes_id_comuna = co.JMMV_comunas_id_comuna\n"
                + "WHERE c.JMMV_clientes_esta_activo = TRUE\n"
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                //obtener datos
                int JMMV_idCliente = rs.getInt("id");
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
                
                //obtener nombre de comuna
                String nombreComuna = JMMV_ObtenerNombreComunaPorId(JMMV_comuna);                      

                //crear objeto de la clase
                JMMV_Cliente cliente = new JMMV_Cliente(JMMV_idCliente, JMMV_nomUsuario, JMMV_contrasena, JMMV_correo, JMMV_run, JMMV_nombres, JMMV_apellidoPaterno, JMMV_apellidoMaterno, nombreComuna, JMMV_calle, JMMV_numCalle, JMMV_telefono);

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
        String sqlUno = "INSERT INTO JMMV_usuarios "
                + "(JMMV_usuarios_nom_usuario,JMMV_usuarios_contrasena,JMMV_usuarios_correo,JMMV_usuarios_id_rol)\n"
                + "VALUES(?,?,?,?)";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmtUno = conn.prepareStatement(sqlUno, Statement.RETURN_GENERATED_KEYS)) {

            //desactivar auto commit
            conn.setAutoCommit(false);

            //preparar valores a enviar
            pstmtUno.setString(1, cliente.getJMMV_Cliente_nomUsuario());
            pstmtUno.setString(2, cliente.getJMMV_Cliente_contrasena());
            pstmtUno.setString(3, cliente.getJMMV_Cliente_correo());
            pstmtUno.setInt(4, 2);

            //ejecutar primer INSERT
            pstmtUno.executeUpdate();

            //recuperar PK
            try (ResultSet rs = pstmtUno.getGeneratedKeys()) {
                if (rs.next()) {
                    pkInsertada = rs.getInt(1);
                }
            }

            //string segundo INSERT            
            String sqlDos = "INSERT INTO JMMV_clientes (JMMV_clientes_id_usuario,JMMV_clientes_run,"
                    + "JMMV_clientes_nombres,JMMV_clientes_apellido_paterno,JMMV_clientes_apellido_materno,"
                    + "JMMV_clientes_id_comuna,JMMV_clientes_calle,JMMV_clientes_num_calle,"
                    + "JMMV_clientes_telefono,JMMV_clientes_esta_activo)\n"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?)";

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
                pstmtDos.setInt(9, cliente.getJMMV_Cliente_telefono());
                pstmtDos.setBoolean(10, true);

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

        //obtener id usuario        
        int idUsuario = 1;
        
        //string para primer UPDATE
        String sqlUno = "UPDATE JMMV_usuarios\n"
                + "SET \n"
                + "JMMV_usuarios_nom_usuario = ?,\n"
                + "JMMV_usuarios_contrasena = ?,\n"
                + "JMMV_usuarios_correo = ?'\n"
                + "WHERE JMMV_usuarios_id_usuario = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmtUno = conn.prepareStatement(sqlUno)) {

            //desactivar auto commit
            conn.setAutoCommit(false);

            //preparar valores a enviar
            pstmtUno.setString(1, cliente.getJMMV_Cliente_nomUsuario());
            pstmtUno.setString(2, cliente.getJMMV_Cliente_contrasena());
            pstmtUno.setString(3, cliente.getJMMV_Cliente_correo());
            pstmtUno.setInt(4, idUsuario);

            //ejecutar primer UPDATE
            pstmtUno.executeUpdate();

            //string segundo UPDATE            
            String sqlDos = "UPDATE jmmv_clientes\n"
                    + "SET\n"
                    + "JMMV_clientes_run = ?,\n"
                    + "JMMV_clientes_nombres = ?,\n"
                    + "JMMV_clientes_apellido_paterno = ?,\n"
                    + "JMMV_clientes_apellido_materno = ?,\n"
                    + "JMMV_clientes_id_comuna = ?,\n"
                    + "JMMV_clientes_calle = ?,\n"
                    + "JMMV_clientes_num_calle = ?,\n"
                    + "JMMV_clientes_telefono = ?\n"
                    + "WHERE JMMV_clientes_id_cliente = ?";

            //preparar valores a enviar
            try (PreparedStatement pstmtDos = conn.prepareStatement(sqlDos)) {
                pstmtDos.setInt(1, cliente.getJMMV_Cliente_run());
                pstmtDos.setString(2, cliente.getJMMV_Cliente_nombres());
                pstmtDos.setString(3, cliente.getJMMV_Cliente_apellidoPaterno());
                pstmtDos.setString(4, cliente.getJMMV_Cliente_apellidoMaterno());
                pstmtDos.setInt(5, JMMV_ObtenerIdComunaPorNombre(cliente.getJMMV_Cliente_comuna()));
                pstmtDos.setString(6, cliente.getJMMV_Cliente_calle());
                pstmtDos.setInt(7, cliente.getJMMV_Cliente_numCalle());
                pstmtDos.setInt(8, cliente.getJMMV_Cliente_telefono());
                pstmtDos.setInt(9, cliente.getJMMV_Cliente_idCliente());

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

        String sql = "UPDATE JMMV_clientes\n"
                + "SET\n"
                + "JMMV_clientes_esta_activo = FALSE\n"
                + "WHERE JMMV_clientes_id_cliente = ?;";

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

        String sql = "SELECT JMMV_comunas_id_comuna AS id_comuna\n"
                + "FROM JMMV_comunas\n"
                + "WHERE JMMV_comunas_nombre = ?;";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreIDComuna);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_comuna");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }

    //obtener nombre completo de todos los clientes
    public List<String> JMMV_ObtenerNombresCompletosClientesActivos() {

        List<String> listaNombresCompletos = new ArrayList<>();

        //consulta
        String sql = "SELECT CONCAT(JMMV_clientes_nombres,' ',JMMV_clientes_apellido_paterno,' ',"
                + "JMMV_clientes_apellido_materno)\n"
                + "AS nombre_completo\n"
                + "FROM JMMV_clientes c\n"
                + "WHERE c.JMMV_clientes_esta_activo=TRUE\n"
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

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

    public String JMMV_ObtenerNombreComunaPorId(int idComuna) {
        String sql = "SELECT JMMV_comunas_nombre AS comuna\n"
                + "FROM JMMV_comunas\n"
                + "WHERE JMMV_comunas_id_comuna = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idComuna);
            
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("comuna");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int JMMV_ObtenerIdUsuario(int idCliente) {
        String sql = "SELECT u.JMMV_usuarios_id_usuario AS id_usuario\n"
                + "FROM JMMV_usuarios u\n"
                + "JOIN JMMV_clientes c ON u.JMMV_usuarios_id_usuario = c.JMMV_clientes_id_usuario\n"
                + "WHERE c.JMMV_clientes_id_cliente = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }

}
