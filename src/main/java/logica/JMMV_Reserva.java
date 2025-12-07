package logica;

import java.time.LocalDate;



public class JMMV_Reserva {
    
    private int JMMV_Reserva_idReserva;
    private int JMMV_Reserva_idCliente;   
    private String JMMV_Reserva_nomCliente;
    private int JMMV_Reserva_idBicicleta;
    private String JMMV_Reserva_nomBicicleta;
    private LocalDate JMMV_Reserva_fechaInicio;
    private LocalDate JMMV_Reserva_fechaFin;
    private boolean JMMV_Reserva_estaActivo;

    public JMMV_Reserva(int JMMV_idReserva, int JMMV_idCliente, String JMMV_nomCliente, int JMMV_idBicicleta, String JMMV_nomBicicleta, LocalDate JMMV_fechaInicio, LocalDate JMMV_fechaFin, boolean JMMV_estaActivo) {
        this.JMMV_Reserva_idReserva = JMMV_idReserva;
        this.JMMV_Reserva_idCliente = JMMV_idCliente;
        this.JMMV_Reserva_nomCliente = JMMV_nomCliente;
        this.JMMV_Reserva_idBicicleta = JMMV_idBicicleta;
        this.JMMV_Reserva_nomBicicleta = JMMV_nomBicicleta;
        this.JMMV_Reserva_fechaInicio = JMMV_fechaInicio;
        this.JMMV_Reserva_fechaFin = JMMV_fechaFin;
        this.JMMV_Reserva_estaActivo = JMMV_estaActivo;
    }

    //constructor sin idReserva, estaActivo para Agregar nuevo.
    public JMMV_Reserva(int JMMV_idCliente, String JMMV_nomCliente, int JMMV_idBicicleta, String JMMV_nomBicicleta, LocalDate JMMV_fechaInicio, LocalDate JMMV_fechaFin) {
        this.JMMV_Reserva_idCliente = JMMV_idCliente;
        this.JMMV_Reserva_nomCliente = JMMV_nomCliente;
        this.JMMV_Reserva_idBicicleta = JMMV_idBicicleta;
        this.JMMV_Reserva_nomBicicleta = JMMV_nomBicicleta;
        this.JMMV_Reserva_fechaInicio = JMMV_fechaInicio;
        this.JMMV_Reserva_fechaFin = JMMV_fechaFin;
    }  
    
    //constructor sin estaActivo
    public JMMV_Reserva(int JMMV_idReserva, int JMMV_idCliente, String JMMV_nomCliente, int JMMV_idBicicleta, String JMMV_nomBicicleta, LocalDate JMMV_fechaInicio, LocalDate JMMV_fechaFin) {
        this.JMMV_Reserva_idReserva = JMMV_idReserva;
        this.JMMV_Reserva_idCliente = JMMV_idCliente;
        this.JMMV_Reserva_nomCliente = JMMV_nomCliente;
        this.JMMV_Reserva_idBicicleta = JMMV_idBicicleta;
        this.JMMV_Reserva_nomBicicleta = JMMV_nomBicicleta;
        this.JMMV_Reserva_fechaInicio = JMMV_fechaInicio;
        this.JMMV_Reserva_fechaFin = JMMV_fechaFin;
    }

    public int getJMMV_Reserva_idReserva() {
        return JMMV_Reserva_idReserva;
    }

    public void setJMMV_Reserva_idReserva(int JMMV_Reserva_idReserva) {
        this.JMMV_Reserva_idReserva = JMMV_Reserva_idReserva;
    }

    public int getJMMV_Reserva_idCliente() {
        return JMMV_Reserva_idCliente;
    }

    public void setJMMV_Reserva_idCliente(int JMMV_Reserva_idCliente) {
        this.JMMV_Reserva_idCliente = JMMV_Reserva_idCliente;
    }

    public String getJMMV_Reserva_nomCliente() {
        return JMMV_Reserva_nomCliente;
    }

    public void setJMMV_Reserva_nomCliente(String JMMV_Reserva_nomCliente) {
        this.JMMV_Reserva_nomCliente = JMMV_Reserva_nomCliente;
    }
    
    public int getJMMV_Reserva_idBicicleta() {
        return JMMV_Reserva_idBicicleta;
    }

    public void setJMMV_Reserva_idBicicleta(int JMMV_Reserva_idBicicleta) {
        this.JMMV_Reserva_idBicicleta = JMMV_Reserva_idBicicleta;
    }

    public String getJMMV_Reserva_nomBicicleta() {
        return JMMV_Reserva_nomBicicleta;
    }

    public void setJMMV_Reserva_nomBicicleta(String JMMV_Reserva_nomBicicleta) {
        this.JMMV_Reserva_nomBicicleta = JMMV_Reserva_nomBicicleta;
    }
    
    public LocalDate getJMMV_Reserva_fechaInicio() {
        return JMMV_Reserva_fechaInicio;
    }

    public void setJMMV_Reserva_fechaInicio(LocalDate JMMV_Reserva_fechaInicio) {
        this.JMMV_Reserva_fechaInicio = JMMV_Reserva_fechaInicio;
    }

    public LocalDate getJMMV_Reserva_fechaFin() {
        return JMMV_Reserva_fechaFin;
    }

    public void setJMMV_Reserva_fechaFin(LocalDate JMMV_Reserva_fechaFin) {
        this.JMMV_Reserva_fechaFin = JMMV_Reserva_fechaFin;
    }

    public boolean isJMMV_Reserva_estaActivo() {
        return JMMV_Reserva_estaActivo;
    }

    public void setJMMV_Reserva_estaActivo(boolean JMMV_Reserva_estaActivo) {
        this.JMMV_Reserva_estaActivo = JMMV_Reserva_estaActivo;
    }
    
}