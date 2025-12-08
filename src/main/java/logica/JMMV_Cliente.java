package logica;



public class JMMV_Cliente {

    private int JMMV_Cliente_idUsuario;
    private int JMMV_Cliente_idCliente;
    private String JMMV_Cliente_nomUsuario;
    private String JMMV_Cliente_contrasena;
    private String JMMV_Cliente_correo;
    private int JMMV_Cliente_rol = 2;
    private int JMMV_Cliente_run;
    private String JMMV_Cliente_nombres;
    private String JMMV_Cliente_apellidoPaterno;
    private String JMMV_Cliente_apellidoMaterno;
    private String JMMV_Cliente_comuna;
    private String JMMV_Cliente_calle;
    private int JMMV_Cliente_numCalle;
    private int JMMV_Cliente_telefono;
    private boolean JMMV_Cliente_estaActivo = true;
    private String JMMV_Cliente_nombreCompleto;

    public JMMV_Cliente(int JMMV_Cliente_idUsuario,int JMMV_Cliente_idCliente, String JMMV_Cliente_nomUsuario, String JMMV_Cliente_contrasena, String JMMV_Cliente_correo, int JMMV_Cliente_rol, int JMMV_Cliente_run, String JMMV_Cliente_nombres, String JMMV_Cliente_apellidoPaterno, String JMMV_Cliente_apellidoMaterno, String JMMV_Cliente_comuna, String JMMV_Cliente_calle, int JMMV_Cliente_numCalle, int JMMV_Cliente_telefono, boolean JMMV_Cliente_estaActivo) {
        this.JMMV_Cliente_idUsuario = JMMV_Cliente_idUsuario;
        this.JMMV_Cliente_idCliente = JMMV_Cliente_idCliente;
        this.JMMV_Cliente_nomUsuario = JMMV_Cliente_nomUsuario;
        this.JMMV_Cliente_contrasena = JMMV_Cliente_contrasena;
        this.JMMV_Cliente_correo = JMMV_Cliente_correo;
        this.JMMV_Cliente_rol = JMMV_Cliente_rol;
        this.JMMV_Cliente_run = JMMV_Cliente_run;
        this.JMMV_Cliente_nombres = JMMV_Cliente_nombres;
        this.JMMV_Cliente_apellidoPaterno = JMMV_Cliente_apellidoPaterno;
        this.JMMV_Cliente_apellidoMaterno = JMMV_Cliente_apellidoMaterno;
        this.JMMV_Cliente_comuna = JMMV_Cliente_comuna;
        this.JMMV_Cliente_calle = JMMV_Cliente_calle;
        this.JMMV_Cliente_numCalle = JMMV_Cliente_numCalle;
        this.JMMV_Cliente_telefono = JMMV_Cliente_telefono;
        this.JMMV_Cliente_estaActivo = JMMV_Cliente_estaActivo;
    }

    //constructor sin: rol, estaActivo: para OBTENER y ACTUALIZAR
    public JMMV_Cliente(int JMMV_Cliente_idCliente,int JMMV_Cliente_idUsuario, String JMMV_Cliente_nomUsuario, String JMMV_Cliente_contrasena, String JMMV_Cliente_correo, int JMMV_Cliente_run, String JMMV_Cliente_nombres, String JMMV_Cliente_apellidoPaterno, String JMMV_Cliente_apellidoMaterno, String JMMV_Cliente_comuna, String JMMV_Cliente_calle, int JMMV_Cliente_numCalle, int JMMV_Cliente_telefono) {
        this.JMMV_Cliente_idCliente = JMMV_Cliente_idCliente;
        this.JMMV_Cliente_idCliente = JMMV_Cliente_idUsuario;
        this.JMMV_Cliente_nomUsuario = JMMV_Cliente_nomUsuario;
        this.JMMV_Cliente_contrasena = JMMV_Cliente_contrasena;
        this.JMMV_Cliente_correo = JMMV_Cliente_correo;
        this.JMMV_Cliente_run = JMMV_Cliente_run;
        this.JMMV_Cliente_nombres = JMMV_Cliente_nombres;
        this.JMMV_Cliente_apellidoPaterno = JMMV_Cliente_apellidoPaterno;
        this.JMMV_Cliente_apellidoMaterno = JMMV_Cliente_apellidoMaterno;
        this.JMMV_Cliente_comuna = JMMV_Cliente_comuna;
        this.JMMV_Cliente_calle = JMMV_Cliente_calle;
        this.JMMV_Cliente_numCalle = JMMV_Cliente_numCalle;
        this.JMMV_Cliente_telefono = JMMV_Cliente_telefono;
    }

    //NO SE ESTÁ USANDO constructor para búsqueda de cliente
    public JMMV_Cliente(int JMMV_Cliente_idCliente, int JMMV_Cliente_run, String JMMV_Cliente_nombreCompleto) {
        this.JMMV_Cliente_idCliente = JMMV_Cliente_idCliente;
        this.JMMV_Cliente_run = JMMV_Cliente_run;
        this.JMMV_Cliente_nombreCompleto = JMMV_Cliente_nombreCompleto;
    }
    
