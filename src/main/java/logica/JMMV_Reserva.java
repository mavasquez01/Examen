package logica;

import java.time.LocalDate;



public class JMMV_Reserva {
    
    private int JMMV_idReserva;
    private String JMMV_nombreCliente;
    private String JMMV_nombreBicicleta;
    private LocalDate JMMV_fechaInicio;
    private LocalDate JMMV_fechaFin;

    public JMMV_Reserva(int JMMV_idReserva, String JMMV_nombreCliente, String JMMV_nombreBicicleta, LocalDate JMMV_fechaInicio, LocalDate JMMV_fechaFin) {
        this.JMMV_idReserva = JMMV_idReserva;
        this.JMMV_nombreCliente = JMMV_nombreCliente;
        this.JMMV_nombreBicicleta = JMMV_nombreBicicleta;
        this.JMMV_fechaInicio = JMMV_fechaInicio;
        this.JMMV_fechaFin = JMMV_fechaFin;
    }

    public int getJMMV_idReserva() {
        return JMMV_idReserva;
    }

    public void setJMMV_idReserva(int JMMV_idReserva) {
        this.JMMV_idReserva = JMMV_idReserva;
    }

    public String getJMMV_nombreCliente() {
        return JMMV_nombreCliente;
    }

    public void setJMMV_nombreCliente(String JMMV_nombreCliente) {
        this.JMMV_nombreCliente = JMMV_nombreCliente;
    }

    public String getJMMV_nombreBicicleta() {
        return JMMV_nombreBicicleta;
    }

    public void setJMMV_nombreBicicleta(String JMMV_nombreBicicleta) {
        this.JMMV_nombreBicicleta = JMMV_nombreBicicleta;
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
    
}