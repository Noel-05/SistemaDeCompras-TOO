package ServletController;

import JavaBeans.EmailUtility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "EmailSendingServlet", urlPatterns = { "/EmailSendingServlet" })
public class EmailSendingServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private String host, port, user, pass;
    public void init(){
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String recipient = request.getParameter("recipient");
               String subject = request.getParameter("subject");
               String codarticulo = request.getParameter("codarticulo");
               String nomarticulo = request.getParameter("nomarticulo");
               int cantidad = Integer.parseInt(request.getParameter("cantidad"));
               float precio = Float.parseFloat(request.getParameter("preciototal"));
        
                String content = codarticulo+"\t"+nomarticulo+"\t"+String.valueOf(cantidad)+"\t"+"$"+String.valueOf(precio);
        
               String resultMessage = "";
        
               try {
                   EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                           content);
                   resultMessage = "Correo enviado de manera correcta";
               } catch (Exception ex) {
                   ex.printStackTrace();
                   resultMessage = "Ocurrió un error: " + ex.getMessage();
               } finally {
                   request.setAttribute("Message", resultMessage);
                   getServletContext().getRequestDispatcher("/Result.jsp").forward(
                           request, response);
               }
    }
}
