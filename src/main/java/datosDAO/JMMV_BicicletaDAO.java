package datosDAO;

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
                + "t.JMMV_tipos_bicicletas_nombre AS tipo, "
                + "b.JMMV_bicicletas_esta_disponible AS disponibilidad, "
                + "t.JMMV_tipos_bicicletas_descripcion AS descripcion\n"
                + "FROM JMMV_bicicletas AS b\n"
                + "JOIN JMMV_tipos_bicicletas AS t ON b.JMMV_bicicletas_id_tipo_bicicleta = t.JMMV_tipos_bicicletas_id_tipo_bicicleta\n"
                + "WHERE b.JMMV_bicicletas_esta_activo = TRUE\n"
                + "ORDER BY b.JMMV_bicicletas_id_bicicleta ASC";

        //enviar consulta
        try (Connection conn = conexion.JMMV_Conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                //obtener datos
                int JMMV_idBicicleta = rs.getInt("id");
                String JMMV_nombre = rs.getString("nombre");
                int JMMV_tipo = rs.getInt("tipo");                
                String JMMV_descripcion = rs.getString("descripcion");
                boolean JMMV_estaDisponible = rs.getBoolean("disponibilidad");

                //obtener nombre de tipo de bicicleta
                String nombreTipo = ObtenerTipoBicicletaPorId(JMMV_tipo);

                //crear objeto de la clase
                JMMV_Bicicleta bicicleta = new JMMV_Bicicleta(JMMV_idBicicleta, JMMV_nombre, nombreTipo, JMMV_descripcion, JMMV_estaDisponible);

                //agregar bicicleta a lista
                listaBicicletas.add(bicicleta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaBicicletas;

    }

    public boolean JMMV_AgregarBicicleta(JMMV_Bicicleta bicicleta) {

        String sql;
        sql = "INSERT INTO JMMV_bicicletas ("
                + "JMMV_bicicletas_nombre,"
                + "JMMV_bicicletas_id_tipo_bicicleta,"
                + "JMMV_bicicletas_esta_disponible,"
                + "JMMV_bicicletas_esta_activo)\n"
                + "VALUES(?,?,?,?)";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //obtener id de la bicicleta
            int idBicicleta = JMMV_ObtenerIdBicicletaPorNombre(bicicleta.getJMMV_Bicicleta_tipoBicicleta());

            //preparar valores a enviar
            pstmt.setString(1, bicicleta.getJMMV_Bicicleta_nombre());
            pstmt.setInt(2, idBicicleta);
            pstmt.setBoolean(3, true);
            pstmt.setBoolean(4, true);

            //ejecutar INSERT
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean JMMV_ActualizarBicicleta(JMMV_Bicicleta bicicleta) {

        //string sql
        String sql = "UPDATE JMMV_bicicletas AS b\n"
                + "SET b.JMMV_bicicletas_nombre = ?, b.JMMV_bicicletas_id_tipo_bicicleta = ?, "
                + "b.JMMV_bicicletas_esta_disponible = ?\n"
                + "WHERE JMMV_bicicletas_id_bicicleta = ?";

        int idBicicleta = JMMV_ObtenerIdBicicletaPorNombre(bicicleta.getJMMV_Bicicleta_tipoBicicleta());

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //preparar valores a enviar
            pstmt.setString(1, bicicleta.getJMMV_Bicicleta_nombre());
            pstmt.setInt(2, idBicicleta);
            pstmt.setBoolean(3, bicicleta.isJMMV_Bicicleta_estaDisponible());
            pstmt.setInt(4, bicicleta.getJMMV_Bicicleta_idBicicleta());

            //ejecutar UPDATE
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean JMMV_EliminarBicicleta(String nombreBicicleta) {

        String sql = "UPDATE JMMV_bicicletas\n"
                + "SET\n"
                + "JMMV_bicicletas_esta_activo = FALSE\n"
                + "WHERE JMMV_bicicletas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreBicicleta);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }

    public String ObtenerTipoBicicletaPorId(int idBicicleta) {

        String sql = "SELECT t.JMMV_tipos_bicicletas_nombre AS tipo\n"
                + "FROM JMMV_tipos_bicicletas t\n"
                + "WHERE t.JMMV_tipos_bicicletas_id_tipo_bicicleta = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idBicicleta);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("tipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public int JMMV_ObtenerIdBicicletaPorNombre(String idBicicleta) {
        String sql = "SELECT t.JMMV_tipos_bicicletas_id_tipo_bicicleta AS tipo\n"
                + "FROM JMMV_tipos_bicicletas t\n"
                + "WHERE t.JMMV_tipos_bicicletas_nombre = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idBicicleta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id_tipo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; //retorna valor no válido
    }

}
