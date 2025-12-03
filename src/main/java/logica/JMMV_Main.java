package logica;

import GUI.JMMV_Login;



public class JMMV_Main {

    public static void main(String[] args) {

        //abrir vista de login
        JMMV_Login login = new JMMV_Login();
        login.setTitle("Módulo Gestión - Iniciar sesión");
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setVisible(true);

    }
}
