package GUI;

public class JMMV_Principal extends javax.swing.JFrame {
 
    public JMMV_Principal() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnUsuarios = new javax.swing.JButton();
        btnBicicletas = new javax.swing.JButton();
        btnReservas = new javax.swing.JButton();
        btnHomePrincipal = new javax.swing.JButton();
        lbBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUsuarios.setText("Gestión de Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnBicicletas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBicicletas.setText("Gestión de Bicicletas");
        btnBicicletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBicicletasActionPerformed(evt);
            }
        });

        btnReservas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnReservas.setText("Gestión de Reservas");
        btnReservas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservasActionPerformed(evt);
            }
        });

        btnHomePrincipal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnHomePrincipal.setText("Inicio");
        btnHomePrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomePrincipalActionPerformed(evt);
            }
        });

        lbBienvenida.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbBienvenida.setText("Bienvenido ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnUsuarios)
                .addGap(18, 18, 18)
                .addComponent(btnBicicletas, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHomePrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lbBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBicicletas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReservas, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHomePrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
        JMMV_GestionUsuarios gestionUsuarios = new JMMV_GestionUsuarios();
        gestionUsuarios.setTitle("Gestion Usuarios");
        gestionUsuarios.setLocationRelativeTo(null);
        gestionUsuarios.setResizable(false);
        gestionUsuarios.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnBicicletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBicicletasActionPerformed
        JMMV_GestionBicicleta gestionBicicletas = new JMMV_GestionBicicleta();
        gestionBicicletas.setTitle("Gestion Bicicletas");
        gestionBicicletas.setLocationRelativeTo(null);
        gestionBicicletas.setResizable(false);
        gestionBicicletas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBicicletasActionPerformed

    private void btnReservasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservasActionPerformed
        JMMV_GestionReserva gestionReservas = new JMMV_GestionReserva();
        gestionReservas.setTitle("Gestion Reservas");
        gestionReservas.setLocationRelativeTo(null);
        gestionReservas.setResizable(false);
        gestionReservas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReservasActionPerformed

    private void btnHomePrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomePrincipalActionPerformed
        JMMV_Principal ventanaPrincipal = new JMMV_Principal();
        ventanaPrincipal.setTitle("Menu");
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setResizable(false);
        ventanaPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnHomePrincipalActionPerformed

    public void JMMV_SaludarUsuario(String nombreUsuario){
        
        lbBienvenida.setText("Bienvenido "+nombreUsuario);
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBicicletas;
    private javax.swing.JButton btnHomePrincipal;
    private javax.swing.JButton btnReservas;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbBienvenida;
    // End of variables declaration//GEN-END:variables
}
