/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Metier.AccueilR;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author pauline
 */
public class CreerCompteClient extends javax.swing.JPanel {

    private AccueilR con;

    /**
     * Creates new form CreerCompteClient
     */
    public CreerCompteClient() {
        initComponents();
        con = new AccueilR();
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
        idClient = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox<>();
        valider = new javax.swing.JButton();

        jLabel1.setText("Id du Client : ");

        jLabel2.setText("Type de Compte: ");

        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Courant", "Epargne" }));

        valider.setText("Valider");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1)
                        .addGap(31, 31, 31)
                        .addComponent(idClient, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(valider)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(valider)
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        if (!idClient.getText().equals("")) {
            if (type.getSelectedItem().equals("Courant")) {
                String reponse1 = con.consulterCompteCourantClient(Integer.parseInt(idClient.getText()));
                if (reponse1.equals("KO")) {
                    con.creerCompteCourant(idClient.getText());
                    ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards,"paneGererCompte");
                } else {
                    JOptionPane.showMessageDialog(this, "Ce client possède déjà un compte courant", "Alerte", JOptionPane.ERROR_MESSAGE);
                }
            } else if (type.getSelectedItem().equals("Epargne")) {
                String reponse2 = con.consulterCompteEpargneClient(Integer.parseInt(idClient.getText()));
                if (reponse2.equals("KO")) {
                    con.creerCompteEpargne(idClient.getText());
                    ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards,"paneGererCompte");                                      

                } else {
                    JOptionPane.showMessageDialog(this, "Ce client possède déjà un compte epargne", "Alerte", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Merci de remplir tous les champs", "Alerte", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_validerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField idClient;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> type;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}