package logica;



public class JMMV_Bicicleta {

    private int JMMV_Bicicleta_idBicicleta;
    private String JMMV_Bicicleta_nombre;
    private String JMMV_Bicicleta_tipoBicicleta;
    private String JMMV_Bicicleta_descripcion;
    private boolean JMMV_Bicicleta_estaDisponible;    
    private boolean JMMV_Bicicleta_estaActivo;

    public JMMV_Bicicleta(int JMMV_Bicicleta_idBicicleta, String JMMV_Bicicleta_nombre, String JMMV_Bicicleta_tipoBicicleta, String JMMV_Bicicleta_descripcion, boolean JMMV_Bicicleta_estaDisponible, boolean JMMV_Bicicleta_estaActivo) {
        this.JMMV_Bicicleta_idBicicleta = JMMV_Bicicleta_idBicicleta;
        this.JMMV_Bicicleta_nombre = JMMV_Bicicleta_nombre;
        this.JMMV_Bicicleta_tipoBicicleta = JMMV_Bicicleta_tipoBicicleta;
        this.JMMV_Bicicleta_descripcion = JMMV_Bicicleta_descripcion;
        this.JMMV_Bicicleta_estaDisponible = JMMV_Bicicleta_estaDisponible;
        this.JMMV_Bicicleta_estaActivo = JMMV_Bicicleta_estaActivo;
    }

    //constructor sin id y sin estaActivo, para Agregar nuevo
    public JMMV_Bicicleta(String JMMV_Bicicleta_nombre, String JMMV_Bicicleta_tipoBicicleta, String JMMV_Bicicleta_descripcion, boolean JMMV_Bicicleta_estaDisponible, boolean JMMV_Bicicleta_estaActivo) {
        this.JMMV_Bicicleta_nombre = JMMV_Bicicleta_nombre;
        this.JMMV_Bicicleta_tipoBicicleta = JMMV_Bicicleta_tipoBicicleta;
        this.JMMV_Bicicleta_descripcion = JMMV_Bicicleta_descripcion;
        this.JMMV_Bicicleta_estaDisponible = JMMV_Bicicleta_estaDisponible;
    }
    
    //constructor sin estaActivo
    public JMMV_Bicicleta(int JMMV_Bicicleta_idBicicleta, String JMMV_Bicicleta_nombre, String JMMV_Bicicleta_tipoBicicleta, String JMMV_Bicicleta_descripcion, boolean JMMV_Bicicleta_estaDisponible) {
        this.JMMV_Bicicleta_idBicicleta = JMMV_Bicicleta_idBicicleta;
        this.JMMV_Bicicleta_nombre = JMMV_Bicicleta_nombre;
        this.JMMV_Bicicleta_tipoBicicleta = JMMV_Bicicleta_tipoBicicleta;
        this.JMMV_Bicicleta_descripcion = JMMV_Bicicleta_descripcion;
        this.JMMV_Bicicleta_estaDisponible = JMMV_Bicicleta_estaDisponible;
    }

    public int getJMMV_Bicicleta_idBicicleta() {
        return JMMV_Bicicleta_idBicicleta;
    }

    public void setJMMV_Bicicleta_idBicicleta(int JMMV_Bicicleta_idBicicleta) {
        this.JMMV_Bicicleta_idBicicleta = JMMV_Bicicleta_idBicicleta;
    }

    public String getJMMV_Bicicleta_nombre() {
        return JMMV_Bicicleta_nombre;
    }

    public void setJMMV_Bicicleta_nombre(String JMMV_Bicicleta_nombre) {
        this.JMMV_Bicicleta_nombre = JMMV_Bicicleta_nombre;
    }

    public String getJMMV_Bicicleta_tipoBicicleta() {
        return JMMV_Bicicleta_tipoBicicleta;
    }

    public void setJMMV_Bicicleta_tipoBicicleta(String JMMV_Bicicleta_tipoBicicleta) {
        this.JMMV_Bicicleta_tipoBicicleta = JMMV_Bicicleta_tipoBicicleta;
    }

    public String getJMMV_Bicicleta_descripcion() {
        return JMMV_Bicicleta_descripcion;
    }

    public void setJMMV_Bicicleta_descripcion(String JMMV_Bicicleta_descripcion) {
        this.JMMV_Bicicleta_descripcion = JMMV_Bicicleta_descripcion;
    }
    
    public boolean isJMMV_Bicicleta_estaDisponible() {
        return JMMV_Bicicleta_estaDisponible;
    }

    public void setJMMV_Bicicleta_estaDisponible(boolean JMMV_Bicicleta_estaDisponible) {
        this.JMMV_Bicicleta_estaDisponible = JMMV_Bicicleta_estaDisponible;
    }

    public boolean isJMMV_Bicicleta_estaActivo() {
        return JMMV_Bicicleta_estaActivo;
    }

    public void setJMMV_Bicicleta_estaActivo(boolean JMMV_Bicicleta_estaActivo) {
        this.JMMV_Bicicleta_estaActivo = JMMV_Bicicleta_estaActivo;
    }

}
