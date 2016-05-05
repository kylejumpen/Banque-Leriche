/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Metier.AdminR;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author pauline
 */
public class AdminMenu extends javax.swing.JPanel {

    /**
     * Creates new form AdminMenu
     */
    
    AdminR con;
    
    
    public AdminMenu() {
        con = new AdminR();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ajouter = new javax.swing.JButton();
        supprimer = new javax.swing.JButton();

        ajouter.setText("Ajouter une Banque");
        ajouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterActionPerformed(evt);
            }
        });

        supprimer.setText("Supprimer une Banque");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(ajouter)
                .addGap(18, 18, 18)
                .addComponent(supprimer)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ajouter)
                    .addComponent(supprimer))
                .addContainerGap(146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        JOptionPane jop1 = new JOptionPane();
        int idBanque;
        String banqueSuppression = JOptionPane.showInputDialog(this, "Banque à supprmer", "Suppression");
        if (banqueSuppression != null) {
            idBanque = Integer.parseInt(banqueSuppression);
            con.supprimerBanque(idBanque);
        }
    }//GEN-LAST:event_supprimerActionPerformed

    private void ajouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterActionPerformed
       ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneAjoutBanque");
    }//GEN-LAST:event_ajouterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouter;
    private javax.swing.JButton supprimer;
    // End of variables declaration//GEN-END:variables
}