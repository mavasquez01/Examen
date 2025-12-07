package controlador;

import DAO.JMMV_UsuarioDAO;
import DAO.JMMV_ClienteDAO;
import DAO.JMMV_BicicletaDAO;
import DAO.JMMV_ReservaDAO;
import java.sql.SQLException;
import java.util.List;
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
    public List<String> JMMV_VerificarLogin(String user, String pass) throws SQLException {

        List<String> usuario = usuarioDAO.JMMV_VerificarLogin(user, pass);
        
        return usuario;
                
    }    
    
    //operaciones relacionadas con los clientes
    public List<JMMV_Cliente> JMMV_ObtenerTodosLosClientesActivos() {

        List<JMMV_Cliente> listaClientes = clienteDAO.JMMV_ObtenerTodosLosClientesActivos();
        
        return listaClientes;
    }
    
    public boolean JMMV_AgregarCliente(JMMV_Cliente cliente){
        
        return clienteDAO.JMMV_AgregarCliente(cliente);
        
    }
    
    public boolean JMMV_ActualizarCliente(JMMV_Cliente cliente) {
        
        return clienteDAO.JMMV_ActualizarCliente(cliente);
        
    }
    
    public boolean JMMV_EliminarCliente(int idCliente){
        
        return clienteDAO.JMMV_EliminarCliente(idCliente);
        
    }
    
    public List<JMMV_Cliente> JMMV_BuscarCliente (String nombres, String apellidoPaterno){
        
        List<JMMV_Cliente> listaClientesEncontrados = clienteDAO.JMMV_BuscarCliente(nombres, apellidoPaterno);
        
        return listaClientesEncontrados;
            
    }
    
    public List<String> JMMV_ObtenerNombresCompletosClientesActivos() {

        List<String> listaNombresCompletos = clienteDAO.JMMV_ObtenerNombresCompletosClientesActivos();
        
        return listaNombresCompletos;
        
    }
    
    //para combobox comunas
    public List<String> JMMV_ObtenerComunasActivas() {
        
        List<String> comunas = clienteDAO.JMMV_ObtenerComunasActivas();
        
        return comunas;
        
    }
    
    
    //operaciones relacionadas a las bicicletas
    public List<JMMV_Bicicleta> JMMV_ObtenerTodasLasBicicletasActivas(){
        
        List<JMMV_Bicicleta> listaBicicletas = bicicletaDAO.JMMV_ObtenerTodasLasBicicletasActivas();
        
        return listaBicicletas;
        
    }
    
    public boolean JMMV_AgregarBicicleta(JMMV_Bicicleta bicicleta){
        
        return bicicletaDAO.JMMV_AgregarBicicleta(bicicleta);        
        
    }
    
    public boolean JMMV_ActualizarBicicleta(JMMV_Bicicleta bicicleta){
        
        return bicicletaDAO.JMMV_ActualizarBicicleta(bicicleta);
        
    }
    
    public boolean JMMV_EliminarBicicleta(String nombreBicicleta){
        
        return bicicletaDAO.JMMV_EliminarBicicleta(nombreBicicleta);
        
    }
    
    public List<String> JMMV_ObtenerNombresBicicletasActivas() {

        List<String> listaNombresBicicletas = bicicletaDAO.JMMV_ObtenerNombresBicicletasActivas();

        return listaNombresBicicletas;

    }
    
    //para combobox bicicletas
    public List<String> JMMV_ObtenerTiposDeBicicletaActivos(){
        
        List<String> listTiposBicicletas = bicicletaDAO.JMMV_ObtenerTiposDeBicicletaActivos();
        
        return listTiposBicicletas;
    }
    
    
    //operaciones relacionadas a las reservas    
    public List<JMMV_Reserva> JMMV_ObtenerTodasLasReservasActivas() {

        List<JMMV_Reserva> listaReservas = reservaDAO.JMMV_ObtenerTodasLasReservasActivas();
        
        return listaReservas;
    }
    
    public boolean JMMV_AgregarReserva(JMMV_Reserva reserva){
        
        return reservaDAO.JMMV_AgregarReserva(reserva);
        
    }
    
    public boolean JMMV_ActualizarReserva(JMMV_Reserva reserva){
        
        return reservaDAO.JMMV_ActualizarReserva(reserva);
        
    }
    
    public boolean JMMV_EliminarReserva(int idReserva){
        
        return reservaDAO.JMMV_EliminarReserva(idReserva);
        
    }

}
