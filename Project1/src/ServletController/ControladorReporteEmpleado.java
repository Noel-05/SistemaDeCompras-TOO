package ServletController;

import JavaBeans.Departamento;
import JavaBeans.Empleado;
import JavaBeans.ModeloReporteEmpleado;

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

@WebServlet(name = "ControladorReporteEmpleado", urlPatterns = { "/controladorreporteempleado" })
public class ControladorReporteEmpleado extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloReporteEmpleado modeloReporteEmpleado;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloReporteEmpleado = new ModeloReporteEmpleado(origenDatos);
            
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

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Departamento> departamentos;

        
        try{
            
            departamentos = modeloReporteEmpleado.getDepartamentos();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTADEPARTAMENTOS1", departamentos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteEmpleado.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }        
    }

    private void filtrarArticulos(HttpServletRequest request, HttpServletResponse response) {
        //Valor del id del jsp
        String emp = request.getParameter("empleado");
        
        //Obtener la lista desde el modelo
        //List<Departamento> departamentos1;


        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo SolicitudArt, Depto
        SolicitudArt solicitudArt = new SolicitudArt(emp, fecha);
        Departamento departamento = new Departamento(emp, fecha);
        Empleado empleado = new Empleado(emp);
        
        List<SolicitudArt> solicitudesArt;
        List<Departamento> departamentos;
        List<Empleado> empleados;
        
        try{
            
            solicitudesArt = modeloReporteEmpleado.getSolicitudesArt(solicitudArt);
            departamentos = modeloReporteEmpleado.getDepartamentosPar(departamento);
            empleados = modeloReporteEmpleado.getEmpleados(empleado);
            
            //Agregar la lista de Articulos al Request
            request.setAttribute("LISTASOLICITUDESART", solicitudesArt);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            request.setAttribute("LISTAEMPLEADOS", empleados);
          
            //Enviar el Request al JSP            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteEmpleado.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }    

    }
}
