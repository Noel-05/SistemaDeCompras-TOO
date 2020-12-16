package ServletController;

import JavaBeans.Articulos;
import JavaBeans.ModeloReporteInventario;

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

@WebServlet(name = "ControladorReporteInventario", urlPatterns = { "/controladorreporteinventario" })
public class ControladorReporteInventario extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";


    private ModeloReporteInventario modeloReporteInventario;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloReporteInventario = new ModeloReporteInventario(origenDatos);
            
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
            filtrarMovimiento(request, response);
            break;

        }
    }

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Articulos> articulos;
        List<Movimiento> movimientos;
        // List<SolicitudArt> solicitudesArt;
        
        try{
            
            movimientos = modeloReporteInventario.getAllMovimientos();
            articulos = modeloReporteInventario.getArticulos();
            //solicitudesArt = modeloRequisicion.getSolicitudesArt();
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAMOVIMIENTOS", movimientos);
            request.setAttribute("LISTAARTICULOS", articulos);
            //request.setAttribute("LISTASOLICITUDESART", solicitudesArt);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteInventario.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();   
        }
    }


    private void filtrarMovimiento(HttpServletRequest request, HttpServletResponse response) {

        //Valor del id del jsp
        String art = request.getParameter("articulo");
        
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
        Movimiento movimiento = new Movimiento(fecha, art);
        //Departamento departamento = new Departamento(depto);
        Articulos articulo = new Articulos();
        // Empleado emp = new Empleado(empleado);
        // Requisicion req = new Requisicion();
        
        List<Movimiento> movimientos;
        //List<Departamento> departamentos;
        List<Articulos> articulos;
        // List<Empleado> empleados;
        // List<Requisicion> requisiciones;
        
        try{
            
            movimientos = modeloReporteInventario.getMovimientos(movimiento);
            //departamentos = modeloReporteDepto.getDepartamentosPar(departamento);
            //departamentos1 = modeloReporteDepto.getDepartamentos();
            articulos = modeloReporteInventario.getArticulos();
            // empleados = modeloRequisicion.getEmpleados(emp);
            // requisiciones = modeloRequisicion.getNumeroRequisicion();

            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAMOVIMIENTOS", movimientos);
            //request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            //request.setAttribute("LISTADEPARTAMENTOS1", departamentos1);
            request.setAttribute("LISTAARTICULOS", articulos);
            // request.setAttribute("LISTAEMPLEADOS", empleados);
            // request.setAttribute("LISTAREQUISICIONES", requisiciones);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteInventario.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }    

    }
}
