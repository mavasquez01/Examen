package logica;

import GUI.JMMV_Login;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.UIManager;



public class JMMV_Main {
    
    
    public static void main(String[] args) {

         try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf()); //el tema
        } catch (Exception ex) {
            System.err.println("Error al configurar FlatLaf:"+ex.getMessage());
        }

        //abrir vista de login
        JMMV_Login login = new JMMV_Login();
        login.setTitle("Módulo Gestión - Iniciar sesión");
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setVisible(true);

    }
}
