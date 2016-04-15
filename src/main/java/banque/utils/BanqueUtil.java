package banque.utils;

import banque.entity.*;
import org.hibernate.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BanqueUtil {
    static Session session;


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
}
