package banque.utils;

import banque.entity.*;
import org.hibernate.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class BanqueUtil {
    static Session session;
    static SessionFactory sessionFactory;


    public static Object getEntity(Class maClasse, Short id) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            return session.load(maClasse, id);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return "Aucune banque d'id " + id +" trouve";
    }

    public static void writeInFile(String fileName, String text) {
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println(text);
            writer.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static int genererIban(String type) {
        Random rand = new Random();
        int min = 1000;
        int max = 9999;
        int iban;
        sessionFactory = HibernateUtil.getSessionFactory();
        if(type.equals("courant")) {
            CompteCourant cc;
            do {
                iban = rand.nextInt((max - min) + 1) + min;
                Query query = sessionFactory.openSession().createQuery("from CompteCourant where iban=:numero");
                query.setParameter("numero", iban);
                cc = (CompteCourant) query.uniqueResult();
            } while(cc != null);
        } else {
            CompteEpargne ce;
            do {
                iban = rand.nextInt((max - min) + 1) + min;
                Query query = sessionFactory.openSession().createQuery("from CompteEpargne where iban=:numero");
                query.setParameter("numero", iban);
                ce = (CompteEpargne) query.uniqueResult();
            } while(ce != null);
        }
        sessionFactory.close();
        return rand.nextInt((max - min) + 1) + min;
    }
}
