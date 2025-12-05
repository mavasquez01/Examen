package logica;

import java.time.LocalDate;



public class JMMV_Reserva {
    
    private int JMMV_idReserva;
    private int JMMV_idCliente;   
    private String JMMV_nomCliente;
    private int JMMV_idBicicleta;
    private String JMMV_nomBicicleta;
    private LocalDate JMMV_fechaInicio;
    private LocalDate JMMV_fechaFin;
    private boolean JMMV_estaActivo;

    public JMMV_Reserva(int JMMV_idReserva, int JMMV_idCliente, String JMMV_nomCliente, int JMMV_idBicicleta, String JMMV_nomBicicleta, LocalDate JMMV_fechaInicio, LocalDate JMMV_fechaFin, boolean JMMV_estaActivo) {
        this.JMMV_idReserva = JMMV_idReserva;
        this.JMMV_idCliente = JMMV_idCliente;
        this.JMMV_nomCliente = JMMV_nomCliente;
        this.JMMV_idBicicleta = JMMV_idBicicleta;
        this.JMMV_nomBicicleta = JMMV_nomBicicleta;
        this.JMMV_fechaInicio = JMMV_fechaInicio;
        this.JMMV_fechaFin = JMMV_fechaFin;
        this.JMMV_estaActivo = JMMV_estaActivo;
    }

    //constructor sin estaActivo
    public JMMV_Reserva(int JMMV_idReserva, int JMMV_idCliente, String JMMV_nomCliente, int JMMV_idBicicleta, String JMMV_nomBicicleta, LocalDate JMMV_fechaInicio, LocalDate JMMV_fechaFin) {
        this.JMMV_idReserva = JMMV_idReserva;
        this.JMMV_idCliente = JMMV_idCliente;
        this.JMMV_nomCliente = JMMV_nomCliente;
        this.JMMV_idBicicleta = JMMV_idBicicleta;
        this.JMMV_nomBicicleta = JMMV_nomBicicleta;
        this.JMMV_fechaInicio = JMMV_fechaInicio;
        this.JMMV_fechaFin = JMMV_fechaFin;
    }

    public int getJMMV_idReserva() {
        return JMMV_idReserva;
    }

    public void setJMMV_idReserva(int JMMV_idReserva) {
        this.JMMV_idReserva = JMMV_idReserva;
    }

    public int getJMMV_idCliente() {
        return JMMV_idCliente;
    }

    public void setJMMV_idCliente(int JMMV_idCliente) {
        this.JMMV_idCliente = JMMV_idCliente;
    }

    public String getJMMV_nomCliente() {
        return JMMV_nomCliente;
    }

    public void setJMMV_nomCliente(String JMMV_nomCliente) {
        this.JMMV_nomCliente = JMMV_nomCliente;
    }
    
    public int getJMMV_idBicicleta() {
        return JMMV_idBicicleta;
    }

    public void setJMMV_idBicicleta(int JMMV_idBicicleta) {
        this.JMMV_idBicicleta = JMMV_idBicicleta;
    }

    public String getJMMV_nomBicicleta() {
        return JMMV_nomBicicleta;
    }

    public void setJMMV_nomBicicleta(String JMMV_nomBicicleta) {
        this.JMMV_nomBicicleta = JMMV_nomBicicleta;
    }
    
    public LocalDate getJMMV_fechaInicio() {
        return JMMV_fechaInicio;
    }

    public void setJMMV_fechaInicio(LocalDate JMMV_fechaInicio) {
        this.JMMV_fechaInicio = JMMV_fechaInicio;
    }

    public LocalDate getJMMV_fechaFin() {
        return JMMV_fechaFin;
    }

    public void setJMMV_fechaFin(LocalDate JMMV_fechaFin) {
        this.JMMV_fechaFin = JMMV_fechaFin;
    }

    public boolean isJMMV_estaActivo() {
        return JMMV_estaActivo;
    }

    public void setJMMV_estaActivo(boolean JMMV_estaActivo) {
        this.JMMV_estaActivo = JMMV_estaActivo;
    }
    
}