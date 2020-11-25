package ServletController;

import java.io.IOException;

import java.io.PrintWriter;

import javax.annotation.Resource;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.sql.ResultSet;

@WebServlet(name = "ServletPrueba", urlPatterns = { "/servletprueba" })

public class ServletPrueba extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    //Definir o establecer el DataSource
    @Resource(name="jdbc/Articulos")
    private DataSource miPool;
    
    
    

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>ServletPrueba</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();*/
        
        //Crear el objeto printWriter
        PrintWriter salida = response.getWriter();
        response.setContentType("text/plain");
        
        //Crear conexion con BD
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        try{
            
            miConexion = miPool.getConnection();
            String miSql = "SELECT * FROM ARTICULOS";
            miStatement = miConexion.createStatement();
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                String nombreArticulo = miResultset.getString(0);
                salida.println(nombreArticulo);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
}
