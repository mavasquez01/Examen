package datosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import logica.JMMV_Reserva;

public class JMMV_ReservaDAO {

    private JMMV_Conexion conexion;

    public JMMV_ReservaDAO() {
        conexion = new JMMV_Conexion();
    }

    public List<JMMV_Reserva> JMMV_ObtenerTodasLasReservasActivas() {

        List<JMMV_Reserva> listaReservas = new ArrayList<>();

        String sql = "SELECT \n"
                + "r.JMMV_reservas_id_reserva AS id,\n"
                + "r.JMMV_reservas_id_cliente AS id_cliente,\n"
                + "c.JMMV_clientes_nombres AS nombre, \n"
                + "c.JMMV_clientes_apellido_paterno AS ap_pat,\n"
                + "c.JMMV_clientes_apellido_materno AS ap_mat,\n"
                + "CONCAT(JMMV_clientes_nombres,' ',JMMV_clientes_apellido_paterno,' ',JMMV_clientes_apellido_materno) AS nombre_cliente,\n"
                + "r.JMMV_reservas_id_bicicleta AS id_bicicleta,\n"
                + "b.JMMV_bicicletas_nombre AS nombre_bicicleta,\n"
                + "r.JMMV_reservas_fecha_inicio AS fecha_inicio,\n"
                + "r.JMMV_reservas_fecha_fin AS fecha_fin\n"
                + "FROM JMMV_reservas r\n"
                + "JOIN JMMV_clientes c ON r.JMMV_reservas_id_cliente = c.JMMV_clientes_id_cliente\n"
                + "JOIN JMMV_bicicletas b ON r.JMMV_reservas_id_bicicleta = b.JMMV_bicicletas_id_bicicleta\n"
                + "WHERE r.JMMV_reservas_esta_activo = TRUE\n"
                + "ORDER BY r.JMMV_reservas_id_reserva ASC";

        try (Connection conn = conexion.JMMV_Conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                int JMMV_idReserva = rs.getInt("id");
                int JMMV_idCliente = rs.getInt("id_cliente");
                String JMMV_nomCliente = rs.getString("nombre_cliente");
                int JMMV_idBicicleta = rs.getInt("id_bicicleta");
                String JMMV_nomBicicleta = rs.getString("nombre_bicicleta");
                LocalDate JMMV_fechaInicio = rs.getDate("fecha_inicio").toLocalDate();
                LocalDate JMMV_fechaFin = rs.getDate("fecha_fin").toLocalDate();

                JMMV_Reserva reserva = new JMMV_Reserva(JMMV_idReserva, JMMV_idCliente, JMMV_nomCliente, JMMV_idBicicleta, JMMV_nomBicicleta, JMMV_fechaInicio, JMMV_fechaFin);

                listaReservas.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaReservas;
    }

    public boolean JMMV_AgregarReserva(JMMV_Reserva reserva) {

        String sql = "INSERT INTO JMMV_reservas (JMMV_reservas_id_cliente, JMMV_reservas_id_bicicleta, JMMV_reservas_fecha_inicio,JMMV_reservas_fecha_fin,JMMV_reservas_esta_activo)\n"
                + "VALUES(?,?,?,?,?)";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //valores a enviar
            pstmt.setInt(1, reserva.getJMMV_idCliente());
            pstmt.setInt(2, reserva.getJMMV_idBicicleta());
            pstmt.setDate(3, java.sql.Date.valueOf(reserva.getJMMV_fechaInicio()));
            pstmt.setDate(4, java.sql.Date.valueOf(reserva.getJMMV_fechaFin()));
            pstmt.setBoolean (5, true);

            //ejecutar INSERT
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return true;

    }

    public boolean JMMV_ActualizarReserva(JMMV_Reserva reserva) {

        //string sql
        String sql = "UPDATE JMMV_reservas as r\n"
                + "SET\n"
                + "r.JMMV_reservas_id_cliente = ?,\n"
                + "r.JMMV_reservas_id_bicicleta = ?,\n"
                + "r.JMMV_reservas_fecha_inicio = ?,\n"
                + "r.JMMV_reservas_fecha_fin = ?\n"
                + "WHERE r.JMMV_reservas_id_reserva = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //valores
            pstmt.setInt(1, reserva.getJMMV_idCliente());
            pstmt.setInt(2, reserva.getJMMV_idBicicleta());
            pstmt.setDate(3, java.sql.Date.valueOf(reserva.getJMMV_fechaInicio()));
            pstmt.setDate(4, java.sql.Date.valueOf(reserva.getJMMV_fechaFin()));
            pstmt.setInt(5, reserva.getJMMV_idReserva());

            //ejecutar UPDATE
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean JMMV_EliminarReserva(int idReserva) {

        String sql = "UPDATE JMMV_reservas\n"
                + "SET\n"
                + "JMMV_reservas_esta_activo = FALSE\n"
                + "WHERE JMMV_reservas_id_reserva = ?";

        try (Connection conn = conexion.JMMV_Conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idReserva);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }

}
