package controlador;

import DAO.JMMV_UsuarioDAO;
import DAO.JMMV_ClienteDAO;
import DAO.JMMV_BicicletaDAO;
import DAO.JMMV_ReservaDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import logica.JMMV_Cliente;
import logica.JMMV_Bicicleta;
import logica.JMMV_Reserva;

public class JMMV_Controlador {

    private JMMV_UsuarioDAO usuarioDAO = new JMMV_UsuarioDAO();
    private JMMV_ClienteDAO clienteDAO = new JMMV_ClienteDAO();
    private JMMV_BicicletaDAO bicicletaDAO = new JMMV_BicicletaDAO();
    private JMMV_ReservaDAO reservaDAO = new JMMV_ReservaDAO();

    public JMMV_Controlador() {
    }

    //login
    public String JMMV_VerificarLogin(String user, String pass) throws SQLException {

        String usuario = usuarioDAO.JMMV_VerificarLogin(user, pass);

        return usuario;

    }

    //operaciones relacionadas con los clientes
    public List<JMMV_Cliente> JMMV_ObtenerTodosLosClientesActivos() {

        List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerTodosLosClientesActivos();

        return listaClientes;
    }

    public boolean JMMV_AgregarCliente(JMMV_Cliente cliente) {

        return clienteDAO.JMMV_AgregarCliente(cliente);

    }

    public boolean JMMV_ActualizarCliente(JMMV_Cliente cliente) {

        return clienteDAO.JMMV_ActualizarCliente(cliente);

    }

    public boolean JMMV_EliminarCliente(int idCliente) {

        return clienteDAO.JMMV_EliminarCliente(idCliente);

    }

    public List<JMMV_Cliente> JMMV_ObtenerClientePorNombre(String nombres) {

        List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorNombre(nombres);
        System.out.println(listaClientes.size());

        return listaClientes;
    }

    public List<JMMV_Cliente> JMMV_ObtenerNombreApellidoYNombresCompletosClientesActivos() {

        List<JMMV_Cliente> listaNombreApellidoYNombreCompleto = clienteDAO.JMMV_ObtenerNombreApellidoYNombresCompletosClientesActivos();

        return listaNombreApellidoYNombreCompleto;
    }

    public List<JMMV_Cliente> JMMV_ObtenerClienteDeCBox(String nombreCompleto) {
        String[] transformador = nombreCompleto.split("\\s+");
        String nombre = "";

        switch (transformador.length) {
            case 2 -> {
                nombre = nombre.concat(transformador[0]);
                List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorNombre(nombre);
                return listaClientes;
            }
            case 3 -> {
                nombre = nombre.concat(transformador[0] + " ");
                nombre = nombre.concat(transformador[1]);
                try {
                    List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorNombre(nombre);
                    listaClientes.get(0);
                    return listaClientes;
                } catch (Exception e) {
                    System.out.println("No");
                    nombre = "";
                }
                nombre = nombre.concat(transformador[0]);
                try {
                    List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorNombre(nombre);
                    listaClientes.get(0);
                    return listaClientes;
                } catch (Exception e) {
                    System.out.println("No");
                    nombre = "";
                    return null;
                }
            }
            case 4 -> {
                nombre = nombre.concat(transformador[0] + " ");
                nombre = nombre.concat(transformador[1]);
                try {
                    List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorNombre(nombre);
                    listaClientes.get(0);
                    return listaClientes;
                } catch (Exception e) {
                    System.out.println("No");
                    nombre = "";
                }
                nombre = nombre.concat(transformador[0] + " ");
                nombre = nombre.concat(transformador[1] + " ");
                nombre = nombre.concat(transformador[2]);
                try {
                    List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorNombre(nombre);
                    listaClientes.get(0);
                    return listaClientes;
                } catch (Exception e) {
                    System.out.println("No");
                    nombre = "";
                    return null;
                }
            }
            case 5 -> {
                nombre = nombre.concat(transformador[0] + " ");
                nombre = nombre.concat(transformador[1] + " ");
                nombre = nombre.concat(transformador[2]);
                System.out.println(nombre);
                try {
                    List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorNombre(nombre);
                    listaClientes.get(0);
                    return listaClientes;
                } catch (Exception e) {
                    System.out.println("No");
                    return null;
                }
            }
            default -> {
                return null;
            }
        }
    }

    public List<JMMV_Cliente> JMMV_ObtenerClientePorRun(int run) {

        List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerClientePorRun(run);

        return listaClientes;

    }

    public List<String> JMMV_ObtenerNombresCompletosClientesActivos() {

        List<String> listaNombresCompletos = clienteDAO.JMMV_ObtenerNombresCompletosClientesActivos();

        return listaNombresCompletos;

    }

    //para combobox comunas
    public List<String> JMMV_ObtenerComunasActivas() {

        List<String> comunas = clienteDAO.JMMV_ObtenerComunasActivas();
        System.out.println(comunas.size());

        return comunas;

    }

