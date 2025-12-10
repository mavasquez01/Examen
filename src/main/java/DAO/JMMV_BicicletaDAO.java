package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logica.JMMV_Bicicleta;



public class JMMV_BicicletaDAO {
    
    //atributo para la conexión a BD
    private JMMV_Conexion conexion;
    
    public JMMV_BicicletaDAO() {
    
        conexion = new JMMV_Conexion();
    }

    public List<JMMV_Bicicleta> JMMV_ObtenerTodasLasBicicletasActivas() {

        List<JMMV_Bicicleta> listaBicicletas = new ArrayList<>();

        //preparar consulta        
        String sql = "SELECT "
                + "b.JMMV_bicicletas_id_bicicleta AS id, "
                + "b.JMMV_bicicletas_nombre AS nombre, "
                + "b.JMMV_bicicletas_id_tipo_bicicleta AS tipo, "
                + "b.JMMV_bicicletas_esta_disponible AS disponibilidad "
                + "FROM JMMV_bicicletas AS b "
                + "JOIN JMMV_tipos_bicicletas AS t ON b.JMMV_bicicletas_id_tipo_bicicleta = t.JMMV_tipos_bicicletas_id_tipo_bicicleta "
                + "WHERE b.JMMV_bicicletas_esta_activo = ? "
                + "ORDER BY b.JMMV_bicicletas_id_bicicleta ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setBoolean(1, true); //bicicleta activa
            
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    //obtener datos
                    int JMMV_idBicicleta = rs.getInt("id");
                    String JMMV_nombre = rs.getString("nombre");
                    int JMMV_tipo = rs.getInt("tipo");
                    boolean JMMV_estaDisponible = rs.getBoolean("disponibilidad");

                    //obtener nombre de tipo de bicicleta
                    String nombreTipo = JMMV_ObtenerTipoBicicletaPorId(JMMV_tipo);

                    //crear objeto de la clase
                    JMMV_Bicicleta bicicleta = new JMMV_Bicicleta(JMMV_idBicicleta, JMMV_nombre, nombreTipo, JMMV_estaDisponible);

                    //agregar bicicleta a lista
                    listaBicicletas.add(bicicleta);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaBicicletas;

    }

    public boolean JMMV_AgregarBicicleta(JMMV_Bicicleta bicicleta) {

        String sql;
        sql = "INSERT INTO JMMV_bicicletas ("
                + "JMMV_bicicletas_nombre, "
                + "JMMV_bicicletas_id_tipo_bicicleta, "
                + "JMMV_bicicletas_esta_disponible, "
                + "JMMV_bicicletas_esta_activo) "
                + "VALUES(?,?,?,?)";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //obtener id de tipo de la bicicleta
            int idTipoBicicleta = JMMV_ObtenerIdTipoNombre(bicicleta.getJMMV_Bicicleta_tipoBicicleta());

            //preparar valores a enviar
            pstmt.setString(1, bicicleta.getJMMV_Bicicleta_nombre());
            pstmt.setInt(2, idTipoBicicleta);
            pstmt.setBoolean(3, bicicleta.isJMMV_Bicicleta_estaDisponible());
            pstmt.setBoolean(4, true); //activo

            //ejecutar INSERT
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        
    }

    public boolean JMMV_ActualizarBicicleta(JMMV_Bicicleta bicicleta) {

        //string sql
        String sql = "UPDATE JMMV_bicicletas AS b "
                + "SET "
                + "b.JMMV_bicicletas_nombre = ?, "
                + "b.JMMV_bicicletas_id_tipo_bicicleta = ?, "
                + "b.JMMV_bicicletas_esta_disponible = ? "
                + "WHERE JMMV_bicicletas_id_bicicleta = ? ";
        System.out.println("Test | tipo bici: "+bicicleta.getJMMV_Bicicleta_tipoBicicleta());
        int idTipoBicicleta = JMMV_ObtenerIdTipoNombre(bicicleta.getJMMV_Bicicleta_tipoBicicleta());
        System.out.println(idTipoBicicleta);
        System.out.println("Test | id bici: "+bicicleta.getJMMV_Bicicleta_idBicicleta());

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //preparar valores a enviar
            pstmt.setString(1, bicicleta.getJMMV_Bicicleta_nombre());
            pstmt.setInt(2, idTipoBicicleta);
            pstmt.setBoolean(3, bicicleta.isJMMV_Bicicleta_estaDisponible());
            pstmt.setInt(4, bicicleta.getJMMV_Bicicleta_idBicicleta());

            //ejecutar UPDATE
            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        
    }

    //eliminar bicicleta por nombre
    public boolean JMMV_EliminarBicicleta(String nombreBicicleta) {

        String sql = "UPDATE JMMV_bicicletas "
                + "SET "
                + "JMMV_bicicletas_esta_activo = ? "
                + "WHERE JMMV_bicicletas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, false); //desactivar
            pstmt.setString(2, nombreBicicleta);

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }

