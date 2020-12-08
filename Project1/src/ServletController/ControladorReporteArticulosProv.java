package ServletController;

import JavaBeans.ModeloReporteArticulosProv;

import JavaBeans.Proveedor;

import JavaBeans.Vigencia;

import Utils.ConexionBaseDatos;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "ControladorReporteArticulosProv", urlPatterns = { "/controladorreportearticulosprov" })
public class ControladorReporteArticulosProv extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloReporteArticulosProv modeloReporteArticulosProv;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloReporteArticulosProv = new ModeloReporteArticulosProv(origenDatos);
            
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
        
        List<Proveedor> proveedores;
        // List<Requisicion> requisicion;
        // List<SolicitudArt> solicitudesArt;
        
        try{
            
            proveedores = modeloReporteArticulosProv.getProveedores();
            
            //Agregar la lista de Articulos al Request
            request.setAttribute("LISTAPROVEEDORES", proveedores);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteArticulosProv.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }     

    }

    private void filtrarArticulos(HttpServletRequest request, HttpServletResponse response) {
        //Valor del id del jsp
        String proveedor = request.getParameter("proveedor");
        
        //Obtener la lista desde el modelo
        
        //Crear un objeto de tipo SolicitudArt, Depto
        Vigencia vigencia = new Vigencia(proveedor);
        Proveedor codProv = new Proveedor(proveedor);
        //Departamento departamento = new Departamento(depto);
        //Articulos articulo = new Articulos();
        // Empleado emp = new Empleado(empleado);
        // Requisicion req = new Requisicion();
        
        List<Vigencia> vigencias;
        List<Proveedor> proveedores;
        List<Proveedor> proveedores1;
        //List<Articulos> articulos;
        // List<Empleado> empleados;
        // List<Requisicion> requisiciones;
        
        try{
            
            vigencias = modeloReporteArticulosProv.getVigencias(vigencia);
            proveedores = modeloReporteArticulosProv.getProveedoresPar(codProv);
            proveedores1 = modeloReporteArticulosProv.getProveedores();
            //articulos = modeloReporteDepto.getArticulos();
            // empleados = modeloRequisicion.getEmpleados(emp);
            // requisiciones = modeloRequisicion.getNumeroRequisicion();

            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAVIGENCIAS", vigencias);
            request.setAttribute("LISTAPROVEEDORES1", proveedores);
            request.setAttribute("LISTAPROVEEDORES", proveedores1);
            //request.setAttribute("LISTAARTICULOS", articulos);
            // request.setAttribute("LISTAEMPLEADOS", empleados);
            // request.setAttribute("LISTAREQUISICIONES", requisiciones);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteArticulosProv.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
   

   }
}
