package ServletController;

import JavaBeans.ModeloReporteArticulosProv;
import JavaBeans.ModeloReporteOrdenesProv;

import JavaBeans.Ordenes;
import JavaBeans.Proveedor;

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

@WebServlet(name = "ControladorReporteOrdenesProv", urlPatterns = { "/controladorreporteordenesprov" })
public class ControladorReporteOrdenesProv extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    private ModeloReporteOrdenesProv modeloReporteOrdenesProv;
    private ModeloReporteArticulosProv modeloReporteArticulosProv;
    
    private ConexionBaseDatos origenDatos;

    public void init() throws ServletException {
        super.init();

        try{
            
            modeloReporteOrdenesProv = new ModeloReporteOrdenesProv(origenDatos);
            
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
            //filtrarArticulos(request, response);
            filtrarOrdenes(request, response);
            break;

        }
    }

    private void obtener(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Articulos desde el modelo
        
        //List<Proveedor> proveedores;
        // List<Requisicion> requisicion;
        // List<SolicitudArt> solicitudesArt;
        
        try{
            
            //proveedores = modeloReporteArticulosProv.getProveedores();
            
            //Agregar la lista de Articulos al Request
            //request.setAttribute("LISTAPROVEEDORES", proveedores);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteOrdenesProv.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }     

    }

    private void filtrarOrdenes(HttpServletRequest request, HttpServletResponse response) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo SolicitudArt, Depto
        Ordenes orden = new Ordenes(fecha);
        //Departamento departamento = new Departamento(depto);

        
        List<Ordenes> ordenes;
        //List<Departamento> departamentos;

        
        try{
            
            ordenes = modeloReporteOrdenesProv.getOrdenes(orden);
            //departamentos = modeloReporteDepto.getDepartamentosPar(departamento);

            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAORDENES", ordenes);
            //request.setAttribute("LISTADEPARTAMENTOS", departamentos);

            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/reporteOrdenesProv.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
