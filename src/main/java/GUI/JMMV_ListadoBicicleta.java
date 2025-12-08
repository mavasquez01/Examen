/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import controlador.JMMV_Controlador;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import logica.JMMV_Bicicleta;

public class JMMV_ListadoBicicleta extends javax.swing.JFrame {
    
    JMMV_Controlador controlador = new JMMV_Controlador();

    public JMMV_ListadoBicicleta() {
        initComponents();
        CargarTabla();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInicio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnInicio.setIcon(new javax.swing.ImageIcon("C:\\Users\\Agustin\\OneDrive\\Escritorio\\examen_max\\src\\static\\home_50dp_1F1F1F_FILL0_wght400_GRAD0_opsz48.png")); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        tbListado.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tbListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo", "Disponibilidad"
            }
        ));
        jScrollPane1.setViewportView(tbListado);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInicio)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(btnInicio)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        JMMV_Principal menu = new JMMV_Principal();
        menu.setTitle("Menu");
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

   private void CargarTabla() {
       List <JMMV_Bicicleta> bicicletasActivas = controlador.JMMV_ObtenerTodasLasBicicletasActivas();
       DefaultTableModel modelo = (DefaultTableModel) tbListado.getModel();
       for (JMMV_Bicicleta bicicleta : bicicletasActivas) {
           modelo.addRow(new Object[]{bicicleta.getJMMV_Bicicleta_nombre(), bicicleta.getJMMV_Bicicleta_tipoBicicleta(), bicicleta.isJMMV_Bicicleta_estaDisponible()});
       }
   }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbListado;
    // End of variables declaration//GEN-END:variables
}
