/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

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

        consulterCompte.setText("Consulter un Compte");
        add(consulterCompte);

        consulterStatistiques.setText("Consulter les Statistiques");
        add(consulterStatistiques);

        creerCompte.setText("Créer un Compte");
        add(creerCompte);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consulterCompte;
    private javax.swing.JButton consulterStatistiques;
    private javax.swing.JButton creerCompte;
    // End of variables declaration//GEN-END:variables
}
