package ServletController;

import JavaBeans.Departamento;
import JavaBeans.ModeloCotizarArticulo;

import JavaBeans.ModeloCotizarProveedorArticulo;

import JavaBeans.Requisicion;

import JavaBeans.Vigencia;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControladorProveedorArticulo", urlPatterns = { "/controladorproveedorarticulo" })
public class ControladorProveedorArticulo extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    private ModeloCotizarProveedorArticulo modeloCotizarProveedorArticulo;
    
    private ConexionBaseDatos origenDatos;

    
    public void init() throws ServletException {
        super.init();
        
        try{
            
            modeloCotizarProveedorArticulo = new ModeloCotizarProveedorArticulo(origenDatos);
            
        }catch(Exception e){
            
            throw new ServletException();
        }
    }
    

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Leer el parametro enviado
        String inst = request.getParameter("instruccion");
        
        if(inst==null) inst="cotizar";
        
        switch(inst){
        
        case "cotizar":
            obtenerProveedoresArticulo(request, response);
            break;
        
        }
    }

    private void obtenerProveedoresArticulo(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Vigencias desde el modelo
        String articulo = request.getParameter("articulo");
        
        //Crear un objeto de tipo Vigencia
        Vigencia vigencias = new Vigencia(articulo);
        
        List<Vigencia> vigenciasArticulo;
        
        try{
            //Agregar la lista de Vigencias al Request¿
            vigenciasArticulo = modeloCotizarProveedorArticulo.getVigencias(vigencias);
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAVIGENCIAS", vigenciasArticulo);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/cotizarProveedorArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