    //uso POSIBLE
    public boolean JMMV_CambiarDisponibilidadBicicleta(int idBicicleta){
        
        String sql = "UPDATE JMMV_bicicletas "
                + "SET "
                + "JMMV_bicicletas_esta_disponible = NOT JMMV_bicicletas_esta_disponible "
                + "WHERE JMMV_bicicletas_id_bicicleta = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idBicicleta);

            pstmt.executeUpdate(); 
            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
          
    }
    //recibe id de TIPO de bicicleta, retorna el nombre del tipo
    public String JMMV_ObtenerTipoBicicletaPorId(int idBicicleta) {

        String sql = "SELECT t.JMMV_tipos_bicicletas_nombre AS nombre_tipo "
                + "FROM JMMV_tipos_bicicletas t "
                + "WHERE t.JMMV_tipos_bicicletas_id_tipo_bicicleta = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idBicicleta);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getString("nombre_tipo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int JMMV_ObtenerIdBicicletaPorNombre(String nombreBicicleta) {
        String sql = "SELECT b.JMMV_bicicletas_id_bicicleta AS id_bicicleta "
                + "FROM JMMV_bicicletas b "
                + "WHERE b.JMMV_bicicletas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreBicicleta);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("id_bicicleta");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }
    
    public JMMV_Bicicleta JMMV_ObtenerBicicletaPorNombre(String nombreBicicleta) {
        String sql = "SELECT b.JMMV_bicicletas_nombre AS nombre, "
                + "b.JMMV_bicicletas_id_bicicleta AS id, "
                + "t.JMMV_tipos_bicicletas_nombre as nombreTipo, "
                + "b.JMMV_bicicletas_esta_disponible AS disponibilidad, "
                + "b.JMMV_bicicletas_esta_activo AS activo "
                + "FROM JMMV_bicicletas b "
                + "JOIN JMMV_tipos_bicicletas t on b.JMMV_bicicletas_id_tipo_bicicleta = t.JMMV_tipos_bicicletas_id_tipo_bicicleta "
                + "WHERE b.JMMV_bicicletas_nombre = ? && b.JMMV_bicicletas_esta_activo = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreBicicleta);
            pstmt.setBoolean(2, true);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String nombreTipo = rs.getString("nombreTipo");
                    boolean disponibilidad = rs.getBoolean("disponibilidad");
                    boolean activo = rs.getBoolean("activo");
                    JMMV_Bicicleta bicicleta = new JMMV_Bicicleta(id, nombreBicicleta, nombreTipo, disponibilidad, activo);
                    return bicicleta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener bicicletas activas por nombre: "+e.getMessage());
        }
        return null; //retorna valor no válido
    }
    
    public String JMMV_ObtenerNombreBicicletaPorId(int idBicicleta) {
        String sql = "SELECT b.JMMV_bicicletas_nombre AS nombre_bicicleta "
                + "FROM JMMV_bicicletas b "
                + "WHERE b.JMMV_bicicletas_id_bicicleta = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idBicicleta);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getString("nombre_bicicleta");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    //recibe nombre de TIPO de bicicleta, retorna el id del tipo
    public int JMMV_ObtenerIdTipoNombre(String tipoBicicleta) {
        
        String sql = "SELECT t.JMMV_tipos_bicicletas_id_tipo_bicicleta AS nombre_tipo "
                + "FROM JMMV_tipos_bicicletas t "
                + "WHERE t.JMMV_tipos_bicicletas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipoBicicleta);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("nombre_tipo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }
    
    //obtener solo nombres de bicicletas activas
    public List<String> JMMV_ObtenerNombresBicicletasActivas(){
        
        List<String> listaNombresBicicletas = new ArrayList<>();

        String sql = "SELECT b.JMMV_bicicletas_nombre AS nombre "
                + "FROM JMMV_bicicletas AS b "
                + "WHERE b.JMMV_bicicletas_esta_activo = ? "
                + "ORDER BY b.JMMV_bicicletas_nombre ASC";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, true); //activa

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    listaNombresBicicletas.add(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaNombresBicicletas;
        
    }
    
    //para combobox, todos los tipos de bicicletas activos, ordenados
    public List<String> JMMV_ObtenerTiposDeBicicletaActivos() {
        
        List<String> listaTiposBicicletas = new ArrayList<>();
        
        String sql = "SELECT t.JMMV_tipos_bicicletas_nombre AS tipo_bicicleta "
                + "FROM JMMV_tipos_bicicletas t "
                + "WHERE t.JMMV_tipos_bicicletas_esta_activo = ? "
                + "ORDER BY t.JMMV_tipos_bicicletas_nombre ASC";
        
        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, true); //activa

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    listaTiposBicicletas.add(rs.getString("tipo_bicicleta"));
                    //System.out.println("tamaño lista bici: "+listaTiposBicicletas.size());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTiposBicicletas;
        
    }
    
    public boolean JMMV_NombreBicicletaYaRegistrado(String nombreBicicleta) {
        String sql = "SELECT  "
                + "COUNT(*) "
                + "FROM jmmv_bicicletas b "
                + "WHERE b.JMMV_bicicletas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreBicicleta);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("Error en verificación: " + e.getMessage());

        }
        return false;
    }

}
