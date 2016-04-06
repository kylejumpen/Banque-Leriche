import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class HelloGet extends HttpServlet {
  public void doGet(HttpServletRequest requete, HttpServletResponse reponse) throws ServletException, IOException {
    String prenom = requete.getParameter("prenom");
    
    reponse.setContentType("text/html");
    PrintWriter out= reponse.getWriter();
    
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>Bonjour " + prenom + " ! </h1>");
    out.println("</body>");
    out.println("</html>");
  }
}
