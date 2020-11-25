package ServletController;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ConexionPrueba", urlPatterns = { "/conexionprueba" })
public class ConexionPrueba extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Crear el objeto printWriter
        PrintWriter salida = response.getWriter();
        response.setContentType("text/plain");
        
        //Crear conexion con BD
        Connection miConexion = null;
        Statement miStatement = null;
        ResultSet miResultset = null;
        
        try{

            miConexion = ConexionBaseDatos.getConexion();
            String miSql = "SELECT * FROM ARTICULO";
            miStatement = miConexion.createStatement();
            miResultset = miStatement.executeQuery(miSql);
            
            while(miResultset.next()){
                String nombreArticulo = miResultset.getString(1);
                salida.println(nombreArticulo);
            }
            
        }catch(Exception e){
            
            e.printStackTrace();
        }
        
    }
}
