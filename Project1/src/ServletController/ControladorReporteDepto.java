package ServletController;

import JavaBeans.Articulos;
import JavaBeans.Departamento;
import JavaBeans.ModeloReporteDepto;

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

@WebServlet(name = "ControladorReporteDepto", urlPatterns = { "/controladorreportedepto" })
public class ControladorReporteDepto extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloReporteDepto modeloReporteDepto;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloReporteDepto = new ModeloReporteDepto(origenDatos);
            
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

        case "consulta":
            filtrarArticulos(request, response);
            break;

        }
    }

    private void filtrarArticulos(HttpServletRequest request, HttpServletResponse response) {
        //Valor del id del jsp
        String depto = request.getParameter("departamento");
        
        //Obtener la lista desde el modelo
        List<Departamento> departamentos1;


        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo SolicitudArt, Depto
        SolicitudArt solicitudArt = new SolicitudArt(fecha, depto);
        Departamento departamento = new Departamento(depto);
        Articulos articulo = new Articulos();
        // Empleado emp = new Empleado(empleado);
        // Requisicion req = new Requisicion();
        
        List<SolicitudArt> solicitudesArt;
        List<Departamento> departamentos;
        List<Articulos> articulos;
        // List<Empleado> empleados;
        // List<Requisicion> requisiciones;
        
        try{
            
            solicitudesArt = modeloReporteDepto.getSolicitudesArt(solicitudArt);
            departamentos = modeloReporteDepto.getDepartamentosPar(departamento);
            departamentos1 = modeloReporteDepto.getDepartamentos();
            articulos = modeloReporteDepto.getArticulos();
            // empleados = modeloRequisicion.getEmpleados(emp);
            // requisiciones = modeloRequisicion.getNumeroRequisicion();

            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTASOLICITUDESART", solicitudesArt);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            request.setAttribute("LISTADEPARTAMENTOS1", departamentos1);
            request.setAttribute("LISTAARTICULOS", articulos);
            // request.setAttribute("LISTAEMPLEADOS", empleados);
            // request.setAttribute("LISTAREQUISICIONES", requisiciones);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteDepartamento.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Departamento> departamentos;
        // List<Requisicion> requisicion;
        // List<SolicitudArt> solicitudesArt;
        
        try{
            
            // requisicion = modeloRequisicion.getRequisicion();
            departamentos = modeloReporteDepto.getDepartamentos();
            //solicitudesArt = modeloRequisicion.getSolicitudesArt();
            
            //Agregar la lista de Articulos al Request
            
            //request.setAttribute("LISTAREQUISICION", requisicion);
            request.setAttribute("LISTADEPARTAMENTOS1", departamentos);
            //request.setAttribute("LISTASOLICITUDESART", solicitudesArt);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteDepartamento.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }    

    }
}
