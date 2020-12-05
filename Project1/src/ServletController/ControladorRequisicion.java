package ServletController;

import JavaBeans.Articulos;
import JavaBeans.Departamento;
import JavaBeans.Empleado;
import JavaBeans.ModeloRequisicion;

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

@WebServlet(name = "ControladorRequisicion", urlPatterns = { "/controladorrequisicion" })
public class ControladorRequisicion extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloRequisicion modeloRequisicion;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();
        
        try{
            
            modeloRequisicion = new ModeloRequisicion(origenDatos);
            
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
        //Obtiene la requisicion y la lista de departamentos
            obtenerRequisicion(request, response);
            break;

        case "consultarDepto":
            consultarRequisicionesDepto(request, response);
            break;

        case "filtrarSol":
            filtrarSolicitudesArt(request, response);
            break;

        case "insertarBBDD":
            insertarRequisicion(request, response);
            break;

        }


    }

    private void obtenerRequisicion(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Requisicion> requisicion;
        List<Departamento> departamentos;
        List<SolicitudArt> solicitudesArt;
        
        try{
            
            requisicion = modeloRequisicion.getRequisicion();
            departamentos = modeloRequisicion.getDepartamentos();
            //solicitudesArt = modeloRequisicion.getSolicitudesArt();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAREQUISICION", requisicion);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            //request.setAttribute("LISTASOLICITUDESART", solicitudesArt);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/requerirCompra.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void consultarRequisicionesDepto(HttpServletRequest request, HttpServletResponse response) {
        String depto = request.getParameter("departamento");
        
        //Crear un objeto de tipo Departamento
        Departamento consultaDepartamento = new Departamento(depto);
        
        List<Requisicion> requisicion;
        List<Departamento> departamentos;
        
        try{
            
            requisicion = modeloRequisicion.obtenerRequisicionDepto(consultaDepartamento);
            departamentos = modeloRequisicion.getDepartamentos();
            // empleados = modeloSolicitudArticulo.getEmpleados();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAREQUISICION", requisicion);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            // request.setAttribute("LISTAEMPLEADOS", empleados);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/requerirCompra.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void filtrarSolicitudesArt(HttpServletRequest request, HttpServletResponse response) {
        //Valor del id del jsp
        String empleado = request.getParameter("empleado");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo SolicitudArt, Depto, Articulos, Empleado
        SolicitudArt solicitudArt = new SolicitudArt(empleado, fecha);
        Departamento departamento = new Departamento(empleado, fecha);
        Articulos articulo = new Articulos(empleado);
        Empleado emp = new Empleado(empleado);
        Requisicion req = new Requisicion();
        
        List<SolicitudArt> solicitudesArt;
        List<Departamento> departamentos;
        List<Articulos> articulos;
        List<Empleado> empleados;
        List<Requisicion> requisiciones;
        
        try{
            
            solicitudesArt = modeloRequisicion.getSolicitudesArt(solicitudArt);
            departamentos = modeloRequisicion.getDepartamentosPar(departamento);
            articulos = modeloRequisicion.getArticulos(articulo);
            empleados = modeloRequisicion.getEmpleados(emp);
            requisiciones = modeloRequisicion.getNumeroRequisicion();

            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTASOLICITUDESART", solicitudesArt);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            request.setAttribute("LISTAARTICULOS", articulos);
            request.setAttribute("LISTAEMPLEADOS", empleados);
            request.setAttribute("LISTAREQUISICIONES", requisiciones);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/requerirCompra.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void insertarRequisicion(HttpServletRequest request, HttpServletResponse response) {

        String carnet = request.getParameter("carnet");
        String codArticulo = request.getParameter("articulo");
        int numReq = Integer.parseInt(request.getParameter("numero"));
        int cantArt = Integer.parseInt(request.getParameter("cantidad"));
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecPedido = null;
        Date fecEntrega = null;
        
        try {
            fecPedido = formatoFecha.parse(request.getParameter("fechaPedido"));
            fecEntrega = formatoFecha.parse(request.getParameter("fechaEntrega"));            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo Solicitud Articulo
        Requisicion nuevaRequisicion = new Requisicion(numReq, fecPedido, fecEntrega, cantArt, codArticulo, carnet);
            
        //Enviar el objeto al Modelo y despues insertar el objeto en la BD
        modeloRequisicion.agregarNuevaRequisicion(nuevaRequisicion);
    
        //Listar las Solicitudes
        obtenerRequisicion(request, response);

    }
}
