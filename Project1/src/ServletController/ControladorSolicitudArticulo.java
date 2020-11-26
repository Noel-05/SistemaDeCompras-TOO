package ServletController;

import JavaBeans.Articulos;
import JavaBeans.ModeloSolicitudArticulo;
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

@WebServlet(name = "ControladorSolicitudArticulo", urlPatterns = { "/controladorsolicitudarticulo" })
public class ControladorSolicitudArticulo extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    private ModeloSolicitudArticulo modeloSolicitudArticulo;
    
    private ConexionBaseDatos origenDatos;
    

    public void init() throws ServletException {
        super.init();
        
        try{
            
            modeloSolicitudArticulo = new ModeloSolicitudArticulo(origenDatos);
            
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
            obtenerArticulos(request, response);
            break;
        
        case "insertarBD":
            insertarSolicitud(request, response);
            break;
        
        default:
            obtenerArticulos(request, response);
        }
            
    }

    private void obtenerArticulos(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Articulos> articulos;
        List<SolicitudArt> solicitudes;
        
        try{
            
            articulos = modeloSolicitudArticulo.getArticulos();
            solicitudes = modeloSolicitudArticulo.getSolicitudesArt();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAARTICULOS", articulos);
            request.setAttribute("LISTASOLICITUDESARTICULO", solicitudes);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/solicitarArticulo.jsp");
            
            miDispatcher.forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void insertarSolicitud(HttpServletRequest request, HttpServletResponse response){
        
        String carnet = request.getParameter("carnet");
        String articulo = request.getParameter("articulo");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo Solicitud Articulo
        SolicitudArt nuevaSolicitud = new SolicitudArt(carnet, articulo, cantidad, fecha);
            
        //Enviar el objeto al Modelo y despues insertar el objeto en la BD
        modeloSolicitudArticulo.agregarNuevaSolicitud(nuevaSolicitud);
    
        //Listar las Solicitudes
        obtenerArticulos(request, response);
        
    }
    
}