package ServletController;

import JavaBeans.ModeloAcceso;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControladorAcceso", urlPatterns = { "/controladoracceso" })
public class ControladorAcceso extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloAcceso modeloAcceso;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloAcceso = new ModeloAcceso(origenDatos);
            
        }catch(Exception e){
            
            throw new ServletException();
        }    
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //Atributos a Utilizar
            String nombre;
            String contra;
            int nivel=0;
            ModeloAcceso acc = new ModeloAcceso();
            RequestDispatcher rd = null;
    
            if(request.getParameter("btnIniciar")!=null){
                nombre=request.getParameter("txtusuario");
                contra=request.getParameter("txtcontra");
                nivel = acc.validar(nombre, contra);
    
                request.setAttribute("nivel", nivel);
                request.setAttribute("nombre", nombre);

                RequestDispatcher miDispatcher = request.getRequestDispatcher("/indexLogin.jsp");
                
                miDispatcher.forward(request, response);
            }

        }catch(Exception e){
                e.printStackTrace();
            }


    }
}
