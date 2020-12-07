

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

@WebServlet(name = "ControladorAutorizarRequisicion", urlPatterns = { "/controladorautorizarrequisicion" })
public class ControladorAutorizarRequisicion extends HttpServlet {
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
                obtenerRequisiciones(request, response);
                //obtenerRequisicionesAutorizadas(request, response);
                break;
    
            case "filtrar":
                filtrarRequisiciones(request, response);
                break;
    
            case "filtrarDepto":
                filtrarRequisicionesAutorizadas(request, response);
                break;
            
        }
        
    }
    
    //--------------------CODIGO DE AUTORIZACION DE REQUISICIONES------------------------------------------------------------------

            
        private void obtenerRequisiciones(HttpServletRequest request, HttpServletResponse response) {
            //Obtener la lista de requisiciones
            
            List<Requisicion> requisicionA;
            List<Requisicion> requisicionN;
            List<Departamento> departamentos;
            
            try{
                
                requisicionA = modeloRequisicion.getRequisicionesAutorizadas();
                requisicionN = modeloRequisicion.getRequisicionesNoAutorizadas();
                departamentos = modeloRequisicion.getDepartamentos();
                
                //Agregar la lista de Requsiciones sin autorizaciion al Request
                
                request.setAttribute("LISTAREQUISICIONESNOAUTORIZADAS", requisicionN);
                request.setAttribute("LISTADEPARTAMENTOS", departamentos);
                request.setAttribute("LISTAREQUISICIONESAUTORIZADAS", requisicionA);
                
                //Enviar el Request al JSP
                
                RequestDispatcher miDispatcher = request.getRequestDispatcher("/autorizarRequisicion.jsp");
                
                miDispatcher.forward(request, response);
                
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        
    private void filtrarRequisiciones(HttpServletRequest request, HttpServletResponse response) {
        //Valor del id del jsp
        String carnet = request.getParameter("carnet");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        List<Requisicion> requisicion;
        List<Requisicion> requisicionA;
        List<Departamento> departamentos;
        
        try{
            
            requisicion = modeloRequisicion.getRequisicionesNoAutorizadasFiltradas(carnet, fecha);
            requisicionA = modeloRequisicion.getRequisicionesAutorizadas();
            departamentos = modeloRequisicion.getDepartamentos();
            
            //Agregar la lista de Requsiciones sin autorizaciion al Request
            
            request.setAttribute("LISTAREQUISICIONESNOAUTORIZADAS", requisicion);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            request.setAttribute("LISTAREQUISICIONESAUTORIZADAS", requisicionA);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/autorizarRequisicion.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    

    private void filtrarRequisicionesAutorizadas(HttpServletRequest request, HttpServletResponse response) {
        //Valor del id del jsp
        String codigoDepartamento = request.getParameter("departamento");       
              
        
        List<Requisicion> requisicion;
        List<Requisicion> requisicionN;
        List<Departamento> departamentos;
        
        try{
            
            requisicion = modeloRequisicion.getRequisicionesAutorizadasFiltradas(codigoDepartamento);
            requisicionN = modeloRequisicion.getRequisicionesNoAutorizadas();
            departamentos = modeloRequisicion.getDepartamentos();
            
            //Agregar la lista de Requsiciones sin autorizaciion al Request
            
            request.setAttribute("LISTAREQUISICIONESAUTORIZADAS", requisicion);
            request.setAttribute("LISTAREQUISICIONESNOAUTORIZADAS", requisicionN);
            request.setAttribute("LISTADEPARTAMENTOS", departamentos);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/autorizarRequisicion.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
    
    
    
        
        
}
