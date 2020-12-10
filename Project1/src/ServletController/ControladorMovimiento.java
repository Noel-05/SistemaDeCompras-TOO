package ServletController;

import JavaBeans.Inventario;
import JavaBeans.ModeloMovimiento;

import JavaBeans.Movimiento;

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

@WebServlet(name = "ControladorMovimiento", urlPatterns = { "/controladormovimiento" })
public class ControladorMovimiento extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloMovimiento modeloMovimiento;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloMovimiento = new ModeloMovimiento(origenDatos);
            
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

        case "insertarBD":
            ingresarBD(request, response);
            break;
        }
    }

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
    
        //Obtener la lista de Articulos desde el modelo
        
        List<Inventario> inventario;
        // List<Requisicion> requisicion;
        // List<SolicitudArt> solicitudesArt;
        
        try{
            
            // requisicion = modeloRequisicion.getRequisicion();
            inventario = modeloMovimiento.getInventario();
            //solicitudesArt = modeloRequisicion.getSolicitudesArt();
            
            //Agregar la lista de Articulos al Request
            
            //request.setAttribute("LISTAREQUISICION", requisicion);
            request.setAttribute("LISTAINVENTARIO", inventario);
            //request.setAttribute("LISTASOLICITUDESART", solicitudesArt);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/movimiento.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }    

    }

    private void ingresarBD(HttpServletRequest request, HttpServletResponse response) {
    
        String carnet = request.getParameter("carnet");
        int articulo = Integer.parseInt(request.getParameter("articulo"));//idInventario(codArticulo)
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String tipo = request.getParameter("movimiento");
        int valorUnidad = Integer.parseInt(request.getParameter("valor"));

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }        
        
        //Crear un objeto de tipo Solicitud Articulo
        Movimiento nuevoMov = new Movimiento(articulo, carnet, tipo, cantidad, valorUnidad, fecha);
            
        //Enviar el objeto al Modelo y despues insertar el objeto en la BD
        modeloMovimiento.agregarNuevoMovimiento(nuevoMov);
    
        //Listar las Solicitudes
        obtener(request, response);
    }
}
