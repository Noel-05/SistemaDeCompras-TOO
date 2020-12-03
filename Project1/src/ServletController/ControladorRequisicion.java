package ServletController;

import JavaBeans.Departamento;
import JavaBeans.ModeloRequisicion;

import JavaBeans.Requisicion;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;
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

        }


    }

    private void obtenerRequisicion(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        List<Requisicion> requisicion;
        List<Departamento> departamentos;
        // List<Empleado> empleados;
        
        try{
            
            requisicion = modeloRequisicion.getRequisicion();
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

    private void consultarRequisicionesDepto(HttpServletRequest request, HttpServletResponse response) {
        String depto = request.getParameter("departamento");
        
        //Crear un objeto de tipo Requisicion
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


}
