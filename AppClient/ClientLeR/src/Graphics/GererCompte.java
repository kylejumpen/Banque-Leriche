/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.CardLayout;

/**
 *
 * @author pauline
 */
public class GererCompte extends javax.swing.JPanel {

    /**
     * Creates new form GererCompte
     */
    public GererCompte() {
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

        consulterCompte = new javax.swing.JButton();
        consulterStatistiques = new javax.swing.JButton();
        creerCompte = new javax.swing.JButton();
        ajoutPersonnel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        consulterCompte.setText("Consulter un Compte");
        consulterCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consulterCompteActionPerformed(evt);
            }
        });

        consulterStatistiques.setText("Consulter les Statistiques");
        consulterStatistiques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consulterStatistiquesActionPerformed(evt);
            }
        });

        creerCompte.setText("Créer un Client");
        creerCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creerCompteActionPerformed(evt);
            }
        });

        ajoutPersonnel.setText("Ajouter un nouveau membre");
        ajoutPersonnel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutPersonnelActionPerformed(evt);
            }
        });

        jButton2.setText("Créer un Compte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Echange d'argent");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consulterStatistiques)
                    .addComponent(consulterCompte)
                    .addComponent(ajoutPersonnel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(creerCompte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(275, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(consulterCompte)
                .addGap(26, 26, 26)
                .addComponent(consulterStatistiques)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creerCompte)
                    .addComponent(jButton2))
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addGap(30, 30, 30)
                .addComponent(ajoutPersonnel)
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void consulterCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consulterCompteActionPerformed
        //Montrer la carte consulter compte
        ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneConsulterCompte");
    }//GEN-LAST:event_consulterCompteActionPerformed

    private void creerCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creerCompteActionPerformed
        // TODO add your handling code here:
        //Montrer la carte creer compte
        ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneCreerCompte");
    }//GEN-LAST:event_creerCompteActionPerformed


    private void ajoutPersonnelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutPersonnelActionPerformed
        ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneAjoutPersonnel");

    }//GEN-LAST:event_ajoutPersonnelActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneCreerCompteClient");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String[] parts = GlobalFrame.idBanquePersonnel.getText().split(":");
System.out.println("id banque"+parts[1]);
        GlobalFrame.paneEchangeArgent.initComboBox(Integer.parseInt(parts[1]));
        ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneEchangeArgent");
        //GlobalFrame.cards.remove(paneEchangeArgent);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void consulterStatistiquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consulterStatistiquesActionPerformed
        GlobalFrame.paneStats.initStats();
        ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneStats");
    }//GEN-LAST:event_consulterStatistiquesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton ajoutPersonnel;
    private javax.swing.JButton consulterCompte;
    private javax.swing.JButton consulterStatistiques;
    private javax.swing.JButton creerCompte;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
