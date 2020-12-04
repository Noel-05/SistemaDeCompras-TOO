package ServletController;

import JavaBeans.Articulos;
import JavaBeans.Empleado;
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
        
        case "consultarEmpeado":
            consultarSolicitudesEmpleado(request, response);
            break;
            
        case "recuperar":
            try {
                cargarSolicitud(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        
        case "actualizarBD":
            try {
                actualizarSolicitud(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        
        case "eliminar":
            try {
                eliminarSolicitud(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
            
        }
            
    }
    

    private void obtenerArticulos(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Articulos> articulos;
        List<SolicitudArt> solicitudes;
        List<Empleado> empleados;
        
        try{
            
            articulos = modeloSolicitudArticulo.getArticulos();
            solicitudes = modeloSolicitudArticulo.getSolicitudesArt();
            empleados = modeloSolicitudArticulo.getEmpleados();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAARTICULOS", articulos);
            request.setAttribute("LISTASOLICITUDESARTICULO", solicitudes);
            request.setAttribute("LISTAEMPLEADOS", empleados);
            
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


    private void consultarSolicitudesEmpleado(HttpServletRequest request, HttpServletResponse response) {
        String carnet = request.getParameter("empleado");
        
        //Crear un objeto de tipo Empleado
        Empleado consultaEmpleado = new Empleado(carnet);
        
        List<Articulos> articulos;
        List<SolicitudArt> solicitudesEmpleados;
        List<Empleado> empleados;
        
        try{
            
            solicitudesEmpleados = modeloSolicitudArticulo.obtenerSolicitudEmpleado(consultaEmpleado);
            articulos = modeloSolicitudArticulo.getArticulos();
            empleados = modeloSolicitudArticulo.getEmpleados();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAARTICULOS", articulos);
            request.setAttribute("LISTASOLICITUDESARTICULO", solicitudesEmpleados);
            request.setAttribute("LISTAEMPLEADOS", empleados);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/solicitarArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    private void cargarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        // Leer el IDSOL que se envia desde el jsp
        int idSol = Integer.parseInt(request.getParameter("idSol"));
        
        // Enviar el IDSOL al modelo
        SolicitudArt solicitud = modeloSolicitudArticulo.obtenerSolicitud(idSol);
        
        // Enviar producto al JSp de Actualizar
        request.setAttribute("SOLICITUDACTUALIZAR", solicitud);
        
        RequestDispatcher miDispatcher = request.getRequestDispatcher("/actualizarSolicitudArticulo.jsp");
        
        miDispatcher.forward(request, response);
    }
    

    private void actualizarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        // Leer los datos que vienen del formulario actualizar
        String carnet = request.getParameter("carnet");
        String articulo = request.getParameter("articulo");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        int idSolicitud = Integer.parseInt(request.getParameter("idSol"));
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Crear un objeto de tipo Solicitud con la informacion recuperada
        SolicitudArt solicitudActualizada = new SolicitudArt(carnet, articulo, cantidad, fecha, idSolicitud);
        
        // Actualizar la BD con la informacion del objeto Solciitud
        modeloSolicitudArticulo.actualizarSolicitud(solicitudActualizada);
        
        //Listar las Solicitudes
        obtenerArticulos(request, response);
    }
    

    private void eliminarSolicitud(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        // Capturar el idSol
        int idSoli = Integer.parseInt(request.getParameter("idSol"));
        
        //Borrar de la BD
        modeloSolicitudArticulo.borrarSolicitud(idSoli);
        
        // Volver a la pantalla de consulta
        obtenerArticulos(request, response);
    }
}
