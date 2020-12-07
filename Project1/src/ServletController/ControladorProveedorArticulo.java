package ServletController;

import JavaBeans.Departamento;
import JavaBeans.ModeloCotizarArticulo;

import JavaBeans.ModeloCotizarProveedorArticulo;

import JavaBeans.Requisicion;

import JavaBeans.RequisicionVigenciaCompra;
import JavaBeans.SolicitudArt;
import JavaBeans.Vigencia;

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

@WebServlet(name = "ControladorProveedorArticulo", urlPatterns = { "/controladorproveedorarticulo" })
public class ControladorProveedorArticulo extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    private ModeloCotizarProveedorArticulo modeloCotizarProveedorArticulo;
    private ModeloCotizarArticulo modeloCotizarArticulo;
    
    private ConexionBaseDatos origenDatos;

    
    public void init() throws ServletException {
        super.init();
        
        try{
            
            modeloCotizarProveedorArticulo = new ModeloCotizarProveedorArticulo(origenDatos);
            modeloCotizarArticulo = new ModeloCotizarArticulo(origenDatos);
            
        }catch(Exception e){
            
            throw new ServletException();
        }
    }
    

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Leer el parametro enviado
        String inst = request.getParameter("instruccion");
        
        if(inst==null) inst="cotizar";
        
        switch(inst){
        
        case "cotizar":
            obtenerProveedoresArticulo(request, response);
            break;
        
        case "seleccionar":
            seleccionarProveedorOrden(request, response);
            break;
        case "enviar":
            enviarOrden(request,response);
            break;
        }
    }

    private void obtenerProveedoresArticulo(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Vigencias desde el modelo
        String articulo = request.getParameter("articulo");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String departamento = request.getParameter("departamento");
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        
        try {
            fecha = formatoFecha.parse(request.getParameter("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //Crear un objeto de tipo Vigencia
        Vigencia vigencias = new Vigencia(articulo, fecha, cantidad, departamento);
        
        List<Vigencia> vigenciasArticulo;
        
        try{
            //Agregar la lista de Vigencias al Request¿
            vigenciasArticulo = modeloCotizarProveedorArticulo.getVigencias(vigencias);
            
            //Agregar la lista de Articulos al Request
            
            request.setAttribute("LISTAVIGENCIAS", vigenciasArticulo);
            
            //Enviar el Request al JSP
            
            RequestDispatcher miDispatcher = request.getRequestDispatcher("/cotizarProveedorArticulo.jsp");
            
            miDispatcher.forward(request, response);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    private void obtenerProveedoresArticuloSinParametros(HttpServletRequest request, HttpServletResponse response) {
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
    

    private void seleccionarProveedorOrden(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Vigencias desde el modelo
        String articulo = request.getParameter("articulo");
        String proveedor = request.getParameter("proveedor");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String departamento = request.getParameter("departamento");
        float precioTotal = Float.parseFloat(request.getParameter("precioTotal"));
        
        //Crear un objeto de tipo REQUISICION_VIGENCIA_COMPRA
        RequisicionVigenciaCompra nuevaCompra = new RequisicionVigenciaCompra(departamento, articulo, proveedor, cantidad, precioTotal);
            
        //Enviar el objeto al Modelo y despues insertar el objeto en la BD
        modeloCotizarProveedorArticulo.agregarNuevaCompra(nuevaCompra);
        
        //Listar las Solicitudes
        obtenerProveedoresArticuloSinParametros(request, response);
        
    }
    private void Listar(HttpServletRequest request, HttpServletResponse response) {
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
    

    private void enviarOrden(HttpServletRequest request, HttpServletResponse response) {
        //Obtener la lista de Vigencias desde el modelo
        int cantidadReg = Integer.parseInt(request.getParameter("cantReg"));
        String articulo = request.getParameter("codart1");
        String proveedor = request.getParameter("proveedor");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        String departamento = request.getParameter("departamento");
        float precioTotal = Float.parseFloat(request.getParameter("precioTotal"));
        System.out.print(cantidadReg);
        System.out.print("\n"+articulo);
        //Crear un objeto de tipo REQUISICION_VIGENCIA_COMPRA
        RequisicionVigenciaCompra nuevaCompra = new RequisicionVigenciaCompra(departamento, articulo, proveedor, cantidad, precioTotal);
            
        //Enviar el objeto al Modelo y despues insertar el objeto en la BD
        //modeloCotizarProveedorArticulo.agregarNuevaCompra(nuevaCompra);
        
        //Listar las Solicitudes
        obtenerProveedoresArticuloSinParametros(request, response);
        
    }
}
