package Graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author kyle
 */
public class GlobalFrame extends javax.swing.JFrame {

    public static JPanel cards;
    private AjoutPersonnel paneAjoutPersonnel;
    private AccueilCo paneAccueil;
    private AdminMenu paneAdmin;
    private CreerCompteClient paneCreerCompteClient;
    private AjouterBanque paneAjoutBanque;
    public static GererCompte paneGererCompte;
    private ConsulterCompte paneConsulterCompte;
    private CreerCompte paneCreerCompte;
    public static EchangeArgent paneEchangeArgent;
    public static Statistiques paneStats;
    private JPanel menu;
    public static JButton retourAccueil;
    public ArrayList<String> oldPanel;
    public static JLabel idPersonnel;
    public static JLabel idBanquePersonnel;

    /**
     * Creates new form GlobalFrame
     */
    public GlobalFrame() {
        //super();
        oldPanel = new ArrayList<String>();
        cards = new JPanel();
        cards.setLayout(new CardLayout());
        paneAjoutBanque = new AjouterBanque();
        paneAdmin = new AdminMenu();
        paneCreerCompteClient = new CreerCompteClient();
        paneAjoutPersonnel = new AjoutPersonnel();
        paneAccueil = new AccueilCo();
        paneStats = new Statistiques();
        paneGererCompte = new GererCompte();
        paneConsulterCompte = new ConsulterCompte();
        paneCreerCompte = new CreerCompte();
        paneEchangeArgent = new EchangeArgent();
        retourAccueil = new JButton("Retour");
        retourAccueil.setVisible(false);
        idPersonnel = new JLabel("");
        idBanquePersonnel = new JLabel("");
        retourAccueil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) GlobalFrame.cards.getLayout()).show(GlobalFrame.cards, "paneGererCompte");
            }
        });
        menu = new JPanel();
        menu.add(retourAccueil, BorderLayout.EAST);
        menu.add(idPersonnel, BorderLayout.EAST);
        menu.add(idBanquePersonnel, BorderLayout.EAST);
        //Ajouter toutes les cartes au Layout
        cards.add(paneAjoutBanque,"paneAjoutBanque");
        cards.add(paneCreerCompteClient,"paneCreerCompteClient");
        cards.add(paneAccueil, "paneAccueil");
        cards.add(paneAdmin,"paneAdmin");
        cards.add(paneStats,"paneStats");
        cards.add(paneGererCompte, "paneGererCompte");
        cards.add(paneConsulterCompte, "paneConsulterCompte");
        cards.add(paneCreerCompte, "paneCreerCompte");
        cards.add(paneEchangeArgent,"paneEchangeArgent");
        cards.add(paneAjoutPersonnel,"paneAjoutPersonnel");
        this.add(cards, BorderLayout.CENTER);
        this.add(menu, BorderLayout.NORTH);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Banque Le Riche");
        //Montrer la carte Accueil
        ((CardLayout) cards.getLayout()).show(cards, "paneAccueil");
        //initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlobalFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GlobalFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
