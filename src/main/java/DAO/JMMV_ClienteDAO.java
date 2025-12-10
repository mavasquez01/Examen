package DAO;

import controlador.JMMV_Controlador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
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
                + "c.JMMV_clientes_id_cliente AS id_cliente, "
                + "c.JMMV_clientes_id_usuario AS id_usuario, "
                + "c.JMMV_clientes_run AS run, "
                + "c.JMMV_clientes_nombres AS nombres, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, "
                + "c.JMMV_clientes_apellido_materno AS ap_mat, "
                + "c.JMMV_clientes_id_comuna AS comuna, "
                + "c.JMMV_clientes_calle AS calle, "
                + "c.JMMV_clientes_num_calle AS numero, "
                + "c.JMMV_clientes_telefono AS telefono, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario, "
                + "u.JMMV_usuarios_contrasena AS contrasena, "
                + "c.JMMV_clientes_correo AS correo "
                + "FROM JMMV_clientes c "
                + "JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario "
                + "WHERE c.JMMV_clientes_esta_activo = TRUE "
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    //obtener datos
                    int JMMV_idCliente = rs.getInt("id_cliente");
                    int JMMV_idUsuario = rs.getInt("id_usuario");
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
                    JMMV_Cliente cliente = new JMMV_Cliente(JMMV_idCliente, JMMV_idUsuario, JMMV_nomUsuario, JMMV_contrasena, JMMV_correo, JMMV_run, JMMV_nombres, JMMV_apellidoPaterno, JMMV_apellidoMaterno, nombreComuna, JMMV_calle, JMMV_numCalle, JMMV_telefono);

                    //agregar cliente a lista
                    listaClientes.add(cliente);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener clientes: "+e.getMessage());
        }
        System.out.println(listaClientes.size());
        return listaClientes;

    }

    public boolean JMMV_AgregarCliente(JMMV_Cliente cliente)  {
        
        Connection conn = null;

        //variable para guardar la PK
        int pkInsertada = -1;

        //string para primer INSERT
        String sqlUsuarios = "INSERT INTO JMMV_usuarios ("
                + "JMMV_usuarios_nom_usuario, "
                + "JMMV_usuarios_contrasena, "
                + "JMMV_usuarios_id_rol, "
                + "JMMV_usuarios_esta_activo) "
                + "VALUES(?,?,?,?)";

        //string segundo INSERT            
        String sqlClientes = "INSERT INTO JMMV_clientes ("
                + "JMMV_clientes_id_usuario, "
                + "JMMV_clientes_correo, "
                + "JMMV_clientes_run, "
                + "JMMV_clientes_nombres, "
                + "JMMV_clientes_apellido_paterno, "
                + "JMMV_clientes_apellido_materno, "
                + "JMMV_clientes_id_comuna, "
                + "JMMV_clientes_calle, "
                + "JMMV_clientes_num_calle, "
                + "JMMV_clientes_telefono, "
                + "JMMV_clientes_esta_activo) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        int idComuna = JMMV_ObtenerIdComunaPorNombre(cliente.getJMMV_Cliente_comuna());
        System.out.println("TEST | ID comuna: "+ idComuna);

        try {
            conn = conexion.JMMV_Conectar();
            //desactivar auto commit
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtUno = conn.prepareStatement(sqlUsuarios, Statement.RETURN_GENERATED_KEYS); 
                    PreparedStatement pstmtDos = conn.prepareStatement(sqlClientes)) {

                //preparar valores a enviar
                pstmtUno.setString(1, cliente.getJMMV_Cliente_nomUsuario());
                pstmtUno.setString(2, cliente.getJMMV_Cliente_contrasena());                
                pstmtUno.setInt(3, 2); //rol de cliente
                pstmtUno.setBoolean(4, true); //usuario activo

                //ejecutar primer INSERT
                int filaUno = pstmtUno.executeUpdate();
                System.out.println("TEST | Agregar cliente, tabla usuarios, filas nuevas: "+filaUno);

                //recuperar PK
                try (ResultSet rs = pstmtUno.getGeneratedKeys()) {
                    if (rs.next()) {
                        System.out.println(rs);
                        pkInsertada = rs.getInt(1);

                        System.out.println("Test JM | pk insertada: " + pkInsertada);
                    }
                }

                pstmtDos.setInt(1, pkInsertada);
                pstmtDos.setString(2, cliente.getJMMV_Cliente_correo());
                pstmtDos.setInt(3, cliente.getJMMV_Cliente_run());
                pstmtDos.setString(4, cliente.getJMMV_Cliente_nombres());
                pstmtDos.setString(5, cliente.getJMMV_Cliente_apellidoPaterno());
                pstmtDos.setString(6, cliente.getJMMV_Cliente_apellidoMaterno());
                pstmtDos.setInt(7, idComuna);
                pstmtDos.setString(8, cliente.getJMMV_Cliente_calle());
                pstmtDos.setInt(9, cliente.getJMMV_Cliente_numCalle());
                pstmtDos.setInt(10, cliente.getJMMV_Cliente_telefono());
                pstmtDos.setBoolean(11, true); //cliente activo

                //ejecutar segundo INSERT
                int filaDos = pstmtDos.executeUpdate();
                System.out.println("TEST | Agregar cliente, tabla clientes, filas nuevas: "+filaDos);

                //commit
                conn.commit();

                return true;

            }
        } catch (SQLException e) {
            
            if (conn != null) {
                try {
                    System.out.println("Error en transacción. Se ejecutará ROLLBACK: "+e.getMessage());
                    System.out.println("Error en transacción. Se ejecutará ROLLBACK: "+e.getSQLState());
                    //rollback
                    conn.rollback();
                } catch (SQLException er) {
                    System.out.println("Error al ejecutar ROLLBACK: "+er.getMessage());
                }
            }
            return false;
        }finally{
            if (conn != null){
                try{
                    conn.setAutoCommit(true);
                    conn.close();
                    
                }catch(SQLException e){
                    System.out.println("Error al cerrar la conexión: "+e.getMessage());
                }
            }
        }
    }

    public boolean JMMV_ActualizarCliente(JMMV_Cliente cliente) {
        
        Connection conn = null;

        //obtener id usuario        
        //int idUsuario = JMMV_ObtenerIdUsuario(cliente.getJMMV_Cliente_idCliente());
        System.out.println("id usuario a actualizar : "+ cliente.getJMMV_Cliente_idUsuario());

        //string para primer UPDATE
        String sqlUno = "UPDATE JMMV_usuarios "
                + "SET "
                + "JMMV_usuarios_nom_usuario = ?, "
                + "JMMV_usuarios_contrasena = ? "
                + "WHERE JMMV_usuarios_id_usuario = ? ";

        //string segundo UPDATE            
        String sqlDos = "UPDATE jmmv_clientes "
                + "SET "                
                + "JMMV_clientes_run = ?, "
                + "JMMV_clientes_nombres = ?, "
                + "JMMV_clientes_apellido_paterno = ?, "
                + "JMMV_clientes_apellido_materno = ?, "
                + "JMMV_clientes_id_comuna = ?, "
                + "JMMV_clientes_calle = ?, "
                + "JMMV_clientes_num_calle = ?, "
                + "JMMV_clientes_correo = ?, "
                + "JMMV_clientes_telefono = ? "
                + "WHERE JMMV_clientes_id_usuario = ?";
        
        try {
            conn = conexion.JMMV_Conectar();
            //desactivar auto commit
            conn.setAutoCommit(false);

            try (PreparedStatement pstmtUno = conn.prepareStatement(sqlUno); 
                    PreparedStatement pstmtDos = conn.prepareStatement(sqlDos)) {

                //preparar valores a enviar
                pstmtUno.setString(1, cliente.getJMMV_Cliente_nomUsuario());
                pstmtUno.setString(2, cliente.getJMMV_Cliente_contrasena());                
                pstmtUno.setInt(3, cliente.getJMMV_Cliente_idUsuario());

                //ejecutar primer UPDATE
                pstmtUno.executeUpdate();

                //preparar valores a enviar
                pstmtDos.setInt(1, cliente.getJMMV_Cliente_run());
                pstmtDos.setString(2, cliente.getJMMV_Cliente_nombres());
                pstmtDos.setString(3, cliente.getJMMV_Cliente_apellidoPaterno());
                pstmtDos.setString(4, cliente.getJMMV_Cliente_apellidoMaterno());
                pstmtDos.setInt(5, JMMV_ObtenerIdComunaPorNombre(cliente.getJMMV_Cliente_comuna()));
                pstmtDos.setString(6, cliente.getJMMV_Cliente_calle());
                pstmtDos.setInt(7, cliente.getJMMV_Cliente_numCalle());
                pstmtDos.setString(8, cliente.getJMMV_Cliente_correo());
                pstmtDos.setInt(9, cliente.getJMMV_Cliente_telefono());
                pstmtDos.setInt(10, cliente.getJMMV_Cliente_idUsuario());
                

                //ejecutar segundo UPDATE
                pstmtDos.executeUpdate();

                //commit
                conn.commit();
                return true;
            }

        } catch (SQLException e) {

            if (conn != null) {
                try {
                    System.out.println("Error en transacción. Se ejecutará ROLLBACK: "+e.getMessage());
                    //rollback
                    conn.rollback();
                } catch (SQLException er) {
                    System.out.println("Error al ejecutar ROLLBACK: " + er.getMessage());
                }
            }
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();

                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    public boolean JMMV_EliminarCliente(int idCliente) {

        System.out.println("Test | idCliente a eliminar: "+idCliente);
        
        String sql = "UPDATE JMMV_clientes "
                + "SET "
                + "JMMV_clientes_esta_activo = ? "
                + "WHERE JMMV_clientes_id_cliente = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {            

            pstmt.setBoolean(1, false);//desactiva cliente
            pstmt.setInt(2, idCliente);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Error al eliminar cliente: "+e.getMessage());

            return false;
        }

    }


    //métodos auxiliares
    //obtener id de comuna por su nombre
    public int JMMV_ObtenerIdComunaPorNombre(String nombreComuna) {

        String sql = "SELECT JMMV_comunas_id_comuna AS id_comuna "
                + "FROM JMMV_comunas "
                + "WHERE JMMV_comunas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreComuna);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("id_comuna");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }

    public List<JMMV_Cliente> JMMV_ObtenerNombreApellidoYNombresCompletosClientesActivos() {

        List<JMMV_Cliente> listaNombreApellidoYNombreCompleto = new ArrayList<>();

        //consulta
        String sql = "SELECT "
                + "SUBSTRING_INDEX(c.JMMV_clientes_nombres, ' ' ,1) AS primer_nombre, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, "
                + "CONCAT(JMMV_clientes_nombres,' ',JMMV_clientes_apellido_paterno,' ',COALESCE(JMMV_clientes_apellido_materno,'')) AS nombre_completo "
                + "FROM JMMV_clientes c "
                + "WHERE c.JMMV_clientes_esta_activo = ? "
                + "ORDER BY c.JMMV_clientes_nombres ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, true);//cliente activo

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    //agregar nombre a la lista
                    String JMMV_primerNombre = rs.getString("primer_nombre");
                    String JMMV_apellidoPaterno = rs.getString("ap_pat");                    
                    String JMMV_nombreCompleto = rs.getString("nombre_completo");
                    
                    //crear objeto de la clase
                    JMMV_Cliente cliente = new JMMV_Cliente(JMMV_primerNombre,JMMV_apellidoPaterno,JMMV_nombreCompleto);
                    
                    //agregar cliente a lista
                    listaNombreApellidoYNombreCompleto.add(cliente);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error en obtención de lista de clientes activos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en obtención de lista de clientes activos.");
        }

        return listaNombreApellidoYNombreCompleto;

    }

    
    //obtener nombre completo de todos los clientes activos, COMBOBOX de reservas
    public List<String> JMMV_ObtenerNombresCompletosClientesActivos() {

        List<String> listaNombresCompletos = new ArrayList<>();

        //consulta
        String sql = "SELECT CONCAT(JMMV_clientes_nombres,' ',JMMV_clientes_apellido_paterno,' ',COALESCE(JMMV_clientes_apellido_materno,''))  "
                + "AS nombre_completo  "
                + "FROM JMMV_clientes c  "
                + "WHERE c.JMMV_clientes_esta_activo = ? "
                + "ORDER BY c.JMMV_clientes_nombres ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, true);//cliente activo

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    //agregar nombre a la lista
                    listaNombresCompletos.add(rs.getString("nombre_completo"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en obtención de lista de clientes activos.");
        }

        return listaNombresCompletos;

    }

    public String JMMV_ObtenerNombreComunaPorId(int idComuna) {
        String sql = "SELECT JMMV_comunas_nombre AS comuna "
                + "FROM JMMV_comunas "
                + "WHERE JMMV_comunas_id_comuna = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idComuna);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getString("comuna");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int JMMV_ObtenerIdUsuario(int idCliente) {
        String sql = "SELECT "
                + "u.JMMV_usuarios_id_usuario AS id_usuario "
                + "FROM JMMV_usuarios u "
                + "JOIN JMMV_clientes c ON u.JMMV_usuarios_id_usuario = c.JMMV_clientes_id_usuario "
                + "WHERE c.JMMV_clientes_id_cliente = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("id_usuario");
                }
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
                + "c.JMMV_comunas_nombre AS comuna "
                + "FROM JMMV_comunas c "
                + "WHERE c.JMMV_comunas_esta_activo = ? "
                + "ORDER BY JMMV_comunas_nombre ASC";
        
        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, true);//comuna activa

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    listaComunas.add(rs.getString("comuna"));
                    //System.out.println("comuna encontrada");
                }
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
                + "c.JMMV_clientes_id_cliente AS id_cliente, "
                + "c.JMMV_clientes_id_usuario AS id_usuario, "
                + "c.JMMV_clientes_run AS run, "
                + "c.JMMV_clientes_nombres AS nombres, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, "
                + "c.JMMV_clientes_apellido_materno AS ap_mat, "
                + "co.JMMV_comunas_nombre AS comuna, "
                + "c.JMMV_clientes_calle AS calle, "
                + "c.JMMV_clientes_correo AS correo, "
                + "c.JMMV_clientes_num_calle AS numero, "
                + "c.JMMV_clientes_telefono AS telefono, "
                + "u.JMMV_usuarios_id_usuario AS id_usuario, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario, "
                + "u.JMMV_usuarios_contrasena AS contrasena "
                + "FROM JMMV_clientes c "
                + "JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario "
                + "JOIN JMMV_comunas co ON c.JMMV_clientes_id_comuna = co.JMMV_comunas_id_comuna "
                + "WHERE c.JMMV_clientes_esta_activo = ? && c.JMMV_clientes_nombres = ? "
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setBoolean(1, true);
            pstmt.setString(2, nombres);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    //obtener datos
                    int JMMV_idCliente = rs.getInt("id_cliente");
                    int JMMV_idUsuario = rs.getInt("id_usuario");
                    String JMMV_nomUsuario = rs.getString("nombre_usuario");
                    String JMMV_contrasena = rs.getString("contrasena");
                    String JMMV_correo = rs.getString("correo");
                    int JMMV_run = rs.getInt("run");
                    String JMMV_nombres = rs.getString("nombres");
                    String JMMV_apellidoPaterno = rs.getString("ap_pat");
                    String JMMV_apellidoMaterno = rs.getString("ap_mat");
                    String JMMV_comuna = rs.getString("comuna");
                    String JMMV_calle = rs.getString("calle");
                    int JMMV_numCalle = rs.getInt("numero");
                    int JMMV_telefono = rs.getInt("telefono");

                    //crear objeto de la clase 
                    JMMV_Cliente cliente = new JMMV_Cliente(JMMV_idCliente, JMMV_idUsuario, JMMV_nomUsuario, JMMV_contrasena, JMMV_correo, JMMV_run, JMMV_nombres, JMMV_apellidoPaterno, JMMV_apellidoMaterno, JMMV_comuna, JMMV_calle, JMMV_numCalle, JMMV_telefono);

                    //agregar cliente a lista
                    listaClientes.add(cliente);
                    System.out.println(listaClientes.size());
                }
            }catch (SQLException e) {
            e.printStackTrace();
                System.out.println("Error en ResultSet."+e.getMessage());
        }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en envío de consulta."+e.getMessage());
        }

        return listaClientes;

    }

    public List<JMMV_Cliente> JMMV_ObtenerClientePorRun(int run) {

        List<JMMV_Cliente> listaClientes = new ArrayList<>();

        //preparar consulta        
        String sql = "SELECT "
                + "c.JMMV_clientes_id_cliente AS id_cliente, "
                + "c.JMMV_clientes_id_usuario AS id_usuario, "
                + "c.JMMV_clientes_run AS run, "
                + "c.JMMV_clientes_nombres AS nombres, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat, "
                + "c.JMMV_clientes_apellido_materno AS ap_mat, "
                + "co.JMMV_comunas_nombre AS comuna, "
                + "c.JMMV_clientes_calle AS calle, "
                + "c.JMMV_clientes_num_calle AS numero, "
                + "c.JMMV_clientes_telefono AS telefono, "
                + "u.JMMV_usuarios_nom_usuario AS nombre_usuario, "
                + "u.JMMV_usuarios_contrasena AS contrasena "
                + "FROM JMMV_clientes c "
                + "JOIN JMMV_usuarios u ON c.JMMV_clientes_id_usuario = u.JMMV_usuarios_id_usuario "
                + "JOIN JMMV_comunas co ON c.JMMV_clientes_id_comuna = co.JMMV_comunas_id_comuna "
                + "WHERE c.JMMV_clientes_esta_activo = ? && c.JMMV_clientes_run = ? "
                + "ORDER BY c.JMMV_clientes_id_cliente ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setBoolean(1, true); //cliente activo
            pstmt.setInt(2, run);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    //obtener datos
                    int JMMV_idCliente = rs.getInt("id_cliente");
                    int JMMV_idUsuario = rs.getInt("id_usuario");
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
                    JMMV_Cliente cliente = new JMMV_Cliente(JMMV_idCliente, JMMV_idUsuario, JMMV_nomUsuario, JMMV_contrasena, JMMV_correo, JMMV_run, JMMV_nombres, JMMV_apellidoPaterno, JMMV_apellidoMaterno, nombreComuna, JMMV_calle, JMMV_numCalle, JMMV_telefono);

                    //agregar cliente a lista
                    listaClientes.add(cliente);
                    System.out.println("TEST | cliente encontrado por run.");
                }
            }catch (SQLException e) {
            e.printStackTrace();
                System.out.println("Error en ResultSet."+e.getMessage());
        }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en envío de consulta"+e.getMessage());
        }

        return listaClientes;

    }
    
    
    //necesita verificación, es necesario para el método de buscar si cliente tiene reservas vigentes
    public int JMMV_ObtenerIdClientePorNombres(String nombres) {
        String sql = "SELECT  "
                + "c.JMMV_clientes_id_cliente AS id_cliente "
                + "FROM JMMV_clientes c "
                + "WHERE c.JMMV_clientes_nombres = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombres);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("id_cliente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error ResultSet: "+e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener id cliente: "+e.getMessage());
        }
        return -1; //retorna valor no válido
    }
 
    //devuelve el número de reservas vigentes del cliente (vigente= la fecha de fin es mayor o igual a hoy)
    //necesito verificar si NOW funciona en java
    public int JMMV_ContarReservasVigentesDeClientesPorIdCliente(int idCliente) {
        String sql = "SELECT "
                + "COUNT(*) "
                + "FROM JMMV_reservas r "
                + "JOIN jmmv_clientes c "
                + "WHERE c.JMMV_clientes_id_cliente = ? && r.JMMV_reservas_fecha_fin >= NOW() && c.JMMV_clientes_esta_activo = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idCliente);
            pstmt.setBoolean(2, true);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    System.out.println("TEST | reservas vigentes encontradas");
                    System.out.println(rs.getInt(1));
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }

}
