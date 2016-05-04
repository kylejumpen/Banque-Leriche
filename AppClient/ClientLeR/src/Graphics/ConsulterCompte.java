package Graphics;

import javax.swing.JOptionPane;
import Metier.ConsulterCompteR;
import Metier.MethodesRest;
//import Metier.MethodesRest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;

/**
 *
 * @author pauline
 */
public class ConsulterCompte extends javax.swing.JPanel {

    private int idCompte;
    private MethodesRest con;
    //private  ConsulterCompteR con;

    /**
     * Creates new form ConsulterCompteR
     */
    public ConsulterCompte() {
        initComponents();
        con = new MethodesRest();
        //con = new ConsulterCompteR();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        numeroClient = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        compteCourant = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        compteEpargne = new javax.swing.JLabel();
        crediter = new javax.swing.JButton();
        supprimer = new javax.swing.JButton();
        rechercher = new javax.swing.JButton();
        debiter = new javax.swing.JButton();
        courant = new javax.swing.JRadioButton();
        epargne = new javax.swing.JRadioButton();

        jLabel1.setText("Numéro de Client :");

        jLabel2.setText("Compte courant :");

        jLabel3.setText("Compte epargne :");

        crediter.setText("Créditer le compte");
        crediter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crediterActionPerformed(evt);
            }
        });

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

        buttonGroup1.add(courant);
        courant.setText("Courant");

        buttonGroup1.add(epargne);
        epargne.setText("Epargne");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(61, 61, 61)
                        .addComponent(compteCourant, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(28, 28, 28)
                                .addComponent(numeroClient, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rechercher))
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(supprimer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(crediter))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(courant)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(epargne)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(compteEpargne, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(debiter))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(numeroClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rechercher))
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(compteCourant, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(epargne)
                            .addComponent(courant)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(compteEpargne, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(crediter)
                    .addComponent(debiter))
                .addGap(18, 18, 18)
                .addComponent(supprimer)
                .addContainerGap(131, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        if (numeroClient.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Aucun compte à supprimer", "Alerte", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(this, "Voulez-vous vraiment supprimer ce compte?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.OK_OPTION) {
                con.supprimerCompte(idCompte);
                compteEpargne.setText("");
                compteCourant.setText("");
            }
        }
    }//GEN-LAST:event_supprimerActionPerformed

    private void rechercherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rechercherActionPerformed
        courant.setEnabled(true);
        epargne.setEnabled(true);
        Gson gson = new Gson();
        try {
            int idClient = Integer.parseInt(numeroClient.getText());

            String reponse = con.consulterClientGet(idClient);
            if (reponse.equals("KO")) {
                JOptionPane.showMessageDialog(this, "Ce client n'existe pas", "Alerte", JOptionPane.ERROR_MESSAGE);
                numeroClient.setText("");
            } else {

                String ccourant = con.consulterCompteCourantClient(idClient);
                if (ccourant.equals("KO")) {
                    compteCourant.setText("Pas de compte courant");
                    courant.setEnabled(false);
                } else {
                    HashMap<String, String> args = gson.fromJson(ccourant, new TypeToken<HashMap<String, String>>() {
                    }.getType());
                    compteCourant.setText(args.get("montant"));
                }
                String cepargne = con.consulterCompteEpargneClient(idClient);
                if (ccourant.equals("KO")) {
                    compteEpargne.setText("Pas de compte epargne");
                    epargne.setEnabled(false);
                } else {
                    HashMap<String, String> args = gson.fromJson(cepargne, new TypeToken<HashMap<String, String>>() {
                    }.getType());
                    compteEpargne.setText(args.get("montant"));
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Entrée invalide", "Alerte", JOptionPane.ERROR_MESSAGE);
            numeroClient.setText("");
        }
    }//GEN-LAST:event_rechercherActionPerformed

    private void crediterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crediterActionPerformed
        if (!courant.isSelected() && !epargne.isSelected()) {
            JOptionPane.showMessageDialog(this, "Merci de séléctionner un compte", "Alerte", JOptionPane.ERROR_MESSAGE);
        } else if (numeroClient.getText().equals("")) {
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
        if (numeroClient.getText().equals("")) {
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel compteCourant;
    private javax.swing.JLabel compteEpargne;
    private javax.swing.JRadioButton courant;
    private javax.swing.JButton crediter;
    private javax.swing.JButton debiter;
    private javax.swing.JRadioButton epargne;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField numeroClient;
    private javax.swing.JButton rechercher;
    private javax.swing.JButton supprimer;
    // End of variables declaration//GEN-END:variables
}
