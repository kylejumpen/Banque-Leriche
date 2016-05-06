package Graphics;

import Metier.EchangeR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import Metier.MethodesRest;

/**
 *
 * @author pauline
 */
public class EchangeArgent extends javax.swing.JPanel {

    //private MethodesRest con;
    private EchangeR con;
    private JComboBox cbDebit;
    private JComboBox cbCredit;
    private JTextField montant;

    /**
     * Creates new form EchangeArgent
     */
    public EchangeArgent() {
        montant = new JTextField("montant");
        con = new EchangeR();
        //initComponents();
        //initComboBox();

        /*montant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                montant.setText("");
            }
        });*/
    }

    public void initComboBox() {

        ArrayList<String> comptes = new ArrayList<String>();
        Gson gson = new Gson();
        JPanel centre = new JPanel();
        centre.setLayout(new BorderLayout());
        String rep = con.getComptesCourant(1);
        HashMap<String, String> hash1 = gson.fromJson(rep, new TypeToken<HashMap<String, String>>() {
        }.getType());
        HashMap<String, String> hash2;
        System.out.println(hash1.toString());
        //On itère sur les différents comptes pour obtenir le json de chaque compte
        Iterator it = hash1.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) it.next();
            String jsonDeMonCompte = (String) pair.getValue();
            hash2 = gson.fromJson(jsonDeMonCompte, new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (hash2.get("bloque").equals("false")) {
                comptes.add("<html>courant, Id :" + hash2.get("compteCourantId") + ",<br>Montant:" + hash2.get("montant") + "</html>");
            }
        }
        it.remove(); // avoids a ConcurrentModificationException

        String rep2 = con.getComptesEpargne(1);
        HashMap<String, String> hash12 = gson.fromJson(rep2, new TypeToken<HashMap<String, String>>() {
        }.getType());
        HashMap<String, String> hash22;
        //On itère sur les différents comptes epargne pour obtenir le json de chaque compte
        Iterator it2 = hash12.entrySet().iterator();
        while (it2.hasNext()) {
            HashMap.Entry pair2 = (HashMap.Entry) it2.next();
            String jsonDeMonCompte2 = (String) pair2.getValue();
            hash22 = gson.fromJson(jsonDeMonCompte2, new TypeToken<HashMap<String, String>>() {
            }.getType());
            if (hash22.get("bloque").equals("false")) {
                System.out.println(hash22.get("compteEpargneId"));
                comptes.add("<html>epargne, Id :" + hash22.get("compteEpargneId") + ",<br>Montant:" + hash22.get("montant") + "</html>");
            }
        }
        it2.remove();
        GridLayout grid = new GridLayout(1, 3);

        String[] comptesModele = new String[comptes.size()];
        comptesModele = comptes.toArray(comptesModele);
        cbDebit = new JComboBox(comptesModele);
        cbCredit = new JComboBox(comptesModele);
        JPanel pane = new JPanel(grid);
        pane.add(cbDebit);
        pane.add(montant);
        pane.add(cbCredit);
        this.add(pane, BorderLayout.NORTH);

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
        valider = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        valider.setText("Valider");
        valider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validerActionPerformed(evt);
            }
        });
        add(valider, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void validerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validerActionPerformed
        String compte1 = cbDebit.getSelectedItem().toString();
        String compte2 = cbCredit.getSelectedItem().toString();
        String[] parts1 = compte1.split(",");
        String[] parts3 = parts1[0].split(">");
        String type1 = parts3[1];
        String[] parts2 = parts1[1].split(":");
        String id1 = parts2[1];

        String[] parts4 = compte2.split(",");
        String[] parts6 = parts4[0].split(">");

        String type2 = parts6[1];
        String[] parts5 = parts4[1].split(":");
        String id2 = parts5[1];
        System.out.println(type1 + id1 + type2 + id2);
        if (id1.equals(id2)) {
            JOptionPane.showMessageDialog(this, "Les deux comptes sélécctionnés sont les mêmes", "Alerte", JOptionPane.ERROR_MESSAGE);
        } else {
            con.echangerArgent(type1, id1, type2, id2, montant.getText());
        }
    }//GEN-LAST:event_validerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton valider;
    // End of variables declaration//GEN-END:variables
}
