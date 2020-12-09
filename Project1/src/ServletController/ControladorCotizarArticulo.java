package ServletController;

import JavaBeans.Articulos;
import JavaBeans.Departamento;
import JavaBeans.Empleado;
import JavaBeans.ModeloCotizarArticulo;
import JavaBeans.ModeloSolicitudArticulo;

import JavaBeans.Requisicion;
import JavaBeans.RequisicionVigenciaCompra;
import JavaBeans.SolicitudArt;

import JavaBeans.Vigencia;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Date;

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
        
        case "detalle":
            obtenerOrdenes(request, response);
            break;
        case "enviar":
            obtenerOrdenesFecha(request, response);
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
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
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
    

    private void obtenerOrdenes(HttpServletRequest request, HttpServletResponse response) {
        String departamento = request.getParameter("departamento");
        
        //Crear un objeto de tipo RequisicionVigenciaCompra
        RequisicionVigenciaCompra consultaOrdenes = new RequisicionVigenciaCompra(departamento);
        
        List<RequisicionVigenciaCompra> ordenes;
        List<Departamento> departamentos;
        
        try{
            
            ordenes = modeloCotizarArticulo.obtenerOrdenes(consultaOrdenes);            
            departamentos = modeloCotizarArticulo.getDepartamentos();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAORDENES", ordenes);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/cotizarArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void obtenerOrdenesFecha(HttpServletRequest request, HttpServletResponse response) {
        String departamento = request.getParameter("departamento");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yy-MM-dd");
        //Date fecha = null;
        
        
        
        //Crear un objeto de tipo RequisicionVigenciaCompra
        RequisicionVigenciaCompra consultaOrdenes = new RequisicionVigenciaCompra(departamento);
        
        List<RequisicionVigenciaCompra> ordenes;
        List<Departamento> departamentos;
        
        try{
            String fecha = request.getParameter("fecha2");
            String[] fecha2 = fecha.split("-");
            String fecha3 = fecha2[2]+"/"+fecha2[1]+"/"+fecha2[0].substring(2);
            //fecha = formatoFecha.parse(request.getParameter("fecha2"));
            System.out.print(fecha3);
            ordenes = modeloCotizarArticulo.obtenerOrdenes2(consultaOrdenes, fecha3);            
            departamentos = modeloCotizarArticulo.getDepartamentos();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAORDENES2", ordenes);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/cotizarArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
