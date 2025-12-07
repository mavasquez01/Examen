package DAO;

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

        String sql = "SELECT "
                + "r.JMMV_reservas_id_reserva AS id,"
                + "r.JMMV_reservas_id_cliente AS id_cliente,"
                + "c.JMMV_clientes_nombres AS nombre_cliente, "
                + "c.JMMV_clientes_apellido_paterno AS ap_pat,"
                + "c.JMMV_clientes_apellido_materno AS ap_mat,"
                + "CONCAT(JMMV_clientes_nombres,' ',JMMV_clientes_apellido_paterno,' ',JMMV_clientes_apellido_materno) AS nombre_cliente,\n"
                + "r.JMMV_reservas_id_bicicleta AS id_bicicleta,"
                + "b.JMMV_bicicletas_nombre AS nombre_bicicleta,"
                + "r.JMMV_reservas_fecha_inicio AS fecha_inicio,"
                + "r.JMMV_reservas_fecha_fin AS fecha_fin"
                + "FROM JMMV_reservas r"
                + "JOIN JMMV_clientes c ON r.JMMV_reservas_id_cliente = c.JMMV_clientes_id_cliente"
                + "JOIN JMMV_bicicletas b ON r.JMMV_reservas_id_bicicleta = b.JMMV_bicicletas_id_bicicleta"
                + "WHERE r.JMMV_reservas_esta_activo = TRUE"
                + "ORDER BY r.JMMV_reservas_id_reserva ASC";

        try (Connection conn = conexion.JMMV_Conectar(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery(sql)) {

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

        String sql = "INSERT INTO JMMV_reservas ("
                + "JMMV_reservas_id_cliente, "
                + "JMMV_reservas_id_bicicleta, "
                + "JMMV_reservas_fecha_inicio,"
                + "JMMV_reservas_fecha_fin,"
                + "JMMV_reservas_esta_activo)"
                + "VALUES(?,?,?,?,?)";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //valores a enviar
            pstmt.setInt(1, reserva.getJMMV_Reserva_idCliente());
            pstmt.setInt(2, reserva.getJMMV_Reserva_idBicicleta());
            pstmt.setDate(3, java.sql.Date.valueOf(reserva.getJMMV_Reserva_fechaInicio()));
            pstmt.setDate(4, java.sql.Date.valueOf(reserva.getJMMV_Reserva_fechaFin()));
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
        String sql = "UPDATE JMMV_reservas as r"
                + "SET"
                + "r.JMMV_reservas_id_cliente = ?,"
                + "r.JMMV_reservas_id_bicicleta = ?,"
                + "r.JMMV_reservas_fecha_inicio = ?,"
                + "r.JMMV_reservas_fecha_fin = ?"
                + "WHERE r.JMMV_reservas_id_reserva = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //valores
            pstmt.setInt(1, reserva.getJMMV_Reserva_idCliente());
            pstmt.setInt(2, reserva.getJMMV_Reserva_idBicicleta());
            pstmt.setDate(3, java.sql.Date.valueOf(reserva.getJMMV_Reserva_fechaInicio()));
            pstmt.setDate(4, java.sql.Date.valueOf(reserva.getJMMV_Reserva_fechaFin()));
            pstmt.setInt(5, reserva.getJMMV_Reserva_idReserva());

            //ejecutar UPDATE
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public boolean JMMV_EliminarReserva(int idReserva) {

        String sql = "UPDATE JMMV_reservas"
                + "SET"
                + "JMMV_reservas_esta_activo = FALSE"
                + "WHERE JMMV_reservas_id_reserva = ?";

        try (Connection conn = conexion.JMMV_Conectar(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idReserva);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }

    }

}
