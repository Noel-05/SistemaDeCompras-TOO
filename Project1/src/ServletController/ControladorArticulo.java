package ServletController;

import JavaBeans.Articulos;
import JavaBeans.ModeloArticulo;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControladorArticulo", urlPatterns = { "/controladorarticulo" })
public class ControladorArticulo extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    private ModeloArticulo modeloArticulo;
    
    private ConexionBaseDatos origenDatos;
    

    public void init() throws ServletException {
        super.init();
        
        try{
            
            modeloArticulo = new ModeloArticulo(origenDatos);
            
        }catch(Exception e){
            
            throw new ServletException();
            
        }
        
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Obtener la lista de Articulos desde el modelo
        
        List<Articulos> articulos;
        
        try{
            
            articulos = modeloArticulo.getArticulos();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAARTICULOS", articulos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/solicitarArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            
        }
        
    }
}
