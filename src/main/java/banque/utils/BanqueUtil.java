package banque.utils;

import banque.entity.*;
import org.hibernate.Session;

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
}
