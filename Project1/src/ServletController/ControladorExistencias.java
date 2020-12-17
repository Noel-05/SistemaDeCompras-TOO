package ServletController;

import JavaBeans.Inventario;
import JavaBeans.ModeloExistencias;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControladorExistencias", urlPatterns = { "/controladorexistencias" })
public class ControladorExistencias extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloExistencias modeloExistencias;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloExistencias = new ModeloExistencias(origenDatos);
            
        }catch(Exception e){
            
            throw new ServletException();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Leer el parametro enviado
        String inst = request.getParameter("instruccion");
        
        if(inst==null) inst="listar";
        
        switch(inst){
        
        case "listar":
            obtener(request, response);
            break;
    }
}

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Inventario> inventario;
        // List<Requisicion> requisicion;
        
        try{
            
            // requisicion = modeloRequisicion.getRequisicion();
            inventario = modeloExistencias.getInventario();
            
            //Agregar la lista de Articulos al Request
            
            //request.setAttribute("LISTAREQUISICION", requisicion);
            request.setAttribute("LISTAINVENTARIO", inventario);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteExistencias.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }    

    }
}