    //operaciones relacionadas a las bicicletas
    public List<JMMV_Bicicleta> JMMV_ObtenerTodasLasBicicletasActivas() {

        List<JMMV_Bicicleta> listaBicicletas = bicicletaDAO.JMMV_ObtenerTodasLasBicicletasActivas();

        return listaBicicletas;

    }

    public boolean JMMV_AgregarBicicleta(JMMV_Bicicleta bicicleta) {

        return bicicletaDAO.JMMV_AgregarBicicleta(bicicleta);

    }

    public boolean JMMV_ActualizarBicicleta(JMMV_Bicicleta bicicleta) {

        return bicicletaDAO.JMMV_ActualizarBicicleta(bicicleta);

    }

    public boolean JMMV_EliminarBicicleta(String nombreBicicleta) {

        return bicicletaDAO.JMMV_EliminarBicicleta(nombreBicicleta);

    }

    public List<String> JMMV_ObtenerNombresBicicletasActivas() {

        List<String> listaNombresBicicletas = bicicletaDAO.JMMV_ObtenerNombresBicicletasActivas();

        return listaNombresBicicletas;

    }

    //para combobox bicicletas
    public List<String> JMMV_ObtenerTiposDeBicicletaActivos() {

        List<String> listTiposBicicletas = bicicletaDAO.JMMV_ObtenerTiposDeBicicletaActivos();

        return listTiposBicicletas;
    }

    //operaciones relacionadas a las reservas    
    public List<JMMV_Reserva> JMMV_ObtenerTodasLasReservasActivas() {

        List<JMMV_Reserva> listaReservas = reservaDAO.JMMV_ObtenerTodasLasReservasActivas();

        return listaReservas;
    }

    public boolean JMMV_AgregarReserva(JMMV_Reserva reserva) {

        return reservaDAO.JMMV_AgregarReserva(reserva);

    }

    public boolean JMMV_ActualizarReserva(JMMV_Reserva reserva) {

        return reservaDAO.JMMV_ActualizarReserva(reserva);

    }

    public boolean JMMV_EliminarReserva(int idReserva) {

        return reservaDAO.JMMV_EliminarReserva(idReserva);

    }

    public List<JMMV_Reserva> JMMV_ObtenerTodasLasReservasActivasDeCliente(int idCliente) {

        List<JMMV_Reserva> listaReservasDeCliente = reservaDAO.JMMV_ObtenerTodasLasReservasActivasDeCliente(idCliente);

        return listaReservasDeCliente;
    }

    public JMMV_Bicicleta JMMV_ObtenerBicicletaPorNombre(String bicicleta) {
        return bicicletaDAO.JMMV_ObtenerBicicletaPorNombre(bicicleta);
    }

    public int JMMV_ObtenerIdBicicletaPorNombre(String bicicleta) {
        return bicicletaDAO.JMMV_ObtenerIdBicicletaPorNombre(bicicleta);
    }

    //creo que no se usará
    public int JMMV_ContarReservasVigentesDeClientes(String nombres) {

        int idCliente = clienteDAO.JMMV_ObtenerIdClientePorNombres(nombres);

        if (idCliente <= 0) {
            System.out.println("No se encontró el cliente por sus nombres");
            return -1;

        }

        return clienteDAO.JMMV_ContarReservasVigentesDeClientesPorIdCliente(idCliente);

    }

    public void InitReservas() {
        List<JMMV_Reserva> reservasActivas = reservaDAO.JMMV_ObtenerTodasLasReservasActivas();
        for (JMMV_Reserva reservaActiva : reservasActivas) {
            System.out.println(LocalDate.now());
            if (reservaActiva.getJMMV_Reserva_fechaFin().isBefore(LocalDate.now())) {
                reservaDAO.JMMV_EliminarReserva(reservaActiva.getJMMV_Reserva_idReserva());
            }
        }
    }
    
    public boolean JMMV_RunYaRegistrado(int run) {

        boolean rutRegistrado = clienteDAO.JMMV_RunYaRegistrado(run);
        return rutRegistrado;

    }
    
     public boolean JMMV_nombreClienteYaRegistrado(String nombres, String apPat, String apMat) {

        boolean nombreClienteRegistrado = clienteDAO.JMMV_nombreClienteYaRegistrado(nombres,apPat,apMat);
        return nombreClienteRegistrado;

    }
     
     public boolean JMMV_nombreUsuarioYaRegistrado(String nombreUsuario) {

        boolean nombreUsuarioRegistrado = clienteDAO.JMMV_nombreUsuarioYaRegistrado(nombreUsuario);
        return nombreUsuarioRegistrado;

    }
     
      public boolean JMMV_correoYaRegistrado(String correo) {

        boolean correoClienteRegistrado = clienteDAO.JMMV_nombreUsuarioYaRegistrado(correo);
        return correoClienteRegistrado;

    }
      
      public boolean JMMV_NombreBicicletaYaRegistrado(String nombreBicicleta) {

        boolean nombreBicicletaRegistrado = clienteDAO.JMMV_nombreUsuarioYaRegistrado(nombreBicicleta);
        return nombreBicicletaRegistrado;

    }
     

}
