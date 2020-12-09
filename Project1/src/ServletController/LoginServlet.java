package ServletController;

import JavaBeans.Usuario;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "LoginServlet", urlPatterns = { "/loginservlet" })
public class LoginServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private Connection con;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    /**Process the HTTP doGet request.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        
    }

    /**Process the HTTP doPost request.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        String user = request.getParameter("userName");
        String pass = request.getParameter("passW");
        Usuario usu = new Usuario(user, pass);
        //
        try {
                   if (checkPassword(usu)) {
                       //HttpSession session = request.getSession();
                       // session.setAttribute("username",username);
                       response.sendRedirect("index.jsp");
                   } else {
                       HttpSession session = request.getSession();
                       //session.setAttribute("user", username);
                       //response.sendRedirect("login.jsp");
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
        
        
    }
    public boolean checkPassword(Usuario usuario){
        try{
            con = ConexionBaseDatos.getConexion();
            String sql="SELECT * FROM USUARIO WHERE NOMUSUARIO =? AND CLAVE=?";
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, usuario.getNomUsuario());
            state.setString(2, usuario.getPassword());
            
            ResultSet rs = state.executeQuery();
            if(rs.next()){
                System.out.println(rs.getString("NOMUSUARIO")+"\n"+rs.getString("CLAVE"));
                return true;
            }
            else{
                
                return false;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
