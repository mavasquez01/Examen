/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import controlador.JMMV_Controlador;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import logica.JMMV_Cliente;

public class JMMV_ListadoUsuarios extends javax.swing.JFrame {
    
    JMMV_Controlador controlador = new JMMV_Controlador();
    String nombreCliente = "";
    List<JMMV_Cliente> cliente;
    
    public JMMV_ListadoUsuarios() {
        
        initComponents();
        CargarTabla();
        ListSelectionModel selectionModel = tbListado.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbListado.getSelectedRow();
                    if (selectedRow != -1) {
                        Object nombresCliente = tbListado.getValueAt(selectedRow, 0);
                        System.out.println(nombresCliente.toString());
                        nombreCliente = nombresCliente.toString();
                        cliente = controlador.JMMV_ObtenerClientePorNombre(nombreCliente);
                        JMMV_GestionUsuarios gestionUser = new JMMV_GestionUsuarios(cliente.get(0));
                        gestionUser.setTitle("Editar Cliente");
                        gestionUser.setResizable(false);
                        gestionUser.setLocationRelativeTo(null);
                        gestionUser.setVisible(true);

                    }
                }
            }
        });
        
        this.dispose();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbListado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(btnInicio)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(308, 308, 308)
                    .addComponent(btnInicio)
                    .addContainerGap(325, Short.MAX_VALUE)))
        );

        jPanel2.setLayout(new java.awt.GridLayout(1, 1));

        tbListado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Cliente", "Nombres", "Apellido Paterno", "Apellido Materno", "Run", "Comuna", "Calle", "Número Calle", "Correo", "Teléfono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbListado.setRowHeight(30);
        tbListado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbListadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbListado);

        jPanel2.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void tbListadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbListadoMouseClicked

            this.dispose();

    }//GEN-LAST:event_tbListadoMouseClicked

   private void CargarTabla() {
       List <JMMV_Cliente> clientesActivos = controlador.JMMV_ObtenerTodosLosClientesActivos();
       DefaultTableModel modelo = (DefaultTableModel) tbListado.getModel();
       for (JMMV_Cliente cliente : clientesActivos) {
           modelo.addRow(new Object[]{cliente.getJMMV_Cliente_nombres(), cliente.getJMMV_Cliente_apellidoPaterno(),
           cliente.getJMMV_Cliente_apellidoMaterno(), cliente.getJMMV_Cliente_run(), cliente.getJMMV_Cliente_comuna(),
           cliente.getJMMV_Cliente_calle(), cliente.getJMMV_Cliente_numCalle(), cliente.getJMMV_Cliente_correo(), cliente.getJMMV_Cliente_telefono()});
       }
   }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbListado;
    // End of variables declaration//GEN-END:variables
}
