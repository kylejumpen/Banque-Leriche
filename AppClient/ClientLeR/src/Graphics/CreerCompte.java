package Graphics;



import Metier.AccueilR;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
//import Metier.MethodesRest;

import Metier.AccueilR;

import javax.swing.JOptionPane;

/**
 *
 * @author kyle
 */
public class CreerCompte extends javax.swing.JPanel {

    //MethodesRest con;
    AccueilR con;

    /**
     * Creates new form CreateAccount
     */
    public CreerCompte() {
        initComponents();
        //con = new MethodesRest();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        validerAjouterCompte = new javax.swing.JButton();
        nom = new javax.swing.JTextField();
        prenom = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        typeCompte = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        motdepasse = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        codepostal = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nom :");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setText("Prénom :");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jLabel3.setText("Email:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel4.setText("Type de compte :");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        validerAjouterCompte.setText("Confirmer");
        validerAjouterCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerAjouterCompteActionPerformed(evt);
            }
        });
        add(validerAjouterCompte, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, -1, -1));
        add(nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 80, -1));
        add(prenom, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 140, -1));
        add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 160, -1));

        typeCompte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Courant", "Epargne" }));
        add(typeCompte, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, -1, -1));

        jLabel5.setText("Mot de passe:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));
        add(motdepasse, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 110, -1));

        jLabel6.setText("Code postal:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));
        add(codepostal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 120, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void validerAjouterCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerAjouterCompteActionPerformed
        Gson gson = new Gson();
        String nomClient = nom.getText();
        String prenomClient = prenom.getText();
        String emailClient = email.getText();
        String mdpClient = motdepasse.getText();
        String codeClient = codepostal.getText();
        String type = typeCompte.getSelectedItem().toString();
        String reponse;
        String idClient;
        if (!prenomClient.equals("") && !nomClient.equals("") && !emailClient.equals("") && !mdpClient.equals("") && !codeClient.equals("")) {
            reponse = con.creerClient(nomClient, prenomClient, emailClient, mdpClient, codeClient);
            JsonElement rootc = new JsonParser().parse(reponse);
            HashMap<String, String> args = gson.fromJson(reponse, new TypeToken<HashMap<String, String>>() {
            }.getType());
            System.out.println(reponse);
            idClient = args.get("clientBanqueId");
            if (type.equals("Courant")) {
                con.creerCompteCourant(idClient);
            } else if (type.equals("Epargne")) {
                con.creerCompteEpargne(idClient);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Merci de remplir tous les champs", "Alerte", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_validerAjouterCompteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codepostal;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField motdepasse;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField prenom;
    private javax.swing.JComboBox<String> typeCompte;
    private javax.swing.JButton validerAjouterCompte;
    // End of variables declaration//GEN-END:variables
}
