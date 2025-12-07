package DAO;

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
        String sql = "SELECT "
                + "c.JMMV_clientes_id_cliente AS id, "
                + "c.JMMV_clientes_run AS run, "
                + "c.JMMV_clientes_nombres AS nombres, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, "
                + "c.JMMV_clientes_apellido_materno AS ap_mat, "
                + "co.JMMV_comunas_nombre AS comuna, "
                + "c.JMMV_clientes_calle AS calle, "
                + "c.JMMV_clientes_num_calle AS numero, "
                + "c.JMMV_clientes_telefono AS telefono, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario, "
                + "u.JMMV_usuarios_contrasena AS contrasena, "
                + "u.JMMV_usuarios_correo AS correo"
                + "FROM JMMV_clientes c"
                + "JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario"
                + "JOIN JMMV_comunas co ON c.JMMV_clientes_id_comuna = co.JMMV_comunas_id_comuna"
                + "WHERE c.JMMV_clientes_esta_activo = TRUE"
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                //obtener datos
                int JMMV_idCliente = rs.getInt("id");
                String JMMV_nomUsuario = rs.getString("nombre_usuario");
                String JMMV_contrasena = rs.getString("contrasena");
                String JMMV_correo = rs.getString("correo");
                int JMMV_run = rs.getInt("run");
                String JMMV_nombres = rs.getString("nombres");
                String JMMV_apellidoPaterno = rs.getString("ap_pat");
                String JMMV_apellidoMaterno = rs.getString("ap_mat");
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

    public boolean JMMV_AgregarCliente(JMMV_Cliente cliente)  {
        
        Connection conn = null;

        //variable para guardar la PK
        int pkInsertada = -1;

        //string para primer INSERT
        String sqlUsuarios = "INSERT INTO JMMV_usuarios "
                + "(JMMV_usuarios_nom_usuario,JMMV_usuarios_contrasena,JMMV_usuarios_correo,JMMV_usuarios_id_rol)"
                + "VALUES(?,?,?,?)";

        //string segundo INSERT            
        String sqlClientes = "INSERT INTO JMMV_clientes ("
                + "JMMV_clientes_id_usuario,JMMV_clientes_run,"
                + "JMMV_clientes_nombres,"
                + "JMMV_clientes_apellido_paterno,"
                + "JMMV_clientes_apellido_materno,"
                + "JMMV_clientes_id_comuna,"
                + "JMMV_clientes_calle,"
                + "JMMV_clientes_num_calle,"
                + "JMMV_clientes_telefono,"
                + "JMMV_clientes_esta_activo)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        int idComuna = JMMV_ObtenerIdComunaPorNombre(cliente.getJMMV_Cliente_comuna());

        try {
            conn = conexion.JMMV_Conectar();
            //desactivar auto commit
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtUno = conn.prepareStatement(sqlUsuarios, Statement.RETURN_GENERATED_KEYS); 
                    PreparedStatement pstmtDos = conn.prepareStatement(sqlClientes)) {

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

                        System.out.println("Test JM | pk insertada: " + pkInsertada);
                    }
                }

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

                //commit
                conn.commit();

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {

                    //rollback
                    conn.rollback();
                } catch (SQLException er) {
                    er.printStackTrace();
                }
            }
            return false;
        }
    }

    public boolean JMMV_ActualizarCliente(JMMV_Cliente cliente) {

        //obtener id usuario        
        int idUsuario = 1;
        
        //string para primer UPDATE
        String sqlUno = "UPDATE JMMV_usuarios"
                + "SET "
                + "JMMV_usuarios_nom_usuario = ?,"
                + "JMMV_usuarios_contrasena = ?,"
                + "JMMV_usuarios_correo = ?"
                + "WHERE JMMV_usuarios_id_usuario = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmtUno = conn.prepareStatement(sqlUno)) {

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
            String sqlDos = "UPDATE jmmv_clientes"
                    + "SET"
                    + "JMMV_clientes_run = ?,"
                    + "JMMV_clientes_nombres = ?,"
                    + "JMMV_clientes_apellido_paterno = ?,"
                    + "JMMV_clientes_apellido_materno = ?,"
                    + "JMMV_clientes_id_comuna = ?,"
                    + "JMMV_clientes_calle = ?,"
                    + "JMMV_clientes_num_calle = ?,"
                    + "JMMV_clientes_telefono = ?"
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

        String sql = "UPDATE JMMV_clientes"
                + "SET"
                + "JMMV_clientes_esta_activo = FALSE"
                + "WHERE JMMV_clientes_id_cliente = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }

    //obtener cliente por primer nombre y apellido paterno. Utiliza LIKE para primero nombre aproximado al final.
    public List<JMMV_Cliente> JMMV_BuscarCliente (String nombres, String apellidoPaterno){
        
            List<JMMV_Cliente> listaClientesEncontrados = new ArrayList<>();

        String nombreBuscadoSQL = nombres + "%";

        String sql = "SELECT "
                + "c.JMMV_clientes_id_cliente AS id, "
                + "c.JMMV_clientes_run AS run, "
                + "CONCAT(c.JMMV_clientes_nombres, ' ' ,c.JMMV_clientes_apellido_paterno, ' ' , c.JMMV_clientes_apellido_materno) AS nombre_cliente"
                + "FROM JMMV_clientes c"
                + "WHERE c.JMMV_clientes_esta_activo = TRUE && c.JMMV_clientes_nombres LIKE ? && c.JMMV_clientes_apellido_paterno = ?"
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreBuscadoSQL);
            pstmt.setString(2, apellidoPaterno);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int idCliente = rs.getInt("id");
                    int rut = rs.getInt("rut");
                    String nombreCompleto = rs.getString("nombre_completo");

                    JMMV_Cliente cliente = new JMMV_Cliente(idCliente, rut, nombreCompleto);

                    listaClientesEncontrados.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientesEncontrados;

    }
    //métodos auxiliares
    //obtener id de comuna por su nombre
    public int JMMV_ObtenerIdComunaPorNombre(String nombreComuna) {

        String sql = "SELECT JMMV_comunas_id_comuna AS id_comuna"
                + "FROM JMMV_comunas"
                + "WHERE JMMV_comunas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreComuna);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_comuna");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }

    //obtener nombre completo de todos los clientes activos
    public List<String> JMMV_ObtenerNombresCompletosClientesActivos() {

        List<String> listaNombresCompletos = new ArrayList<>();

        //consulta
        String sql = "SELECT CONCAT(JMMV_clientes_nombres,' ',JMMV_clientes_apellido_paterno,' ',"
                + "JMMV_clientes_apellido_materno)"
                + "AS nombre_completo"
                + "FROM JMMV_clientes c"
                + "WHERE c.JMMV_clientes_esta_activo=TRUE"
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(sql)) {

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
        String sql = "SELECT JMMV_comunas_nombre AS comuna"
                + "FROM JMMV_comunas"
                + "WHERE JMMV_comunas_id_comuna = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
        String sql = "SELECT "
                + "u.JMMV_usuarios_id_usuario AS id_usuario"
                + "FROM JMMV_usuarios u"
                + "JOIN JMMV_clientes c ON u.JMMV_usuarios_id_usuario = c.JMMV_clientes_id_usuario"
                + "WHERE c.JMMV_clientes_id_cliente = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
    
    //para combobox
    public List<String> JMMV_ObtenerComunasActivas() {
        List<String> listaComunas = new ArrayList<>();
        String sql = "SELECT "
                + "c.JMMV_comunas_nombre AS comuna"
                + "FROM JMMV_comunas c"
                + "WHERE c.JMMV_comunas_esta_activo = true"
                + "ORDER BY JMMV_comunas_nombre ASC";
        try (Connection conn = conexion.JMMV_Conectar(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                listaComunas.add(rs.getString("comuna"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaComunas;

        
    }
    
    public List<JMMV_Cliente> JMMV_ObtenerClientePorNombre(String nombres) {

        List<JMMV_Cliente> listaClientes = new ArrayList<>();

        //preparar consulta        
        String sql = "SELECT "
                + "c.JMMV_clientes_id_cliente AS id, "
                + "c.JMMV_clientes_run AS run, "
                + "c.JMMV_clientes_nombres AS nombres, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, "
                + "c.JMMV_clientes_apellido_materno AS ap_mat, "
                + "co.JMMV_comunas_nombre AS comuna, "
                + "c.JMMV_clientes_calle AS calle, "
                + "c.JMMV_clientes_num_calle AS numero, "
                + "c.JMMV_clientes_telefono AS telefono, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario, "
                + "u.JMMV_usuarios_contrasena AS contrasena"
                + "FROM JMMV_clientes c"
                + "JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario"
                + "JOIN JMMV_comunas co ON c.JMMV_clientes_id_comuna = co.JMMV_comunas_id_comuna"
                + "WHERE c.JMMV_clientes_esta_activo = TRUE && nombres = ?"
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            
            pstmt.setString(1, nombres);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {

                //obtener datos
                int JMMV_idCliente = rs.getInt("id");
                String JMMV_nomUsuario = rs.getString("nom_usuario");
                String JMMV_contrasena = rs.getString("contrasena");
                String JMMV_correo = rs.getString("correo");
                int JMMV_run = rs.getInt("run");
                String JMMV_nombres = rs.getString("nombres");
                String JMMV_apellidoPaterno = rs.getString("ap_pat");
                String JMMV_apellidoMaterno = rs.getString("ap_mat");
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
    
    public List<JMMV_Cliente> JMMV_ObtenerClientePorRun(int run) {

        List<JMMV_Cliente> listaClientes = new ArrayList<>();

        //preparar consulta        
        String sql = "SELECT "
                + "c.JMMV_clientes_id_cliente AS id, "
                + "c.JMMV_clientes_run AS run, "
                + "c.JMMV_clientes_nombres AS nombres, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, "
                + "c.JMMV_clientes_apellido_materno AS ap_mat, "
                + "co.JMMV_comunas_nombre AS comuna, "
                + "c.JMMV_clientes_calle AS calle, "
                + "c.JMMV_clientes_num_calle AS numero, "
                + "c.JMMV_clientes_telefono AS telefono, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario, "
                + "u.JMMV_usuarios_contrasena AS contrasena"
                + "FROM JMMV_clientes c"
                + "JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario"
                + "JOIN JMMV_comunas co ON c.JMMV_clientes_id_comuna = co.JMMV_comunas_id_comuna"
                + "WHERE c.JMMV_clientes_esta_activo = TRUE && run = ?"
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
            
            pstmt.setInt(1, run);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {

                //obtener datos
                int JMMV_idCliente = rs.getInt("id");
                String JMMV_nomUsuario = rs.getString("nom_usuario");
                String JMMV_contrasena = rs.getString("contrasena");
                String JMMV_correo = rs.getString("correo");
                int JMMV_run = rs.getInt("run");
                String JMMV_nombres = rs.getString("nombres");
                String JMMV_apellidoPaterno = rs.getString("ap_pat");
                String JMMV_apellidoMaterno = rs.getString("ap_mat");
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

}
