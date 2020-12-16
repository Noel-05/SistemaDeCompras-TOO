package ServletController;

import JavaBeans.CostoPromedio;
import JavaBeans.ModeloStockPrecios;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControladorStockPrecios", urlPatterns = { "/controladorstockprecios" })
public class ControladorStockPrecios extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloStockPrecios modeloStockPrecios;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloStockPrecios = new ModeloStockPrecios(origenDatos);
            
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
        
        List<CostoPromedio> costoPromedio;
        // List<Requisicion> requisicion;
        
        try{
            
            // requisicion = modeloRequisicion.getRequisicion();
            costoPromedio = modeloStockPrecios.getCostoPromedio();
            
            //Agregar la lista de Articulos al Request
            
            //request.setAttribute("LISTAREQUISICION", requisicion);
            request.setAttribute("LISTACOSTOPROMEDIO", costoPromedio);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteStockPrecios.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }     
    }
}
