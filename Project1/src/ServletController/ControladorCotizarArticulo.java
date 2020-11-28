package ServletController;

import JavaBeans.Articulos;
import JavaBeans.Departamento;
import JavaBeans.Empleado;
import JavaBeans.ModeloCotizarArticulo;
import JavaBeans.ModeloSolicitudArticulo;

import JavaBeans.Requisicion;
import JavaBeans.SolicitudArt;

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

@WebServlet(name = "ControladorCotizarArticulo", urlPatterns = { "/controladorcotizararticulo" })
public class ControladorCotizarArticulo extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    private ModeloCotizarArticulo modeloCotizarArticulo;
    
    private ConexionBaseDatos origenDatos;
    

    public void init() throws ServletException {
        super.init();
        
        try{
            
            modeloCotizarArticulo = new ModeloCotizarArticulo(origenDatos);
            
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
            obtenerDepartamentos(request, response);
            break;
        
        case "filtrar":
            filtrarRequisiciones(request, response);
            break;
            
        }
    }
    

    private void obtenerDepartamentos(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Departamentos desde el modelo
        
        List<Departamento> departamentos;
        
        try{
            
            departamentos = modeloCotizarArticulo.getDepartamentos();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/cotizarArticulo.jsp");
            
            miDispatcher.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    

    private void filtrarRequisiciones(HttpServletRequest request, HttpServletResponse response) {
        String departamento = request.getParameter("departamento");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo Empleado
        Requisicion requisicion = new Requisicion(fecha, departamento);
        
        List<Requisicion> requisiciones;
        List<Departamento> departamentos;
        
        try{
            
            requisiciones = modeloCotizarArticulo.obtenerRequisiciones(requisicion);
            departamentos = modeloCotizarArticulo.getDepartamentos();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAREQUISICIONES", requisiciones);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/cotizarArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
