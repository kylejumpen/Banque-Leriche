/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import javax.swing.JOptionPane;
import Metier.ConsulterCompteR;

/**
 *
 * @author pauline
 */
public class ConsulterCompte extends javax.swing.JPanel {

    private int idCompte;
    private ConsulterCompteR con;

    /**
     * Creates new form ConsulterCompteR
     */
    public ConsulterCompte() {
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

        jLabel1 = new javax.swing.JLabel();
        numeroCompte = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        proprietaire = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        montant = new javax.swing.JLabel();
        virement = new javax.swing.JButton();
        crediter = new javax.swing.JButton();
        pret = new javax.swing.JButton();
        supprimer = new javax.swing.JButton();
        rechercher = new javax.swing.JButton();
        debiter = new javax.swing.JButton();

        jLabel1.setText("Numéro de Compte :");

        jLabel2.setText("Propiétaire :");

        jLabel3.setText("Montant:");

        virement.setText("Faire un virement");

        crediter.setText("Créditer le compte");
        crediter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crediterActionPerformed(evt);
            }
        });

        pret.setText("Faire un prêt");

        supprimer.setText("Supprimer le Compte");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        rechercher.setText("Rechercher");
        rechercher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rechercherActionPerformed(evt);
            }
        });

        debiter.setText("Débiter le compte");
        debiter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debiterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28)
                        .addComponent(numeroCompte, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rechercher))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(virement)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(proprietaire, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(pret)
                                .addGap(24, 24, 24)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crediter)
                    .addComponent(debiter)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(supprimer)
                .addGap(118, 118, 118))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroCompte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rechercher))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(proprietaire, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addComponent(jLabel3))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(montant, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(crediter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debiter)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pret)
                    .addComponent(virement))
                .addGap(52, 52, 52)
                .addComponent(supprimer)
                .addGap(49, 49, 49))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        if (numeroCompte.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Aucun compte à supprimer", "Alerte", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce compte?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                con.supprimerCompte(idCompte);
                montant.setText("");
                proprietaire.setText("");
            }
        }
    }//GEN-LAST:event_supprimerActionPerformed

    private void rechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercherActionPerformed

        String reponse = con.consulterCompteGet(Integer.parseInt(numeroCompte.getText()));
        System.out.println(reponse);
        if (reponse.equals("KO")) {
            JOptionPane.showMessageDialog(this, "Ce compte n'existe pas", "Alerte", JOptionPane.ERROR_MESSAGE);
            numeroCompte.setText("");
        } else {
            idCompte = Integer.parseInt(numeroCompte.getText());
            String[] parts = reponse.split("-");
            montant.setText(parts[3] + "€");
            proprietaire.setText(parts[1] + " " + parts[2]);
        }


    }//GEN-LAST:event_rechercherActionPerformed

    private void crediterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crediterActionPerformed
        if (numeroCompte.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Aucun compte à créditer", "Alerte", JOptionPane.ERROR_MESSAGE);
        } else {

            JOptionPane jop1 = new JOptionPane();
            int somme;
            String sommeTransaction = JOptionPane.showInputDialog(this, "somme à créditer", "Crédit");
            if (sommeTransaction != null) {
                somme = Integer.parseInt(sommeTransaction);
                con.crediterCompte(somme, idCompte);
            }
        }
    }//GEN-LAST:event_crediterActionPerformed

    private void debiterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debiterActionPerformed
        if (numeroCompte.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Aucun compte à débiter", "Alerte", JOptionPane.ERROR_MESSAGE);
        } else {

            JOptionPane jop1 = new JOptionPane();
            int somme;
            String sommeTransaction = JOptionPane.showInputDialog(this, "somme à débiter", "Débit");
            if (sommeTransaction != null) {
                somme = Integer.parseInt(sommeTransaction);
                con.debiterCompte(somme, idCompte);

            }
        }
    }//GEN-LAST:event_debiterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton crediter;
    private javax.swing.JButton debiter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel montant;
    private javax.swing.JTextField numeroCompte;
    private javax.swing.JButton pret;
    private javax.swing.JLabel proprietaire;
    private javax.swing.JButton rechercher;
    private javax.swing.JButton supprimer;
    private javax.swing.JButton virement;
    // End of variables declaration//GEN-END:variables
}