    //contructor sin idUsuario, idCliente y estaActivo. Para AGREGAR nuevo.
    public JMMV_Cliente(String JMMV_Cliente_nomUsuario, String JMMV_Cliente_contrasena, String JMMV_Cliente_correo, int JMMV_Cliente_run, String JMMV_Cliente_nombres, String JMMV_Cliente_apellidoPaterno, String JMMV_Cliente_apellidoMaterno, String JMMV_Cliente_comuna, String JMMV_Cliente_calle, int JMMV_Cliente_numCalle, int JMMV_Cliente_telefono) {
        this.JMMV_Cliente_nomUsuario = JMMV_Cliente_nomUsuario;
        this.JMMV_Cliente_contrasena = JMMV_Cliente_contrasena;
        this.JMMV_Cliente_correo = JMMV_Cliente_correo;
        this.JMMV_Cliente_run = JMMV_Cliente_run;
        this.JMMV_Cliente_nombres = JMMV_Cliente_nombres;
        this.JMMV_Cliente_apellidoPaterno = JMMV_Cliente_apellidoPaterno;
        this.JMMV_Cliente_apellidoMaterno = JMMV_Cliente_apellidoMaterno;
        this.JMMV_Cliente_comuna = JMMV_Cliente_comuna;
        this.JMMV_Cliente_calle = JMMV_Cliente_calle;
        this.JMMV_Cliente_numCalle = JMMV_Cliente_numCalle;
        this.JMMV_Cliente_telefono = JMMV_Cliente_telefono;
    }
    

    public int getJMMV_Cliente_idUsuario() {
        return JMMV_Cliente_idUsuario;
    }

    public void setJMMV_Cliente_idUsuario(int JMMV_Cliente_idUsuario) {
        this.JMMV_Cliente_idUsuario = JMMV_Cliente_idUsuario;
    }

    public int getJMMV_Cliente_idCliente() {
        return JMMV_Cliente_idCliente;
    }

    public void setJMMV_Cliente_idCliente(int JMMV_Cliente_idCliente) {
        this.JMMV_Cliente_idCliente = JMMV_Cliente_idCliente;
    }

    public String getJMMV_Cliente_nomUsuario() {
        return JMMV_Cliente_nomUsuario;
    }

    public void setJMMV_Cliente_nomUsuario(String JMMV_Cliente_nomUsuario) {
        this.JMMV_Cliente_nomUsuario = JMMV_Cliente_nomUsuario;
    }

    public String getJMMV_Cliente_contrasena() {
        return JMMV_Cliente_contrasena;
    }

    public void setJMMV_Cliente_contrasena(String JMMV_Cliente_contrasena) {
        this.JMMV_Cliente_contrasena = JMMV_Cliente_contrasena;
    }

    public String getJMMV_Cliente_correo() {
        return JMMV_Cliente_correo;
    }

    public void setJMMV_Cliente_correo(String JMMV_Cliente_correo) {
        this.JMMV_Cliente_correo = JMMV_Cliente_correo;
    }

    public int getJMMV_Cliente_rol() {
        return JMMV_Cliente_rol;
    }

    public void setJMMV_Cliente_rol(int JMMV_Cliente_rol) {
        this.JMMV_Cliente_rol = JMMV_Cliente_rol;
    }

    public int getJMMV_Cliente_run() {
        return JMMV_Cliente_run;
    }

    public void setJMMV_Cliente_run(int JMMV_Cliente_run) {
        this.JMMV_Cliente_run = JMMV_Cliente_run;
    }

    public String getJMMV_Cliente_nombres() {
        return JMMV_Cliente_nombres;
    }

    public void setJMMV_Cliente_nombres(String JMMV_Cliente_nombres) {
        this.JMMV_Cliente_nombres = JMMV_Cliente_nombres;
    }

    public String getJMMV_Cliente_apellidoPaterno() {
        return JMMV_Cliente_apellidoPaterno;
    }

    public void setJMMV_Cliente_apellidoPaterno(String JMMV_Cliente_apellidoPaterno) {
        this.JMMV_Cliente_apellidoPaterno = JMMV_Cliente_apellidoPaterno;
    }

    public String getJMMV_Cliente_apellidoMaterno() {
        return JMMV_Cliente_apellidoMaterno;
    }

    public void setJMMV_Cliente_apellidoMaterno(String JMMV_Cliente_apellidoMaterno) {
        this.JMMV_Cliente_apellidoMaterno = JMMV_Cliente_apellidoMaterno;
    }

    public String getJMMV_Cliente_comuna() {
        return JMMV_Cliente_comuna;
    }

    public void setJMMV_Cliente_comuna(String JMMV_Cliente_comuna) {
        this.JMMV_Cliente_comuna = JMMV_Cliente_comuna;
    }

    public String getJMMV_Cliente_calle() {
        return JMMV_Cliente_calle;
    }

    public void setJMMV_Cliente_calle(String JMMV_Cliente_calle) {
        this.JMMV_Cliente_calle = JMMV_Cliente_calle;
    }

    public int getJMMV_Cliente_numCalle() {
        return JMMV_Cliente_numCalle;
    }

    public void setJMMV_Cliente_numCalle(int JMMV_Cliente_numCalle) {
        this.JMMV_Cliente_numCalle = JMMV_Cliente_numCalle;
    }

    public int getJMMV_Cliente_telefono() {
        return JMMV_Cliente_telefono;
    }

    public void setJMMV_Cliente_telefono(int JMMV_Cliente_telefono) {
        this.JMMV_Cliente_telefono = JMMV_Cliente_telefono;
    }

    public boolean isJMMV_Cliente_estaActivo() {
        return JMMV_Cliente_estaActivo;
    }

    public void setJMMV_Cliente_estaActivo(boolean JMMV_Cliente_estaActivo) {
        this.JMMV_Cliente_estaActivo = JMMV_Cliente_estaActivo;
    }

    public String getJMMV_Cliente_nombreCompleto() {
        return JMMV_Cliente_nombreCompleto;
    }

    public void setJMMV_Cliente_nombreCompleto(String JMMV_Cliente_nombreCompleto) {
        this.JMMV_Cliente_nombreCompleto = JMMV_Cliente_nombreCompleto;
    }

    
}
